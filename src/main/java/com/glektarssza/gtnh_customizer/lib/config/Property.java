package com.glektarssza.gtnh_customizer.lib.config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.gui.GuiListExtended;

/**
 * A base configuration property type.
 */
public abstract class Property<T, U extends GuiListExtended.IGuiListEntry> {
    // #region Protected Fields

    /**
     * The comment about the configuration property represented by the instance.
     */
    @Nullable
    protected String comment;

    /**
     * The translation key to use when showing the configuration property
     * represented by this in the game.
     */
    @Nullable
    protected String languageKey;

    /**
     * Whether the configuration property represented by this is hidden from the
     * configuration GUI.
     */
    protected boolean hidden;

    /**
     * Whether the configuration property represented by this requires the world
     * to be restarted when changed.
     */
    protected boolean requiresWorldRestart;

    /**
     * Whether the configuration property represented by this requires the game
     * to be restarted when changed.
     */
    protected boolean requiresGameRestart;

    /**
     * Whether this the configuration property represented by this instance has
     * been changed but not yet written to disk.
     */
    protected boolean isDirty;

    /**
     * The value of the configuration property represented by this instance.
     */
    @Nonnull
    protected T value;

    /**
     * The default value of the configuration property represented by this
     * instance.
     */
    @Nullable
    protected T defaultValue;

    /**
     * Get the class that will be used to display the configuration property
     * represented by this instance in the game.
     */
    @Nullable
    protected Class<U> uiDisplayClass;

    // #endregion Protected Fields

    // #region Public Fields

    /**
     * The name of the configuration property represented by the instance.
     */
    @Nonnull
    public final String name;

    /**
     * The type of the configuration property represented by the instance.
     */
    @Nonnull
    public final PropertyType type;

