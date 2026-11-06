package com.glektarssza.gtnh_customizer.utils;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;

import com.glektarssza.gtnh_customizer.Tags;
import com.glektarssza.gtnh_customizer.api.annotations.NotNull;

public class CustomProxySelector extends ProxySelector {
    /**
     * The logger for this class.
     */
    @NotNull
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(
            LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
                MethodHandles.lookup().lookupClass().getSimpleName())));

    /**
     * Create our internal {@link Proxy} instance.
     *
     * @return Our internal {@link Proxy} instance.
     */
    private static Proxy constructProxyInstance() {
        final Proxy mcProxy = Minecraft.getMinecraft().getProxy();
        Proxy resultProxy = null;
        // NOTE: Pull proxy from other sources ONLY if it's not set yet! The
        // -- CLI should take absolute priority!
        CustomProxySelector.LOGGER.debug(
            "Checking system environment variables for proxy settings...");
        // NOTE: Prefer environment variables last
        final Map<String, String> env = System.getenv();
        // -- Start by searching for "SOCKS_PROXY"
        resultProxy = env.keySet().parallelStream()
            .filter(key -> key.equalsIgnoreCase("socks_proxy")).findFirst()
            .flatMap(proxyHost -> {
                CustomProxySelector.LOGGER.info(
                    "Proxy settings detected in system environment variables, configuring Minecraft proxy!");
                final String proxyPortRaw = Arrays
                    .stream(proxyHost.split(":"))
                    .limit(2).skip(1).findFirst().orElse("1080");
                try {
                    final Integer proxyPort = (int) Short
                        .parseShort(proxyPortRaw);
                    return Optional.<Proxy>of(new Proxy(Type.SOCKS,
                        InetSocketAddress
                            .createUnresolved(proxyHost, proxyPort)));
                } catch (final Throwable _) {
                    CustomProxySelector.LOGGER.warn(
                        "Failed to parse proxy port, ignoring SOCKS_PROXY environment variable!");
                }
                return Optional.<Proxy>empty();
            })
            // -- Next look for our custom variables if there is still no
            // -- proxy configured
            .orElseGet(() -> env.keySet().parallelStream()
                .filter(key -> key.equalsIgnoreCase("proxy_host"))
                .findFirst()
                .flatMap(proxyHost -> {
                    CustomProxySelector.LOGGER.info(
                        "Proxy settings detected in system environment variables, configuring Minecraft proxy!");
                    final String proxyPortRaw = Arrays
                        .stream(proxyHost.split(":"))
                        .limit(2).skip(1).findFirst()
                        .orElseGet(() -> env.keySet().parallelStream()
                            .filter(
                                key -> key
                                    .equalsIgnoreCase("proxy_port"))
                            .findFirst().orElse("1080"));
                    try {
                        final Integer proxyPort = (int) Short
                            .parseShort(proxyPortRaw);
                        return Optional.<Proxy>of(new Proxy(Type.SOCKS,
                            InetSocketAddress.createUnresolved(
                                proxyHost,
                                proxyPort)));
                    } catch (final Throwable _) {
                        CustomProxySelector.LOGGER.warn(
                            "Failed to parse proxy port, leaving no proxy configured!");
                    }
                    return Optional.<Proxy>empty();
                })// NOTE: Prefer JVM properties next
                .orElseGet(() -> {
                    CustomProxySelector.LOGGER
                        .debug(
                            "Checking JVM properties for proxy settings...");
                    final Properties props = System.getProperties();
                    if (props.containsKey("socksProxyHost")) {
                        if (mcProxy == null
                            || mcProxy == Proxy.NO_PROXY) {
                            CustomProxySelector.LOGGER.info(
                                "Proxy settings detected in JVM properties, configuring Minecraft proxy!");
                        } else {
                            CustomProxySelector.LOGGER.info(
                                "Proxy settings detected in JVM properties, overriding system environment variable settings for Minecraft proxy!");
                        }
                        final String proxyHost = (String) props
                            .get("socksProxyHost");
                        final String proxyPortRaw = (String) props
                            .getOrDefault("socksProxyPort", "1080");
                        try {
                            final Integer proxyPort = (int) Short
                                .parseShort(proxyPortRaw);
                            return new Proxy(Type.SOCKS,
                                InetSocketAddress.createUnresolved(
                                    proxyHost,
                                    proxyPort));
                        } catch (final Throwable _) {
                            CustomProxySelector.LOGGER.warn(
                                "Failed to parse proxy port, leaving no proxy configured!");

                        }
                    }
                    return Proxy.NO_PROXY;
                }));

        if (resultProxy == null) {
            resultProxy = Proxy.NO_PROXY;
        }
        final Proxy ourProxy = resultProxy;
        if ((mcProxy == null
            || mcProxy == Proxy.NO_PROXY)
            && (ourProxy == null
                || ourProxy == Proxy.NO_PROXY)) {
            CustomProxySelector.LOGGER.info(
                "Minecraft has been configured to use no proxy!");
        } else if (mcProxy != null && mcProxy != Proxy.NO_PROXY) {
            String proxyTypeStr = "unknown type";
            String proxyHostStr = "unknown host";
            String proxyPortStr = "unknown port";
            switch (mcProxy.type()) {
                case DIRECT:
                    CustomProxySelector.LOGGER.info(
                        "Minecraft has been configured to use a direct proxy via CLI!");
                    return ourProxy;
                case HTTP:
                    proxyTypeStr = "HTTP";
                    break;
                case SOCKS:
                    proxyTypeStr = "SOCKS";
                    break;
            }
            proxyHostStr = ((InetSocketAddress) mcProxy.address())
                .getHostString();
            proxyPortStr = Integer
                .toString(((InetSocketAddress) mcProxy.address()).getPort(),
                    10);
            CustomProxySelector.LOGGER.info(
                "Minecraft has been configured to use the {} proxy at {} on port {} via CLI!",
                proxyTypeStr, proxyHostStr, proxyPortStr);
        } else if (ourProxy != null) {
            String proxyTypeStr = "unknown type";
            String proxyHostStr = "unknown host";
            String proxyPortStr = "unknown port";
            switch (ourProxy.type()) {
                case DIRECT:
                    CustomProxySelector.LOGGER.info(
                        "Minecraft has been configured to use a direct proxy via JVM properties or environment variables!");
                    return ourProxy;
                case HTTP:
                    proxyTypeStr = "HTTP";
                    break;
                case SOCKS:
                    proxyTypeStr = "SOCKS";
                    break;
            }
            proxyHostStr = ((InetSocketAddress) ourProxy.address())
                .getHostString();
            proxyPortStr = Integer
                .toString(
                    ((InetSocketAddress) ourProxy.address()).getPort(),
                    10);
            CustomProxySelector.LOGGER.info(
                "Minecraft has been configured to use the {} proxy at {} on port {} via JVM properties or environment variables!",
                proxyTypeStr, proxyHostStr, proxyPortStr);
        }
        return resultProxy;
    }

    /**
     * The Minecraft built-in proxy.
     */
    private final Proxy minecraftProxy;

    /**
     * The valid proxies and their failure counts.
     */
    private final Map<Proxy, Integer> proxies;

    /**
     * Create a new instance.
     */
    public CustomProxySelector() {
        this.proxies = new HashMap<Proxy, Integer>();
        this.minecraftProxy = Minecraft.getMinecraft().getProxy();
        this.proxies.put(CustomProxySelector.constructProxyInstance(), 0);
    }

    @Override
    public void connectFailed(final URI uri, final SocketAddress sa,
        final IOException ioe) {
        this.proxies.entrySet().parallelStream()
            .filter(proxy -> proxy.getKey().address().equals(sa))
            .findFirst()
            .ifPresent(proxy -> {
                this.proxies.put(proxy.getKey(), proxy.getValue() + 1);
                if (proxy.getValue() >= 3) {
                    this.proxies.remove(proxy.getKey());
                }
            });
    }

    /**
     * {@inheritDoc java.net.ProxySelector#select(java.net.URI)}
     */
    @Override
    public List<Proxy> select(final URI _uri) {
        final List<Proxy> validProxies = new ArrayList<Proxy>(
            this.proxies.size() + 1);
        validProxies.addAll(this.proxies.keySet());
        validProxies.add(this.minecraftProxy);
        return validProxies;
    }
}
