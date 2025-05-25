package com.glektarssza.gtnh_customizer.api.functional;

/**
 * A functional interface representing a procedure that takes two arguments and
 * returns no result.
 *
 * @param <T1> The type of the first input argument.
 * @param <T2> The type of the second input argument.
 * @param <R> The type of the result.
 *
 * @see glektarssza.gtnh_customizer.utils.functional.ProcedureWithoutResult
 */
public interface BinaryProcedureWithResult<R, T1, T2> {
    /**
     * Invoke the procedure.
     *
     * @param arg1 The first input argument.
     * @param arg2 The second input argument.
     *
     * @return The result of the procedure.
     */
    R invoke(T1 arg1, T2 arg2);
}
