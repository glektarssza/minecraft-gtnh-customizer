package com.glektarssza.gtnh_customizer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraftforge.event.entity.player.BonemealEvent;

import serverutils.events.ServerUtilitiesPreInitRegistryEvent;

/**
 * The root mod class.
 */
@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.MOD_VERSION, dependencies = Tags.MOD_DEPENDENCIES, acceptableRemoteVersions = "*", acceptedMinecraftVersions = "[1.7.10]", modLanguage = "java", guiFactory = "com.glektarssza.gtnh_customizer.config.GuiFactory", canBeDeactivated = false, useMetadata = true)
public class GTNHCustomizer {
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
    public static final Map<String, Integer> WARNING_LIMIT_TRACKER = new HashMap<>();

    /**
     * The mod singleton instance.
     */
    @Mod.Instance
    public static GTNHCustomizer instance;

    /**
     * Get the main logger for the mod.
     *
     * @return The main logger for the mod.
     */
    public static Logger getLogger() {
        return proxy.getLogger();
    }

    /**
     * Get the configuration directory for the mod.
     *
     * @return The configuration directory for the mod.
     */
    @Nonnull
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
        return proxy;
    }

    /**
     * Check if a given warning category should be emitted.
     *
     * @param category The warning category to check.
     *
     * @return {@code true} if the given warning category should be emitted;
     *         {@code false} otherwise.
     */
    public static boolean shouldEmitWarning(String category) {
        WARNING_LIMIT_TRACKER.putIfAbsent(category, WARNING_EMIT_LIMIT);
        return WARNING_LIMIT_TRACKER.get(category) > 0;
    }

    /**
     * Track that a given warning category was emitted.
     *
     * @param category The warning category to check.
     */
    public static void trackEmitWarning(String category) {
        WARNING_LIMIT_TRACKER.putIfAbsent(category, WARNING_EMIT_LIMIT);
        int limit = WARNING_LIMIT_TRACKER.compute(category, (_k, v) -> v - 1);
        if (limit <= 0) {
            getLogger().warn(String.format(
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
     * Handle the Forge Mod Loader pre-initialization event.
     *
     * @param event The event to handle.
     */
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.init(event);
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

    /**
     * An event handler for when the {@code ServerUtilities} mod triggers a
     * server reload.
     *
     * @param event The event data.
     */
    @SubscribeEvent
    public void onServerUtilitiesPreInitRegistry(
        ServerUtilitiesPreInitRegistryEvent event) {
        proxy.serverUtilitiesPreInitRegistry(event);
    }

    /**
     * An event handler for when a player tries to use bone meal.
     *
     * @param event The event data.
     */
    @SubscribeEvent
    public void onBoneMeal(BonemealEvent event) {
        proxy.boneMeal(event);
    }
}
