package com.glektarssza.gtnh_customizer.api.v2.functional.supplier;

/**
 * An object that takes two arguments and returns a value.
 *
 * @param <A1> The type of the first argument.
 * @param <A2> The type of the second argument.
 * @param <R> The type of the return value.
 */
public interface BinarySupplier<A1, A2, R> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     *
     * @return The result.
     */
    R call(A1 arg1, A2 arg2);
}
