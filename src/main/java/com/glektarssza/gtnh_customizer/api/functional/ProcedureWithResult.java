package com.glektarssza.gtnh_customizer.api.functional;

/**
 * A functional interface representing a procedure that takes no arguments and
 * returns a result.
 *
 * This is similar to a {@link java.util.function.Consumer} but with a return
 * value and no input parameter.
 *
 * @param <R> The type of the result.
 *
 * @see java.util.function.Consumer
 */
public interface ProcedureWithResult<R> {
    /**
     * Invoke the procedure.
     *
     * @return The result of the procedure.
     */
    R invoke();
}
