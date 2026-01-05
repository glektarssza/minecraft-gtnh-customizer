package com.glektarssza.gtnh_customizer.utils.exceptions;

import javax.annotation.Nullable;

/**
 * A base exception that indicates an argument was invalid.
 */
public class InvalidArgumentException extends BaseRuntimeException {
    /**
     * The name of the argument that was invalid.
     */
    @Nullable
    public final String argumentName;

    /**
     * Create a new instance.
     */
    public InvalidArgumentException() {
        super();
        this.argumentName = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     */
    public InvalidArgumentException(@Nullable String argumentName) {
        super();
        this.argumentName = argumentName;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param message The message describing the nature of the exception.
     */
    public InvalidArgumentException(@Nullable String argumentName,
        @Nullable String message) {
        super(message);
        this.argumentName = argumentName;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param cause The throwable that is the cause of the exception.
     */
    public InvalidArgumentException(@Nullable String argumentName,
        @Nullable Throwable cause) {
        super(cause);
        this.argumentName = argumentName;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param message The message describing the nature of the exception.
     * @param cause The throwable that is the cause of the exception.
     */
    public InvalidArgumentException(@Nullable String argumentName,
        @Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
        this.argumentName = argumentName;
    }
}
