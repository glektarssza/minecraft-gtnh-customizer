package com.glektarssza.gtnh_customizer.api.functional;

/**
 * A function that takes two arguments and returns a value.
 */
public interface BinarySupplier<A1, A2, R> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     *
     * @return The return value.
     */
    public R call(A1 arg1, A2 arg2);
}
