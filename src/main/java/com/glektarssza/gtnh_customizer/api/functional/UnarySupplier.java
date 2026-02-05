package com.glektarssza.gtnh_customizer.api.functional;

/**
 * A function that takes one argument and returns a value.
 */
public interface UnarySupplier<A1, R> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     *
     * @return The return value.
     */
    public R call(A1 arg1);
}
