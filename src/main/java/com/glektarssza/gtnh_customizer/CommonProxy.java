package com.glektarssza.gtnh_customizer;

import java.io.File;

import org.apache.logging.log4j.Logger;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
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
 * The common and server-side proxy.
 */
public class CommonProxy {
    /**
     * The main logger for the mod.
     */
    private Logger logger;

    /**
     * The configuration directory.
     */
    private File configDir;

    /**
     * Get the currently configured view distance, in chunks.
     *
     * @return The currently configured view distance, in chunks.
     */
    public int getViewDistanceChunks() {
        return MinecraftServer.getServer().getConfigurationManager()
            .getViewDistance();
    }

    /**
     * Get the currently configured view distance, in blocks.
     *
     * This is just {@link #getViewDistanceChunks()} multiplied by the size of a
     * chunk which <b>should</b> be <code>16</code>.
     *
     * @return The currently configured view distance, in blocks.
     */
    public int getViewDistanceBlocks() {
        return this.getViewDistanceChunks() * 16;
    }

    /**
     * Get the main logger for the mod.
     *
     * @return The main logger for the mod.
     */
    public Logger getLogger() {
        return this.logger;
    }

    /**
     * Get the configuration directory for the mod.
     *
     * @return The configuration directory for the mod.
     */
    public File getConfigDir() {
        return this.configDir;
    }

    /**
     * Handle the mod pre-initialization event.
     *
     * @param event The incoming event.
     */
    public void preInit(FMLPreInitializationEvent event) {
        this.logger = event.getModLog();
        VersionChecker.registerCallback((result) -> {
            switch (result.status) {
                case FAILED:
                    this.logger.error("Failed to check for version updates!");
                    break;
                case UNKNOWN:
                    this.logger.error("Failed to check for version updates!");
                    break;
                case UP_TO_DATE:
                    this.logger.info("Mod is up to date!");
                    break;
                case AHEAD_OF_BETA:
                    this.logger
                        .info(
                            "Mod is ahead of the latest version! Latest version is '{}' but you are running '{}'",
                            result.recommendedVersion,
                            GTNHCustomizer.getMetadata().version);
                    break;
                case AHEAD_OF_RECOMMENDED:
                    this.logger
                        .info(
                            "Mod is ahead of the recommended version! Recommend version is '{}' but you are running '{}'",
                            result.recommendedVersion,
                            GTNHCustomizer.getMetadata().version);
                    break;
                case BEHIND_BETA:
                    this.logger
                        .info(
                            "Mod is behind the latest version! Latest version is '{}' but you are running '{}'",
                            result.recommendedVersion,
                            GTNHCustomizer.getMetadata().version);
                    break;
                case BEHIND_RECOMMENDED:
                    this.logger
                        .info(
                            "Mod is behind the recommended version! Recommend version is '{}' but you are running '{}'",
                            result.recommendedVersion,
                            GTNHCustomizer.getMetadata().version);
                    break;
                case NOT_STARTED:
                    this.logger.error(
                        "Uhhhhhhh... Check is not started? That's wrong...");
                    break;
                case IN_PROGRESS:
                    this.logger.error(
                        "Uhhhhhhh... Check is in progress? That's wrong...");
                    break;
            }
        });
        new VersionChecker().start();
        GTNHCustomizer.getLogger().info(
            "Pre-initializing server/common-side for {}...",
            Tags.MOD_NAME);
        this.configDir = event.getModConfigurationDirectory();
        Config.init(configDir, String.format("%s.cfg", Tags.MOD_ID));
        GTNHCustomizer.getLogger().info("Synchronizing configuration for {}...",
            Tags.MOD_NAME);
        Config.sync();
        GTNHCustomizer.getLogger().info(
            "Registering mod {} with the Forge event bus...",
            Tags.MOD_NAME);
        MinecraftForge.EVENT_BUS.register(this);
        // -- OnConfigChangedEvent comes in through here
        FMLCommonHandler.instance().bus().register(this);
        GTNHCustomizer.getLogger().info(
            "Done pre-initializing server/common-side for {}!",
            Tags.MOD_NAME);
    }

    /**
     * Handle the mod initialization event.
     *
     * @param event The incoming event.
     */
    public void init(FMLInitializationEvent event) {
        getLogger().info("Initializing server/common-side for {}...",
            Tags.MOD_NAME);
        // -- Does nothing yet!
        getLogger().info("Done initializing server/common-side for {}!",
            Tags.MOD_NAME);
    }

    /**
     * Handle the mod post-initialization event.
     *
     * @param event The incoming event.
     */
    public void postInit(FMLPostInitializationEvent event) {
        getLogger().info("Post-initializing server/common-side for {}...",
            Tags.MOD_NAME);
        // -- Does nothing yet!
        getLogger().info("Done post-initializing server/common-side for {}!",
            Tags.MOD_NAME);
    }

    /**
     * Handle the server starting event.
     *
     * @param event The incoming event.
     */
    public void serverStarting(FMLServerStartingEvent event) {
        getLogger().info("Handling server about to start for {}...",
            Tags.MOD_NAME);
        getLogger().info("Registering custom commands for {}...",
            Tags.MOD_NAME);
        event.registerServerCommand(new TeleportCrossDimensionCommand());
        event.registerServerCommand(new ListDimensionsCommand());
        event.registerServerCommand(new RepairCommand());
        event.registerServerCommand(new ExtinguishCommand());
        getLogger().info("Done registering custom commands for {}!",
            Tags.MOD_NAME);
        getLogger().info("Done handling server about to start for {}!",
            Tags.MOD_NAME);
    }

    /**
     * Handle the Server Utilities mod pre-initialization registry event.
     *
     * @param event The incoming event.
     */
    @SubscribeEvent
    public void serverUtilitiesPreInitRegistry(
        ServerUtilitiesPreInitRegistryEvent event) {
        GTNHCustomizer.getLogger().info(
            "Pre-initializing server/common-side of Server Utilities stuff for {}...",
            Tags.MOD_NAME);
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
        GTNHCustomizer.getLogger().info(
            "Done pre-initializing server/common-side of Server Utilities stuff for {}!",
            Tags.MOD_NAME);
    }

    /**
     * Handle the bone meal event.
     *
     * @param event The incoming event.
     */
    public void boneMeal(BonemealEvent event) {
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
