package com.glektarssza.gtnh_customizer.api.v1.functional;

/**
 * A function that takes three arguments and returns no value.
 *
 * @deprecated Use the v2 APIs instead.
 */
@Deprecated
public interface TrinaryConsumer<A1, A2, A3> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     * @param arg3 The third argument.
     *
     * @deprecated Use the v2 APIs instead.
     */
    @Deprecated
    void call(A1 arg1, A2 arg2, A3 arg3);
}
