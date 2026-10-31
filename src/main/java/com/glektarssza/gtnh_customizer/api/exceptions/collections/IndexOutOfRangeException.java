package com.glektarssza.gtnh_customizer.api.exceptions.collections;

import com.glektarssza.gtnh_customizer.api.annotations.Nullable;
import com.glektarssza.gtnh_customizer.api.exceptions.ValueOutOfRangeException;

/**
 * An exception indicating the index into the collection was out of range for
 * the requested operation.
 */
public class IndexOutOfRangeException extends ValueOutOfRangeException {
    // #region Constructors

    /**
     * Create a new instance.
     */
    public IndexOutOfRangeException() {
        super();
    }

    /**
     * Create a new instance.
     *
     * @param msg A string describing the error that occurred.
     */
    public IndexOutOfRangeException(@Nullable final String msg) {
        super(msg);
    }

    /**
     * Create a new instance.
     *
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public IndexOutOfRangeException(@Nullable final Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public IndexOutOfRangeException(@Nullable final String msg,
        @Nullable final Throwable cause) {
        super(msg, cause);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     */
    public IndexOutOfRangeException(@Nullable final Integer index) {
        super(index, 0);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     * @param msg A string describing the error that occurred.
     */
    public IndexOutOfRangeException(@Nullable final Integer index,
        @Nullable final String msg) {
        super(index, 0, msg);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public IndexOutOfRangeException(@Nullable final Integer index,
        @Nullable final Throwable cause) {
        super(index, 0, cause);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public IndexOutOfRangeException(@Nullable final Integer index,
        @Nullable final String msg, @Nullable final Throwable cause) {
        super(index, 0, msg, cause);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     * @param minimumIndex The minimum allowed index of the index that was
     *        outside of the allowed range.
     */
    public IndexOutOfRangeException(@Nullable final Integer index,
        @Nullable final Integer minimumIndex) {
        super(index, minimumIndex);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     * @param minimumIndex The minimum allowed index of the index that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     */
    public IndexOutOfRangeException(@Nullable final Integer index,
        @Nullable final Integer minimumIndex, @Nullable final String msg) {
        super(index, minimumIndex, msg);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     * @param minimumIndex The minimum allowed index of the index that was
     *        outside of the allowed range.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public IndexOutOfRangeException(@Nullable final Integer index,
        @Nullable final Integer minimumIndex, @Nullable final Throwable cause) {
        super(index, minimumIndex, cause);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     * @param minimumIndex The minimum allowed index of the index that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public IndexOutOfRangeException(@Nullable final Integer index,
        @Nullable final Integer minimumIndex, @Nullable final String msg,
        @Nullable final Throwable cause) {
        super(index, minimumIndex, msg, cause);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     * @param minimumIndex The minimum allowed index of the index that was
     *        outside of the allowed range.
     * @param maximumIndex The maximum allowed index of the index that was
     *        outside of the allowed range.
     */
    public IndexOutOfRangeException(@Nullable final Integer index,
        @Nullable final Integer minimumIndex,
        @Nullable final Integer maximumIndex) {
        super(index, minimumIndex, maximumIndex);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     * @param minimumIndex The minimum allowed index of the index that was
     *        outside of the allowed range.
     * @param maximumIndex The maximum allowed index of the index that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     */
    public IndexOutOfRangeException(@Nullable final Integer index,
        @Nullable final Integer minimumIndex,
        @Nullable final Integer maximumIndex,
        @Nullable final String msg) {
        super(index, minimumIndex, maximumIndex, msg);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     * @param minimumIndex The minimum allowed index of the index that was
     *        outside of the allowed range.
     * @param maximumIndex The maximum allowed index of the index that was
     *        outside of the allowed range.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public IndexOutOfRangeException(@Nullable final Integer index,
        @Nullable final Integer minimumIndex,
        @Nullable final Integer maximumIndex,
        @Nullable final Throwable cause) {
        super(index, minimumIndex, maximumIndex, cause);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     * @param minimumIndex The minimum allowed index of the index that was
     *        outside of the allowed range.
     * @param maximumIndex The maximum allowed index of the index that was
     *        outside of the allowed range.
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public IndexOutOfRangeException(@Nullable final Integer index,
        @Nullable final Integer minimumIndex,
        @Nullable final Integer maximumIndex,
        @Nullable final String msg, @Nullable final Throwable cause) {
        super(index, minimumIndex, maximumIndex, msg, cause);
    }

    // #endregion
}
