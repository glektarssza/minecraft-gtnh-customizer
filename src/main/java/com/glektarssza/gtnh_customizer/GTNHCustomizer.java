package com.glektarssza.gtnh_customizer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;

import com.glektarssza.gtnh_customizer.commands.ExtinguishCommand;
import com.glektarssza.gtnh_customizer.commands.ListDimensionsCommand;
import com.glektarssza.gtnh_customizer.commands.RepairCommand;
import com.glektarssza.gtnh_customizer.commands.TeleportCrossDimensionCommand;
import com.glektarssza.gtnh_customizer.config.Config;

import serverutils.events.ServerUtilitiesPreInitRegistryEvent;
import thaumcraft.common.blocks.BlockCustomPlant;

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
        LOGGER.info("Registering mod {} with the Forge event bus...",
            Tags.MOD_NAME);
        MinecraftForge.EVENT_BUS.register(this);
        // -- OnConfigChangedEvent comes in through here
        FMLCommonHandler.instance().bus().register(this);
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
        event.registerServerCommand(new ExtinguishCommand());
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
        if (event.configID.equalsIgnoreCase(Config.CONFIG_ID.toString())) {
            LOGGER.info("Synchronizing configuration for {}...", Tags.MOD_NAME);
            Config.sync();
        }
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
        event.getRegistry().registerServerReloadHandler(
            new ResourceLocation(Tags.MOD_ID, "config"),
            reloadEvent -> {
                try {
                    Config.sync();
                } catch (Throwable t) {
                    return false;
                }
                return true;
            });
    }

    /**
     * An event handler for when a player tries to use bone meal.
     *
     * @param event The event data.
     */
    @SubscribeEvent
    public void onBoneMeal(BonemealEvent event) {
        if (!Loader.isModLoaded("Thaumcraft")) {
            return;
        }
        if (!(event.block instanceof BlockCustomPlant)) {
            return;
        }
        if (event.world.getBlockLightValue(event.x, event.y, event.z) < 9) {
            return;
        }
        switch (event.world.getBlockMetadata(event.x, event.y, event.z)) {
            case 0:
                if (!Config.getThaumcraftCanBoneMealGreatwoodSaplings()) {
                    return;
                }
                // -- The bone meal is used at this point no matter what, but
                // -- the growth is NOT guaranteed!
                event.setResult(Result.ALLOW);
                if (event.world.isRemote
                    || event.world.rand.nextFloat() >= 0.45D) {
                    return;
                }
                ((BlockCustomPlant) event.block).growGreatTree(event.world,
                    event.x, event.y, event.z,
                    event.world.rand);
                break;
            case 1:
                if (!Config.getThaumcraftCanBoneMealSilverwoodSaplings()) {
                    return;
                }
                // -- The bone meal is used at this point no matter what, but
                // -- the growth is NOT guaranteed!
                event.setResult(Result.ALLOW);
                if (event.world.isRemote
                    || event.world.rand.nextFloat() >= 0.45D) {
                    return;
                }
                ((BlockCustomPlant) event.block).growSilverTree(event.world,
                    event.x, event.y, event.z,
                    event.world.rand);
                break;
        }
    }
}
