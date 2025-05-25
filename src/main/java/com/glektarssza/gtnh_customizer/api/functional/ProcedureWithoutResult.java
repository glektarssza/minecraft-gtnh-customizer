package com.glektarssza.gtnh_customizer.api.functional;

/**
 * A functional interface representing a procedure that takes no arguments and
 * returns no result.
 *
 * This is similar to a {@link java.util.function.Consumer} but without the
 * input parameter.
 *
 * @see java.util.function.Consumer
 */
public interface ProcedureWithoutResult {
    /**
     * Invoke the procedure.
     */
    void invoke();
}
