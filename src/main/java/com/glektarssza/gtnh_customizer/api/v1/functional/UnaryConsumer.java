package com.glektarssza.gtnh_customizer.api.v1.functional;

/**
 * A function that takes one argument and returns no value.
 */
public interface UnaryConsumer<A1> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     */
    public void call(A1 arg1);
}
