package com.glektarssza.gtnh_customizer.api.exceptions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * An exception that is thrown when an argument is invalid because its value was
 * outside of the allowed range.
 */
public class ArgumentOutOfRangeException extends InvalidArgumentException {
    // #region Public Fields

    /**
     * The value of the argument that was invalid.
     */
    @Nullable
    public final Number argumentValue;

    /**
     * The minimum allowed value of the argument that was invalid.
     */
    @Nullable
    public final Number argumentMinimumValue;

    /**
     * The maximum allowed value of the argument that was invalid.
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
    public ArgumentOutOfRangeException(@Nonnull String argumentName) {
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
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable String msg) {
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
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable Throwable cause) {
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
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable String msg, @Nullable Throwable cause) {
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
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable Number argumentValue) {
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
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable Number argumentValue, @Nullable String msg) {
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
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable Number argumentValue, @Nullable Throwable cause) {
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
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable Number argumentValue, @Nullable String msg,
        @Nullable Throwable cause) {
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
     * @param argumentMinimumValue The minimum allowed value of the argument
     *        that was invalid.
     */
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable Number argumentValue, @Nullable Number argumentMinimumValue) {
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
     * @param argumentMinimumValue The minimum allowed value of the argument
     *        that was invalid.
     * @param msg A string describing the error that occurred.
     */
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable Number argumentValue, @Nullable Number argumentMinimumValue,
        @Nullable String msg) {
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
     * @param argumentMinimumValue The minimum allowed value of the argument
     *        that was invalid.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable Number argumentValue, @Nullable Number argumentMinimumValue,
        @Nullable Throwable cause) {
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
     * @param argumentMinimumValue The minimum allowed value of the argument
     *        that was invalid.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable Number argumentValue, @Nullable Number argumentMinimumValue,
        @Nullable String msg, @Nullable Throwable cause) {
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
     * @param argumentMinimumValue The minimum allowed value of the argument
     *        that was invalid.
     * @param argumentMaximumValue The maximum allowed value of the argument
     *        that was invalid.
     */
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable Number argumentValue, @Nullable Number argumentMinimumValue,
        @Nullable Number argumentMaximumValue) {
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
     * @param argumentMinimumValue The minimum allowed value of the argument
     *        that was invalid.
     * @param argumentMaximumValue The maximum allowed value of the argument
     *        that was invalid.
     * @param msg A string describing the error that occurred.
     */
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable Number argumentValue, @Nullable Number argumentMinimumValue,
        @Nullable Number argumentMaximumValue, @Nullable String msg) {
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
     * @param argumentMinimumValue The minimum allowed value of the argument
     *        that was invalid.
     * @param argumentMaximumValue The maximum allowed value of the argument
     *        that was invalid.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable Number argumentValue, @Nullable Number argumentMinimumValue,
        @Nullable Number argumentMaximumValue, @Nullable Throwable cause) {
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
     * @param argumentMinimumValue The minimum allowed value of the argument
     *        that was invalid.
     * @param argumentMaximumValue The maximum allowed value of the argument
     *        that was invalid.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ArgumentOutOfRangeException(@Nonnull String argumentName,
        @Nullable Number argumentValue, @Nullable Number argumentMinimumValue,
        @Nullable Number argumentMaximumValue, @Nullable String msg,
        @Nullable Throwable cause) {
        super(argumentName, msg, cause);
        this.argumentValue = argumentValue;
        this.argumentMinimumValue = argumentMinimumValue;
        this.argumentMaximumValue = argumentMaximumValue;
    }

    // #endregion Constructors
}
