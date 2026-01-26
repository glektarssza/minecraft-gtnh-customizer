package com.glektarssza.gtnh_customizer.lib.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import cpw.mods.fml.client.config.GuiConfigEntries.ArrayEntry;

/**
 * A configuration property type which encapsulates a list of values.
 */
public class ListProperty<T, U extends ArrayEntry>
    extends Property<List<T>, U> {
    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValues The value of the configuration property represented
     *        by the new instance.
     */
    public ListProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T[] initialValues) {
        super(name, type, Arrays.asList(initialValues), Collections.emptyList(),
            null, null, false, false, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValues The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     */
    public ListProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T[] initialValues, @Nullable T[] defaultValues) {
        super(name, type, Arrays.asList(initialValues),
            Arrays.asList(defaultValues), null, null, false, false, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValues The value of the configuration property represented
     *        by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     */
    public ListProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T[] initialValues, @Nullable String languageKey) {
        super(name, type, Arrays.asList(initialValues), Collections.emptyList(),
            languageKey, null, false, false, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValues The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     */
    public ListProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T[] initialValues, @Nullable T[] defaultValues,
        @Nullable String languageKey) {
        super(name, type, Arrays.asList(initialValues),
            Arrays.asList(defaultValues), languageKey, null, false, false,
            false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValues The value of the configuration property represented
     *        by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     */
    public ListProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T[] initialValues, @Nullable String languageKey,
        @Nullable String comment) {
        super(name, type, Arrays.asList(initialValues), Collections.emptyList(),
            languageKey, comment, false, false, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValues The value of the configuration property represented
     *        by the new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     */
    public ListProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T[] initialValues, @Nullable T[] defaultValues,
        @Nullable String languageKey, @Nullable String comment) {
        super(name, type, Arrays.asList(initialValues),
            Arrays.asList(defaultValues), languageKey, comment, false, false,
            false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValues The value of the configuration property represented
     *        by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     */
    public ListProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T[] initialValues, @Nullable String languageKey,
        @Nullable String comment, boolean hidden) {
        super(name, type, Arrays.asList(initialValues), Collections.emptyList(),
            languageKey, comment, hidden, false, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValues The value of the configuration property represented
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
    public ListProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T[] initialValues, @Nullable T[] defaultValues,
        @Nullable String languageKey,
        @Nullable String comment, boolean hidden) {
        super(name, type, Arrays.asList(initialValues),
            Arrays.asList(defaultValues), languageKey, comment, hidden, false,
            false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValues The value of the configuration property represented
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
    public ListProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T[] initialValues, @Nullable String languageKey,
        @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart) {
        super(name, type, Arrays.asList(initialValues), Collections.emptyList(),
            languageKey, comment, hidden, requiresWorldRestart, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValues The value of the configuration property represented
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
    public ListProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T[] initialValues, @Nullable T[] defaultValues,
        @Nullable String languageKey, @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart) {
        super(name, type, Arrays.asList(initialValues),
            Arrays.asList(defaultValues), languageKey, comment, hidden,
            requiresWorldRestart, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param initialValues The value of the configuration property represented
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
    public ListProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T[] initialValues, @Nullable T[] defaultValues,
        @Nullable String languageKey, @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart, boolean requiresGameRestart) {
        super(name, type, Arrays.asList(initialValues),
            Arrays.asList(defaultValues), languageKey, comment, hidden,
            requiresWorldRestart, requiresGameRestart);
    }

    // #endregion Constructors
}
