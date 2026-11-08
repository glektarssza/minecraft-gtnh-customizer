package com.glektarssza.gtnh_customizer.api.v1.exceptions.args;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.glektarssza.gtnh_customizer.api.v1.exceptions.BaseRuntimeException;

/**
 * An exception that is thrown when an argument is invalid.
 */
public class ArgumentException extends BaseRuntimeException {
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
     * @param argName The name of the argument that was invalid.
     */
    public ArgumentException(@Nonnull final String argName) {
        this.argumentName = argName;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument that was invalid.
     * @param msg A string describing the error that occurred.
     */
    public ArgumentException(@Nonnull final String argName,
        @Nullable final String msg) {
        super(msg);
        this.argumentName = argName;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument that was invalid.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentException(@Nonnull final String argName,
        @Nullable final Throwable cause) {
        super(cause);
        this.argumentName = argName;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument that was invalid.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentException(@Nonnull final String argName,
        @Nullable final String msg, @Nullable final Throwable cause) {
        super(msg, cause);
        this.argumentName = argName;
    }

    // #endregion Constructors
}
