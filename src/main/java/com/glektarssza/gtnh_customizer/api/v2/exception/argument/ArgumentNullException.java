package com.glektarssza.gtnh_customizer.api.v2.exception.argument;

/**
 * An exception which indicates an argument was invalid because its value was
 * {@code null}.
 */
public class ArgumentNullException extends InvalidArgumentException {
    // #region Private Static Fields

    /**
     * The serialization version UID.
     */
    private static final long serialVersionUID = 2026_11_17_001L;

    // #endregion

    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     */
    public ArgumentNullException(final String argName) {
        super(argName);
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param message A string describing what went wrong.
     */
    public ArgumentNullException(final String argName,
        final String message) {
        super(argName, message);
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ArgumentNullException(final String argName,
        final Throwable cause) {
        super(argName, cause);
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    public ArgumentNullException(final String argName,
        final String message, final Throwable cause) {
        super(argName, message, cause);
    }

    // #endregion
}
