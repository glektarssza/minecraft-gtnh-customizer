package com.glektarssza.gtnh_customizer.api.exceptions.args;

import com.glektarssza.gtnh_customizer.api.annotations.NotNull;
import com.glektarssza.gtnh_customizer.api.annotations.Nullable;

/**
 * An exception that is thrown when an argument is invalid because its value was
 * outside of the allowed range.
 */
public class ArgumentOutOfRangeException extends ArgumentException {
    // #region Public Fields

    /**
     * The value of the argument that was outside of the allowed range.
     */
    @Nullable
    public final Number argumentValue;

    /**
     * The minimum allowed value of the argument that was outside of the allowed
     * range.
     */
    @Nullable
    public final Number argumentMinimumValue;

    /**
     * The maximum allowed value of the argument that was outside of the allowed
     * range.
     */
    @Nullable
    public final Number argumentMaximumValue;

    // #endregion Public Fields

    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName) {
        super(argumentName);
        this.argumentValue = null;
        this.argumentMinimumValue = null;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param msg A string describing the error that occurred.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final String msg) {
        super(argumentName, msg);
        this.argumentValue = null;
        this.argumentMinimumValue = null;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final Throwable cause) {
        super(argumentName, cause);
        this.argumentValue = null;
        this.argumentMinimumValue = null;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final String msg, @Nullable final Throwable cause) {
        super(argumentName, msg, cause);
        this.argumentValue = null;
        this.argumentMinimumValue = null;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argumentValue The value of the argument that was invalid.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final Number argumentValue) {
        super(argumentName);
        this.argumentValue = argumentValue;
        this.argumentMinimumValue = null;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argumentValue The value of the argument that was invalid.
     * @param msg A string describing the error that occurred.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final Number argumentValue, @Nullable final String msg) {
        super(argumentName, msg);
        this.argumentValue = argumentValue;
        this.argumentMinimumValue = null;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argumentValue The value of the argument that was invalid.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final Number argumentValue, @Nullable final Throwable cause) {
        super(argumentName, cause);
        this.argumentValue = argumentValue;
        this.argumentMinimumValue = null;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argumentValue The value of the argument that was invalid.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final Number argumentValue, @Nullable final String msg,
        @Nullable final Throwable cause) {
        super(argumentName, msg, cause);
        this.argumentValue = argumentValue;
        this.argumentMinimumValue = null;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argumentValue The value of the argument that was invalid.
     * @param argumentMinimumValue The minimum allowed value of the value that
     *        was outside of the allowed range.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final Number argumentValue,
        @Nullable final Number argumentMinimumValue) {
        super(argumentName);
        this.argumentValue = argumentValue;
        this.argumentMinimumValue = argumentMinimumValue;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argumentValue The value of the argument that was invalid.
     * @param argumentMinimumValue The minimum allowed value of the value that
     *        was outside of the allowed range.
     * @param msg A string describing the error that occurred.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final Number argumentValue,
        @Nullable final Number argumentMinimumValue,
        @Nullable final String msg) {
        super(argumentName, msg);
        this.argumentValue = argumentValue;
        this.argumentMinimumValue = argumentMinimumValue;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argumentValue The value of the argument that was invalid.
     * @param argumentMinimumValue The minimum allowed value of the value that
     *        was outside of the allowed range.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final Number argumentValue,
        @Nullable final Number argumentMinimumValue,
        @Nullable final Throwable cause) {
        super(argumentName, cause);
        this.argumentValue = argumentValue;
        this.argumentMinimumValue = argumentMinimumValue;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argumentValue The value of the argument that was invalid.
     * @param argumentMinimumValue The minimum allowed value of the value that
     *        was outside of the allowed range.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final Number argumentValue,
        @Nullable final Number argumentMinimumValue,
        @Nullable final String msg, @Nullable final Throwable cause) {
        super(argumentName, msg, cause);
        this.argumentValue = argumentValue;
        this.argumentMinimumValue = argumentMinimumValue;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argumentValue The value of the argument that was invalid.
     * @param argumentMinimumValue The minimum allowed value of the value that
     *        was outside of the allowed range.
     * @param argumentMaximumValue The maximum allowed value of the value that
     *        was outside of the allowed range.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final Number argumentValue,
        @Nullable final Number argumentMinimumValue,
        @Nullable final Number argumentMaximumValue) {
        super(argumentName);
        this.argumentValue = argumentValue;
        this.argumentMinimumValue = argumentMinimumValue;
        this.argumentMaximumValue = argumentMaximumValue;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argumentValue The value of the argument that was invalid.
     * @param argumentMinimumValue The minimum allowed value of the value that
     *        was outside of the allowed range.
     * @param argumentMaximumValue The maximum allowed value of the value that
     *        was outside of the allowed range.
     * @param msg A string describing the error that occurred.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final Number argumentValue,
        @Nullable final Number argumentMinimumValue,
        @Nullable final Number argumentMaximumValue,
        @Nullable final String msg) {
        super(argumentName, msg);
        this.argumentValue = argumentValue;
        this.argumentMinimumValue = argumentMinimumValue;
        this.argumentMaximumValue = argumentMaximumValue;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argumentValue The value of the argument that was invalid.
     * @param argumentMinimumValue The minimum allowed value of the value that
     *        was outside of the allowed range.
     * @param argumentMaximumValue The maximum allowed value of the value that
     *        was outside of the allowed range.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final Number argumentValue,
        @Nullable final Number argumentMinimumValue,
        @Nullable final Number argumentMaximumValue,
        @Nullable final Throwable cause) {
        super(argumentName, cause);
        this.argumentValue = argumentValue;
        this.argumentMinimumValue = argumentMinimumValue;
        this.argumentMaximumValue = argumentMaximumValue;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argumentValue The value of the argument that was invalid.
     * @param argumentMinimumValue The minimum allowed value of the value that
     *        was outside of the allowed range.
     * @param argumentMaximumValue The maximum allowed value of the value that
     *        was outside of the allowed range.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@NotNull final String argumentName,
        @Nullable final Number argumentValue,
        @Nullable final Number argumentMinimumValue,
        @Nullable final Number argumentMaximumValue, @Nullable final String msg,
        @Nullable final Throwable cause) {
        super(argumentName, msg, cause);
        this.argumentValue = argumentValue;
        this.argumentMinimumValue = argumentMinimumValue;
        this.argumentMaximumValue = argumentMaximumValue;
    }

    // #endregion Constructors
}
