package com.glektarssza.gtnh_customizer.api.exceptions;

/**
 * A base custom runtime exception from which other custom runtime exceptions
 * can extend from.
 */
public class BaseRuntimeException extends RuntimeException {
    // #region Constructors

    /**
     * Create a new instance.
     */
    public BaseRuntimeException() {
        super();
    }

    /**
     * Create a new instance.
     *
     * @param msg A string describing the error that occurred.
     */
    public BaseRuntimeException(String msg) {
        super(msg);
    }

    /**
     * Create a new instance.
     *
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public BaseRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    // #endregion Constructors
}
