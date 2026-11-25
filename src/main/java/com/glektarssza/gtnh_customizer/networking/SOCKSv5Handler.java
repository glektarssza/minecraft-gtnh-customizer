package com.glektarssza.gtnh_customizer.networking;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.glektarssza.gtnh_customizer.Tags;
import com.glektarssza.gtnh_customizer.api.v2.annotation.NotNull;
import com.glektarssza.gtnh_customizer.api.v2.annotation.Nullable;
import com.glektarssza.gtnh_customizer.api.v2.exception.InvalidStateException;
import com.glektarssza.gtnh_customizer.api.v2.exception.argument.ArgumentNullException;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.socks.SocksAddressType;
import io.netty.handler.codec.socks.SocksAuthRequest;
import io.netty.handler.codec.socks.SocksAuthResponse;
import io.netty.handler.codec.socks.SocksAuthResponseDecoder;
import io.netty.handler.codec.socks.SocksAuthScheme;
import io.netty.handler.codec.socks.SocksCmdRequest;
import io.netty.handler.codec.socks.SocksCmdResponse;
import io.netty.handler.codec.socks.SocksCmdResponseDecoder;
import io.netty.handler.codec.socks.SocksCmdType;
import io.netty.handler.codec.socks.SocksInitRequest;
import io.netty.handler.codec.socks.SocksInitResponse;
import io.netty.handler.codec.socks.SocksInitResponseDecoder;
import io.netty.handler.codec.socks.SocksMessageEncoder;
import io.netty.util.concurrent.Promise;

/**
 * A SOCKSv5 Netty handler.
 */
public class SOCKSv5Handler extends ChannelDuplexHandler {
    private enum State {
        IDLE,
        HANDSHAKING,
        AUTHENTICATING,
        CONNECTING,
        CONNECTED,
        FAILED
    };

    // #region Private Static Fields

