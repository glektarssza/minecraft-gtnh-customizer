package com.glektarssza.gtnh_customizer.utils.exceptions;

/**
 * An exception that signals a key already exists.
 */
public class KeyAlreadyExistsException extends RuntimeException {

    /**
     * Create a new instance.
     */
    public KeyAlreadyExistsException() {
        super();
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing the exception.
     */
    public KeyAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Create a new instance.
     *
     * @param cause The throwable that caused the new instance to be created.
     */
    public KeyAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing the exception.
     * @param cause The throwable that caused the new instance to be created.
     */
    public KeyAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing the exception.
     * @param cause The throwable that caused the new instance to be created.
     * @param enableSuppression
     * @param writeableStackTrace
     */
    public KeyAlreadyExistsException(String m, Throwable t,
        boolean enableSuppression,
        boolean writeableStackTrace) {
        super(m, t, enableSuppression, writeableStackTrace);
    }
}
