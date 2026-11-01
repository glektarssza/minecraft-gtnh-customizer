package com.glektarssza.gtnh_customizer.api.exceptions.collections;

import com.glektarssza.gtnh_customizer.api.annotations.NotNull;
import com.glektarssza.gtnh_customizer.api.annotations.Nullable;
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
    @NotNull
    public final Object key;

    // #endregion Public Fields

    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param key The key that was being used for the map insertion.
     */
    public NoSuchKeyException(@NotNull final Object key) {
        super();
        this.key = key;
    }

    /**
     * Create a new instance.
     *
     * @param key The key that was being used for the map insertion.
     * @param msg A string describing the error that occurred.
     */
    public NoSuchKeyException(@NotNull final Object key,
        @Nullable final String msg) {
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
    public NoSuchKeyException(@NotNull final Object key,
        @Nullable final Throwable cause) {
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
    public NoSuchKeyException(@NotNull final Object key,
        @Nullable final String msg,
        @Nullable final Throwable cause) {
        super(msg, cause);
        this.key = key;
    }

    // #endregion Constructors
}
