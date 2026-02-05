package com.glektarssza.gtnh_customizer;

import java.lang.invoke.MethodHandles;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;

import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

/**
 * The client-side proxy.
 */
public class ClientProxy extends CommonProxy {
    /**
     * The logger for this class.
     */
    @Nonnull
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
            MethodHandles.lookup().lookupClass().getSimpleName())));

    /**
     * Get the Minecraft world.
     *
     * @return The Minecraft world.
     */
    @Override
    public World getWorld() {
        return Minecraft.getMinecraft().theWorld;
    }

    /**
     * Get the side the mod is running on.
     *
     * @return The side the mod is running on.
     */
    @Nonnull
    @Override
    public Side getSide() {
        return Side.SERVER;
    }

    /**
     * Get the currently configured view distance, in chunks.
     *
     * @return The currently configured view distance, in chunks.
     */
    @Override
    public int getViewDistanceChunks() {
        return Minecraft.getMinecraft().gameSettings.renderDistanceChunks;
    }

    /**
     * Handle the mod initialization event.
     *
     * @param event The incoming event.
     */
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        LOGGER.info("Initializing client-side for {}...", Tags.MOD_NAME);
        LOGGER.info("Registering key bindings for {}...",
            Tags.MOD_NAME);
        KeyBindings.registerKeybinds();
        LOGGER.info("Done initializing client-side for {}!",
            Tags.MOD_NAME);
    }
}
