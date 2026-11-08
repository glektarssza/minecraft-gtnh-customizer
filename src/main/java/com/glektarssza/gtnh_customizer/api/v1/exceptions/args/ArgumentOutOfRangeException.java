package com.glektarssza.gtnh_customizer.api.v1.exceptions.args;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
    public ArgumentOutOfRangeException(@Nonnull final String argumentName) {
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
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
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
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
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
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
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
     * @param argValue The value of the argument that was invalid.
     */
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
        @Nullable final Number argValue) {
        super(argumentName);
        this.argumentValue = argValue;
        this.argumentMinimumValue = null;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argValue The value of the argument that was invalid.
     * @param msg A string describing the error that occurred.
     */
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
        @Nullable final Number argValue, @Nullable final String msg) {
        super(argumentName, msg);
        this.argumentValue = argValue;
        this.argumentMinimumValue = null;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argValue The value of the argument that was invalid.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
        @Nullable final Number argValue, @Nullable final Throwable cause) {
        super(argumentName, cause);
        this.argumentValue = argValue;
        this.argumentMinimumValue = null;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argValue The value of the argument that was invalid.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
        @Nullable final Number argValue, @Nullable final String msg,
        @Nullable final Throwable cause) {
        super(argumentName, msg, cause);
        this.argumentValue = argValue;
        this.argumentMinimumValue = null;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argValue The value of the argument that was invalid.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     */
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
        @Nullable final Number argValue,
        @Nullable final Number argMinimumValue) {
        super(argumentName);
        this.argumentValue = argValue;
        this.argumentMinimumValue = argMinimumValue;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argValue The value of the argument that was invalid.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     */
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
        @Nullable final Number argValue,
        @Nullable final Number argMinimumValue,
        @Nullable final String msg) {
        super(argumentName, msg);
        this.argumentValue = argValue;
        this.argumentMinimumValue = argMinimumValue;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argValue The value of the argument that was invalid.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
        @Nullable final Number argValue,
        @Nullable final Number argMinimumValue,
        @Nullable final Throwable cause) {
        super(argumentName, cause);
        this.argumentValue = argValue;
        this.argumentMinimumValue = argMinimumValue;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argValue The value of the argument that was invalid.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
        @Nullable final Number argValue,
        @Nullable final Number argMinimumValue,
        @Nullable final String msg,
        @Nullable final Throwable cause) {
        super(argumentName, msg, cause);
        this.argumentValue = argValue;
        this.argumentMinimumValue = argMinimumValue;
        this.argumentMaximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argValue The value of the argument that was invalid.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param argMaximumValue The maximum allowed value of the value that was
     *        outside of the allowed range.
     */
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
        @Nullable final Number argValue,
        @Nullable final Number argMinimumValue,
        @Nullable final Number argMaximumValue) {
        super(argumentName);
        this.argumentValue = argValue;
        this.argumentMinimumValue = argMinimumValue;
        this.argumentMaximumValue = argMaximumValue;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argValue The value of the argument that was invalid.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param argMaximumValue The maximum allowed value of the value that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     */
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
        @Nullable final Number argValue,
        @Nullable final Number argMinimumValue,
        @Nullable final Number argMaximumValue,
        @Nullable final String msg) {
        super(argumentName, msg);
        this.argumentValue = argValue;
        this.argumentMinimumValue = argMinimumValue;
        this.argumentMaximumValue = argMaximumValue;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argValue The value of the argument that was invalid.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param argMaximumValue The maximum allowed value of the value that was
     *        outside of the allowed range.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
        @Nullable final Number argValue,
        @Nullable final Number argMinimumValue,
        @Nullable final Number argMaximumValue,
        @Nullable final Throwable cause) {
        super(argumentName, cause);
        this.argumentValue = argValue;
        this.argumentMinimumValue = argMinimumValue;
        this.argumentMaximumValue = argMaximumValue;
    }

    /**
     * Create a new instance.
     *
     * @param argumentName The name of the argument that was invalid.
     * @param argValue The value of the argument that was invalid.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param argMaximumValue The maximum allowed value of the value that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@Nonnull final String argumentName,
        @Nullable final Number argValue,
        @Nullable final Number argMinimumValue,
        @Nullable final Number argMaximumValue,
        @Nullable final String msg,
        @Nullable final Throwable cause) {
        super(argumentName, msg, cause);
        this.argumentValue = argValue;
        this.argumentMinimumValue = argMinimumValue;
        this.argumentMaximumValue = argMaximumValue;
    }

    // #endregion Constructors
}
