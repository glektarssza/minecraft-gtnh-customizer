package com.glektarssza.gtnh_customizer.api.exceptions.collections;

import javax.annotation.Nullable;

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
    public IndexOutOfRangeException(@Nullable String msg) {
        super(msg);
    }

    /**
     * Create a new instance.
     *
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public IndexOutOfRangeException(@Nullable Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param msg A string describing the error that occurred.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public IndexOutOfRangeException(@Nullable String msg,
        @Nullable Throwable cause) {
        super(msg, cause);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     */
    public IndexOutOfRangeException(@Nullable Integer index) {
        super(index, 0);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     * @param msg A string describing the error that occurred.
     */
    public IndexOutOfRangeException(@Nullable Integer index,
        @Nullable String msg) {
        super(index, 0, msg);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     * @param cause The {@link Throwable} that caused the new instance to be
     *        created.
     */
    public IndexOutOfRangeException(@Nullable Integer index,
        @Nullable Throwable cause) {
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
    public IndexOutOfRangeException(@Nullable Integer index,
        @Nullable String msg, @Nullable Throwable cause) {
        super(index, 0, msg, cause);
    }

    /**
     * Create a new instance.
     *
     * @param index The index that was outside of the allowed range.
     * @param minimumIndex The minimum allowed index of the index that was
     *        outside of the allowed range.
     */
    public IndexOutOfRangeException(@Nullable Integer index,
        @Nullable Integer minimumIndex) {
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
    public IndexOutOfRangeException(@Nullable Integer index,
        @Nullable Integer minimumIndex, @Nullable String msg) {
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
    public IndexOutOfRangeException(@Nullable Integer index,
        @Nullable Integer minimumIndex, @Nullable Throwable cause) {
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
    public IndexOutOfRangeException(@Nullable Integer index,
        @Nullable Integer minimumIndex, @Nullable String msg,
        @Nullable Throwable cause) {
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
    public IndexOutOfRangeException(@Nullable Integer index,
        @Nullable Integer minimumIndex, @Nullable Integer maximumIndex) {
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
    public IndexOutOfRangeException(@Nullable Integer index,
        @Nullable Integer minimumIndex, @Nullable Integer maximumIndex,
        @Nullable String msg) {
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
    public IndexOutOfRangeException(@Nullable Integer index,
        @Nullable Integer minimumIndex, @Nullable Integer maximumIndex,
        @Nullable Throwable cause) {
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
    public IndexOutOfRangeException(@Nullable Integer index,
        @Nullable Integer minimumIndex, @Nullable Integer maximumIndex,
        @Nullable String msg, @Nullable Throwable cause) {
        super(index, minimumIndex, maximumIndex, msg, cause);
    }

    // #endregion
}
