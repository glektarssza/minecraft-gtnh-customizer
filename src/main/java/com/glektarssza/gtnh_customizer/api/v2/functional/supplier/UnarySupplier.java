package com.glektarssza.gtnh_customizer.api.v2.functional.supplier;

/**
 * An object that takes one arguments and returns a value.
 *
 * @param <A1> The type of the first argument.
 * @param <R> The type of the return value.
 */
public interface UnarySupplier<A1, R> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     *
     * @return The result.
     */
    R call(A1 arg1);
}
