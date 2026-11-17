package com.glektarssza.gtnh_customizer.api.v2.exception.collection;

import com.glektarssza.gtnh_customizer.api.v2.annotation.NotNull;
import com.glektarssza.gtnh_customizer.api.v2.annotation.Nullable;
import com.glektarssza.gtnh_customizer.api.v2.exception.BaseRuntimeException;

/**
 * An exception that indicates there is no such entry in a map for the requested
 * key.
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
     * @param argKey The key that was being used for the map insertion.
     */
    public NoSuchKeyException(@NotNull final Object argKey) {
        this.key = argKey;
    }

    /**
     * Create a new instance.
     *
     * @param argKey The key that was being used for the map insertion.
     * @param msg A string describing the error that occurred.
     */
    public NoSuchKeyException(@NotNull final Object argKey,
        @Nullable final String msg) {
        super(msg);
        this.key = argKey;
    }

    /**
     * Create a new instance.
     *
     * @param argKey The key that was being used for the map insertion.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public NoSuchKeyException(@NotNull final Object argKey,
        @Nullable final Throwable cause) {
        super(cause);
        this.key = argKey;
    }

    /**
     * Create a new instance.
     *
     * @param argKey The key that was being used for the map insertion.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public NoSuchKeyException(
        @NotNull final Object argKey,
        @Nullable final String msg,
        @Nullable final Throwable cause) {
        super(msg, cause);
        this.key = argKey;
    }

    // #endregion Constructors
}
