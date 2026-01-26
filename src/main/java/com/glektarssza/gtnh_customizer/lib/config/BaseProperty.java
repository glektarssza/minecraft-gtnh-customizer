package com.glektarssza.gtnh_customizer.lib.config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A base configuration property type.
 */
public abstract class BaseProperty<T> {
    // #region Protected Fields

    /**
     * The comment about the configuration property represented by the instance.
     */
    @Nullable
    protected String propertyComment;

    /**
     * The translation key to use when showing the configuration property
     * represented by this in the game.
     */
    @Nullable
    protected String propertyLanguageKey;

    /**
     * Whether the configuration property represented by this is hidden from the
     * configuration GUI.
     */
    protected boolean propertyHidden;

    /**
     * Whether the configuration property represented by this requires the world
     * to be restarted when changed.
     */
    protected boolean propertyRequiresWorldRestart;

    /**
     * Whether the configuration property represented by this requires the game
     * to be restarted when changed.
     */
    protected boolean propertyRequiresGameRestart;

    /**
     * Whether this the configuration property represented by this instance has
     * been changed but not yet written to disk.
     */
    protected boolean propertyIsDirty;

    // #endregion Protected Fields

    // #region Public Fields

    /**
     * The name of the configuration property represented by the instance.
     */
    @Nonnull
    public final String propertyName;

    /**
     * The type of the configuration property represented by the instance.
     */
    @Nonnull
    public final PropertyType propertyType;

    // #endregion Public Fields

    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param propertyName The name of the configuration property represented by
     *        the new instance.
     * @param propertyType The type of the configuration property represented by
     *        the new instance.
     */
    protected BaseProperty(@Nonnull String propertyName,
        @Nonnull PropertyType propertyType) {
        this(propertyName, propertyType, null, null);
    }

    /**
     * Create a new instance.
     *
     * @param propertyName The name of the configuration property represented by
     *        the new instance.
     * @param propertyType The type of the configuration property represented by
     *        the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     */
    protected BaseProperty(@Nonnull String propertyName,
        @Nonnull PropertyType propertyType, @Nullable String languageKey) {
        this(propertyName, propertyType, languageKey, null);
    }

    /**
     * Create a new instance.
     *
     * @param propertyName The name of the configuration property represented by
     *        the new instance.
     * @param propertyType The type of the configuration property represented by
     *        the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param propertyComment The comment of the configuration property
     *        represented by the new instance.
     */
    protected BaseProperty(@Nonnull String propertyName,
        @Nonnull PropertyType propertyType, @Nullable String languageKey,
        @Nullable String propertyComment) {
        this(propertyName, propertyType, languageKey, propertyComment, false,
            false, false);
    }

    /**
     * Create a new instance.
     *
     * @param propertyName The name of the configuration property represented by
     *        the new instance.
     * @param propertyType The type of the configuration property represented by
     *        the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param propertyComment The comment of the configuration property
     *        represented by the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     */
    protected BaseProperty(@Nonnull String propertyName,
        @Nonnull PropertyType propertyType, @Nullable String languageKey,
        @Nullable String propertyComment, boolean hidden) {
        this(propertyName, propertyType, languageKey, propertyComment,
            hidden, false, false);
    }

    /**
     * Create a new instance.
     *
     * @param propertyName The name of the configuration property represented by
     *        the new instance.
     * @param propertyType The type of the configuration property represented by
     *        the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param propertyComment The comment of the configuration property
     *        represented by the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the world to be
     *        restarted when changed.
     */
    protected BaseProperty(@Nonnull String propertyName,
        @Nonnull PropertyType propertyType, @Nullable String languageKey,
        @Nullable String propertyComment, boolean hidden,
        boolean requiresWorldRestart) {
        this(propertyName, propertyType, languageKey, propertyComment,
            hidden, requiresWorldRestart, false);
    }

