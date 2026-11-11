package com.glektarssza.gtnh_customizer.api.v1.functional;

/**
 * A function that takes three arguments and returns a value.
 *
 * @deprecated Use the v2 APIs instead.
 */
@Deprecated
public interface TrinarySupplier<A1, A2, A3, R> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     * @param arg3 The third argument.
     *
     * @return The return value.
     *
     * @deprecated Use the v2 APIs instead.
     */
    @Deprecated
    R call(A1 arg1, A2 arg2, A3 arg3);
}
