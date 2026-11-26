package com.glektarssza.gtnh_customizer;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;

import com.glektarssza.gtnh_customizer.api.v2.annotation.NotNull;

/**
 * The client-side proxy.
 */
public class ClientProxy extends CommonProxy {
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
    @NotNull
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
    public void init(final FMLInitializationEvent event) {
        super.init(event);
        this.getLogger().info("Initializing client-side for {}...",
            Tags.MOD_NAME);
        this.getLogger().info("Registering key bindings for {}...",
            Tags.MOD_NAME);
        KeyBindings.registerKeybinds();
        this.getLogger().info("Done initializing client-side for {}!",
            Tags.MOD_NAME);
    }
}
