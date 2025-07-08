package com.glektarssza.gtnh_customizer.config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

/**
 * A configuration property.
 */
public interface Property<T> {
    /**
     * Get the ID of the property.
     *
     * @return The ID of the property.
     */
    @Nonnull
    public String getID();

    /**
     * Get the full path of the property.
     *
     * @return The full path of the property.
     */
    @Nonnull
    public default String getFullPath() {
        return String.join(Configuration.CATEGORY_SPLITTER,
            this.getParent().getFullPath(), this.getID());
    }

    /**
     * Get the language key of the property.
     *
     * @return The language key of the property.
     */
    @Nonnull
    public default String getLanguageKey() {
        return String.join(Configuration.CATEGORY_SPLITTER,
            this.getParent().getLanguageKey(), this.getID());
    }

    /**
     * Get the comment of the property.
     *
     * @return The comment of the property.
     */
    @Nullable
    public String getComment();

    /**
     * Get whether to show this property in the GUI.
     *
     * @return Whether to show this property in the GUI.
     */
    public default boolean getShowInGui() {
        return true;
    }

    /**
     * Get whether changes to this property require a world restart.
     *
     * @return Whether changes to this property require a world restart.
     */
    public boolean getRequiresWorldRestart();

    /**
     * Get whether changes to this property require a game restart.
     *
     * @return Whether changes to this property require a game restart.
     */
    public boolean getRequiresGameRestart();

    /**
     * Get the type of value held in this property.
     *
     * @return The type of value held in this property.
     */
    @Nonnull
    public Type getValueType();

    /**
     * Get whether the type of value held in this property is a list.
     *
     * @return Whether the type of value held in this property is a list.
     */
    public boolean isList();

    /**
     * Get the default value of the property.
     *
     * @param <T> The type of the default value.
     *
     * @return The default value of the property.
     */
    @Nonnull
    public T getDefaultValue();

    /**
     * Get the parent category of this property.
     *
     * @return The parent category of this property.
     */
    @Nonnull
    public Category getParent();

    /**
     * Register this property onto the given Forge configuration.
     *
     * @param config The Forge configuration to register this property on to.
     */
    public void registerForgeConfigCategory(Configuration config);

    /**
     * Load this property's values.
     *
     * @param config The Forge configuration to load this property's values
     *        from.
     */
    public void loadValue(Configuration config);
}
