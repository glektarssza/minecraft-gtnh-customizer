package com.glektarssza.gtnh_customizer.api.v1.functional;

/**
 * A function that takes two arguments and returns no value.
 *
 * @param <A1> The type of the first argument.
 * @param <A2> The type of the second argument.
 *
 * @deprecated Use the v2 APIs instead.
 */
@Deprecated
public interface BinaryConsumer<A1, A2> {
    /**
     * Call the function.
     *
     * @param arg1 The first argument.
     * @param arg2 The second argument.
     *
     * @deprecated Use the v2 APIs instead.
     */
    @Deprecated
    void call(A1 arg1, A2 arg2);
}
