package com.glektarssza.gtnh_customizer.utils.functional;

/**
 * A functional interface representing a procedure that takes two arguments and
 * returns no result.
 *
 * @param <T1> The type of the first input argument.
 * @param <T2> The type of the second input argument.
 *
 * @see glektarssza.gtnh_customizer.utils.functional.ProcedureWithoutResult
 */
public interface BinaryProcedureWithoutResult<T1, T2> {
    /**
     * Invoke the procedure.
     *
     * @param arg1 The first input argument.
     * @param arg2 The second input argument.
     */
    void invoke(T1 arg1, T2 arg2);
}
