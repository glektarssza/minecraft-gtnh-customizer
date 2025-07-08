package com.glektarssza.gtnh_customizer.config;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

/**
 * An interface which describes a configuration category.
 */
public interface Category {
    /**
     * Get the ID of the category.
     *
     * @return The ID of the category.
     */
    @Nonnull
    public String getID();

    /**
     * Get the full path of the category.
     *
     * @return The full path of the category.
     */
    @Nonnull
    public default String getFullPath() {
        Category parent = this.getParent();
        if (parent == null) {
            return this.getID();
        }
        return String.join(Configuration.CATEGORY_SPLITTER,
            parent.getFullPath(), this.getID());
    }

    /**
     * Get the language key of the category.
     *
     * @return The language key of the category.
     */
    @Nonnull
    public default String getLanguageKey() {
        Category parent = this.getParent();
        if (parent == null) {
            return String.join(Configuration.CATEGORY_SPLITTER,
                "gtnh_customizer.config.categories", this.getID());
        }
        return String.join(Configuration.CATEGORY_SPLITTER,
            parent.getLanguageKey(), this.getID());
    }

    /**
     * Get the comment of the category.
     *
     * @return The comment of the category.
     */
    @Nullable
    public String getComment();

    /**
     * Get whether to show this category in the GUI.
     *
     * @return Whether to show this category in the GUI.
     */
    public default boolean getShowInGui() {
        return true;
    }

    /**
     * Get whether changes to this category require a world restart.
     *
     * @return Whether changes to this category require a world restart.
     */
    public boolean getRequiresWorldRestart();

    /**
     * Get whether changes to this category require a game restart.
     *
     * @return Whether changes to this category require a game restart.
     */
    public boolean getRequiresGameRestart();

    /**
     * Get the order to show properties in, if any.
     *
     * @return The order to show properties in, if any; {@code null} to specify
     *         no specific order.
     */
    @Nullable
    public default List<String> getPropertyOrder() {
        return null;
    }

    /**
     * Get the parent category of this category.
     *
     * @return The parent category of this category if it is not the root
     *         category; {@code null} otherwise.
     */
    @Nullable
    public Category getParent();

    /**
     * Check whether this category does not have any children categories and
     * does not have any children properties.
     *
     * @return {@code true} if this category does not have any children
     *         categories and does not have any children properties;
     *         {@code false} otherwise.
     */
    public default boolean isEmpty() {
        return !this.hasChildCategories() && !this.hasChildProperties();
    }

    /**
     * Check whether this category has any children categories.
     *
     * @return {@code true} if this category has any children categories;
     *         {@code false} otherwise.
     */
    public default boolean hasChildCategories() {
        return this.getChildrenCategories().length <= 0;
    }

    /**
     * Check whether this category has any children properties.
     *
     * @return {@code true} if this category has any children properties;
     *         {@code false} otherwise.
     */
    public default boolean hasChildProperties() {
        return this.getChildrenProperties().length <= 0;
    }

    /**
     * Get the children categories of this category.
     *
     * @return The children categories of this category.
     */
    @Nonnull
    public Category[] getChildrenCategories();

    /**
     * Get the children properties of this category.
     *
     * @return The children properties of this category.
     */
    @Nonnull
    public Property<?>[] getChildrenProperties();

    /**
     * Register this category and its children onto the given Forge
     * configuration.
     *
     * @param config The Forge configuration to register this category on to.
     */
    public default void registerForgeConfigCategory(Configuration config) {
        this.registerForgeConfigCategory(config, true);
    }

    /**
     * Register this category and, optionally, its children onto the given Forge
     * configuration.
     *
     * @param config The Forge configuration to register this category on to.
     * @param recurse Whether to register this category's children onto the
     *        Forge configuration.
     *
     * @return This instance for chaining.
     */
    public default Category registerForgeConfigCategory(Configuration config,
        boolean recurse) {
        ConfigCategory cat = config.getCategory(this.getFullPath());
        cat.setComment(this.getComment());
        cat.setLanguageKey(this.getLanguageKey());
        cat.setShowInGui(this.getShowInGui());
        cat.setRequiresWorldRestart(this.getRequiresWorldRestart());
        cat.setRequiresMcRestart(this.getRequiresGameRestart());
        List<String> order = this.getPropertyOrder();
        if (order != null) {
            cat.setPropertyOrder(order);
        }
        Arrays.stream(this.getChildrenProperties())
            .forEach((prop) -> prop.registerForgeConfigCategory(config));
        if (recurse) {
            Arrays.stream(this.getChildrenCategories())
                .forEach(
                    (ccat) -> ccat.registerForgeConfigCategory(config, true));
        }
        return this;
    }

    /**
     * Load this category, its child category's, and its property's values.
     *
     * @param config The Forge configuration to load this category's values
     *        from.
     *
     * @return This instance for chaining.
     */
    public default Category loadValues(Configuration config) {
        Arrays.stream(this.getChildrenProperties())
            .forEach((prop) -> prop.loadValue(config));
        Arrays.stream(this.getChildrenCategories())
            .forEach((prop) -> prop.loadValues(config));
        return this;
    }
}
