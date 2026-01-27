package com.glektarssza.gtnh_customizer.lib.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import cpw.mods.fml.client.config.GuiEditArrayEntries.IArrayEntry;

import com.glektarssza.gtnh_customizer.lib.utils.TypeHelpers;

/**
 * A configuration property type which encapsulates a list of values.
 */
public class ListProperty<T, U extends IArrayEntry>
    extends Property<List<T>, U> {
    // #region Protected Fields

    /**
     * Whether the list represented by this configuration property is of a fixed
     * length.
     *
     * @implNote Mutually exclusive with {@link ListProperty#hasMaxLength
     *           hasMaxLength}.
     */
    protected boolean isFixedLength;

    /**
     * Whether the list represented by this configuration property has a maximum
     * length.
     *
     * @implNote Mutually exclusive with {@link ListProperty#isFixedLength
     *           isFixedLength}
     */
    protected boolean hasMaxLength;

    /**
     * The maximum length the list represented by this configuration property
     * can have.
     */
    protected int maxListLength;

    // #endregion Protected Fields

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
        super(name, type, TypeHelpers.castToNonNull(Arrays.asList(
            initialValues)), Collections.emptyList(), null, null, false, false,
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
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     */
    public ListProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T[] initialValues, @Nullable T[] defaultValues) {
        super(name, type,
            TypeHelpers.castToNonNull(Arrays.asList(initialValues)),
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
        super(name, type,
            TypeHelpers.castToNonNull(Arrays.asList(initialValues)),
            Collections.emptyList(), languageKey, null, false, false, false);
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
        super(name, type,
            TypeHelpers.castToNonNull(Arrays.asList(initialValues)),
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
        super(name, type,
            TypeHelpers.castToNonNull(Arrays.asList(initialValues)),
            Collections.emptyList(), languageKey, comment, false, false, false);
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
        super(name, type,
            TypeHelpers.castToNonNull(Arrays.asList(initialValues)),
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
        super(name, type,
            TypeHelpers.castToNonNull(Arrays.asList(initialValues)),
            Collections.emptyList(), languageKey, comment, hidden, false,
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
        super(name, type,
            TypeHelpers.castToNonNull(Arrays.asList(initialValues)),
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
        super(name, type,
            TypeHelpers.castToNonNull(Arrays.asList(initialValues)),
            Collections.emptyList(), languageKey, comment, hidden,
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
     */
    public ListProperty(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T[] initialValues, @Nullable T[] defaultValues,
        @Nullable String languageKey, @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart) {
        super(name, type,
            TypeHelpers.castToNonNull(Arrays.asList(initialValues)),
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
        super(name, type,
            TypeHelpers.castToNonNull(Arrays.asList(initialValues)),
            Arrays.asList(defaultValues), languageKey, comment, hidden,
            requiresWorldRestart, requiresGameRestart);
    }

    // #endregion Constructors

    // #region Public Methods

    /**
     * Get whether the list represented by this configuration property is of a
     * fixed length.
     *
     * @return Whether the list represented by this configuration property is of
     *         a fixed length.
     */
    public boolean isFixedLength() {
        return this.isFixedLength;
    }

    /**
     * Set whether the list represented by this configuration property is of a
     * fixed length.
     *
     * @param value Whether the list represented by this configuration property
     *        is of a fixed length.
     */
    public void setIsFixedLength(boolean value) {
        this.isFixedLength = value;
        if (value) {
            this.hasMaxLength = false;
        }
    }

    /**
     * Get whether the list represented by this configuration property has a
     * maximum length.
     *
     * @return Whether the list represented by this configuration property has a
     *         maximum length.
     */
    public boolean hasMaxLength() {
        return this.hasMaxLength;
    }

    /**
     * Set whether the list represented by this configuration property has a
     * maximum length.
     *
     * @param value Whether the list represented by this configuration property
     *        has a maximum length.
     */
    public void setHasMaxLength(boolean value) {
        this.hasMaxLength = value;
        if (value) {
            this.isFixedLength = false;
        }
    }

    // #endregion Public Methods
}
