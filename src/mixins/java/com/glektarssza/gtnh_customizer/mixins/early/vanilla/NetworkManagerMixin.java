package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

import net.minecraft.network.NetworkManager;
import net.minecraft.util.MessageDeserializer;
import net.minecraft.util.MessageDeserializer2;
import net.minecraft.util.MessageSerializer;
import net.minecraft.util.MessageSerializer2;

import org.spongepowered.asm.mixin.Mixin;

import com.glektarssza.gtnh_customizer.Tags;
import com.glektarssza.gtnh_customizer.api.v2.annotation.NotNull;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * A mixin to the {@link NetworkManager} class.
 */
@Mixin(NetworkManager.class)
public abstract class NetworkManagerMixin {
    /**
     * The logger for this class.
     */
    @NotNull
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
            MethodHandles.lookup().lookupClass().getSimpleName())));

    /**
     * A wrapper around the
     * {@link NetworkManager#provideLanClient(InetAddress, int)
     * provideLanClient} method.
     *
     * @param address The address to connect to.
     * @param port The port to connect to.
     * @param original The original operation for calling if required.
     *
     * @return The new {@link NetworkManager} instance.
     */
    // NOTE: expect = 0 because this is a client-only method and will not exist
    // -- on servers!
    @WrapMethod(method = "provideLanClient(Ljava/net/InetAddress;I)Lnet/minecraft/network/NetworkManager;", expect = 0)
    private static NetworkManager wrapProvideLanClientWithProxySupport(
        final InetAddress address, final int port,
        final Operation<NetworkManager> original) {
        NetworkManagerMixin.LOGGER
            .debug("Entering NetworkManager#provideLanClient wrapper...");
        try {
            final NetworkManager networkManager = new NetworkManager(true);
            final URI uri = URI
                .create("socket://" + address.getHostAddress() + ":"
                    + Integer.toString(port));
            NetworkManagerMixin.LOGGER
                .debug("Attempting to fetch default proxy selector...");
            final ProxySelector ps = ProxySelector.getDefault();
            final List<Proxy> proxies = ps.select(uri);
            NetworkManagerMixin.LOGGER
                .debug(
                    "Got {} possible proxies, attempting proxied connection!",
                    proxies.size());
            final Iterator<Proxy> it = proxies.iterator();
            while (it.hasNext()) {
                final Proxy p = it.next();
                if (p == Proxy.NO_PROXY) {
                    NetworkManagerMixin.LOGGER
                        .debug("Attempting non-proxied connection to \"{}\"...",
                            uri);
                } else {
                    NetworkManagerMixin.LOGGER
                        .debug(
                            "Attempting proxied connection via \"{}\" to \"{}\"...",
                            p.address(), uri);
                }
                try {
                    final Bootstrap bootstrap = new Bootstrap();
                    bootstrap.group(NetworkManager.eventLoops)
                        .handler(new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(
                                final NioSocketChannel channel) {
                                try {
                                    channel.config().setOption(
                                        ChannelOption.IP_TOS,
                                        Integer.valueOf(24));
                                } catch (final ChannelException _) {
                                    // -- Does nothing
                                }

                                try {
                                    channel.config().setOption(
                                        ChannelOption.TCP_NODELAY,
                                        Boolean.valueOf(false));
                                } catch (final ChannelException _) {
                                    // -- Does nothing
                                }

                                channel.pipeline()
                                    .addLast("timeout",
                                        new ReadTimeoutHandler(20))
                                    .addLast("splitter",
                                        new MessageDeserializer2())
                                    .addLast("decoder",
                                        new MessageDeserializer(
                                            NetworkManager.field_152462_h))
                                    .addLast("prepender",
                                        new MessageSerializer2())
                                    .addLast("encoder",
                                        new MessageSerializer(
                                            NetworkManager.field_152462_h))
                                    .addLast("packet_handler", networkManager);
                            }
                        }).channel(NioSocketChannel.class).connect(p.address())
                        .syncUninterruptibly();
                    return networkManager;
                } catch (final Throwable tt) {
                    if (tt instanceof final IOException ioe) {
                        NetworkManagerMixin.LOGGER
                            .debug(
                                "Failed to establish a connection via proxy, continuing to next proxy!");
                        ps.connectFailed(uri, p.address(), ioe);
                    }
                }
            }
            NetworkManagerMixin.LOGGER
                .debug(
                    "All proxies failed, falling back to Minecraft's logic!");
            // -- If none of our proxies work, just call the original method
            return original.call(address, port);
        } catch (final Throwable t) {
            NetworkManagerMixin.LOGGER
                .warn(
                    "Fatal unhandled exception in proxy support mixin while attempting server connection, falling back to Minecraft's original code!");
            NetworkManagerMixin.LOGGER
                .warn(t);
            // -- Any unhandled exceptions, just call the original method
            return original.call(address, port);
        }
    }
}
