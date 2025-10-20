package com.glektarssza.gtnh_customizer;

import net.minecraft.client.Minecraft;

import cpw.mods.fml.common.event.FMLInitializationEvent;

/**
 * The client-side proxy.
 */
public class ClientProxy extends CommonProxy {
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
        getLogger().info("Initializing client-side for {}...", Tags.MOD_NAME);
        getLogger().info("Registering key bindings for {}...",
            Tags.MOD_NAME);
        getLogger().info("Done initializing client-side for {}!",
            Tags.MOD_NAME);
    }
}
