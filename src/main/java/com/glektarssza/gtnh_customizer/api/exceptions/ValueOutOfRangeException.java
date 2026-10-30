package com.glektarssza.gtnh_customizer.api.exceptions;

import com.glektarssza.gtnh_customizer.api.annotations.Nullable;

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
        super();
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
     * @param value The value that was outside of the allowed range.
     */
    public ValueOutOfRangeException(@Nullable final Number value) {
        super();
        this.value = value;
        this.minimumValue = null;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param value The value that was outside of the allowed range.
     * @param msg A string describing the error that occurred.
     */
    public ValueOutOfRangeException(@Nullable final Number value,
        @Nullable final String msg) {
        super(msg);
        this.value = value;
        this.minimumValue = null;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param value The value that was outside of the allowed range.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ValueOutOfRangeException(@Nullable final Number value,
        @Nullable final Throwable cause) {
        super(cause);
        this.value = value;
        this.minimumValue = null;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param value The value that was outside of the allowed range.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ValueOutOfRangeException(@Nullable final Number value,
        @Nullable final String msg, @Nullable final Throwable cause) {
        super(msg, cause);
        this.value = value;
        this.minimumValue = null;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param value The value that was outside of the allowed range.
     * @param minimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     */
    public ValueOutOfRangeException(@Nullable final Number value,
        @Nullable final Number minimumValue) {
        super();
        this.value = value;
        this.minimumValue = minimumValue;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param value The value that was outside of the allowed range.
     * @param minimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     */
    public ValueOutOfRangeException(@Nullable final Number value,
        @Nullable final Number minimumValue, @Nullable final String msg) {
        super(msg);
        this.value = value;
        this.minimumValue = minimumValue;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param value The value that was outside of the allowed range.
     * @param minimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ValueOutOfRangeException(@Nullable final Number value,
        @Nullable final Number minimumValue, @Nullable final Throwable cause) {
        super(cause);
        this.value = value;
        this.minimumValue = minimumValue;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param value The value that was outside of the allowed range.
     * @param minimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ValueOutOfRangeException(@Nullable final Number value,
        @Nullable final Number minimumValue, @Nullable final String msg,
        @Nullable final Throwable cause) {
        super(msg, cause);
        this.value = value;
        this.minimumValue = minimumValue;
        this.maximumValue = null;
    }

    /**
     * Create a new instance.
     *
     * @param value The value that was outside of the allowed range.
     * @param minimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param maximumValue The maximum allowed value of the value that was
     *        outside of the allowed range.
     */
    public ValueOutOfRangeException(@Nullable final Number value,
        @Nullable final Number minimumValue,
        @Nullable final Number maximumValue) {
        super();
        this.value = value;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }

    /**
     * Create a new instance.
     *
     * @param value The value that was outside of the allowed range.
     * @param minimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param maximumValue The maximum allowed value of the value that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     */
    public ValueOutOfRangeException(@Nullable final Number value,
        @Nullable final Number minimumValue,
        @Nullable final Number maximumValue,
        @Nullable final String msg) {
        super(msg);
        this.value = value;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }

    /**
     * Create a new instance.
     *
     * @param value The value that was outside of the allowed range.
     * @param minimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param maximumValue The maximum allowed value of the value that was
     *        outside of the allowed range.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ValueOutOfRangeException(@Nullable final Number value,
        @Nullable final Number minimumValue,
        @Nullable final Number maximumValue,
        @Nullable final Throwable cause) {
        super(cause);
        this.value = value;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }

    /**
     * Create a new instance.
     *
     * @param value The value that was outside of the allowed range.
     * @param minimumValue The minimum allowed value of the value that was
     *        outside of the allowed range.
     * @param maximumValue The maximum allowed value of the value that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public ValueOutOfRangeException(@Nullable final Number value,
        @Nullable final Number minimumValue,
        @Nullable final Number maximumValue,
        @Nullable final String msg, @Nullable final Throwable cause) {
        super(msg, cause);
        this.value = value;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }

    // #endregion Constructors
}
