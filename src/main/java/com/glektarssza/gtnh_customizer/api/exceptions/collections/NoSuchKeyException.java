package com.glektarssza.gtnh_customizer.api.exceptions.collections;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.glektarssza.gtnh_customizer.api.exceptions.BaseRuntimeException;

/**
 * An exception that is thrown when a value is being requested from a map but
 * there is no such entry for the requested key.
 */
public class NoSuchKeyException extends BaseRuntimeException {
    // #region Public Fields

    /**
     * The key that was being used for the map insertion.
     */
    @Nonnull
    public final Object key;

    // #endregion Public Fields

    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param key The key that was being used for the map insertion.
     */
    public NoSuchKeyException(@Nonnull Object key) {
        super();
        this.key = key;
    }

    /**
     * Create a new instance.
     *
     * @param key The key that was being used for the map insertion.
     * @param msg A string describing the error that occurred.
     */
    public NoSuchKeyException(@Nonnull Object key,
        @Nullable String msg) {
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
    public NoSuchKeyException(@Nonnull Object key,
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
    public NoSuchKeyException(@Nonnull Object key, @Nullable String msg,
        @Nullable Throwable cause) {
        super(msg, cause);
        this.key = key;
    }

    // #endregion Constructors
}