    /**
     * Create a new instance.
     *
     * @param propertyName The name of the configuration property represented by
     *        the new instance.
     * @param propertyType The type of the configuration property represented by
     *        the new instance.
     * @param languageKey The translation key to be used when showing the new
     *        configuration property represented by the new instance in the
     *        game.
     * @param propertyComment The comment of the configuration property
     *        represented by the new instance.
     * @param hidden Whether the configuration property represented by the new
     *        instance will be hidden from the configuration GUI.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the world to be
     *        restarted when changed.
     * @param requiresWorldRestart Whether the configuration property
     *        represented by the new instance will be require the game to be
     *        restarted when changed.
     */
    protected BaseProperty(@Nonnull String propertyName,
        @Nonnull PropertyType propertyType, @Nullable String languageKey,
        @Nullable String propertyComment, boolean hidden,
        boolean requiresWorldRestart, boolean requiresGameRestart) {
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.propertyComment = propertyComment;
        this.propertyLanguageKey = languageKey;
        this.propertyHidden = hidden;
        this.propertyRequiresWorldRestart = requiresWorldRestart;
        this.propertyRequiresGameRestart = requiresGameRestart;
        this.propertyIsDirty = false;
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
    public String getPropertyComment() {
        return this.propertyComment;
    }

    /**
     * Set the comment for the configuration property represented by this
     * instance.
     *
     * @param comment The comment for the configuration property represented by
     *        this instance.
     */
    public void setPropertyComment(@Nullable String comment) {
        this.propertyComment = comment;
    }

    /**
     * Get the translation key for the configuration property represented by
     * this instance.
     *
     * @return The translation key for the configuration property represented by
     *         this instance.
     */
    @Nullable
    public String getPropertyLanguageKey() {
        return this.propertyLanguageKey;
    }

    /**
     * Set the translation key for the configuration property represented by
     * this instance.
     *
     * @param languageKey The translation key for the configuration property
     *        represented by this instance.
     */
    public void setPropertyLanguageKey(@Nullable String languageKey) {
        this.propertyLanguageKey = languageKey;
    }

    /**
     * Get whether the configuration property represented by this instance is
     * hidden from the configuration GUI.
     *
     * @return Whether the configuration property represented by this is hidden
     *         from the configuration GUI.
     */
    public boolean getPropertyHidden() {
        return this.propertyHidden;
    }

    /**
     * Set whether the configuration property represented by this instance is
     * hidden from the configuration GUI.
     *
     * @param hidden Whether the configuration property represented by this is
     *        hidden from the configuration GUI.
     */
    public void setPropertyHidden(boolean hidden) {
        this.propertyHidden = hidden;
    }

    /**
     * Get whether the configuration property represented by this instance
     * requires the world to be restarted when changed.
     *
     * @return Whether the configuration property represented by this requires
     *         the world to be restarted when changed.
     */
    public boolean getPropertyRequiresWorldRestart() {
        return this.propertyRequiresWorldRestart;
    }

    /**
     * Set whether the configuration property represented by this instance
     * requires the world to be restarted when changed.
     *
     * @param requiresWorldRestart Whether the configuration property
     *        represented by this requires the world to be restarted when
     *        changed.
     */
    public void setPropertyRequiresWorldRestart(boolean requiresWorldRestart) {
        this.propertyRequiresWorldRestart = requiresWorldRestart;
    }

    /**
     * Get whether the configuration property represented by this instance
     * requires the game to be restarted when changed.
     *
     * @return Whether the configuration property represented by this requires
     *         the game to be restarted when changed.
     */
    public boolean getPropertyRequiresGameRestart() {
        return this.propertyRequiresGameRestart;
    }

    /**
     * Set whether the configuration property represented by this instance
     * requires the game to be restarted when changed.
     *
     * @param requiresGameRestart Whether the configuration property represented
     *        by this requires the game to be restarted when changed.
     */
    public void setPropertyRequiresGameRestart(boolean requiresGameRestart) {
        this.propertyRequiresGameRestart = requiresGameRestart;
    }

    /**
     * Check whether this the configuration property represented by this
     * instance has been changed but not yet written to disk.
     *
     * @return Whether this the configuration property represented by this
     *         instance has been changed but not yet written to disk.
     */
    public boolean isDirty() {
        return this.propertyIsDirty;
    }

    // #endregion Public Methods

    // #region Overrides

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + propertyName.hashCode();
        result = 31 * result + propertyType.hashCode();
        result = 31 * result +
            (propertyComment == null ? 0
                : propertyComment.hashCode());
        result = 31 * result +
            (propertyLanguageKey == null ? 0
                : propertyLanguageKey.hashCode());
        result = 31 * result + (this.propertyRequiresWorldRestart ? 1 : 0);
        result = 31 * result + (this.propertyRequiresGameRestart ? 1 : 0);
        result = 31 * result + (this.propertyHidden ? 1 : 0);
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BaseProperty)) {
            return false;
        }
        BaseProperty<?> lhs = (BaseProperty<?>) obj;
        return this.propertyName.equals(lhs.propertyName)
            && this.propertyType.equals(lhs.propertyType)
            && (this.propertyComment == null ? lhs.propertyComment == null
                : this.propertyComment.equals(lhs.propertyComment))
            && this.propertyRequiresWorldRestart == lhs.propertyRequiresWorldRestart
            && this.propertyRequiresGameRestart == lhs.propertyRequiresGameRestart
            && this.propertyHidden == lhs.propertyHidden;
    }

    // #endregion Overrides
}
