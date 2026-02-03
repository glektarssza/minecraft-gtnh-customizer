package com.glektarssza.gtnh_customizer;

import java.lang.invoke.MethodHandles;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import cpw.mods.fml.common.event.FMLInitializationEvent;

import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

/**
 * The client-side proxy.
 */
public class ClientProxy extends CommonProxy {
    /**
     * The logger for this class.
     */
    @Nonnull
    private static final Logger LOGGER = TypeHelpers.castToNonNull(LoggerFactory
        .getLogger(MethodHandles.lookup().lookupClass()));

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
