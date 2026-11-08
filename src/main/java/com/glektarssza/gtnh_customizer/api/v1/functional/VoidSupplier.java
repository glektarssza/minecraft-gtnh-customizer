package com.glektarssza.gtnh_customizer.api.v1.functional;

/**
 * A function that takes no arguments and returns a value.
 */
public interface VoidSupplier<R> {
    /**
     * Call the function.
     *
     * @return The return value.
     */
    public R call();
}
