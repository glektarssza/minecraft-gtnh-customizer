package com.glektarssza.gtnh_customizer.utils.exceptions;

import javax.annotation.Nullable;

/**
 * A base exception for other custom exceptions to extend off of.
 */
public class BaseException extends Exception {
    /**
     * Create a new exception.
     */
    public BaseException() {
        super();
    }

    /**
     * Create a new instance.
     *
     * @param message The message describing the nature of the exception.
     */
    public BaseException(@Nullable String message) {
        super(message);
    }

    /**
     * Create a new instance.
     *
     * @param cause The throwable that is the cause of the exception.
     */
    public BaseException(@Nullable Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param message The message describing the nature of the exception.
     * @param cause The throwable that is the cause of the exception.
     */
    public BaseException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }
}
