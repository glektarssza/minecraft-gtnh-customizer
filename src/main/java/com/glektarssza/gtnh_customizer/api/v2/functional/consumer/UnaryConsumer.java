package com.glektarssza.gtnh_customizer.api.v2.functional.consumer;

/**
 * An object that takes one arguments and returns no value.
 */
public interface UnaryConsumer<A1> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     */
    void call(A1 arg1);
}
