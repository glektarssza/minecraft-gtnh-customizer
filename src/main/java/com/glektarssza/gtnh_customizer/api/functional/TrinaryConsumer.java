package com.glektarssza.gtnh_customizer.api.functional;

/**
 * A function that takes three arguments and returns no value.
 */
public interface TrinaryConsumer<A1, A2, A3> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     * @param arg3 The third argument.
     */
    public void call(A1 arg1, A2 arg2, A3 arg3);
}
