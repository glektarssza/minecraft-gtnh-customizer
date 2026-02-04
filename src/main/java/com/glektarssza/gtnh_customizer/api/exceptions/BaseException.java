package com.glektarssza.gtnh_customizer.api.exceptions;

/**
 * A base custom exception from which other custom exceptions can extend from.
 */
public class BaseException extends Exception {
    // #region Constructors

    /**
     * Create a new instance.
     */
    public BaseException() {
        super();
    }

    /**
     * Create a new instance.
     *
     * @param msg A string describing the error that occurred.
     */
    public BaseException(String msg) {
        super(msg);
    }

    /**
     * Create a new instance.
     *
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public BaseException(Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
    }

    // #endregion Constructors
}
