package com.glektarssza.gtnh_customizer.api.functional;

/**
 * A function that takes two arguments and returns no value.
 */
public interface BinaryConsumer<A1, A2> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     */
    public void call(A1 arg1, A2 arg2);
}
