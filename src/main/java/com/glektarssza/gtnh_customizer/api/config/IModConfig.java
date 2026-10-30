package com.glektarssza.gtnh_customizer.api.config;

import com.glektarssza.gtnh_customizer.api.annotations.NotNull;

/**
 * An interface defining the shape of a mod configuration.
 */
public interface IModConfig {
    /**
     * Get the name of the mod this instance represents a configuration for.
     *
     * @return The name of the mod this instance represents a configuration for.
     */
    @NotNull
    public String getModName();
}
