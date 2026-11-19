package com.glektarssza.gtnh_customizer.networking;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import com.glektarssza.gtnh_customizer.api.v2.annotation.NotNull;
import com.glektarssza.gtnh_customizer.api.v2.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;

/**
 * A Netty handler for working with a SOCKSv5 tunnel.
 */
public class Socks5TunnelHandler extends ChannelDuplexHandler {
    /**
     * The state of the tunnel.
     */
    private enum State {
        INIT,
        AUTH,
        CONNECT,
        DONE
    }

    /**
     * The address of the proxy.
     */
    private final InetSocketAddress targetAddress;

    /**
     * The username to use for authentication.
     */
    private final String username;

    /**
     * The password to use for authentication.
     */
    private final String password;

    /**
     * The state of the tunnel instance
     */
    private State state = State.INIT;

    /**
     * A connection promise.
     */
    private ChannelPromise connectPromise;

    /**
     * The byte buffer used for reading data off the connection.
     */
    private ByteBuf buffer;

    public Socks5TunnelHandler(final @NotNull InetSocketAddress aProxyAddress) {
        this(aProxyAddress, null, null);
    }

    public Socks5TunnelHandler(final @NotNull InetSocketAddress aProxyAddress,
        final @Nullable String aUsername, final @Nullable String aPassword) {
        this.targetAddress = aProxyAddress;
        this.username = aUsername;
        this.password = aPassword;
    }

    @Override
    public void handlerAdded(final ChannelHandlerContext ctx) throws Exception {
        this.buffer = ctx.alloc().buffer(64);
    }

    @Override
    public void handlerRemoved(final ChannelHandlerContext ctx)
        throws Exception {
        this.releaseBuffer();
    }

    @Override
    public void channelInactive(final ChannelHandlerContext ctx)
        throws Exception {
        if (this.state != State.DONE) {
            this.failConnect(
                "Connection closed before SOCKS5 handshake completed");
        }
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx,
        final Throwable cause)
        throws Exception {
        if (this.state != State.DONE) {
            this.failConnect(cause.getMessage());
            ctx.close();
        } else {
            ctx.fireExceptionCaught(cause);
        }
    }

    @Override
    public void connect(final ChannelHandlerContext ctx,
        final SocketAddress remoteAddress,
        final SocketAddress localAddress, final ChannelPromise promise)
        throws Exception {
        if (this.connectPromise != null) {
            promise.tryFailure(
                new IllegalStateException("Duplicate connect call"));
            return;
        }
        this.connectPromise = promise;

        // remoteAddress is the SOCKS5 proxy address
        ctx.connect(remoteAddress, localAddress, ctx.newPromise())
            .addListener((ChannelFutureListener) future -> {
                if (!future.isSuccess()) {
                    this.failConnect(
                        "Failed to connect to SOCKS5 proxy: " + future.cause());
                    return;
                }
                this.sendHandshakeGreeting(ctx);
            });
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg)
        throws Exception {
        if (this.state == State.DONE || !(msg instanceof final ByteBuf in)) {
            ctx.fireChannelRead(msg);
            return;
        }

        try {
            if (this.buffer != null) {
                this.buffer.writeBytes(in);
            }
        } finally {
            in.release();
        }

        switch (this.state) {
            case INIT -> this.handleHandshakeGreetingResponse(ctx);
            case AUTH -> this.handleHandshakeAuthResponse(ctx);
            case CONNECT -> this.handleConnectResponse(ctx);
            case DONE -> {}
        }
    }

    private void sendHandshakeGreeting(final ChannelHandlerContext ctx) {
        final boolean auth = this.username != null && this.password != null;
        final ByteBuf msg = ctx.alloc().buffer(auth ? 4 : 3);
        // -- Protocol version (5)
        msg.writeByte(0x05);
        if (auth) {
            // -- Inform the proxy we support 2 auth methods (no auth and
            // -- username/password)
            msg.writeByte(2);
            msg.writeByte(0x00);
            msg.writeByte(0x02);
        } else {
            // -- Inform the proxy we support 1 auth methods (no auth)
            msg.writeByte(1);
            msg.writeByte(0x00);
        }
        this.state = State.INIT;
        ctx.writeAndFlush(msg);
    }

    private void handleHandshakeGreetingResponse(
        final ChannelHandlerContext ctx) {
        if (this.buffer.readableBytes() < 2) {
            return;
        }
        final byte version = this.buffer.readByte();
        final byte method = this.buffer.readByte();
        this.buffer.discardReadBytes();

        if (version != 0x05) {
            this.failConnect(
                "Proxy server sent invalid SOCKS version: " + version);
            ctx.close();
            return;
        }

        if (method == 0x00) {
            this.sendConnectRequest(ctx);
        } else if (method == 0x02 && this.username != null
            && this.password != null) {
            this.sendHandshakeAuth(ctx);
        } else if (method == 0xFF) {
            this.failConnect(
                "Proxy server rejected provided SOCKSv5 auth methods");
            ctx.close();

        } else {
            this.failConnect(
                "Proxy server requested unsupported SOCKSv5 auth method: "
                    + method);
            ctx.close();
        }
    }

