package com.glektarssza.gtnh_customizer.api.utils;

import com.glektarssza.gtnh_customizer.api.annotations.NotNull;
import com.glektarssza.gtnh_customizer.api.annotations.Nullable;

/**
 * Various type and class utilities.
 */
public class TypeUtilities {
    /**
     * Assert that a value is not {@code null}.
     *
     * @param <T> The type of the value to assert on.
     * @param maybeValue The possible value to assert on.
     *
     * @return The value if it is not {@code null}.
     *
     * @throws NullPointerException Thrown if the value is {@code null}.
     */
    @NotNull
    public static <T> T assertNotNull(@Nullable T maybeValue)
        throws NullPointerException {
        return assertNotNull(maybeValue, "Expected value to not be null");
    }

    /**
     * Assert that a value is not {@code null}.
     *
     * @param <T> The type of the value to assert on.
     * @param maybeValue The possible value to assert on.
     * @param message The message to throw an exception with if the value is
     *        {@code null}.
     *
     * @return The value if it is not {@code null}.
     *
     * @throws NullPointerException Thrown if the value is {@code null}.
     */
    @NotNull
    public static <T> T assertNotNull(@Nullable T maybeValue,
        @Nullable String message) {
        if (maybeValue == null) {
            throw new NullPointerException(message);
        }
        return maybeValue;
    }
}
