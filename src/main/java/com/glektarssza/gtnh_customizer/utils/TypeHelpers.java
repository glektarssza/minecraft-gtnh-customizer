package com.glektarssza.gtnh_customizer.utils;

import com.glektarssza.gtnh_customizer.api.annotations.NotNull;
import com.glektarssza.gtnh_customizer.api.annotations.Nullable;

/**
 * Some useful type helpers.
 */
public class TypeHelpers {
    /**
     * Determine of a value is not {@code null} and return it as a
     * {@link NotNull} annotated value if so, otherwise throw a
     * {@link NullPointerException}.
     *
     * @param value The value to check/cast.
     *
     * @return The {@code value}, cast to a {@link NotNull} annotated value.
     *
     * @throws NullPointerException Thrown if the {@code value} is {@code null}.
     */
    public static @NotNull <T> T castToNonNull(@Nullable final T value) {
        return castToNonNull(value, null);
    }

    /**
     * Determine of a value is not {@code null} and return it as a
     * {@link NotNull} annotated value if so, otherwise throw a
     * {@link NullPointerException}.
     *
     * @param value The value to check/cast.
     * @param message The message to throw a {@link NullPointerException} with
     *        if the {@code value} is {@code null}.
     *
     * @return The {@code value}, cast to a {@link NotNull} annotated value.
     *
     * @throws NullPointerException Thrown if the {@code value} is {@code null}.
     */
    public static @NotNull <T> T castToNonNull(@Nullable final T value,
        @Nullable final String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }
}
