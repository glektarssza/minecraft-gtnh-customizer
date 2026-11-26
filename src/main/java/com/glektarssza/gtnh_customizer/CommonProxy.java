package com.glektarssza.gtnh_customizer;

import java.io.File;

import org.apache.logging.log4j.Logger;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;

import com.glektarssza.gtnh_customizer.api.v2.annotation.NotNull;
import com.glektarssza.gtnh_customizer.api.v2.annotation.Nullable;
import com.glektarssza.gtnh_customizer.api.v2.exception.InvalidStateException;
import com.glektarssza.gtnh_customizer.commands.CureCommand;
import com.glektarssza.gtnh_customizer.commands.ExtinguishCommand;
import com.glektarssza.gtnh_customizer.commands.GTNHCustomizerCommand;
import com.glektarssza.gtnh_customizer.commands.ListDimensionsCommand;
import com.glektarssza.gtnh_customizer.commands.RepairCommand;
import com.glektarssza.gtnh_customizer.commands.TeleportCrossDimensionCommand;
import com.glektarssza.gtnh_customizer.config.Config;

import serverutils.ServerUtilitiesRegistry;
import thaumcraft.common.blocks.BlockCustomPlant;

/**
 * The common and server-side proxy.
 */
public class CommonProxy {
    /**
     * The logger for the mod.
     */
    @Nullable
    private Logger logger;

    /**
     * The configuration directory.
     */
    @Nullable
    private File configDir;

    /**
     * Get the logger for this mod.
     *
     * @return The logger for this mod.
     *
     * @throws InvalidStateException Thrown if the logger is not available.
     */
    @NotNull
    public Logger getLogger() throws InvalidStateException {
        if (this.logger != null) {
            return this.logger;
        }
        throw new InvalidStateException("Mod logger is not available");
    }

    /**
     * Try to get the logger for this mod.
     *
     * @return The logger for this mod or {@code null} if the logger is not
     *         available.
     */
    @Nullable
    public Logger tryGetLogger() {
        return this.logger;
    }

    /**
     * Get the Minecraft world.
     *
     * @return The Minecraft world.
     */
    public World getWorld() {
        return MinecraftServer.getServer().getEntityWorld();
    }

    /**
     * Get the side the mod is running on.
     *
     * @return The side the mod is running on.
     */
    @NotNull
    public Side getSide() {
        return Side.SERVER;
    }

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
     * Get the configuration directory for the mod.
     *
     * @return The configuration directory for the mod.
     *
     * @throws InvalidStateException Thrown if the configuration directory is
     *         not available.
     */
    @NotNull
    public File getConfigDir() throws InvalidStateException {
        if (this.configDir != null) {
            return this.configDir;
        }
        throw new InvalidStateException(
            "Configuration directory is not available");
    }

    /**
     * Try to get the configuration directory for the mod.
     *
     * @return The configuration directory for the mod or {@code null} if the
     *         configuration directory is not available.
     */
    @Nullable
    public File tryGetConfigDir() {
        return this.configDir;
    }

    /**
     * Handle the mod pre-initialization event.
     *
     * @param event The incoming event.
     */
    public void preInit(final FMLPreInitializationEvent event) {
        this.logger = event.getModLog();
        this.getLogger().info("Pre-initializing server/common-side for {}...",
            Tags.MOD_NAME);
        this.configDir = event.getModConfigurationDirectory();
        Config.init(this.configDir, String.format("%s.cfg", Tags.MOD_ID));
        this.getLogger().info("Loading configuration for {}...",
            Tags.MOD_NAME);
        Config.load();
        this.getLogger().info(
            "Registering mod {} with the Forge event bus...",
            Tags.MOD_NAME);
        MinecraftForge.EVENT_BUS.register(this);
        // -- OnConfigChangedEvent comes in through here
        FMLCommonHandler.instance().bus().register(this);
        this.serverUtilitiesPreInit();
        this.getLogger().info(
            "Done pre-initializing server/common-side for {}!",
            Tags.MOD_NAME);
    }

    /**
     * Handle the mod initialization event.
     *
     * @param event The incoming event.
     */
    public void init(final FMLInitializationEvent event) {
        this.getLogger().info("Initializing server/common-side for {}...",
            Tags.MOD_NAME);
        // -- Does nothing yet!
        this.getLogger().info("Done initializing server/common-side for {}!",
            Tags.MOD_NAME);
    }

    /**
     * Handle the mod post-initialization event.
     *
     * @param event The incoming event.
     */
    public void postInit(final FMLPostInitializationEvent event) {
        this.getLogger().info(
            "Post-initializing server/common-side for {}...",
            Tags.MOD_NAME);
        // -- Does nothing yet!
        this.getLogger().info(
            "Done post-initializing server/common-side for {}!",
            Tags.MOD_NAME);
    }

    /**
     * Handle the server starting event.
     *
     * @param event The incoming event.
     */
    public void serverStarting(final FMLServerStartingEvent event) {
        this.getLogger().info("Handling server about to start for {}...",
            Tags.MOD_NAME);
        this.getLogger().info("Registering custom commands for {}...",
            Tags.MOD_NAME);
        this.registerCommands(event);
        this.getLogger().info("Done registering custom commands for {}!",
            Tags.MOD_NAME);
        this.getLogger().info("Done handling server about to start for {}!",
            Tags.MOD_NAME);
    }

    /**
     * Handle the bone meal event.
     *
     * @param event The incoming event.
     */
    @SubscribeEvent
    public void boneMeal(final BonemealEvent event) {
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

    @SubscribeEvent
    public void configChanged(final ConfigChangedEvent event) {
        if (!event.modID.equals(Tags.MOD_ID)) {
            return;
        }
        Config.refresh();
        Config.save();
    }

    /**
     * Handle the Server Utilities mod pre-initialization registry event.
     */
    private void serverUtilitiesPreInit() {
        this.getLogger().info(
            "Pre-initializing server/common-side of Server Utilities stuff for {}...",
            Tags.MOD_NAME);
        ServerUtilitiesRegistry.registerServerReloadHandler(
            new ResourceLocation(Tags.MOD_ID, "config"),
            _ -> {
                try {
                    Config.save();
                    Config.load();
                } catch (final Throwable t) {
                    return false;
                }
                return true;
            });
        this.getLogger().info(
            "Done pre-initializing server/common-side of Server Utilities stuff for {}!",
            Tags.MOD_NAME);
    }

    /**
     * Register custom commands.
     *
     * @param event The incoming event.
     */
    private void registerCommands(final FMLServerStartingEvent event) {
        event.registerServerCommand(new GTNHCustomizerCommand());
        event.registerServerCommand(new TeleportCrossDimensionCommand());
        event.registerServerCommand(new ListDimensionsCommand());
        event.registerServerCommand(new RepairCommand());
        event.registerServerCommand(new ExtinguishCommand());
        event.registerServerCommand(new CureCommand());
    }
}
