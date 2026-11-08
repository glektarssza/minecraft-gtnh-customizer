package com.glektarssza.gtnh_customizer.api.v1.exceptions;

import javax.annotation.Nullable;

/**
 * An exception that is thrown when something is in an invalid state for the
 * requested operation.
 */
public class InvalidStateException extends BaseRuntimeException {
    // #region Constructors

    /**
     * Create a new instance.
     */
    public InvalidStateException() {
        // -- Does nothing
    }

    /**
     * Create a new instance.
     *
     * @param msg A string describing the error that occurred.
     */
    public InvalidStateException(@Nullable final String msg) {
        super(msg);
    }

    /**
     * Create a new instance.
     *
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public InvalidStateException(@Nullable final Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public InvalidStateException(@Nullable final String msg,
        @Nullable final Throwable cause) {
        super(msg, cause);
    }

    // #endregion Constructors
}
