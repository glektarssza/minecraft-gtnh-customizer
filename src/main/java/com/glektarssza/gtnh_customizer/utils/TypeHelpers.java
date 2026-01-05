package com.glektarssza.gtnh_customizer.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A collection of type-related helpers.
 */
public class TypeHelpers {
    /**
     * Cast a value to a non-null annotation or produce an exception if it is
     * {@code null}.
     *
     * @param value The value to cast.
     *
     * @return The value provided if it is not {@code null}.
     */
    @Nonnull
    public static <T> T asNonnull(@Nullable T value) {
        if (value == null) {
            throw new NullPointerException(
                "Could not cast null value to non-null annotation");
        }
        return value;
    }
}
