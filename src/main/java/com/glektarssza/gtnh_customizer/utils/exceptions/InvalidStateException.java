package com.glektarssza.gtnh_customizer.utils.exceptions;

import javax.annotation.Nullable;

/**
 * An exception that is produced when something is in an invalid state for the
 * requested operation.
 */
public class InvalidStateException extends BaseRuntimeException {
    /**
     * Create a new exception.
     */
    public InvalidStateException() {
        super();
    }

    /**
     * Create a new instance.
     *
     * @param message The message describing the nature of the exception.
     */
    public InvalidStateException(@Nullable String message) {
        super(message);
    }

    /**
     * Create a new instance.
     *
     * @param cause The throwable that is the cause of the exception.
     */
    public InvalidStateException(@Nullable Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param message The message describing the nature of the exception.
     * @param cause The throwable that is the cause of the exception.
     */
    public InvalidStateException(@Nullable String message,
        @Nullable Throwable cause) {
        super(message, cause);
    }
}
