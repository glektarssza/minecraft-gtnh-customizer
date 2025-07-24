package com.glektarssza.gtnh_customizer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cpw.mods.fml.common.versioning.ComparableVersion;

import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;

/**
 * A simple version checker based on the official Forge implementation.
 *
 * @see https://github.com/MinecraftForge/MinecraftForge/blob/1.12.x/src/main/java/net/minecraftforge/common/ForgeVersion.java
 */
public class VersionChecker extends Thread {
    /**
     * Our local logger.
     */
    private static final Logger LOGGER = LogManager
        .getLogger(String.format("%s/%s", Tags.MOD_ID,
            VersionChecker.class.getTypeName()));

    /**
     * The maximum number of times a HTTP redirect can happen before aborting.
     */
    private static final int MAX_HTTP_REDIRECTS = Integer
        .getInteger("http.maxRedirects", 20);

    public static class Result {
        /**
         * The status of the check.
         */
        @Nonnull
        public final Status status;

        /**
         * The recommended version.
         */
        @Nullable
        public final ComparableVersion recommendedVersion;

        /**
         * The latest version.
         */
        @Nullable
        public final ComparableVersion latestVersion;

        /**
         * Create a new instance.
         *
         * @param status - The status of the check.
         * @param recommendedVersion - The recommended version.
         * @param latestVersion - The latest version.
         */
        public Result(Status status, ComparableVersion recommendedVersion,
            ComparableVersion latestVersion) {
            this.status = status;
            this.recommendedVersion = recommendedVersion;
            this.latestVersion = latestVersion;
        }
    }

    /**
     * The status of the version check.
     */
    public static enum Status {
        /**
         * The check is not started.
         */
        NOT_STARTED,

        /**
         * The check is in progress.
         */
        IN_PROGRESS,

        /**
         * The check has failed.
         */
        FAILED,

        /**
         * The check is completed and the mod is behind the current recommended
         * release.
         */
        BEHIND_RECOMMENDED,

        /**
         * The check is completed and the mod is ahead of the current
         * recommended release.
         */
        AHEAD_OF_RECOMMENDED,

        /**
         * The check is completed and the mod is ahead of the current
         * recommended release but behind the current beta release.
         */
        BEHIND_BETA,

        /**
         * The check is completed and the mod is ahead of the current beta
         * release.
         */
        AHEAD_OF_BETA,

        /**
         * The check is completed and the mod is fully up to date.
         *
         * There are no newer recommended or beta releases available.
         */
        UP_TO_DATE,

        /**
         * The check is completed and the status is unknown as no recommended or
         * latest version could be found.
         */
        UNKNOWN;
    }

    /**
     * The status of the version check.
     */
    @Nonnull
    private static Status status = Status.NOT_STARTED;

    /**
     * The recommended version of the mod.
     */
    @Nullable
    private static ComparableVersion recommendedVersion = null;

    /**
     * The latest available version of the mod.
     */
    @Nullable
    private static ComparableVersion latestVersion = null;

    /**
     * Get the status of the version check.
     *
     * @return The status of the version check.
     */
    @Nonnull
    public static synchronized Status getStatus() {
        return status;
    }

    /**
     * Get the recommended version of the mod.
     *
     * @return The recommended version of the mod.
     */
    @Nullable
    public static synchronized ComparableVersion getRecommendedVersion() {
        return recommendedVersion;
    }

    /**
     * Get the latest version of the mod.
     *
     * @return The latest version of the mod.
     */
    @Nullable
    public static synchronized ComparableVersion getLatestVersion() {
        return latestVersion;
    }

    /**
     * Set the status of the version check.
     *
     * @param value The new status of the version check.
     */
    private static synchronized void setStatus(@Nonnull Status value) {
        status = value;
    }

    /**
     * Set the recommended version of the mod.
     *
     * @param value The new recommended version of the mod.
     */
    public static synchronized void setRecommendedVersion(
        ComparableVersion value) {
        recommendedVersion = value;
    }

    /**
     * Set the latest version of the mod.
     *
     * @param value The new latest version of the mod.
     */
    public static synchronized void setLatestVersion(ComparableVersion value) {
        latestVersion = value;
    }

    /**
     * The list of callbacks to trigger once the version check is complete.
     */
    private static final List<Consumer<Result>> callbacks = new ArrayList<Consumer<Result>>();

    /**
     * Register a callback to be triggered once the version check is completed.
     *
     * @implNote If the version check is already completed the callback will not
     *           be registered and instead will be triggered immediately.
     *
     * @param callback - The callback to register.
     */
    public static synchronized void registerCallback(
        Consumer<Result> callback) {
        Status status = getStatus();
        if (status != Status.NOT_STARTED && status != Status.IN_PROGRESS) {
            // -- Check complete, trigger callback IMMEDIATELY
            callback.accept(new Result(status, getRecommendedVersion(),
                getLatestVersion()));
            return;
        }
        callbacks.add(callback);
    }

