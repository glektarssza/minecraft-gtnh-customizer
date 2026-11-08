package com.glektarssza.gtnh_customizer;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

import com.glektarssza.gtnh_customizer.api.v1.annotations.NotNull;
import com.glektarssza.gtnh_customizer.api.v1.annotations.Nullable;
import com.glektarssza.gtnh_customizer.api.v1.functional.UnaryConsumer;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

/**
 * The root mod class.
 */
@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.MOD_VERSION, dependencies = Tags.MOD_DEPENDENCIES, acceptableRemoteVersions = Tags.MOD_ACCEPTABLE_REMOTE_VERSION_RANGE, acceptedMinecraftVersions = Tags.MOD_MC_VERSION_RANGE, modLanguage = Tags.MOD_LANGUAGE, guiFactory = Tags.MOD_GUI_CLASS, canBeDeactivated = false, useMetadata = true)
public class GTNHCustomizer {
    /**
     * The logger for this class.
     */
    @NotNull
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
    @NotNull
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
        return GTNHCustomizer.instance;
    }

    /**
     * Get the mod metadata singleton instance.
     *
     * @return The mod metadata singleton instance.
     */
    public static ModMetadata getMetadata() {
        return GTNHCustomizer.metadata;
    }

    /**
     * Get the configuration directory for the mod.
     *
     * @return The configuration directory for the mod.
     */
    @Nullable
    public File getConfigDir() {
        return GTNHCustomizer.proxy.getConfigDir();
    }

    /**
     * Get the mod proxy object.
     *
     * @return The mod proxy object.
     */
    @NotNull
    public static CommonProxy getProxy() {
        return TypeHelpers.castToNonNull(GTNHCustomizer.proxy);
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
    public static void emitTrackedWarning(@NotNull final String category,
        @NotNull final UnaryConsumer<Logger> logFunction) {
        GTNHCustomizer.emitTrackedWarning(GTNHCustomizer.LOGGER, category,
            logFunction);
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
    public static void emitTrackedWarning(@NotNull final Logger logger,
        @NotNull final String category,
        @NotNull final UnaryConsumer<Logger> logFunction) {
        if (GTNHCustomizer.shouldEmitWarning(logger, category)) {
            GTNHCustomizer.trackEmitWarning(logger, category);
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
    public static boolean shouldEmitWarning(@NotNull final String category) {
        return GTNHCustomizer.shouldEmitWarning(GTNHCustomizer.LOGGER,
            category);
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
    public static boolean shouldEmitWarning(@NotNull final Logger logger,
        @NotNull final String category) {
        final Map<Logger, Integer> map = GTNHCustomizer.WARNING_LIMIT_TRACKER
            .computeIfAbsent(category, _ -> new HashMap<Logger, Integer>());
        map.putIfAbsent(logger, GTNHCustomizer.WARNING_EMIT_LIMIT);
        return map.get(logger) > 0;
    }

    /**
     * Track that a given warning category was emitted.
     *
     * @param category The warning category to check.
     */
    public static void trackEmitWarning(@NotNull final String category) {
        GTNHCustomizer.trackEmitWarning(GTNHCustomizer.LOGGER, category);
    }

    /**
     * Track that a given warning category was emitted.
     *
     * @param logger The logger to emitted to.
     * @param category The warning category to check.
     */
    private static void trackEmitWarning(@NotNull final Logger logger,
        @NotNull final String category) {
        final Map<Logger, Integer> map = GTNHCustomizer.WARNING_LIMIT_TRACKER
            .computeIfAbsent(category, _ -> new HashMap<Logger, Integer>());
        map.putIfAbsent(logger, GTNHCustomizer.WARNING_EMIT_LIMIT);
        final int currentValue = map.get(logger);
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
    public void onPreInit(final FMLPreInitializationEvent event) {
        GTNHCustomizer.proxy.preInit(event);
    }

    /**
     * Handle the Forge Mod Loader initialization event.
     *
     * @param event The event to handle.
     */
    @Mod.EventHandler
    public void onInit(final FMLInitializationEvent event) {
        GTNHCustomizer.proxy.init(event);
    }

    /**
     * Handle the Forge Mod Loader pre-initialization event.
     *
     * @param event The event to handle.
     */
    @Mod.EventHandler
    public void onPostInit(final FMLPostInitializationEvent event) {
        GTNHCustomizer.proxy.postInit(event);
    }

    /**
     * Handle the Forge Mod Loader server starting event.
     *
     * @param event The event to handle.
     */
    @Mod.EventHandler
    public void onServerStarting(final FMLServerStartingEvent event) {
        GTNHCustomizer.proxy.serverStarting(event);
    }
}
