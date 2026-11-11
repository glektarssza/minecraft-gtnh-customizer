package com.glektarssza.gtnh_customizer.api.v1.functional;

/**
 * A function that takes no arguments and returns a value.
 *
 * @deprecated Use the v2 APIs instead.
 */
@Deprecated
public interface VoidSupplier<R> {
    /**
     * Call the function.
     *
     * @return The return value.
     *
     * @deprecated Use the v2 APIs instead.
     */
    @Deprecated
    R call();
}