    @Override
    public void run() {
        if (ForgeModContainer.disableVersionCheck) {
            LOGGER.info(
                "Version checking is disabled globally in Forge, no further processing to perform");
            setStatus(Status.FAILED);
            return;
        }
        LOGGER.info("Starting version check...");
        setStatus(Status.IN_PROGRESS);
        String updateUrlString = GTNHCustomizer.getMetadata().updateUrl;
        if (updateUrlString == null) {
            LOGGER.info(
                "No update URL available, no further processing to perform");
            setStatus(Status.FAILED);
            return;
        }
        URL url = null;
        try {
            url = new URL(updateUrlString);
        } catch (MalformedURLException ex) {
            LOGGER.error("Malformed update URL: {}", ex);
            setStatus(Status.FAILED);
            return;
        }
        ComparableVersion localRecommendedVersion = null;
        ComparableVersion localLatestVersion = null;
        InputStream urlConnection = null;
        String rawUpdateData = null;
        try {
            urlConnection = openUrlStream(url);
        } catch (IOException ex) {
            LOGGER.error("Failed to open connection to update URL: {}", ex);
            setStatus(Status.FAILED);
            return;
        }
        try {
            rawUpdateData = new String(ByteStreams.toByteArray(urlConnection),
                "UTF-8");
        } catch (Throwable t) {
            LOGGER.error("Failed to read data from update URL: {}", t);
            setStatus(Status.FAILED);
            return;
        } finally {
            try {
                urlConnection.close();
            } catch (Throwable t) {
                LOGGER.warn("Throwable while closing URL connection: {}", t);
            }
        }
        LOGGER.debug("Received update check data");
        LOGGER.debug(rawUpdateData);
        Map<String, Object> json = new Gson().fromJson(rawUpdateData,
            new TypeToken<Map<String, Object>>() {}.getType());
        @SuppressWarnings("unchecked")
        Map<String, String> promos = (Map<String, String>) json.get("promos");
        String recommendedVersionString = (String) promos
            .get(String.format("%s-recommended", MinecraftForge.MC_VERSION));
        String latestVersionString = promos
            .get(String.format("%s-latest", MinecraftForge.MC_VERSION));
        if (recommendedVersionString != null) {
            localRecommendedVersion = new ComparableVersion(
                recommendedVersionString);
        }
        ComparableVersion currentVersion = new ComparableVersion(
            GTNHCustomizer.getMetadata().version);
        setStatus(Status.UNKNOWN);
        if (latestVersionString != null) {
            localLatestVersion = new ComparableVersion(latestVersionString);
        }
        if (localLatestVersion != null) {
            int diff = localLatestVersion.compareTo(currentVersion);
            if (diff == 0) {
                setStatus(Status.UP_TO_DATE);
            } else if (diff < 0) {
                setStatus(Status.AHEAD_OF_BETA);
            } else if (diff > 0) {
                setStatus(Status.BEHIND_BETA);
            }
        }
        if (localRecommendedVersion != null && getStatus() == Status.UNKNOWN) {
            int diff = localRecommendedVersion.compareTo(currentVersion);
            if (diff == 0) {
                setStatus(Status.UP_TO_DATE);
            } else if (diff < 0) {
                setStatus(Status.AHEAD_OF_RECOMMENDED);
            } else if (diff > 0) {
                setStatus(Status.BEHIND_RECOMMENDED);
            }
        }
        setRecommendedVersion(localRecommendedVersion);
        setLatestVersion(localLatestVersion);
        Status finalStatus = getStatus();
        callbacks.forEach((callback) -> callback.accept(new Result(finalStatus,
            getRecommendedVersion(), getLatestVersion())));
        callbacks.clear();
    }

    /**
     * Open an input stream to the given URL, following redirects if present up
     * to a limit.
     *
     * @param url - The URL to connect to.
     *
     * @return The opened input stream.
     *
     * @throws IOException
     *
     */
    private InputStream openUrlStream(URL url) throws IOException {
        URL currentUrl = url;
        int redirectCount = 0;
        for (; redirectCount < MAX_HTTP_REDIRECTS; redirectCount += 1) {
            URLConnection connection = currentUrl.openConnection();
            if (connection instanceof HttpURLConnection) {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setInstanceFollowRedirects(false);
                int responseCode = httpConnection.getResponseCode();
                if (responseCode >= 300 && responseCode < 399) {
                    try {
                        String newLocation = httpConnection
                            .getHeaderField("Location");
                        currentUrl = new URL(currentUrl, newLocation);
                        continue;
                    } finally {
                        httpConnection.disconnect();
                    }
                }
            }
            return connection.getInputStream();
        }
        throw new IOException(String.format(
            "Too many redirects while fetching URL '%s' (%d redirects)", url,
            redirectCount));
    }
}
