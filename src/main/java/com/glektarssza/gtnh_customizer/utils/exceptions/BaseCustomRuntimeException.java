package com.glektarssza.gtnh_customizer.utils.exceptions;

import javax.annotation.Nullable;

/**
 * A base type for custom runtime exceptions to extend from.
 */
public class BaseCustomRuntimeException extends RuntimeException {
    /**
     * Create a new instance.
     */
    public BaseCustomRuntimeException() {
        super();
    }

    /**
     * Create a new instance.
     *
     * @param message An optional string describing the nature of the error that
     *        caused the new instance to be created. Can be {@code null} if no
     *        description is available.
     */
    public BaseCustomRuntimeException(@Nullable String message) {
        super(message);
    }

    /**
     * Create a new instance.
     *
     * @param cause The {@link Throwable} that caused the newly created instance
     *        to occur.
     */
    public BaseCustomRuntimeException(@Nullable Throwable cause) {
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
    public BaseCustomRuntimeException(@Nullable String message,
        @Nullable Throwable cause) {
        super(message, cause);
    }
}
