package com.glektarssza.gtnh_customizer.api.v2.exception;

import com.glektarssza.gtnh_customizer.api.v2.annotation.Nullable;

/**
 * An exception which indicates a value was invalid because it was outside of
 * the allowed bounds.
 */
public class ValueOutOfBoundsException extends BaseRuntimeException {
    // #region Private Static Fields

    /**
     * The serialization version UID.
     */
    private static final long serialVersionUID = 2026_11_17_001L;

    // #endregion

    // #region Public Fields

    /**
     * The value which was invalid.
     */
    @Nullable
    public final Object value;

    /**
     * The lower bounds of the value which was invalid.
     */
    @Nullable
    public final Object valueLowerBounds;

    /**
     * The upper bounds of the value which was invalid.
     */
    @Nullable
    public final Object valueUpperBounds;

    // #endregion

    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param argValue The value of the argument which was invalid.
     */
    public ValueOutOfBoundsException(final Object argValue) {
        this.value = argValue;
        this.valueLowerBounds = null;
        this.valueUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value of the argument which was invalid.
     * @param message A string describing what went wrong.
     */
    public ValueOutOfBoundsException(final Object argValue,
        final String message) {
        super(message);
        this.value = argValue;
        this.valueLowerBounds = null;
        this.valueUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value of the argument which was invalid.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ValueOutOfBoundsException(final Object argValue,
        final Throwable cause) {
        super(cause);
        this.value = argValue;
        this.valueLowerBounds = null;
        this.valueUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value of the argument which was invalid.
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ValueOutOfBoundsException(final Object argValue,
        final String message, final Throwable cause) {
        super(message, cause);
        this.value = argValue;
        this.valueLowerBounds = null;
        this.valueUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     */
    public ValueOutOfBoundsException(final Object argValue,
        final Object argLowerBounds) {
        this.value = argValue;
        this.valueLowerBounds = argLowerBounds;
        this.valueUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     * @param message A string describing what went wrong.
     */
    public ValueOutOfBoundsException(final Object argValue,
        final Object argLowerBounds, final String message) {
        super(message);
        this.value = argValue;
        this.valueLowerBounds = argLowerBounds;
        this.valueUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ValueOutOfBoundsException(final Object argValue,
        final Object argLowerBounds, final Throwable cause) {
        super(cause);
        this.value = argValue;
        this.valueLowerBounds = argLowerBounds;
        this.valueUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ValueOutOfBoundsException(final Object argValue,
        final Object argLowerBounds, final String message,
        final Throwable cause) {
        super(message, cause);
        this.value = argValue;
        this.valueLowerBounds = argLowerBounds;
        this.valueUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     * @param argUpperBounds The upper bounds of the argument which was invalid.
     */
    public ValueOutOfBoundsException(final Object argValue,
        final Object argLowerBounds, final Object argUpperBounds) {
        this.value = argValue;
        this.valueLowerBounds = argLowerBounds;
        this.valueUpperBounds = argUpperBounds;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     * @param argUpperBounds The upper bounds of the argument which was invalid.
     * @param message A string describing what went wrong.
     */
    public ValueOutOfBoundsException(final Object argValue,
        final Object argLowerBounds, final Object argUpperBounds,
        final String message) {
        super(message);
        this.value = argValue;
        this.valueLowerBounds = argLowerBounds;
        this.valueUpperBounds = argUpperBounds;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     * @param argUpperBounds The upper bounds of the argument which was invalid.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ValueOutOfBoundsException(final Object argValue,
        final Object argLowerBounds, final Object argUpperBounds,
        final Throwable cause) {
        super(cause);
        this.value = argValue;
        this.valueLowerBounds = argLowerBounds;
        this.valueUpperBounds = argUpperBounds;
    }

    /**
     * Create a new instance.
     *
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     * @param argUpperBounds The upper bounds of the argument which was invalid.
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ValueOutOfBoundsException(final Object argValue,
        final Object argLowerBounds, final Object argUpperBounds,
        final String message, final Throwable cause) {
        super(message, cause);
        this.value = argValue;
        this.valueLowerBounds = argLowerBounds;
        this.valueUpperBounds = argUpperBounds;
    }

    // #endregion
}
