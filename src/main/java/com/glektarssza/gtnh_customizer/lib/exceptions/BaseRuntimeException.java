package com.glektarssza.gtnh_customizer.lib.exceptions;

import javax.annotation.Nullable;

/**
 * A base runtime exception type for other custom runtime exceptions to extend.
 */
public abstract class BaseRuntimeException extends RuntimeException {
    /**
     * Create a new instance.
     */
    public BaseRuntimeException() {
        super();
    }

    /**
     * Create a new instance.
     *
     * @param msg The message describing the nature of error that caused the new
     *        instance to be created.
     */
    public BaseRuntimeException(@Nullable String msg) {
        super(msg);
    }

    /**
     * Create a new instance.
     *
     * @param cause The throwable that caused the new instance to be created.
     */
    public BaseRuntimeException(@Nullable Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param msg The message describing the nature of error that caused the new
     *        instance to be created.
     * @param cause The throwable that caused the new instance to be created.
     */
    public BaseRuntimeException(@Nullable String msg,
        @Nullable Throwable cause) {
        super(msg, cause);
    }
}
