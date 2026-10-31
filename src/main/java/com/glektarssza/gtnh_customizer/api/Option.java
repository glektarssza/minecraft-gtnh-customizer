package com.glektarssza.gtnh_customizer.api;

import java.util.function.Function;
import java.util.function.Supplier;

import com.glektarssza.gtnh_customizer.api.annotations.NotNull;
import com.glektarssza.gtnh_customizer.api.annotations.Nullable;

/**
 * An object that can hold an optional value.
 */
public class Option<T> {
    /**
     * Create a new instance holding some value.
     *
     * @param <T> The type of value to be held.
     * @param value The value to be held.
     *
     * @return The new instance.
     */
    public static <T> Option<T> some(@NotNull final T value) {
        return Option.maybeSome(value);
    }

    /**
     * Create a new instance holding no value.
     *
     * @param <T> The type of value that would have been held.
     *
     * @return The new instance.
     */
    public static <T> Option<T> none() {
        return Option.maybeSome(null);
    }

    /**
     * Create a new instance holding a possible value.
     *
     * @param <T> The type of value that might be held.
     * @param value The possible value to be held.
     *
     * @return The new instance.
     */
    public static <T> Option<T> maybeSome(@Nullable final T maybeValue) {
        return new Option<T>(maybeValue);
    }

    /**
     * The value held in this instance.
     */
    @Nullable
    private final T value;

    /**
     * Create a new instance.
     *
     * @param value The value to be held in the instance.
     */
    private Option(@Nullable final T value) {
        this.value = value;
    }

    /**
     * Get whether this instance holds some value.
     *
     * @return Whether this instance holds some value.
     */
    public boolean isSome() {
        return this.value != null;
    }

    /**
     * Get whether this instance holds no value.
     *
     * @return Whether this instance holds no value.
     */
    public boolean isNone() {
        return this.value == null;
    }

    /**
     * Get the value held in this instance.
     *
     * @return The value held in this instance.
     *
     * @throws NullPointerException Thrown if this instance does not hold a
     *         value.
     */
    @NotNull
    public T getValue() throws NullPointerException {
        if (this.value == null) {
            throw new NullPointerException();
        }
        return this.value;
    }

    /**
     * Get the value held in this instance.
     *
     * @return The value held in this instance.
     */
    @Nullable
    public T tryGetValue() {
        return this.value;
    }

    /**
     * Get the value held in this instance or the given default value if this
     * instance does not hold a value.
     *
     * @param defaultValue The value to return if this instance does not hold a
     *        value.
     *
     * @return The value held in this instance or the given default value if
     *         this instance does not hold a value.
     */
    public T getValueOrElse(@NotNull final T defaultValue) {
        if (this.value == null) {
            return defaultValue;
        }
        return this.value;
    }

    /**
     * If this instance holds a value then return the given instance, otherwise
     * return an instance holding nothing.
     *
     * @param <U> The type held in the other instance.
     * @param other The other instance to return.
     *
     * @return The other instance if this instance holds a value, otherwise an
     *         instance holding no value.
     */
    public <U> Option<U> and(final Option<U> other) {
        if (this.value != null) {
            return other;
        }
        return Option.none();
    }

    /**
     * If this instance holds a value then return the instance returned by the
     * given callback, otherwise return an instance holding nothing.
     *
     * @param <U> The type held in the other instance returned by the callback.
     * @param otherCallback The callback to use to get the other instance to
     *        return.
     *
     * @return The other instance returned by the callback if this instance
     *         holds a value, otherwise an instance holding no value.
     */
    public <U> Option<U> andThen(final Function<T, Option<U>> otherCallback) {
        if (this.value != null) {
            return otherCallback.apply(this.value);
        }
        return Option.none();
    }

    /**
     * If this instance holds no value then return the given instance, otherwise
     * return an instance holding nothing.
     *
     * @param <U> The type held in the other instance.
     * @param other The other instance to return.
     *
     * @return The other instance if this instance holds a value, otherwise an
     *         instance holding no value.
     */
    public <U> Option<U> or(final Option<U> other) {
        if (this.value == null) {
            return other;
        }
        return Option.none();
    }

    /**
     * If this instance holds a value then return the instance returned by the
     * given callback, otherwise return an instance holding nothing.
     *
     * @param <U> The type held in the other instance returned by the callback.
     * @param otherCallback The callback to use to get the other instance to
     *        return.
     *
     * @return The other instance returned by the callback if this instance
     *         holds a value, otherwise an instance holding no value.
     */
    public <U> Option<U> orElse(final Supplier<Option<U>> otherCallback) {
        if (this.value == null) {
            return otherCallback.get();
        }
        return Option.none();
    }
}
