package com.glektarssza.gtnh_customizer.utils.exceptions;

import javax.annotation.Nullable;

/**
 * A base type for custom exceptions to extend from.
 */
public class BaseCustomException extends Exception {
    /**
     * Create a new instance.
     */
    public BaseCustomException() {
        super();
    }

    /**
     * Create a new instance.
     *
     * @param message An optional string describing the nature of the error that
     *        caused the new instance to be created. Can be {@code null} if no
     *        description is available.
     */
    public BaseCustomException(@Nullable String message) {
        super(message);
    }

    /**
     * Create a new instance.
     *
     * @param cause The {@link Throwable} that caused the newly created instance
     *        to occur.
     */
    public BaseCustomException(@Nullable Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param message An optional string describing the nature of the error that
     *        caused the new instance to be created. Can be {@code null} if no
     *        description is available.
     * @param cause The {@link Throwable} that caused the newly created instance
     *        to occur.
     */
    public BaseCustomException(@Nullable String message,
        @Nullable Throwable cause) {
        super(message, cause);
    }
}
