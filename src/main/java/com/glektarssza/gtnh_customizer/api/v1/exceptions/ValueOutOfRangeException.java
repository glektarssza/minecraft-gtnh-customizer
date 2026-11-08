package com.glektarssza.gtnh_customizer.api.v1.exceptions;

import javax.annotation.Nullable;

/**
 * An exception indicating that a value was outside of the allowed range.
 */
public class ValueOutOfRangeException extends BaseRuntimeException {
    // #region Public Fields

    /**
     * The value that was outside of the allowed range.
     */
    @Nullable
    public final Number value;

    /**
     * The minimum allowed value of the outside of the allowed range.
     */
    @Nullable
    public final Number minimumValue;

    /**
     * The maximum allowed value of the outside of the allowed range.
     */
    @Nullable
    public final Number maximumValue;

    // #endregion Public Fields

    // #region Constructors

    /**
     * Create a new instance.
     */
    public ValueOutOfRangeException() {
        this.value = null;
        this.minimumValue = null;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param msg A string describing the error that occurred.
     */
    public ValueOutOfRangeException(@Nullable final String msg) {
        super(msg);
        this.value = null;
        this.minimumValue = null;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ValueOutOfRangeException(@Nullable final Throwable cause) {
        super(cause);
        this.value = null;
        this.minimumValue = null;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ValueOutOfRangeException(@Nullable final String msg,
        @Nullable final Throwable cause) {
        super(msg, cause);
        this.value = null;
        this.minimumValue = null;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value that was outside of the allowed range.
     */
    public ValueOutOfRangeException(@Nullable final Number argValue) {
        this.value = argValue;
        this.minimumValue = null;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value that was outside of the allowed range.
     * @param msg A string describing the error that occurred.
     */
    public ValueOutOfRangeException(@Nullable final Number argValue,
        @Nullable final String msg) {
        super(msg);
        this.value = argValue;
        this.minimumValue = null;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value that was outside of the allowed range.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ValueOutOfRangeException(@Nullable final Number argValue,
        @Nullable final Throwable cause) {
        super(cause);
        this.value = argValue;
        this.minimumValue = null;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value that was outside of the allowed range.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ValueOutOfRangeException(@Nullable final Number argValue,
        @Nullable final String msg, @Nullable final Throwable cause) {
        super(msg, cause);
        this.value = argValue;
        this.minimumValue = null;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value that was outside of the allowed range.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     */
    public ValueOutOfRangeException(@Nullable final Number argValue,
        @Nullable final Number argMinimumValue) {
        this.value = argValue;
        this.minimumValue = argMinimumValue;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value that was outside of the allowed range.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     */
    public ValueOutOfRangeException(@Nullable final Number argValue,
        @Nullable final Number argMinimumValue, @Nullable final String msg) {
        super(msg);
        this.value = argValue;
        this.minimumValue = argMinimumValue;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value that was outside of the allowed range.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ValueOutOfRangeException(@Nullable final Number argValue,
        @Nullable final Number argMinimumValue,
        @Nullable final Throwable cause) {
        super(cause);
        this.value = argValue;
        this.minimumValue = argMinimumValue;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value that was outside of the allowed range.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ValueOutOfRangeException(@Nullable final Number argValue,
        @Nullable final Number argMinimumValue, @Nullable final String msg,
        @Nullable final Throwable cause) {
        super(msg, cause);
        this.value = argValue;
        this.minimumValue = argMinimumValue;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value that was outside of the allowed range.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param argMaximumValue The maximum allowed value of the value that was
     *        outside of the allowed range.
     */
    public ValueOutOfRangeException(@Nullable final Number argValue,
        @Nullable final Number argMinimumValue,
        @Nullable final Number argMaximumValue) {
        this.value = argValue;
        this.minimumValue = argMinimumValue;
        this.maximumValue = argMaximumValue;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value that was outside of the allowed range.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param argMaximumValue The maximum allowed value of the value that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     */
    public ValueOutOfRangeException(@Nullable final Number argValue,
        @Nullable final Number argMinimumValue,
        @Nullable final Number argMaximumValue,
        @Nullable final String msg) {
        super(msg);
        this.value = argValue;
        this.minimumValue = argMinimumValue;
        this.maximumValue = argMaximumValue;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value that was outside of the allowed range.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param argMaximumValue The maximum allowed value of the value that was
     *        outside of the allowed range.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ValueOutOfRangeException(@Nullable final Number argValue,
        @Nullable final Number argMinimumValue,
        @Nullable final Number argMaximumValue,
        @Nullable final Throwable cause) {
        super(cause);
        this.value = argValue;
        this.minimumValue = argMinimumValue;
        this.maximumValue = argMaximumValue;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value that was outside of the allowed range.
     * @param argMinimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param argMaximumValue The maximum allowed value of the value that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ValueOutOfRangeException(@Nullable final Number argValue,
        @Nullable final Number argMinimumValue,
        @Nullable final Number argMaximumValue,
        @Nullable final String msg, @Nullable final Throwable cause) {
        super(msg, cause);
        this.value = argValue;
        this.minimumValue = argMinimumValue;
        this.maximumValue = argMaximumValue;
    }

    // #endregion Constructors
}