    /**
     * The logger for this class.
     */
    @NotNull
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
            MethodHandles.lookup().lookupClass().getSimpleName())));

    /**
     * A pattern for validating IPv4 addresses.
     *
     * <p>
     * This pattern does not check the values of the individual segments of the
     * IPv4 address.
     * </p>
     */
    private static final Pattern IPV4_REGEX = Pattern
        .compile("^(\\d{1,3}\\.){3}(\\d{1,3})$");

    // #endregion

    // #region Public Static Methods

    /**
     * Determine the type of a SOCKS address based on a few simple rules.
     *
     * @param address The address to get the SOCKS address type from.
     *
     * @return The SOCKS address type of the address.
     */
    @NotNull
    public static SocksAddressType determineAddressType(final String address) {
        if (SOCKSv5Handler.IPV4_REGEX.matcher(address).matches()) {
            final String[] comps = address.split("\\.");
            if (comps.length == 4
                && Arrays.stream(comps).map(Long::parseLong)
                    .allMatch(l -> l >= 0 && l <= 255)) {
                return SocksAddressType.IPv4;
            }
        } else if (address.contains(":")) {
            return SocksAddressType.IPv6;
        } else if (address.contains(".")) {
            return SocksAddressType.DOMAIN;
        }
        return SocksAddressType.UNKNOWN;
    }

    // #endregion

    // #region Public Fields

    /**
     * The address of the proxy server to connect to.
     */
    @NotNull
    public final String proxyAddress;

    /**
     * The port of the proxy server to connect to.
     */
    @NotNull
    public final Short proxyPort;

    /**
     * The address of the server to connect to on the other side of the proxy.
     */
    @NotNull
    public final String targetAddress;

    /**
     * The address of the server to connect to on the other side of the proxy.
     */
    @NotNull
    public final Short targetPort;

    /**
     * The address type of the target address.
     *
     * @see SOCKSv5Handler#targetAddress
     */
    @NotNull
    public final SocksAddressType targetAddressType;

    // #endregion

    // #region Private Fields

    /**
     * The username to use to authenticate with the proxy.
     */
    @NotNull
    private final Optional<@NotNull String> proxyUsername;

    /**
     * The password to use to authenticate with the proxy.
     */
    @NotNull
    private final Optional<@NotNull String> proxyPassword;

    /**
     * The state of the handler.
     */
    private State state;

    /**
     * The promise to trigger once the operation completes.
     */
    @NotNull
    private final Promise<Object> promise;

    // #endregion

    // #region Constructors

    /**
     * Create a new instance that does not authenticate.
     *
     * @param aProxyAddress The address of the proxy server to connect to.
     * @param aProxyPort The port of the proxy server to connect to.
     * @param aTargetAddress The address of the server to connect to on the
     *        other side of the proxy.
     * @param aTargetPort The port of the server to connect to on the other side
     *        of the proxy.
     */
    public SOCKSv5Handler(
        final @NotNull String aProxyAddress,
        final @NotNull Short aProxyPort,
        final @NotNull String aTargetAddress,
        final @NotNull Short aTargetPort,
        final @NotNull Promise<Object> aPromise) {
        this(aProxyAddress, aProxyPort,
            TypeHelpers.castToNonNull(Optional.empty()),
            TypeHelpers.castToNonNull(Optional.empty()),
            aTargetAddress, aTargetPort, aPromise);
    }

    /**
     * Create a new instance.
     *
     * @param aProxyAddress The address of the proxy server to connect to.
     * @param aProxyPort The port of the proxy server to connect to.
     * @param aTargetAddress The address of the server to connect to on the
     *        other side of the proxy.
     * @param aTargetPort The port of the server to connect to on the other side
     *        of the proxy.
     */
    public SOCKSv5Handler(
        final @NotNull String aProxyAddress,
        final @NotNull Short aProxyPort,
        final @NotNull String aProxyUsername,
        final @NotNull String aProxyPassword,
        final @NotNull String aTargetAddress,
        final @NotNull Short aTargetPort,
        final @NotNull Promise<Object> aPromise) {
        this(aProxyAddress, aProxyPort,
            TypeHelpers.castToNonNull(Optional.of(aProxyUsername)),
            TypeHelpers.castToNonNull(Optional.of(aProxyPassword)),
            aTargetAddress, aTargetPort, aPromise);
    }

    /**
     * Create a new instance.
     *
     * @param aProxyAddress The address of the proxy server to connect to.
     * @param aProxyPort The port of the proxy server to connect to.
     * @param aTargetAddress The address of the server to connect to on the
     *        other side of the proxy.
     * @param aTargetPort The port of the server to connect to on the other side
     *        of the proxy.
     */
    private SOCKSv5Handler(
        final @NotNull String aProxyAddress,
        final @NotNull Short aProxyPort,
        final @NotNull Optional<@NotNull String> aProxyUsername,
        final @NotNull Optional<@NotNull String> aProxyPassword,
        final @NotNull String aTargetAddress,
        final @NotNull Short aTargetPort,
        final @NotNull Promise<Object> aPromise) {
        this.proxyAddress = aProxyAddress;
        this.proxyPort = aProxyPort;
        this.proxyUsername = aProxyUsername;
        this.proxyPassword = aProxyPassword;
        this.targetAddress = aTargetAddress;
        this.targetPort = aTargetPort;
        this.promise = aPromise;
        this.state = State.IDLE;
        this.targetAddressType = SOCKSv5Handler
            .determineAddressType(this.targetAddress);
    }

    // #endregion

    // #region Public Methods

    /**
     * Get an unmodifiable set of the supported authentication schemes for this
     * instance.
     *
     * @return An unmodifiable set of the supported authentication schemes for
     *         this instance.
     */
    public List<SocksAuthScheme> getSupportedAuthSchemes() {
        final List<SocksAuthScheme> authSchemes = new ArrayList<SocksAuthScheme>();
        authSchemes.add(SocksAuthScheme.NO_AUTH);
        if (this.proxyUsername.isPresent() && this.proxyPassword.isPresent()) {
            authSchemes.add(SocksAuthScheme.AUTH_PASSWORD);
        }
        return Collections.unmodifiableList(authSchemes);
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx)
        throws Exception {
        ctx.pipeline().addFirst("socks_encoder", new SocksMessageEncoder())
            .addAfter("socks_encoder", "socks_init_response_decoder",
                new SocksInitResponseDecoder());
        ctx.writeAndFlush(new SocksInitRequest(this.getSupportedAuthSchemes()));
        this.state = State.HANDSHAKING;
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(final ChannelHandlerContext ctx)
        throws Exception {
        if (this.state != State.CONNECTED && this.state != State.FAILED) {
            this.fail(TypeHelpers.castToNonNull(ctx), new IllegalStateException(
                "Channel closed before SOCKSv5 handshake completed"));
        }
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx,
        final Throwable cause) {
        this.fail(TypeHelpers.castToNonNull(ctx), cause);
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg)
        throws Exception {
        if (ctx == null) {
            throw new ArgumentNullException("ctx");
        }
        switch (this.state) {
            case HANDSHAKING:
                if (msg instanceof final SocksInitResponse initResponse) {
                    ctx.pipeline().remove("socks_init_response_decoder");
                    switch (initResponse.authScheme()) {
                        case UNKNOWN:
                            this.fail(ctx, new RuntimeException(
                                "Unknown auth scheme requested by remote SOCKSv5 proxy"));
                            break;
                        case AUTH_GSSAPI:
                            this.fail(ctx, new RuntimeException(
                                "Unsupported auth scheme \"GSSAPI\" requested by remote SOCKSv5 proxy"));
                            break;
                        case AUTH_PASSWORD:
                            if (!this.proxyUsername.isPresent()
                                || !this.proxyPassword
                                    .isPresent()) {
                                this.fail(ctx, new InvalidStateException(
                                    "SOCKSv5 proxy requested authentication but none was available"));
                                break;
                            }
                            ctx.pipeline().addAfter("socks_encoder",
                                "socks_auth_response_decoder",
                                new SocksAuthResponseDecoder());
                            ctx.writeAndFlush(new SocksAuthRequest(
                                this.proxyUsername.get(),
                                this.proxyPassword.get()));
                            this.state = State.AUTHENTICATING;
                            break;
                        case NO_AUTH:
                            ctx.pipeline().addAfter("socks_encoder",
                                "socks_cmd_response_decoder",
                                new SocksCmdResponseDecoder());
                            ctx.writeAndFlush(new SocksCmdRequest(
                                SocksCmdType.CONNECT, this.targetAddressType,
                                this.targetAddress, this.targetPort));
                            this.state = State.CONNECTING;
                            break;
                    }
                } else {
                    this.fail(ctx, new IllegalStateException(
                        "Unknown response received while handshaking with SOCKSv5 proxy"));
                }
                break;
            case AUTHENTICATING:
                if (msg instanceof final SocksAuthResponse authResponse) {
                    ctx.pipeline().remove("socks_auth_response_decoder");
                    switch (authResponse.authStatus()) {
                        case SUCCESS:
                            ctx.pipeline().addAfter("socks_encoder",
                                "socks_cmd_response_decoder",
                                new SocksCmdResponseDecoder());
                            ctx.writeAndFlush(new SocksCmdRequest(
                                SocksCmdType.CONNECT, this.targetAddressType,
                                this.targetAddress, this.targetPort));
                            this.state = State.CONNECTING;
                            break;
                        case FAILURE:
                            this.fail(ctx, new RuntimeException(
                                "Failed to authenticate with SOCKSv5 proxy"));
                            break;
                    }
                } else {
                    this.fail(ctx, new IllegalStateException(
                        "Unknown response received while authenticating with SOCKSv5 proxy"));
                }
                break;
            case CONNECTING:
                if (msg instanceof final SocksCmdResponse cmdResponse) {
                    ctx.pipeline().remove("socks_cmd_response_decoder");
                    switch (cmdResponse.cmdStatus()) {
                        case SUCCESS:
                            this.succeed(ctx);
                            break;
                        default:
                            this.fail(ctx, new RuntimeException(
                                "Failed to connect to SOCKSv5 proxy with reason: "
                                    + cmdResponse.cmdStatus().name()));
                            break;
                    }
                } else {
                    this.fail(ctx, new IllegalStateException(
                        "Unknown response received while connecting with SOCKSv5 proxy"));
                }
                break;
            default:
                SOCKSv5Handler.LOGGER
                    .warn("Got unexpected message in SOCKSv5 proxy handler!");
                break;
        }
    }

    // #endregion

    // #region Private Methods

    private void succeed(final @NotNull ChannelHandlerContext ctx) {
        if (this.promise.isDone()) {
            return;
        }
        if (this.state == State.CONNECTED) {
            return;
        }
        if (this.state == State.CONNECTING) {
            if (ctx.pipeline().get("socks_encoder") != null) {
                ctx.pipeline().remove("socks_encoder");
            }
            if (ctx.pipeline().get(this.getClass()) != null) {
                ctx.pipeline().remove(this.getClass());
            }
            this.state = State.CONNECTED;
            this.promise.trySuccess(null);
        } else {
            this.fail(ctx, new InvalidStateException(
                "Attempted to succeed SOCKSv5 proxy connection while in invalid state: "
                    + this.state.name()));
        }
    }

    private void fail(final @NotNull ChannelHandlerContext ctx,
        final @Nullable Throwable cause) {
        if (this.state == State.CONNECTED || this.state == State.FAILED) {
            return;
        }
        this.state = State.FAILED;
        this.promise.tryFailure(cause);
        ctx.close();
    }

    // #endregion
}
