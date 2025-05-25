package com.glektarssza.gtnh_customizer.utils;

import com.glektarssza.gtnh_customizer.api.functional.ProcedureWithoutResult;

/**
 * A utility class for executing code on the client or server side.
 */
public final class SidedExecutor {
    /**
     * Checks if the current execution context is on the common side.
     *
     * @return {@code true} if the current context is on the common side,
     *         {@code false} otherwise.
     */
    public static boolean isCommonSide() {
        return true;
    }

    /**
     * Checks if the current execution context is on the client side.
     *
     * @return {@code true} if the current context is on the client side,
     *         {@code false} otherwise.
     */
    public static boolean isClientSide() {
        return false;
    }

    /**
     * Checks if the current execution context is on the server side.
     *
     * @return {@code true} if the current context is on the server side,
     *         {@code false} otherwise.
     */
    public static boolean isServerSide() {
        return true;
    }

    /**
     * Executes the given procedure if the current context is on either the
     * client or server sides.
     *
     * @param procedure The procedure to execute.
     */
    public static void onCommonSide(ProcedureWithoutResult procedure) {
        if (isCommonSide()) {
            procedure.invoke();
        }
    }

    /**
     * Executes the given procedure if the current context is on the client
     * side.
     *
     * @param procedure The procedure to execute.
     */
    public static void onClientSide(ProcedureWithoutResult procedure) {
        if (isClientSide()) {
            procedure.invoke();
        }
    }

    /**
     * Executes the given procedure if the current context is on the server
     * side.
     *
     * @param procedure The procedure to execute.
     */
    public static void onServerSide(ProcedureWithoutResult procedure) {
        if (isServerSide()) {
            procedure.invoke();
        }
    }
}
