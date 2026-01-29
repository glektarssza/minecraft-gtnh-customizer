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

    // #region Public Methods

    /**
     * Get whether the configuration property represented by this instance has a
     * minimum value.
     *
     * @return Whether the configuration property represented by this instance
     *         has a minimum value.
     */
    public boolean hasMinimumValue() {
        return this.hasMinimumValue;
    }

    /**
     * Get the minimum allowed value of the configuration property represented
     * by the new instance, if any.
     *
     * @return The minimum allowed value of the configuration property
     *         represented by this instance, if any.
     */
    @Nullable
    public Number getMinimumValue() {
        return this.minimumValue;
    }

    /**
     * Get the minimum allowed value of the configuration property represented
     * by this instance, if any.
     *
     * @param minimumValue The minimum allowed value of the configuration
     *        property represented by this instance, if any.
     */
    public void setMinimumValue(@Nullable Number minimumValue) {
        this.minimumValue = minimumValue;
    }

    /**
     * Get the maximum allowed value of the configuration property represented
     * by this instance, if any.
     *
     * @return The maximum allowed value of the configuration property
     *         represented by this instance, if any.
     */
    @Nullable
    public Number getMaximumValue() {
        return this.maximumValue;
    }

    /**
     * Get the maximum allowed value of the configuration property represented
     * by this instance, if any.
     *
     * @param maximumValue The maximum allowed value of the configuration
     *        property represented by this instance, if any.
     */
    public void setMaximumValue(@Nullable Number maximumValue) {
        this.maximumValue = maximumValue;
    }

    /**
     * Get the byte value of the configuration property represented by this
     * instance.
     *
     * @return The byte value of the configuration property represented by this
     *         instance.
     */
    public byte getByteValue() {
        return this.value.byteValue();
    }

    /**
     * Get the default byte value of the configuration property represented by
     * this instance.
     *
     * @return The default byte value of the configuration property represented
     *         by this instance.
     */
    public byte getDefaultByteValue() {
        if (this.defaultValue == null) {
            return 0;
        }
        return this.defaultValue.byteValue();
    }

    /**
     * Get the half precision integer value of the configuration property
     * represented by this instance.
     *
     * @return The half precision integer value of the configuration property
     *         represented by this instance.
     */
    public short getShortValue() {
        return this.value.shortValue();
    }

    /**
     * Get the default half prevision integer value of the configuration
     * property represented by this instance.
     *
     * @return The default half prevision integer value of the configuration
     *         property represented by this instance.
     */
    public short getDefaultShortValue() {
        if (this.defaultValue == null) {
            return 0;
        }
        return this.defaultValue.shortValue();
    }

    /**
     * Get the integer value of the configuration property represented by this
     * instance.
     *
     * @return The integer value of the configuration property represented by
     *         this instance.
     */
    public int getIntValue() {
        return this.value.intValue();
    }

    /**
     * Get the default integer value of the configuration property represented
     * by this instance.
     *
     * @return The default integer value of the configuration property
     *         represented by this instance.
     */
    public int getIntDefaultValue() {
        if (this.defaultValue == null) {
            return 0;
        }
        return this.defaultValue.intValue();
    }

    /**
     * Get the double precision integer value of the configuration property
     * represented by this instance.
     *
     * @return The double precision integer value of the configuration property
     *         represented by this instance.
     */
    public long getLongValue() {
        return this.value.longValue();
    }

    /**
     * Get the double precision default integer value of the configuration
     * property represented by this instance.
     *
     * @return The double precision default integer value of the configuration
     *         property represented by this instance.
     */
    public long getLongDefaultValue() {
        if (this.defaultValue == null) {
            return 0;
        }
        return this.defaultValue.longValue();
    }

    /**
     * Get the floating point value of the configuration property represented by
     * this instance.
     *
     * @return The floating point value of the configuration property
     *         represented by this instance.
     */
    public float getFloatValue() {
        return this.value.floatValue();
    }

    /**
     * Get the default floating point value of the configuration property
     * represented by this instance.
     *
     * @return The default floating point value of the configuration property
     *         represented by this instance.
     */
    public float getFloatDefaultValue() {
        if (this.defaultValue == null) {
            return 0;
        }
        return this.value.floatValue();
    }

    /**
     * Get the double precision floating point value of the configuration
     * property represented by this instance.
     *
     * @return The double precision floating point value of the configuration
     *         property represented by this instance.
     */
    public double getDoubleValue() {
        return this.value.doubleValue();
    }

    /**
     * Get the default double precision floating point value of the
     * configuration property represented by this instance.
     *
     * @return The default double precision floating point value of the
     *         configuration property represented by this instance.
     */
    public double getDoubleDefaultValue() {
        if (this.defaultValue == null) {
            return 0;
        }
        return this.value.doubleValue();
    }

    // #endregion Public Methods
}
