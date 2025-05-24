package com.glektarssza.gtnh_customizer.utils.functional;

/**
 * A functional interface representing a procedure that takes one argument and
 * returns a result.
 *
 * @param <R> The type of the result.
 * @param <T> The type of the input argument.
 *
 * @see glektarssza.gtnh_customizer.utils.functional.ProcedureWithResult
 */
public interface UnaryProcedureWithResult<R, T> {
    /**
     * Invoke the procedure.
     *
     * @param arg1 The input argument.
     *
     * @return The result of the procedure.
     */
    R invoke(T arg1);
}
