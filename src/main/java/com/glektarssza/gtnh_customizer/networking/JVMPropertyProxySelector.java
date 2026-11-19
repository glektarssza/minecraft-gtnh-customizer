package com.glektarssza.gtnh_customizer.networking;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.glektarssza.gtnh_customizer.Tags;
import com.glektarssza.gtnh_customizer.api.v2.annotation.NotNull;
import com.glektarssza.gtnh_customizer.api.v2.annotation.Nullable;
import com.glektarssza.gtnh_customizer.api.v2.exception.argument.InvalidArgumentException;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

/**
 * A {@link ProxySelector} implementation that selects a single proxy based on
 * JVM properties.
 */
public class JVMPropertyProxySelector extends ProxySelector {
    // #region Public Static Fields

    /**
     * The logger for this class.
     */
    @NotNull
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
            MethodHandles.lookup().lookupClass().getSimpleName())));

    // #endregion

    // #region Private Fields

    /**
     * The proxy host address.
     */
    @Nullable
    private final String proxyHostAddress;

    /**
     * The proxy host port.
     */
    @Nullable
    private final Integer proxyHostPort;

    // #endregion

    // #region Public Fields

    /**
     * The JVM property to use to access the proxy host address.
     */
    @NotNull
    public final String proxyHostAddressProperty;

    /**
     * The JVM property to use to access the proxy host port.
     */
    @NotNull
    public final String proxyHostPortProperty;

    /**
     * Whether to allow direct connections via this instance;
     */
    public final boolean allowDirectConnection;

    // #endregion

    // #region Constructors

    /**
     * Create a new instance with direct connection allowed.
     *
     * @param aProxyHostAddressProperty The JVM property to use to access the
     *        proxy host address.
     * @param aProxyHostPortProperty The JVM property to use to access the proxy
     *        host port.
     */
    public JVMPropertyProxySelector(
        final @NotNull String aProxyHostAddressProperty,
        final @NotNull String aProxyHostPortProperty) {
        this(aProxyHostAddressProperty, aProxyHostPortProperty, true);
    }

    /**
     * Create a new instance.
     *
     * @param aProxyHostAddressProperty The JVM property to use to access the
     *        proxy host address.
     * @param aProxyHostPortProperty The JVM property to use to access the proxy
     *        host port.
     * @param aAllowDirectConnect Whether to allow direct connection attempts
     *        via this proxy.
     */
    public JVMPropertyProxySelector(
        final @NotNull String aProxyHostAddressProperty,
        final @NotNull String aProxyHostPortProperty,
        final boolean aAllowDirectConnect) {
        JVMPropertyProxySelector.LOGGER
            .debug("Initializing JVM Property Proxy Selector...");
        this.proxyHostAddressProperty = aProxyHostAddressProperty;
        JVMPropertyProxySelector.LOGGER
            .debug("Using \"{}\" as proxy host address property",
                this.proxyHostAddressProperty);
        this.proxyHostPortProperty = aProxyHostPortProperty;
        JVMPropertyProxySelector.LOGGER
            .debug("Using \"{}\" as proxy host port property",
                this.proxyHostPortProperty);
        this.proxyHostAddress = System
            .getProperty(this.proxyHostAddressProperty);
        JVMPropertyProxySelector.LOGGER
            .debug("Using \"{}\" as proxy host address",
                this.proxyHostAddress);
        this.proxyHostPort = Integer.getInteger(this.proxyHostPortProperty);
        JVMPropertyProxySelector.LOGGER
            .debug("Using \"{}\" as proxy host port",
                this.proxyHostPort);
        this.allowDirectConnection = aAllowDirectConnect;
        JVMPropertyProxySelector.LOGGER
            .debug("Direct connection allowed? {}",
                this.allowDirectConnection);
        JVMPropertyProxySelector.LOGGER
            .debug("JVM Property Proxy Selector initialized");
    }

    // #endregion

    // #region Public Methods

    @Override
    public List<Proxy> select(final URI uri) {
        JVMPropertyProxySelector.LOGGER
            .debug("Selecting proxies for URI \"{}\"...", uri);
        final List<Proxy> proxies = new ArrayList<Proxy>();
        if (this.proxyHostAddress != null && !this.proxyHostAddress.isEmpty()) {
            switch (uri.getScheme()) {
                case "http":
                    proxies.add(new Proxy(Type.HTTP, new InetSocketAddress(
                        this.proxyHostAddress,
                        this.proxyHostPort == null ? 80 : this.proxyHostPort)));
                    break;
                case "https":
                    proxies.add(new Proxy(Type.HTTP, new InetSocketAddress(
                        this.proxyHostAddress,
                        this.proxyHostPort == null ? 443
                            : this.proxyHostPort)));
                    break;
                case "ftp":
                    proxies.add(new Proxy(Type.HTTP, new InetSocketAddress(
                        this.proxyHostAddress,
                        this.proxyHostPort == null ? 21
                            : this.proxyHostPort)));
                case "socket":
                    proxies.add(new Proxy(Type.SOCKS, new InetSocketAddress(
                        this.proxyHostAddress,
                        this.proxyHostPort == null ? 1080
                            : this.proxyHostPort)));
                    break;
                default:
                    throw new InvalidArgumentException("uri",
                        "Unsupported URI scheme \"" + uri.getScheme()
                            + "\"");
            }
        }
        if (this.allowDirectConnection) {
            proxies.add(Proxy.NO_PROXY);
        }
        JVMPropertyProxySelector.LOGGER
            .debug("Selected {} proxies for URI \"{}\"", proxies.size(), uri);
        return proxies;
    }

    @Override
    public void connectFailed(final URI uri, final SocketAddress sa,
        final IOException ioe) {
        // -- Does nothing
    }

    // #endregion
}