    private void sendHandshakeAuth(final ChannelHandlerContext ctx) {
        final byte[] u = this.username.getBytes(CharsetUtil.UTF_8);
        final byte[] p = this.password.getBytes(CharsetUtil.UTF_8);
        if (u.length > 255 || p.length > 255) {
            this.failConnect("SOCKSv5 username/password exceeds 255 bytes");
            ctx.close();
            return;
        }
        final ByteBuf buf = ctx.alloc().buffer(3 + u.length + p.length);
        // -- Magic "version" byte 0x01
        buf.writeByte(0x01);
        // -- "ID" (username) length
        buf.writeByte(u.length);
        // -- "ID" (username)
        buf.writeBytes(u);
        // -- Password length
        buf.writeByte(p.length);
        // -- Password
        buf.writeBytes(p);
        this.state = State.AUTH;
        ctx.writeAndFlush(buf);
    }

    private void handleHandshakeAuthResponse(final ChannelHandlerContext ctx) {
        // -- Not enough data
        if (this.buffer.readableBytes() < 2) {
            return;
        }
        final byte version = this.buffer.readByte();
        if (version != 0x01) {
            this.failConnect(
                "Wrong SOCKSv5 authentication response version: " + version);
            ctx.close();
            return;
        }
        final byte status = this.buffer.readByte();
        // -- Dump the rest of the buffer
        this.buffer.discardReadBytes();

        if (status != 0x00) {
            this.failConnect(
                "SOCKS5 authentication failed with status: " + status);
            // -- Die IMMEDIATELY!
            ctx.close();
            return;
        }
        this.sendConnectRequest(ctx);
    }

    private void sendConnectRequest(final ChannelHandlerContext ctx) {
        final String host = this.targetAddress.getHostString();
        final int port = this.targetAddress.getPort();

        if (this.targetAddress
            .getAddress() instanceof final Inet6Address ipv6) {
            final byte[] ipBytes = ipv6.getAddress();
            final ByteBuf req = ctx.alloc().buffer(22);
            // -- Write the protocol version, command, a reserved byte, the
            // -- address type, then the address
            req.writeByte(0x05);
            req.writeByte(0x01);
            req.writeByte(0x00);
            req.writeByte(0x04);
            req.writeBytes(ipBytes);
            req.writeShort(port);
            this.state = State.CONNECT;
            ctx.writeAndFlush(req);
        } else if (this.targetAddress
            .getAddress() instanceof final Inet4Address ipv4) {
            final byte[] ipBytes = ipv4.getAddress();
            final ByteBuf req = ctx.alloc().buffer(10);
            // -- Write the protocol version, command, a reserved byte, the
            // -- address type, then the address
            req.writeByte(0x05);
            req.writeByte(0x01);
            req.writeByte(0x00);
            req.writeByte(0x01);
            req.writeBytes(ipBytes);
            req.writeShort(port);
            this.state = State.CONNECT;
            ctx.writeAndFlush(req);
        } else {
            final byte[] hostBytes = host.getBytes(CharsetUtil.UTF_8);
            if (hostBytes.length > 255) {
                this.failConnect("Encoded target hostname exceeds 255 bytes");
                ctx.close();
                return;
            }
            // -- Write the protocol version, command, a reserved byte, the
            // -- address type, then the address
            final ByteBuf req = ctx.alloc().buffer(7 + hostBytes.length);
            req.writeByte(0x05);
            req.writeByte(0x01);
            req.writeByte(0x00);
            req.writeByte(0x03);
            req.writeByte(hostBytes.length);
            req.writeBytes(hostBytes);
            req.writeShort(port);
            this.state = State.CONNECT;
            ctx.writeAndFlush(req);
        }
    }

    private void handleConnectResponse(final ChannelHandlerContext ctx) {
        if (this.buffer.readableBytes() < 2) {
            return;
        }
        this.buffer.markReaderIndex();
        final byte version = this.buffer.readByte();

        if (version != 0x05) {
            this.failConnect(
                "Proxy server sent invalid SOCKS version: " + version);
            ctx.close();
            return;
        }

        final byte response = this.buffer.readByte();

        if (response != 0x00) {
            this.failConnect(
                "Proxy server refused connection request with reason: "
                    + response);
            ctx.close();
            return;
        }

        // -- Drop the reserved byte
        this.buffer.readByte();
        final byte addressType = this.buffer.readByte();

        final int addrLen = switch (addressType) {
            case 0x01 -> 4;
            case 0x04 -> 16;
            case 0x03 -> this.buffer.readUnsignedByte() + 1;
            default -> -1;
        };

        if (addrLen < 0) {
            this.failConnect(
                "Invalid SOCKSv5 address type in reply: " + addressType);
            ctx.close();
            return;
        }

        final int totalPacketBytes = addrLen + 6;
        this.buffer.resetReaderIndex();
        if (this.buffer.readableBytes() < totalPacketBytes) {
            this.failConnect(
                "Invalid SOCKSv5 CONNECT response: not enough data in response");
            ctx.close();
            return;
        }

        this.buffer.skipBytes(totalPacketBytes);
        this.buffer.discardReadBytes();

        this.state = State.DONE;
        final ChannelPromise promise = this.connectPromise;
        this.connectPromise = null;
        ctx.pipeline().remove(this);
        promise.trySuccess();
    }

    private void failConnect(final String message) {
        this.releaseBuffer();
        if (this.connectPromise != null && !this.connectPromise.isDone()) {
            this.connectPromise.tryFailure(new IllegalStateException(message));
            this.connectPromise = null;
        }
    }

    private void releaseBuffer() {
        if (this.buffer != null && this.buffer.refCnt() > 0) {
            this.buffer.release();
            this.buffer = null;
        }
    }
}