    // #endregion Public Fields

    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param value The value of the configuration property represented by the
     *        new instance.
     */
    protected Property(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T value) {
        this(name, type, value, null, null, null, false, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param value The value of the configuration property represented by the
     *        new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     */
    public Property(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T value, @Nullable T defaultValue) {
        this(name, type, value, defaultValue, null, null, false, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param value The value of the configuration property represented by the
     *        new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     */
    public Property(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T value, @Nullable String languageKey) {
        this(name, type, value, null, languageKey, null, false, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param value The value of the configuration property represented by the
     *        new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     */
    public Property(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T value, @Nullable T defaultValue,
        @Nullable String languageKey) {
        this(name, type, value, defaultValue, languageKey, null, false, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param value The value of the configuration property represented by the
     *        new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     */
    public Property(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T value, @Nullable String languageKey,
        @Nullable String comment) {
        this(name, type, value, null, languageKey, comment, false, false,
            false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param value The value of the configuration property represented by the
     *        new instance.
     * @param defaultValue The default value of the configuration property
     *        represented by the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     */
    public Property(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T value, @Nullable T defaultValue,
        @Nullable String languageKey, @Nullable String comment) {
        this(name, type, value, defaultValue, languageKey, comment, false,
            false, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param value The value of the configuration property represented by the
     *        new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param comment The comment of the configuration property represented by
     *        the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     */
    public Property(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T value, @Nullable String languageKey,
        @Nullable String comment, boolean hidden) {
        this(name, type, value, null, languageKey, comment, hidden, false,
            false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param value The value of the configuration property represented by the
     *        new instance.
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
    public Property(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T value, @Nullable T defaultValue,
        @Nullable String languageKey,
        @Nullable String comment, boolean hidden) {
        this(name, type, value, defaultValue, languageKey, comment, hidden,
            false, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param value The value of the configuration property represented by the
     *        new instance.
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
    public Property(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T value, @Nullable String languageKey,
        @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart) {
        this(name, type, value, null, languageKey, comment, hidden,
            requiresWorldRestart, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param value The value of the configuration property represented by the
     *        new instance.
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
    public Property(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T value, @Nullable T defaultValue,
        @Nullable String languageKey, @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart) {
        this(name, type, value, defaultValue, languageKey, comment, hidden,
            requiresWorldRestart, false);
    }

    /**
     * Create a new instance.
     *
     * @param name The name of the configuration property represented by the new
     *        instance.
     * @param type The type of the configuration property represented by the new
     *        instance.
     * @param value The value of the configuration property represented by the
     *        new instance.
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
    public Property(@Nonnull String name, @Nonnull PropertyType type,
        @Nonnull T value, @Nullable T defaultValue,
        @Nullable String languageKey, @Nullable String comment, boolean hidden,
        boolean requiresWorldRestart, boolean requiresGameRestart) {
        this.name = name;
        this.type = type;
        this.comment = comment;
        this.languageKey = languageKey;
        this.hidden = hidden;
        this.requiresWorldRestart = requiresWorldRestart;
        this.requiresGameRestart = requiresGameRestart;
        this.isDirty = false;
        this.value = value;
        this.defaultValue = defaultValue;
    }

    // #endregion Constructors

    // #region Public Methods

    /**
     * Get the comment for the configuration property represented by this
     * instance.
     *
     * @return The comment for the configuration property represented by this
     *         instance.
     */
    @Nullable
    public String getComment() {
        return this.comment;
    }

    /**
     * Set the comment for the configuration property represented by this
     * instance.
     *
     * @param comment The new comment for the configuration property represented
     *        by this instance.
     */
    public void setComment(@Nullable String comment) {
        this.comment = comment;
    }

    /**
     * Get the translation key for the configuration property represented by
     * this instance.
     *
     * @return The translation key for the configuration property represented by
     *         this instance.
     */
    @Nullable
    public String getLanguageKey() {
        return this.languageKey;
    }

    /**
     * Set the translation key for the configuration property represented by
     * this instance.
     *
     * @param languageKey The new translation key for the configuration property
     *        represented by this instance.
     */
    public void setLanguageKey(@Nullable String languageKey) {
        this.languageKey = languageKey;
    }

    /**
     * Get whether the configuration property represented by this instance is
     * hidden from the configuration GUI.
     *
     * @return Whether the configuration property represented by this is hidden
     *         from the configuration GUI.
     */
    public boolean getHidden() {
        return this.hidden;
    }

    /**
     * Set whether the configuration property represented by this instance is
     * hidden from the configuration GUI.
     *
     * @param hidden Whether the configuration property represented by this is
     *        hidden from the configuration GUI.
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * Get whether the configuration property represented by this instance
     * requires the world to be restarted when changed.
     *
     * @return Whether the configuration property represented by this requires
     *         the world to be restarted when changed.
     */
    public boolean getRequiresWorldRestart() {
        return this.requiresWorldRestart;
    }

    /**
     * Set whether the configuration property represented by this instance
     * requires the world to be restarted when changed.
     *
     * @param requiresWorldRestart Whether the configuration property
     *        represented by this requires the world to be restarted when
     *        changed.
     */
    public void setRequiresWorldRestart(boolean requiresWorldRestart) {
        this.requiresWorldRestart = requiresWorldRestart;
    }

    /**
     * Get whether the configuration property represented by this instance
     * requires the game to be restarted when changed.
     *
     * @return Whether the configuration property represented by this requires
     *         the game to be restarted when changed.
     */
    public boolean getRequiresGameRestart() {
        return this.requiresGameRestart;
    }

    /**
     * Set whether the configuration property represented by this instance
     * requires the game to be restarted when changed.
     *
     * @param requiresGameRestart Whether the configuration property represented
     *        by this requires the game to be restarted when changed.
     */
    public void setRequiresGameRestart(boolean requiresGameRestart) {
        this.requiresGameRestart = requiresGameRestart;
    }

    /**
     * Check whether this the configuration property represented by this
     * instance has been changed but not yet written to disk.
     *
     * @return Whether this the configuration property represented by this
     *         instance has been changed but not yet written to disk.
     */
    public boolean isDirty() {
        return this.isDirty;
    }

    /**
     * Get the value of the configuration property represented by this instance.
     *
     * @return The value of the configuration property represented by this
     *         instance.
     */
    @Nonnull
    public T getValue() {
        return this.value;
    }

    /**
     * Set the value of the configuration property represented by this instance.
     *
     * @param value The new value of the configuration property represented by
     *        this instance.
     */
    public void setValue(@Nonnull T value) {
        this.value = value;
    }

    /**
     * Get the default value of the configuration property represented by this
     * instance.
     *
     * @return The default value of the configuration property represented by
     *         this instance.
     */
    @Nullable
    public T getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * Set the default value of the configuration property represented by this
     * instance.
     *
     * @param defaultValue The new default value of the configuration property
     *        represented by this instance.
     */
    public void setDefaultValue(@Nullable T defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Check if the value of the configuration property represented by this
     * instance is set to the default value.
     *
     * @return {@code true} if the value of the configuration property
     *         represented by this instance is set to the default value,
     *         {@code false} otherwise.
     */
    public boolean isDefaultValue() {
        return this.defaultValue != null ? this.value.equals(this.defaultValue)
            : false;
    }

    /**
     * Reset the value of the configuration property represented by this
     * instance to the default value.
     *
     * @implNote If this instance has no default value configured (the return
     *           value of {@link Property#getDefaultValue() getDefaultValue()}
     *           is {@code null}), this method performs no operation when
     *           called.
     */
    public void resetToDefaultValue() {
        T defaultValue = this.getDefaultValue();
        if (defaultValue != null) {
            this.setValue(defaultValue);
        }
    }

    /**
     * Get the class that will be used to display the configuration property
     * represented by this instance in the game.
     *
     * @return The class that will be used to display the configuration property
     *         represented by this instance in the game.
     */
    @Nullable
    public Class<U> getUIDisplayClass() {
        return this.uiDisplayClass;
    }

    /**
     * Set the class that will be used to display the configuration property
     * represented by this instance in the game.
     *
     * @param uiDisplayClass The new class that will be used to display the
     *        configuration property represented by this instance in the game.
     */
    public void setUiDisplayClass(
        @Nullable Class<U> uiDisplayClass) {
        this.uiDisplayClass = uiDisplayClass;
    }

    // #endregion Public Methods

    // #region Overrides

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.name.hashCode();
        result = 31 * result + this.type.hashCode();
        result = 31 * result +
            (this.comment == null ? 0
                : this.comment.hashCode());
        result = 31 * result +
            (this.languageKey == null ? 0
                : this.languageKey.hashCode());
        result = 31 * result + (this.requiresWorldRestart ? 1 : 0);
        result = 31 * result + (this.requiresGameRestart ? 1 : 0);
        result = 31 * result + (this.hidden ? 1 : 0);
        result = 31 * result + this.value.hashCode();
        result = 31 * result +
            (this.defaultValue == null ? 0
                : this.defaultValue.hashCode());
        return super.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Property)) {
            return false;
        }
        Property<?, ?> lhs = (Property<?, ?>) obj;
        return this.name.equals(lhs.name)
            && this.type.equals(lhs.type)
            && (this.comment == null ? lhs.comment == null
                : this.comment.equals(lhs.comment))
            && this.requiresWorldRestart == lhs.requiresWorldRestart
            && this.requiresGameRestart == lhs.requiresGameRestart
            && this.hidden == lhs.hidden
            && this.value == lhs.value
            && (this.defaultValue == null ? lhs.defaultValue == null
                : this.defaultValue == lhs.defaultValue);
    }

    // #endregion Overrides
}
