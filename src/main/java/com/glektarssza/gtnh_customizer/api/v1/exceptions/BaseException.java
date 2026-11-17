package com.glektarssza.gtnh_customizer.api.v1.exceptions;

import javax.annotation.Nullable;

/**
 * A base custom exception from which other custom exceptions can extend from.
 *
 * @deprecated Use the v2 API instead.
 */
@Deprecated
public class BaseException extends Exception {
    // #region Constructors

    /**
     * Create a new instance.
     *
     * @deprecated Use the v2 API instead.
     */
    @Deprecated
    public BaseException() {
        // -- Does nothing
    }

    /**
     * Create a new instance.
     *
     * @param msg A string describing the error that occurred.
     *
     * @deprecated Use the v2 API instead.
     */
    @Deprecated
    public BaseException(@Nullable final String msg) {
        super(msg);
    }

    /**
     * Create a new instance.
     *
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     *
     * @deprecated Use the v2 API instead.
     */
    @Deprecated
    public BaseException(@Nullable final Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     *
     * @deprecated Use the v2 API instead.
     */
    @Deprecated
    public BaseException(@Nullable final String msg,
        @Nullable final Throwable cause) {
        super(msg, cause);
    }

    // #endregion Constructors
}
