package com.glektarssza.gtnh_customizer.api.v2.exception.argument;

import com.glektarssza.gtnh_customizer.api.v2.annotation.Nullable;
import com.glektarssza.gtnh_customizer.api.v2.exception.BaseRuntimeException;

/**
 * An exception indicating an argument to a method was invalid.
 */
public class InvalidArgumentException extends BaseRuntimeException {
    // #region Private Static Fields

    /**
     * The serialization version UID.
     */
    private static final long serialVersionUID = 2026_11_17_001L;

    // #endregion

    // #region Public Fields

    /**
     * The name of the argument that was invalid.
     */
    @Nullable
    public final String argumentName;

    // #endregion

    // #region Constructors

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     */
    public InvalidArgumentException(final String argName) {
        this.argumentName = argName;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param message A string describing what went wrong.
     */
    public InvalidArgumentException(final String argName,
        final String message) {
        super(message);
        this.argumentName = argName;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param cause The exception that triggered the new instance to be created.
     */
    public InvalidArgumentException(final String argName,
        final Throwable cause) {
        super(cause);
        this.argumentName = argName;
    }

    /**
     * Create a new instance.
     *
     * @param argName The name of the argument which was invalid.
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    public InvalidArgumentException(final String argName,
        final String message, final Throwable cause) {
        super(message, cause);
        this.argumentName = argName;
    }

    // #endregion
}
