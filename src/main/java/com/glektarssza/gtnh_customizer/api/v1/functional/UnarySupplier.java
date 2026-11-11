package com.glektarssza.gtnh_customizer.api.v1.functional;

/**
 * A function that takes one argument and returns a value.
 *
 * @deprecated Use the v2 APIs instead.
 */
@Deprecated
public interface UnarySupplier<A1, R> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     *
     * @return The return value.
     *
     * @deprecated Use the v2 APIs instead.
     */
    @Deprecated
    R call(A1 arg1);
}
