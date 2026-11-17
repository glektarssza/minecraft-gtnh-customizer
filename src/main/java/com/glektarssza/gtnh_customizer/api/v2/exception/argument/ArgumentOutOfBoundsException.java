package com.glektarssza.gtnh_customizer.api.v2.exception.argument;

import com.glektarssza.gtnh_customizer.api.v2.annotation.Nullable;

/**
 * An exception which indicates an argument was invalid because its value was
 * outside of the allowed bounds.
 */
public class ArgumentOutOfBoundsException extends InvalidArgumentException {
    // #region Private Static Fields

    /**
     * The serialization version UID.
     */
    private static final long serialVersionUID = 2026_11_17_001L;

    // #endregion

    // #region Public Fields

    /**
     * The value of the argument which was invalid.
     */
    @Nullable
    public final Object argumentValue;

    /**
     * The lower bounds of the argument which was invalid.
     */
    @Nullable
    public final Object argumentLowerBounds;

    /**
     * The upper bounds of the argument which was invalid.
     */
    @Nullable
    public final Object argumentUpperBounds;

    // #endregion

    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     */
    public ArgumentOutOfBoundsException(final String argName) {
        super(argName);
        this.argumentValue = null;
        this.argumentLowerBounds = null;
        this.argumentUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param message A string describing what went wrong.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final String message) {
        super(argName, message);
        this.argumentValue = null;
        this.argumentLowerBounds = null;
        this.argumentUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final Throwable cause) {
        super(argName, cause);
        this.argumentValue = null;
        this.argumentLowerBounds = null;
        this.argumentUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final String message, final Throwable cause) {
        super(argName, message, cause);
        this.argumentValue = null;
        this.argumentLowerBounds = null;
        this.argumentUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param argValue The value of the argument which was invalid.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final Object argValue) {
        super(argName);
        this.argumentValue = argValue;
        this.argumentLowerBounds = null;
        this.argumentUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param argValue The value of the argument which was invalid.
     * @param message A string describing what went wrong.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final Object argValue, final String message) {
        super(argName, message);
        this.argumentValue = argValue;
        this.argumentLowerBounds = null;
        this.argumentUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param argValue The value of the argument which was invalid.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final Object argValue, final Throwable cause) {
        super(argName, cause);
        this.argumentValue = argValue;
        this.argumentLowerBounds = null;
        this.argumentUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param argValue The value of the argument which was invalid.
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final Object argValue, final String message, final Throwable cause) {
        super(argName, message, cause);
        this.argumentValue = argValue;
        this.argumentLowerBounds = null;
        this.argumentUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final Object argValue, final Object argLowerBounds) {
        super(argName);
        this.argumentValue = argValue;
        this.argumentLowerBounds = argLowerBounds;
        this.argumentUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     * @param message A string describing what went wrong.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final Object argValue, final Object argLowerBounds,
        final String message) {
        super(argName, message);
        this.argumentValue = argValue;
        this.argumentLowerBounds = argLowerBounds;
        this.argumentUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final Object argValue, final Object argLowerBounds,
        final Throwable cause) {
        super(argName, cause);
        this.argumentValue = argValue;
        this.argumentLowerBounds = argLowerBounds;
        this.argumentUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final Object argValue, final Object argLowerBounds,
        final String message, final Throwable cause) {
        super(argName, message, cause);
        this.argumentValue = argValue;
        this.argumentLowerBounds = argLowerBounds;
        this.argumentUpperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     * @param argUpperBounds The upper bounds of the argument which was invalid.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final Object argValue, final Object argLowerBounds,
        final Object argUpperBounds) {
        super(argName);
        this.argumentValue = argValue;
        this.argumentLowerBounds = argLowerBounds;
        this.argumentUpperBounds = argUpperBounds;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     * @param argUpperBounds The upper bounds of the argument which was invalid.
     * @param message A string describing what went wrong.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final Object argValue, final Object argLowerBounds,
        final Object argUpperBounds, final String message) {
        super(argName, message);
        this.argumentValue = argValue;
        this.argumentLowerBounds = argLowerBounds;
        this.argumentUpperBounds = argUpperBounds;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     * @param argUpperBounds The upper bounds of the argument which was invalid.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final Object argValue, final Object argLowerBounds,
        final Object argUpperBounds, final Throwable cause) {
        super(argName, cause);
        this.argumentValue = argValue;
        this.argumentLowerBounds = argLowerBounds;
        this.argumentUpperBounds = argUpperBounds;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param argValue The value of the argument which was invalid.
     * @param argLowerBounds The lower bounds of the argument which was invalid.
     * @param argUpperBounds The upper bounds of the argument which was invalid.
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ArgumentOutOfBoundsException(final String argName,
        final Object argValue, final Object argLowerBounds,
        final Object argUpperBounds, final String message,
        final Throwable cause) {
        super(argName, message, cause);
        this.argumentValue = argValue;
        this.argumentLowerBounds = argLowerBounds;
        this.argumentUpperBounds = argUpperBounds;
    }

    // #endregion
}
