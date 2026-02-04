package com.glektarssza.gtnh_customizer.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Some useful type helpers.
 */
public class TypeHelpers {
    /**
     * Determine of a value is not {@code null} and return it as a
     * {@link Nonnull} annotated value if so, otherwise throw a
     * {@link NullPointerException}.
     *
     * @param value The value to check/cast.
     *
     * @return The {@code value}, cast to a {@link Nonnull} annotated value.
     *
     * @throws NullPointerException Thrown if the {@code value} is {@code null}.
     */
    public static @Nonnull <T> T castToNonNull(@Nullable T value) {
        return castToNonNull(value, null);
    }

    /**
     * Determine of a value is not {@code null} and return it as a
     * {@link Nonnull} annotated value if so, otherwise throw a
     * {@link NullPointerException}.
     *
     * @param value The value to check/cast.
     * @param message The message to throw a {@link NullPointerException} with
     *        if the {@code value} is {@code null}.
     *
     * @return The {@code value}, cast to a {@link Nonnull} annotated value.
     *
     * @throws NullPointerException Thrown if the {@code value} is {@code null}.
     */
    public static @Nonnull <T> T castToNonNull(@Nullable T value,
        @Nullable String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }
}
