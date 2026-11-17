package com.glektarssza.gtnh_customizer.api.v2.exception.collection;

import com.glektarssza.gtnh_customizer.api.v2.annotation.Nullable;
import com.glektarssza.gtnh_customizer.api.v2.exception.BaseRuntimeException;

/**
 * An exception that indicates an index was outside the allowed bounds.
 */
public class IndexOutOfBoundsException extends BaseRuntimeException {
    // #region Private Static Fields

    /**
     * The serialization version UID.
     */
    private static final long serialVersionUID = 2026_11_12_001L;

    // #endregion

    // #region Public Fields

    /**
     * The index which was out of bounds.
     */
    @Nullable
    public final Integer index;

    /**
     * The lower allowed bounds of the index.
     */
    @Nullable
    public final Integer lowerBounds;

    /**
     * The upper allowed bounds of the index.
     */
    @Nullable
    public final Integer upperBounds;

    // #endregion

    // #region Constructors

    /**
     * Create a new instance.
     */
    public IndexOutOfBoundsException() {
        this.index = null;
        this.lowerBounds = null;
        this.upperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing what went wrong.
     */
    public IndexOutOfBoundsException(final String message) {
        super(message);
        this.index = null;
        this.lowerBounds = null;
        this.upperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param cause The exception that triggered the new instance to be created.
     */
    public IndexOutOfBoundsException(final Throwable cause) {
        super(cause);
        this.index = null;
        this.lowerBounds = null;
        this.upperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    public IndexOutOfBoundsException(final String message,
        final Throwable cause) {
        super(message, cause);
        this.index = null;
        this.lowerBounds = null;
        this.upperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param index The index which was out of bounds.
     */
    public IndexOutOfBoundsException(final int index) {
        this.index = index;
        this.lowerBounds = null;
        this.upperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param index The index which was out of bounds.
     * @param message A string describing what went wrong.
     */
    public IndexOutOfBoundsException(final int index, final String message) {
        super(message);
        this.index = index;
        this.lowerBounds = null;
        this.upperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param index The index which was out of bounds.
     * @param cause The exception that triggered the new instance to be created.
     */
    public IndexOutOfBoundsException(final int index, final Throwable cause) {
        super(cause);
        this.index = index;
        this.lowerBounds = null;
        this.upperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param index The index which was out of bounds.
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    public IndexOutOfBoundsException(final int index, final String message,
        final Throwable cause) {
        super(message, cause);
        this.index = index;
        this.lowerBounds = null;
        this.upperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param index The index which was out of bounds.
     * @param lowerBounds The lower allowed bounds of the index.
     */
    public IndexOutOfBoundsException(final int index, final int lowerBounds) {
        this.index = index;
        this.lowerBounds = lowerBounds;
        this.upperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param index The index which was out of bounds.
     * @param lowerBounds The lower allowed bounds of the index.
     * @param message A string describing what went wrong.
     */
    public IndexOutOfBoundsException(final int index, final int lowerBounds,
        final String message) {
        super(message);
        this.index = index;
        this.lowerBounds = lowerBounds;
        this.upperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param index The index which was out of bounds.
     * @param lowerBounds The lower allowed bounds of the index.
     * @param cause The exception that triggered the new instance to be created.
     */
    public IndexOutOfBoundsException(final int index, final int lowerBounds,
        final Throwable cause) {
        super(cause);
        this.index = index;
        this.lowerBounds = lowerBounds;
        this.upperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param index The index which was out of bounds.
     * @param lowerBounds The lower allowed bounds of the index.
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    public IndexOutOfBoundsException(final int index, final int lowerBounds,
        final String message, final Throwable cause) {
        super(message, cause);
        this.index = index;
        this.lowerBounds = lowerBounds;
        this.upperBounds = null;
    }

    /**
     * Create a new instance.
     *
     * @param index The index which was out of bounds.
     * @param lowerBounds The lower allowed bounds of the index.
     * @param upperBounds The upper allowed bounds of the index.
     */
    public IndexOutOfBoundsException(final int index, final int lowerBounds,
        final int upperBounds) {
        this.index = index;
        this.lowerBounds = lowerBounds;
        this.upperBounds = upperBounds;
    }

    /**
     * Create a new instance.
     *
     * @param index The index which was out of bounds.
     * @param lowerBounds The lower allowed bounds of the index.
     * @param upperBounds The upper allowed bounds of the index.
     * @param message A string describing what went wrong.
     */
    public IndexOutOfBoundsException(final int index, final int lowerBounds,
        final int upperBounds, final String message) {
        super(message);
        this.index = index;
        this.lowerBounds = lowerBounds;
        this.upperBounds = upperBounds;
    }

    /**
     * Create a new instance.
     *
     * @param index The index which was out of bounds.
     * @param lowerBounds The lower allowed bounds of the index.
     * @param upperBounds The upper allowed bounds of the index.
     * @param cause The exception that triggered the new instance to be created.
     */
    public IndexOutOfBoundsException(final int index, final int lowerBounds,
        final int upperBounds, final Throwable cause) {
        super(cause);
        this.index = index;
        this.lowerBounds = lowerBounds;
        this.upperBounds = upperBounds;
    }

    /**
     * Create a new instance.
     *
     * @param index The index which was out of bounds.
     * @param lowerBounds The lower allowed bounds of the index.
     * @param upperBounds The upper allowed bounds of the index.
     * @param message A string describing what went wrong.
     * @param cause The exception that triggered the new instance to be created.
     */
    public IndexOutOfBoundsException(final int index, final int lowerBounds,
        final int upperBounds, final String message, final Throwable cause) {
        super(message, cause);
        this.index = index;
        this.lowerBounds = lowerBounds;
        this.upperBounds = upperBounds;
    }

    // #endregion
}
