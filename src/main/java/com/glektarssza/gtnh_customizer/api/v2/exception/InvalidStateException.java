package com.glektarssza.gtnh_customizer.api.v2.exception;

/**
 * An exception that indicates some part of the system was in an invalid state
 * for the requested operation.
 */
public class InvalidStateException extends BaseRuntimeException {
    // #region Private Static Fields

    /**
     * The serialization version UID.
     */
    private static final long serialVersionUID = 2026_11_17_001L;

    // #endregion

    // #region Constructors

    /**
     * Create a new instance.
     */
    public InvalidStateException() {
        // -- Does nothing.
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing what went wrong.
     */
    public InvalidStateException(final String message) {
        super(message);
    }

    /**
     * Create a new instance.
     *
     * @param cause The exception that triggered the new instance to be created.
     */
    public InvalidStateException(final Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    public InvalidStateException(final String message, final Throwable cause) {
        super(message, cause);
    }

    // #endregion
}
