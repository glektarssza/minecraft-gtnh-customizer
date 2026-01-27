package com.glektarssza.gtnh_customizer.lib.config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.gui.GuiListExtended;

/**
 * A configuration property type which encapsulates a numerical value.
 */
public class NumberProperty<U extends GuiListExtended.IGuiListEntry>
    extends Property<Number, U> {
    // #region Protected Fields

    /**
     * Whether the configuration property represented by this instance has a
     * maximum value.
     */
    protected boolean hasMaximumValue;

    /**
     * Whether the configuration property represented by this instance has a
     * minimum value.
     */
    protected boolean hasMinimumValue;

    /**
     * The maximum allowed value of the configuration property represented by
     * this instance, if any.
     */
    @Nullable
    protected Number maximumValue;

    /**
     * The minimum allowed value of the configuration property represented by
     * this instance, if any.
     */
    @Nullable
    protected Number minimumValue;

    // #endregion Protected Fields

    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue) {
        this(name, type, initialValue, null, null, null, null, null, false,
            false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number minimumValue,
        @Nullable Number maximumValue) {
        this(name, type, initialValue, null, minimumValue,
            maximumValue, null, null, false, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, null, null, null, null, null, false,
            false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number minimumValue,
        @Nullable Number maximumValue, @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, null, minimumValue, maximumValue, null,
            null, false, false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue) {
        this(name, type, initialValue, defaultValue, null, null, null, null,
            false, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable Number minimumValue, @Nullable Number maximumValue) {
        this(name, type, initialValue, defaultValue,
            minimumValue, maximumValue, null, null, false, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, defaultValue, null, null, null, null,
            false, false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable Number minimumValue, @Nullable Number maximumValue,
        @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, defaultValue,
            minimumValue, maximumValue, null, null, false, false, false,
            uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable String languageKey) {
        this(name, type, initialValue, null, null, null, languageKey, null,
            false, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number minimumValue,
        @Nullable Number maximumValue, @Nullable String languageKey) {
        this(name, type, initialValue, null, minimumValue, maximumValue,
            languageKey, null, false, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable String languageKey,
        @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, null, null, null, languageKey, null,
            false, false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number minimumValue,
        @Nullable Number maximumValue, @Nullable String languageKey,
        @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, null, minimumValue, maximumValue,
            languageKey, null,
            false, false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable String languageKey) {
        this(name, type, initialValue, defaultValue, null, null, languageKey,
            null, false, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number minimumValue,
        @Nullable Number maximumValue, @Nullable Number defaultValue,
        @Nullable String languageKey) {
        this(name, type, initialValue, defaultValue, minimumValue, maximumValue,
            languageKey, null, false, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable String languageKey, @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, defaultValue, null, null, languageKey,
            null, false, false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable Number minimumValue, @Nullable Number maximumValue,
        @Nullable String languageKey, @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, defaultValue, minimumValue, maximumValue,
            languageKey, null, false, false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable String languageKey,
        @Nullable String comment) {
        this(name, type, initialValue, null, null, null, languageKey, comment,
            false, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number minimumValue,
        @Nullable Number maximumValue, @Nullable String languageKey,
        @Nullable String comment) {
        this(name, type, initialValue, null, minimumValue, maximumValue,
            languageKey, comment, false, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable String languageKey,
        @Nullable String comment, @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, null, null, null, languageKey, comment,
            false, false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number minimumValue,
        @Nullable Number maximumValue, @Nullable String languageKey,
        @Nullable String comment, @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, null, minimumValue, maximumValue,
            languageKey, comment, false, false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable String languageKey, @Nullable String comment) {
        this(name, type, initialValue, defaultValue, null, null, languageKey,
            comment, false, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number minimumValue,
        @Nullable Number maximumValue, @Nullable Number defaultValue,
        @Nullable String languageKey, @Nullable String comment) {
        this(name, type, initialValue, defaultValue, minimumValue, maximumValue,
            languageKey, comment, false, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable String languageKey, @Nullable String comment,
        @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, defaultValue, null, null, languageKey,
            comment, false, false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number minimumValue,
        @Nullable Number maximumValue, @Nullable Number defaultValue,
        @Nullable String languageKey, @Nullable String comment,
        @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, defaultValue, minimumValue, maximumValue,
            languageKey, comment, false, false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable String languageKey,
        @Nullable String comment, boolean hidden) {
        this(name, type, initialValue, null, null, null, languageKey, comment,
            hidden, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number minimumValue,
        @Nullable Number maximumValue, @Nullable String languageKey,
        @Nullable String comment, boolean hidden) {
        this(name, type, initialValue, null, minimumValue, maximumValue,
            languageKey, comment, hidden, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable String languageKey,
        @Nullable String comment, boolean hidden,
        @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, null, null, null, languageKey, comment,
            hidden, false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number minimumValue,
        @Nullable Number maximumValue, @Nullable String languageKey,
        @Nullable String comment, boolean hidden,
        @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, null, minimumValue, maximumValue,
            languageKey, comment, hidden, false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable String languageKey, @Nullable String comment,
        boolean hidden) {
        this(name, type, initialValue, defaultValue, null, null, languageKey,
            comment, hidden, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number minimumValue,
        @Nullable Number maximumValue, @Nullable Number defaultValue,
        @Nullable String languageKey,
        @Nullable String comment, boolean hidden) {
        this(name, type, initialValue, defaultValue, minimumValue, maximumValue,
            languageKey, comment, hidden, false, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable String languageKey, @Nullable String comment,
        boolean hidden, @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, defaultValue, null, null, languageKey,
            comment, hidden, false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable Number minimumValue, @Nullable Number maximumValue,
        @Nullable String languageKey, @Nullable String comment,
        boolean hidden, @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, defaultValue, minimumValue, maximumValue,
            languageKey, comment, hidden, false, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the world to be
     *        restarted when changed.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable String languageKey,
        @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart) {
        this(name, type, initialValue, null, null, null, languageKey, comment,
            hidden, requiresWorldRestart, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the world to be
     *        restarted when changed.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number minimumValue,
        @Nullable Number maximumValue, @Nullable String languageKey,
        @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart) {
        this(name, type, initialValue, null, minimumValue, maximumValue,
            languageKey, comment, hidden, requiresWorldRestart, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the world to be
     *        restarted when changed.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable String languageKey,
        @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart, @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, null, null, null, languageKey, comment,
            hidden, requiresWorldRestart, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the world to be
     *        restarted when changed.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number minimumValue,
        @Nullable Number maximumValue, @Nullable String languageKey,
        @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart, @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, null, minimumValue, maximumValue,
            languageKey, comment, hidden, requiresWorldRestart, false,
            uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the world to be
     *        restarted when changed.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable String languageKey, @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart) {
        this(name, type, initialValue, defaultValue, null, null, languageKey,
            comment, hidden, requiresWorldRestart, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the world to be
     *        restarted when changed.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable Number minimumValue, @Nullable Number maximumValue,
        @Nullable String languageKey, @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart) {
        this(name, type, initialValue, defaultValue, minimumValue, maximumValue,
            languageKey, comment, hidden, requiresWorldRestart, false, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the world to be
     *        restarted when changed.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable String languageKey, @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart, @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, defaultValue, null, null, languageKey,
            comment, hidden, requiresWorldRestart, false, uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the world to be
     *        restarted when changed.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable Number minimumValue, @Nullable Number maximumValue,
        @Nullable String languageKey, @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart, @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, defaultValue, minimumValue, maximumValue,
            languageKey, comment, hidden, requiresWorldRestart, false,
            uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the world to be
     *        restarted when changed.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the game to be
     *        restarted when changed.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable String languageKey, @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart, boolean requiresGameRestart) {
        this(name, type, initialValue, defaultValue, null, null, languageKey,
            comment, hidden, requiresWorldRestart, requiresGameRestart, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the world to be
     *        restarted when changed.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the game to be
     *        restarted when changed.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable Number minimumValue, @Nullable Number maximumValue,
        @Nullable String languageKey, @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart, boolean requiresGameRestart) {
        this(name, type, initialValue, defaultValue, minimumValue, maximumValue,
            languageKey, comment, hidden, requiresWorldRestart,
            requiresGameRestart, null);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the world to be
     *        restarted when changed.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the game to be
     *        restarted when changed.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable String languageKey, @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart, boolean requiresGameRestart,
        @Nullable Class<U> uiDisplayClass) {
        this(name, type, initialValue, defaultValue, null, null, languageKey,
            comment, hidden, requiresWorldRestart, requiresGameRestart,
            uiDisplayClass);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValue The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by the new instance, if any.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the world to be
     *        restarted when changed.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the game to be
     *        restarted when changed.
     * @param uiDisplayClass The class that will be used to display the
     *        configuration property represented by the new instance in the
     *        game.
     */
    public NumberProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull Number initialValue, @Nullable Number defaultValue,
        @Nullable Number minimumValue, @Nullable Number maximumValue,
        @Nullable String languageKey, @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart, boolean requiresGameRestart,
        @Nullable Class<U> uiDisplayClass) {
        super(name, type, initialValue, defaultValue, languageKey, comment,
            hidden, requiresWorldRestart, requiresGameRestart, uiDisplayClass);
        this.minimumValue = minimumValue;
        this.hasMinimumValue = minimumValue != null;
        this.maximumValue = maximumValue;
        this.hasMinimumValue = maximumValue != null;
    }

    // #endregion Constructors
}
