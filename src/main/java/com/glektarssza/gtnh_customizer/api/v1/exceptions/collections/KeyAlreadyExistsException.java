package com.glektarssza.gtnh_customizer.api.v1.exceptions.collections;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.glektarssza.gtnh_customizer.api.v1.exceptions.BaseRuntimeException;

/**
 * An exception that is thrown when a value is being inserted into a map but
 * there is already an entry for the requested key.
 */
public class KeyAlreadyExistsException extends BaseRuntimeException {
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
     * @param argKey The key that was being used for the map insertion.
     */
    public KeyAlreadyExistsException(@Nonnull final Object argKey) {
        this.key = argKey;
    }

    /**
     * Create a new instance.
     *
     * @param argKey The key that was being used for the map insertion.
     * @param msg A string describing the error that occurred.
     */
    public KeyAlreadyExistsException(@Nonnull final Object argKey,
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
    public KeyAlreadyExistsException(@Nonnull final Object argKey,
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
    public KeyAlreadyExistsException(@Nonnull final Object argKey,
        @Nullable final String msg,
        @Nullable final Throwable cause) {
        super(msg, cause);
        this.key = argKey;
    }

    // #endregion Constructors
}
