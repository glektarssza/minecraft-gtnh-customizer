package com.glektarssza.gtnh_customizer.api.v2.functional.supplier;

/**
 * An object that takes four arguments and returns a value.
 *
 * @param <A1> The type of the first argument.
 * @param <A2> The type of the second argument.
 * @param <A3> The type of the third argument.
 * @param <A4> The type of the fourth argument.
 * @param <R> The type of the return value.
 */
public interface QuaternarySupplier<A1, A2, A3, A4, R> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     * @param arg3 The third argument.
     * @param arg4 The fourth argument.
     *
     * @return The result.
     */
    R call(A1 arg1, A2 arg2, A3 arg3, A4 arg4);
}
