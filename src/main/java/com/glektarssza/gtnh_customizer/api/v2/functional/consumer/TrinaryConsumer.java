package com.glektarssza.gtnh_customizer.api.v2.functional.consumer;

/**
 * An object that takes three arguments and returns no value.
 *
 * @param <A1> The type of the first argument.
 * @param <A2> The type of the second argument.
 * @param <A3> The type of the third argument.
 */
public interface TrinaryConsumer<A1, A2, A3> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     * @param arg3 The third argument.
     */
    void call(A1 arg1, A2 arg2, A3 arg3);
}
