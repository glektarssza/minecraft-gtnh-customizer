package com.glektarssza.gtnh_customizer.networking;

import java.util.Collections;
import java.util.regex.Pattern;

import com.github.bsideup.jabel.Desugar;

import com.glektarssza.gtnh_customizer.api.v2.exception.argument.ArgumentNullException;
import com.glektarssza.gtnh_customizer.api.v2.functional.consumer.BinaryConsumer;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.socks.SocksAddressType;
import io.netty.handler.codec.socks.SocksAuthScheme;
import io.netty.handler.codec.socks.SocksCmdRequest;
import io.netty.handler.codec.socks.SocksCmdResponse;
import io.netty.handler.codec.socks.SocksCmdResponseDecoder;
import io.netty.handler.codec.socks.SocksCmdStatus;
import io.netty.handler.codec.socks.SocksCmdType;
import io.netty.handler.codec.socks.SocksInitRequest;
import io.netty.handler.codec.socks.SocksInitResponse;
import io.netty.handler.codec.socks.SocksInitResponseDecoder;
import io.netty.handler.codec.socks.SocksMessageEncoder;
import io.netty.handler.codec.socks.SocksResponse;
import io.netty.util.concurrent.Promise;

/**
 * A Netty network client that connects via a SOCKS proxy.
 */
public class SocksClient {
    private static final Pattern EMPTY_OR_WHITESPACE_PATTERN = Pattern
        .compile("^\\w*$");

    private static final Pattern IPV4_PATTERN = Pattern.compile(
        "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.){3}(25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)$");

    @Desugar
    public record ProxyEndpoint(String host, int port) {}

    @Desugar
    public record TargetEndpoint(String host, int port) {
        public SocksAddressType inferAddressType() {
            if (this.host == null
                || SocksClient.EMPTY_OR_WHITESPACE_PATTERN.matcher(this.host)
                    .matches()) {
                return SocksAddressType.DOMAIN;
            }
            if (SocksClient.IPV4_PATTERN.matcher(this.host).matches()) {
                return SocksAddressType.IPv4;
            }
            if (this.host.indexOf(':') >= 0) {
                return SocksAddressType.IPv6;
            }
            return SocksAddressType.DOMAIN;
        }
    }

    public static ChannelFuture connectThroughProxy(
        final EventLoopGroup group,
        final ProxyEndpoint proxy,
        final TargetEndpoint target,
        final BinaryConsumer<Channel, ChannelPipeline> onTunnelReady) {
        if (group == null) {
            throw new ArgumentNullException("group");
        }
        if (proxy == null) {
            throw new ArgumentNullException("proxy");
        }
        if (target == null) {
            throw new ArgumentNullException("target");
        }
        if (onTunnelReady == null) {
            throw new ArgumentNullException("onTunnelReady");
        }

        final ChannelPromise[] tunnelReadyPromise = new ChannelPromise[1];

        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10_000)
            .option(ChannelOption.TCP_NODELAY, true)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(final SocketChannel ch) {
                    tunnelReadyPromise[0] = ch.newPromise();
                    ch.pipeline()
                        .addLast("socks_encoder", new SocksMessageEncoder())
                        .addLast("socks_init_decoder",
                            new SocksInitResponseDecoder())
                        .addLast("socks_handshake",
                            new SocksClientHandshakeHandler(target,
                                onTunnelReady, tunnelReadyPromise[0]));
                }
            });

        final ChannelFuture tcpConnect = bootstrap.connect(proxy.host(),
            proxy.port());
        if (tunnelReadyPromise[0] == null) {
            tunnelReadyPromise[0] = tcpConnect.channel().newPromise();
        }
        tcpConnect.addListener((final ChannelFuture f) -> {
            if (!f.isSuccess() && !tunnelReadyPromise[0].isDone()) {
                tunnelReadyPromise[0].setFailure(f.cause());
            }
        });

        return tunnelReadyPromise[0];
    }

    private static final class SocksClientHandshakeHandler
        extends SimpleChannelInboundHandler<SocksResponse> {
        private final TargetEndpoint target;
        private final BinaryConsumer<Channel, ChannelPipeline> onTunnelReady;
        private final Promise<Void> tunnelReadyPromise;

        private SocksClientHandshakeHandler(
            final TargetEndpoint target,
            final BinaryConsumer<Channel, ChannelPipeline> onTunnelReady,
            final Promise<Void> tunnelReadyPromise) {
            this.target = target;
            this.onTunnelReady = onTunnelReady;
            this.tunnelReadyPromise = tunnelReadyPromise;
        }

        @Override
        public void channelActive(final ChannelHandlerContext ctx) {
            ctx.writeAndFlush(
                new SocksInitRequest(
                    Collections.singletonList(SocksAuthScheme.NO_AUTH)));
        }

        @Override
        protected void channelRead0(final ChannelHandlerContext ctx,
            final SocksResponse msg) {
            switch (msg.responseType()) {
                case INIT -> {
                    final var initRes = (SocksInitResponse) msg;
                    if (initRes.authScheme() != SocksAuthScheme.NO_AUTH) {
                        this.failHandshake(ctx, new IllegalStateException(
                            "Upstream SOCKSv5 proxy rejected NO_AUTH; selected: "
                                + initRes.authScheme()));
                        return;
                    }
                    final ChannelPipeline p = ctx.pipeline();
                    p.remove(SocksInitResponseDecoder.class);
                    p.addBefore(ctx.name(),
                        SocksCmdResponseDecoder.class.getName(),
                        new SocksCmdResponseDecoder());

                    ctx.writeAndFlush(new SocksCmdRequest(
                        SocksCmdType.CONNECT,
                        this.target.inferAddressType(),
                        this.target.host(),
                        this.target.port()));
                }

                case CMD -> {
                    final var cmdRes = (SocksCmdResponse) msg;
                    if (cmdRes.cmdStatus() != SocksCmdStatus.SUCCESS) {
                        this.failHandshake(ctx, new IllegalStateException(
                            "SOCKSv5 CONNECT failed with status: "
                                + cmdRes.cmdStatus()));
                        return;
                    }
                    final ChannelPipeline p = ctx.pipeline();
                    if (p.get(SocksCmdResponseDecoder.class) != null) {
                        p.remove(SocksCmdResponseDecoder.class);
                    }
                    if (p.get(SocksMessageEncoder.class) != null) {
                        p.remove(SocksMessageEncoder.class);
                    }
                    if (p.get(SocksClientHandshakeHandler.class) != null) {
                        p.remove(this);
                    }

                    this.onTunnelReady.call(ctx.channel(), p);
                    if (!this.tunnelReadyPromise.isDone()) {
                        this.tunnelReadyPromise.setSuccess(null);
                    }
                    ctx.fireChannelActive();
                }

                case AUTH,
                    UNKNOWN -> this.failHandshake(ctx,
                        new IllegalStateException(
                            "Unexpected SOCKSv5 response type: "
                                + msg.responseType()));
            }
        }

        private void failHandshake(final ChannelHandlerContext ctx,
            final Throwable cause) {
            if (!this.tunnelReadyPromise.isDone()) {
                this.tunnelReadyPromise.setFailure(cause);
            }
            if (ctx.channel().isActive()) {
                ctx.channel().writeAndFlush(Unpooled.EMPTY_BUFFER)
                    .addListener(ChannelFutureListener.CLOSE);
            }
        }

        @Override
        public void exceptionCaught(final ChannelHandlerContext ctx,
            final Throwable cause) {
            this.failHandshake(ctx, cause);
        }
    }
}
