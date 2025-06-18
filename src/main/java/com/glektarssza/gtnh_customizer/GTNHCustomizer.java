package com.glektarssza.gtnh_customizer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CommandEvent;

import com.glektarssza.gtnh_customizer.commands.ListDimensionsCommand;
import com.glektarssza.gtnh_customizer.commands.RepairCommand;
import com.glektarssza.gtnh_customizer.commands.TeleportCrossDimensionCommand;
import com.glektarssza.gtnh_customizer.config.Config;

/**
 * The root mod class.
 */
@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.MOD_VERSION, dependencies = Tags.MOD_DEPENDENCIES, acceptableRemoteVersions = "*", guiFactory = "com.glektarssza.gtnh_customizer.config.GuiFactory")
public class GTNHCustomizer {

    /**
     * The configuration directory.
     */
    private static File configDir;

    /**
     * The logger to use for the mod.
     */
    public static Logger LOGGER;

    /**
     * The maximum number of times to emit a warning before silencing it.
     */
    public static final int WARNING_EMIT_LIMIT = 20;

    /**
     * A map for tracking how many times a particular warning has been emitted.
     */
    public static final Map<String, Integer> WARNING_LIMIT_TRACKER = new HashMap<>();

    /**
     * The mod singleton instance.
     */
    @Mod.Instance
    public static GTNHCustomizer instance;

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
            LOGGER.warn(
                String.format(
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
        LOGGER = event.getModLog();
        LOGGER.info("Pre-initializing {}...", Tags.MOD_NAME);
        configDir = event.getModConfigurationDirectory();
        Config.init(configDir, String.format("%s.cfg", Tags.MOD_ID));
        LOGGER.info("Synchronizing configuration for {}...", Tags.MOD_NAME);
        Config.sync();
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("Done pre-initializing {}!", Tags.MOD_NAME);
    }

    /**
     * Handle the Forge Mod Loader pre-initialization event.
     *
     * @param event The event to handle.
     */
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        LOGGER.info("Initializing {}...", Tags.MOD_NAME);
        // -- No initialization code... Yet
        LOGGER.info("Done initializing {}!", Tags.MOD_NAME);
    }

    /**
     * Handle the Forge Mod Loader server starting event.
     *
     * @param event The event to handle.
     */
    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("Handling server about to start...");
        LOGGER.info("Registering custom commands...");
        event.registerServerCommand(new TeleportCrossDimensionCommand());
        event.registerServerCommand(new ListDimensionsCommand());
        event.registerServerCommand(new RepairCommand());
        LOGGER.info("Done registering custom commands!");
        LOGGER.info("Done handling server about to start!");
    }

    /**
     * An event handler for when a configuration changes.
     *
     * @param event The event data.
     */
    @SubscribeEvent
    public void onConfigChange(OnConfigChangedEvent event) {
        if (event.modID.equals(Tags.MOD_ID)) {
            LOGGER.info("Synchronizing configuration for {}...", Tags.MOD_NAME);
            Config.sync();
        }
    }

    /**
     * An event handler for when a command is issued.
     *
     * @param event The event data.
     */
    @SubscribeEvent
    public void onCommand(CommandEvent event) {
        if (event.command.getCommandName()
            .equals("reload")) {
            LOGGER.info("Synchronizing configuration for {}...", Tags.MOD_NAME);
            Config.sync();
        }
    }
}
