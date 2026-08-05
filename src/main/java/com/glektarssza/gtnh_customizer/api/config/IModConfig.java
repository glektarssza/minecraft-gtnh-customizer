package com.glektarssza.gtnh_customizer.api.config;

import java.nio.file.Path;
import java.util.Optional;

import com.glektarssza.gtnh_customizer.api.annotations.NotNull;
import com.glektarssza.gtnh_customizer.api.utils.TypeUtilities;

/**
 * An interface which defines a mod configuration.
 */
public interface IModConfig {
    /**
     * Get the name of the configuration this instance represents.
     *
     * <p>
     * This value will be the name of the configuration file, suffixed with
     * {@code .json}.
     * </p>
     *
     * @return The name of the configuration this instance represents.
     */
    @NotNull
    public abstract String getName();

    /**
     * Get the root Minecraft configuration directory path.
     *
     * @return The root Minecraft configuration directory path.
     */
    @NotNull
    public abstract Path getRootConfigDirPath();

    /**
     * Get the sub-directory path of the Minecraft configuration directory which
     * the configuration represented by this instance lives at.
     *
     * @return The sub-directory path of the Minecraft configuration directory
     *         which the configuration represented by this instance lives at.
     */
    @NotNull
    public abstract Optional<String> getConfigSubDirPath();

    /**
     * Get the full path to the configuration represented by this instance.
     *
     * @return The full path to the configuration represented by this instance.
     */
    @NotNull
    public default Path getFullPath() {
        Path fullPath = this.getRootConfigDirPath();
        final Optional<String> subDir = this.getConfigSubDirPath();
        if (subDir.isPresent()) {
            fullPath = fullPath.resolve(subDir.get());
        }
        return TypeUtilities
            .assertNotNull(fullPath.resolve(this.getName() + ".json"));
    }

    /**
     * Load the instance from disk.
     */
    public abstract void load();

    /**
     * Save the instance to disk.
     */
    public abstract void save();

    /**
     * Synchronize the instance with the one from the disk.
     *
     * <p>
     * This involves loading the values from disk, merging them with any changed
     * values in memory, and then saving the entire instance back to disk.
     * </p>
     */
    public abstract void sync();
}
