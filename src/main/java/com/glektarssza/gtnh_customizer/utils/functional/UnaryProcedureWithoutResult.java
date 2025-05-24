package com.glektarssza.gtnh_customizer.utils.functional;

/**
 * A functional interface representing a procedure that takes one argument and
 * returns a result.
 *
 * @param <T> The type of the input argument.
 *
 * @see glektarssza.gtnh_customizer.utils.functional.ProcedureWithoutResult
 */
public interface UnaryProcedureWithoutResult<T> {
    /**
     * Invoke the procedure.
     *
     * @param arg1 The input argument.
     */
    void invoke(T arg1);
}
