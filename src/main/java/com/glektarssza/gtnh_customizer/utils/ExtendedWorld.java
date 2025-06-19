package com.glektarssza.gtnh_customizer.utils;

import net.minecraft.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveHandler;

/**
 * A {@link World} with extended functionality.
 */
public abstract class ExtendedWorld extends World implements IWorldExtensions {
    /**
     * Constructor so Java stops complaining.
     */
    public ExtendedWorld(ISaveHandler saveHandler, String p_i45368_2_,
        WorldSettings worldSettings, WorldProvider worldProvider,
        Profiler profiler) {
        super(saveHandler, p_i45368_2_, worldSettings, worldProvider, profiler);
    }

    /**
     * Constructor so Java stops complaining.
     */
    public ExtendedWorld(ISaveHandler saveHandler, String p_i45368_2_,
        WorldProvider worldProvider, WorldSettings worldSettings,
        Profiler profiler) {
        super(saveHandler, p_i45368_2_, worldProvider, worldSettings, profiler);
    }

    /**
     * Get the render distance of the world.
     *
     * @return The render distance of the world.
     */
    @Override
    public int getRenderDistance() {
        return this.func_152379_p();
    }
}
