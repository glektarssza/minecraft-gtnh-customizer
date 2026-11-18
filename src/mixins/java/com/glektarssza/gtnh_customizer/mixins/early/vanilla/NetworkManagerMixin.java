package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.minecraft.network.NetworkManager;
import net.minecraft.util.MessageDeserializer;
import net.minecraft.util.MessageDeserializer2;
import net.minecraft.util.MessageSerializer;
import net.minecraft.util.MessageSerializer2;

import org.spongepowered.asm.mixin.Mixin;

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
    @WrapOperation(method = "provideLanClient(Ljava/net/InetAddress;I)Lnet/minecraft/network/NetworkManager;")
    private static NetworkManager wrapProvideLanClientWithProxySupport(
        final InetAddress address, final int port,
        final Operation<NetworkManager> original) {
        try {
            final NetworkManager networkManager = new NetworkManager(true);
            final URI uri = URI.create("socket://" + address.toString() + ":"
                + Integer.toString(port));
            final ProxySelector ps = ProxySelector.getDefault();
            final List<Proxy> proxies = ps.select(uri);
            final Iterator<Proxy> it = proxies.iterator();
            while (it.hasNext()) {
                final Proxy p = it.next();
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
                        ps.connectFailed(uri, p.address(), ioe);
                    }
                }
            }
            // -- If none of our proxies work, just call the original method
            return original.call(address, port);
        } catch (final Throwable t) {
            // -- Any unhandled exceptions, just call the original method
            return original.call(address, port);
        }
    }
}
