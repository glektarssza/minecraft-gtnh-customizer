package com.glektarssza.gtnh_customizer.api.v2.functional.supplier;

/**
 * An object that takes no arguments and returns a value.
 *
 * @param <R> The type of the return value.
 */
public interface VoidSupplier<R> {
    /**
     * Call the function.
     *
     * @return The result.
     */
    R call();
}
