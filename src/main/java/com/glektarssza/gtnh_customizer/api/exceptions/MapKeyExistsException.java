package com.glektarssza.gtnh_customizer.api.exceptions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * An exception that is thrown when a value being inserted into a map but there
 * is already an entry for the requested key.
 */
public class MapKeyExistsException extends BaseRuntimeException {
    // #region Public Fields

    /**
     * The key that was being used for the map insertion.
     */
    @Nonnull
    public final String key;

    // #endregion Public Fields

    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param key The key that was being used for the map insertion.
     */
    public MapKeyExistsException(@Nonnull String key) {
        super();
        this.key = key;
    }

    /**
     * Create a new instance.
     *
     * @param key The key that was being used for the map insertion.
     * @param msg A string describing the error that occurred.
     */
    public MapKeyExistsException(@Nonnull String key, @Nullable String msg) {
        super(msg);
        this.key = key;
    }

    /**
     * Create a new instance.
     *
     * @param key The key that was being used for the map insertion.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public MapKeyExistsException(@Nonnull String key,
        @Nullable Throwable cause) {
        super(cause);
        this.key = key;
    }

    /**
     * Create a new instance.
     *
     * @param key The key that was being used for the map insertion.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public MapKeyExistsException(@Nonnull String key, @Nullable String msg,
        @Nullable Throwable cause) {
        super(msg, cause);
        this.key = key;
    }

    // #endregion Constructors
}
