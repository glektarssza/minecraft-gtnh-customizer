package com.glektarssza.gtnh_customizer.api.exceptions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * An exception that is thrown when an argument is invalid due to being a
 * {@code null} value.
 */
public class ArgumentNullException extends InvalidArgumentException {
    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that had a {@code null}
     *        value.
     */
    public ArgumentNullException(@Nonnull String argumentName) {
        super(argumentName);
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that had a {@code null}
     *        value.
     * @param msg A string describing the reason the argument had a {@code null}
     *        value (ideally) or describing the reason the error occurred
     *        otherwise.
     */
    public ArgumentNullException(@Nonnull String argumentName,
        @Nullable String msg) {
        super(argumentName, msg);
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that had a {@code null}
     *        value.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentNullException(@Nonnull String argumentName,
        @Nullable Throwable cause) {
        super(argumentName, cause);
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that had a {@code null}
     *        value.
     * @param msg A string describing the reason the argument had a {@code null}
     *        value (ideally) or describing the reason the error occurred
     *        otherwise.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentNullException(@Nonnull String argumentName,
        @Nullable String msg, @Nullable Throwable cause) {
        super(argumentName, msg, cause);
    }

    // #endregion Constructors
}
