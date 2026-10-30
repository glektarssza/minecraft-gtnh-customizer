package com.glektarssza.gtnh_customizer.api.exceptions.args;

import com.glektarssza.gtnh_customizer.api.annotations.NotNull;
import com.glektarssza.gtnh_customizer.api.annotations.Nullable;
import com.glektarssza.gtnh_customizer.api.exceptions.BaseRuntimeException;

/**
 * An exception that is thrown when an argument is invalid.
 */
public class ArgumentException extends BaseRuntimeException {
    // #region Public Fields

    /**
     * The name of the argument that was invalid.
     */
    @NotNull
    public final String argumentName;

    // #endregion Public Fields

    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     */
    public ArgumentException(@NotNull final String argumentName) {
        super();
        this.argumentName = argumentName;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param msg A string describing the error that occurred.
     */
    public ArgumentException(@NotNull final String argumentName,
        @Nullable final String msg) {
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
    public ArgumentException(@NotNull final String argumentName,
        @Nullable final Throwable cause) {
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
    public ArgumentException(@NotNull final String argumentName,
        @Nullable final String msg, @Nullable final Throwable cause) {
        super(msg, cause);
        this.argumentName = argumentName;
    }

    // #endregion Constructors
}
