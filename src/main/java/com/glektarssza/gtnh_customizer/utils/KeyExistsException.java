package com.glektarssza.gtnh_customizer.utils;

/**
 * An exception that signals a key already exists.
 */
public class KeyExistsException extends RuntimeException {

    /**
     * Create a new instance.
     */
    public KeyExistsException() {
        super();
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing the exception.
     */
    public KeyExistsException(String message) {
        super(message);
    }

    /**
     * Create a new instance.
     *
     * @param cause The throwable that caused the new instance to be created.
     */
    public KeyExistsException(Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing the exception.
     * @param cause The throwable that caused the new instance to be created.
     */
    public KeyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing the exception.
     * @param cause The throwable that caused the new instance to be created.
     * @param enableSupression
     * @param writeableStackTrace
     */
    public KeyExistsException(String m, Throwable t, boolean enableSupression,
        boolean writeableStackTrace) {
        super(m, t, enableSupression, writeableStackTrace);
    }
}
