package com.glektarssza.gtnh_customizer.api.exceptions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * An exception that is thrown when an argument is invalid.
 */
public class InvalidArgumentException extends BaseRuntimeException {
    // #region Public Fields

    /**
     * The name of the argument that was invalid.
     */
    @Nonnull
    public final String argumentName;

    // #endregion Public Fields

    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     */
    public InvalidArgumentException(@Nonnull String argumentName) {
        super();
        this.argumentName = argumentName;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param msg A string describing the error that occurred.
     */
    public InvalidArgumentException(@Nonnull String argumentName,
        @Nullable String msg) {
        super(msg);
        this.argumentName = argumentName;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public InvalidArgumentException(@Nonnull String argumentName,
        @Nullable Throwable cause) {
        super(cause);
        this.argumentName = argumentName;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public InvalidArgumentException(@Nonnull String argumentName,
        @Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
        this.argumentName = argumentName;
    }

    // #endregion Constructors
}
