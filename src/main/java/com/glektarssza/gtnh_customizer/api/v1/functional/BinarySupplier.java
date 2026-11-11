package com.glektarssza.gtnh_customizer.api.v1.functional;

/**
 * A function that takes two arguments and returns a value.
 *
 * @deprecated Use the v2 APIs instead.
 */
@Deprecated
public interface BinarySupplier<A1, A2, R> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     *
     * @return The return value.
     *
     * @deprecated Use the v2 APIs instead.
     */
    @Deprecated
    R call(A1 arg1, A2 arg2);
}
