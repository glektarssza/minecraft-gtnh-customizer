package com.glektarssza.gtnh_customizer;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

import com.glektarssza.gtnh_customizer.api.functional.UnaryConsumer;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

/**
 * The root mod class.
 */
@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.MOD_VERSION, dependencies = Tags.MOD_DEPENDENCIES, acceptableRemoteVersions = "*", acceptedMinecraftVersions = "[1.7.10]", modLanguage = "java", guiFactory = "com.glektarssza.gtnh_customizer.config.GuiFactory", canBeDeactivated = false, useMetadata = true)
public class GTNHCustomizer {
    /**
     * The logger for this class.
     */
    @Nonnull
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
            MethodHandles.lookup().lookupClass().getSimpleName())));

    /**
     * The proxy class.
     */
    @SidedProxy(modId = Tags.MOD_ID, serverSide = "com.glektarssza.gtnh_customizer.CommonProxy", clientSide = "com.glektarssza.gtnh_customizer.ClientProxy")
    private static CommonProxy proxy;

    /**
     * The maximum number of times to emit a warning before silencing it.
     */
    public static final int WARNING_EMIT_LIMIT = 20;

    /**
     * A map for tracking how many times a particular warning has been emitted.
     */
    @Nonnull
    public static final Map<String, Map<Logger, Integer>> WARNING_LIMIT_TRACKER = new HashMap<>();

    /**
     * The mod singleton instance.
     */
    @Mod.Instance
    private static GTNHCustomizer instance;

    /**
     * The mod metadata singleton instance.
     */
    @Mod.Metadata
    private static ModMetadata metadata;

    /**
     * Get the mod singleton instance.
     *
     * @return The mod singleton instance.
     */
    public static GTNHCustomizer getInstance() {
        return instance;
    }

    /**
     * Get the mod metadata singleton instance.
     *
     * @return The mod metadata singleton instance.
     */
    public static ModMetadata getMetadata() {
        return metadata;
    }

    /**
     * Get the configuration directory for the mod.
     *
     * @return The configuration directory for the mod.
     */
    @Nullable
    public File getConfigDir() {
        return proxy.getConfigDir();
    }

    /**
     * Get the mod proxy object.
     *
     * @return The mod proxy object.
     */
    @Nonnull
    public static CommonProxy getProxy() {
        return TypeHelpers.castToNonNull(proxy);
    }

    /**
     * Check if a given warning category should be emitted.
     *
     * @param category The warning category to check.
     * @param logFunction The function to call if logging is permitted.
     *
     * @return {@code true} if the given warning category should be emitted;
     *         {@code false} otherwise.
     */
    public static void emitTrackedWarning(@Nonnull String category,
        @Nonnull UnaryConsumer<Logger> logFunction) {
        emitTrackedWarning(LOGGER, category, logFunction);
    }

    /**
     * Check if a given warning category should be emitted.
     *
     * @param logger The logger to be emitted to.
     * @param category The warning category to check.
     * @param logFunction The function to call if logging is permitted.
     *
     * @return {@code true} if the given warning category should be emitted;
     *         {@code false} otherwise.
     */
    public static void emitTrackedWarning(@Nonnull Logger logger,
        @Nonnull String category, @Nonnull UnaryConsumer<Logger> logFunction) {
        if (shouldEmitWarning(logger, category)) {
            trackEmitWarning(logger, category);
            logFunction.call(logger);
        }
    }

    /**
     * Check if a given warning category should be emitted.
     *
     * @param category The warning category to check.
     *
     * @return {@code true} if the given warning category should be emitted;
     *         {@code false} otherwise.
     */
    public static boolean shouldEmitWarning(@Nonnull String category) {
        return shouldEmitWarning(LOGGER, category);
    }

    /**
     * Check if a given warning category should be emitted.
     *
     * @param logger The logger to be emitted to.
     * @param category The warning category to check.
     *
     * @return {@code true} if the given warning category should be emitted;
     *         {@code false} otherwise.
     */
    public static boolean shouldEmitWarning(@Nonnull Logger logger,
        @Nonnull String category) {
        Map<Logger, Integer> map = WARNING_LIMIT_TRACKER
            .computeIfAbsent(category, (key) -> new HashMap<Logger, Integer>());
        return map.putIfAbsent(logger, WARNING_EMIT_LIMIT) > 0;
    }

    /**
     * Track that a given warning category was emitted.
     *
     * @param category The warning category to check.
     */
    public static void trackEmitWarning(@Nonnull String category) {
        trackEmitWarning(LOGGER, category);
    }

    /**
     * Track that a given warning category was emitted.
     *
     * @param logger The logger to emitted to.
     * @param category The warning category to check.
     */
    private static void trackEmitWarning(@Nonnull Logger logger,
        @Nonnull String category) {
        Map<Logger, Integer> map = WARNING_LIMIT_TRACKER
            .computeIfAbsent(category, (key) -> new HashMap<Logger, Integer>());
        int currentValue = map.putIfAbsent(logger, WARNING_EMIT_LIMIT);
        if (currentValue >= 0) {
            map.put(logger, currentValue - 1);
        }
        if (currentValue == 0) {
            logger.warn(String.format(
                "Too many identical warnings logged for category \"%s\"! Silencing further warnings on this issue!",
                category));
        }
    }

    /**
     * Handle the Forge Mod Loader pre-initialization event.
     *
     * @param event The event to handle.
     */
    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    /**
     * Handle the Forge Mod Loader initialization event.
     *
     * @param event The event to handle.
     */
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.init(event);
    }

    /**
     * Handle the Forge Mod Loader pre-initialization event.
     *
     * @param event The event to handle.
     */
    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    /**
     * Handle the Forge Mod Loader server starting event.
     *
     * @param event The event to handle.
     */
    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
