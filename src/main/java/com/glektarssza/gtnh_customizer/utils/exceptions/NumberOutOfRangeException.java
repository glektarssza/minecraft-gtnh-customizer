package com.glektarssza.gtnh_customizer.utils.exceptions;

/**
 * An exception that signals a number was outside of the allowed range.
 */
public class NumberOutOfRangeException extends RuntimeException {

    /**
     * Create a new instance.
     */
    public NumberOutOfRangeException() {
        super();
    }

    /**
     * Create a new instance.
     *
     * @param number The number which was outside the allowed range.
     * @param min The minimum allowed value of the number, inclusive.
     * @param max The maximum allowed value of the number, inclusive.
     */
    public NumberOutOfRangeException(byte number, byte min, byte max) {
        super(String
            .format("Number '%d' was outside allowed range of '%d' to '%d'",
                number, min, max));
    }

    /**
     * Create a new instance.
     *
     * @param number The number which was outside the allowed range.
     * @param min The minimum allowed value of the number, inclusive.
     * @param max The maximum allowed value of the number, inclusive.
     */
    public NumberOutOfRangeException(short number, short min, short max) {
        super(String
            .format("Number '%d' was outside allowed range of '%d' to '%d'",
                number, min, max));
    }

    /**
     * Create a new instance.
     *
     * @param number The number which was outside the allowed range.
     * @param min The minimum allowed value of the number, inclusive.
     * @param max The maximum allowed value of the number, inclusive.
     */
    public NumberOutOfRangeException(int number, int min, int max) {
        super(String
            .format("Number '%d' was outside allowed range of '%d' to '%d'",
                number, min, max));
    }

    /**
     * Create a new instance.
     *
     * @param number The number which was outside the allowed range.
     * @param min The minimum allowed value of the number, inclusive.
     * @param max The maximum allowed value of the number, inclusive.
     */
    public NumberOutOfRangeException(long number, long min, long max) {
        super(String
            .format("Number '%d' was outside allowed range of '%d' to '%d'",
                number, min, max));
    }

    /**
     * Create a new instance.
     *
     * @param number The number which was outside the allowed range.
     * @param min The minimum allowed value of the number, inclusive.
     * @param max The maximum allowed value of the number, inclusive.
     */
    public NumberOutOfRangeException(float number, float min, float max) {
        super(String
            .format("Number '%f' was outside allowed range of '%f' to '%f'",
                number, min, max));
    }

    /**
     * Create a new instance.
     *
     * @param number The number which was outside the allowed range.
     * @param min The minimum allowed value of the number, inclusive.
     * @param max The maximum allowed value of the number, inclusive.
     */
    public NumberOutOfRangeException(double number, double min, double max) {
        super(String
            .format("Number '%d' was outside allowed range of '%d' to '%d'",
                number, min, max));
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing the exception.
     */
    public NumberOutOfRangeException(String message) {
        super(message);
    }

    /**
     * Create a new instance.
     *
     * @param cause The throwable that caused the new instance to be created.
     */
    public NumberOutOfRangeException(Throwable cause) {
        super(cause);
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing the exception.
     * @param cause The throwable that caused the new instance to be created.
     */
    public NumberOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a new instance.
     *
     * @param message A string describing the exception.
     * @param cause The throwable that caused the new instance to be created.
     * @param enableSuppression
     * @param writeableStackTrace
     */
    public NumberOutOfRangeException(String message, Throwable cause,
        boolean enableSuppression,
        boolean writeableStackTrace) {
        super(message, cause, enableSuppression, writeableStackTrace);
    }
}
