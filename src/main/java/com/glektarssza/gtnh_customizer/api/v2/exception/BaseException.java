package com.glektarssza.gtnh_customizer.api.v2.exception;

/**
 * A basic exception which other custom exceptions can extend off of.
 */
public abstract class BaseException extends Exception {
    // #region Private Static Fields

    /**
     * The serialization version UID.
     */
    private static final long serialVersionUID = 2026_11_12_001L;

    // #endregion

    // #region Constructors

    /**
     * Create a new instance.
     */
    protected BaseException() {
        // -- Does nothing.
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing what went wrong.
     */
    protected BaseException(final String message) {
        super(message);
    }

    /**
     * Create a new instance.
     *
     * @param cause The exception that triggered the new instance to be created.
     */
    protected BaseException(final Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    protected BaseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    // #endregion
}
