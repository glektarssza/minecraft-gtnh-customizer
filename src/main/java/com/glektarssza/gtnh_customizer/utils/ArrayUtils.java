package com.glektarssza.gtnh_customizer.utils;

import java.util.Comparator;

/**
 * A collection of utilities for working with arrays.
 */
public class ArrayUtils {
    /**
     * A simple linear search to check if an array contains a value.
     *
     * @param haystack The array to search.
     * @param needle The value to check for.
     *
     * @return {@code true} if the array contains the value, {@code false}
     *         otherwise.
     */
    public static boolean contains(byte[] haystack, byte needle) {
        for (byte element : haystack) {
            if (element == needle) {
                return true;
            }
        }
        return false;
    }

    /**
     * A simple linear search to check if an array contains a value.
     *
     * @param haystack The array to search.
     * @param needle The value to check for.
     *
     * @return {@code true} if the array contains the value, {@code false}
     *         otherwise.
     */
    public static boolean contains(short[] haystack, short needle) {
        for (short element : haystack) {
            if (element == needle) {
                return true;
            }
        }
        return false;
    }

    /**
     * A simple linear search to check if an array contains a value.
     *
     * @param haystack The array to search.
     * @param needle The value to check for.
     *
     * @return {@code true} if the array contains the value, {@code false}
     *         otherwise.
     */
    public static boolean contains(int[] haystack, int needle) {
        for (int element : haystack) {
            if (element == needle) {
                return true;
            }
        }
        return false;
    }

    /**
     * A simple linear search to check if an array contains a value.
     *
     * @param haystack The array to search.
     * @param needle The value to check for.
     *
     * @return {@code true} if the array contains the value, {@code false}
     *         otherwise.
     */
    public static boolean contains(long[] haystack, long needle) {
        for (long element : haystack) {
            if (element == needle) {
                return true;
            }
        }
        return false;
    }

    /**
     * A simple linear search to check if an array contains a value.
     *
     * @param haystack The array to search.
     * @param needle The value to check for.
     *
     * @return {@code true} if the array contains the value, {@code false}
     *         otherwise.
     */
    public static boolean contains(float[] haystack, float needle) {
        for (float element : haystack) {
            if (element == needle) {
                return true;
            }
        }
        return false;
    }

    /**
     * A simple linear search to check if an array contains a value.
     *
     * @param haystack The array to search.
     * @param needle The value to check for.
     *
     * @return {@code true} if the array contains the value, {@code false}
     *         otherwise.
     */
    public static boolean contains(double[] haystack, double needle) {
        for (double element : haystack) {
            if (element == needle) {
                return true;
            }
        }
        return false;
    }

    /**
     * A simple linear search to check if an array contains a value.
     *
     * @param haystack The array to search.
     * @param needle The value to check for.
     *
     * @return {@code true} if the array contains the value, {@code false}
     *         otherwise.
     */
    public static <T> boolean contains(T[] haystack, T needle) {
        for (T element : haystack) {
            if (element == needle) {
                return true;
            }
        }
        return false;
    }

    /**
     * A simple linear search to check if an array contains a value.
     *
     * @param haystack The array to search.
     * @param needle The value to check for.
     *
     * @return {@code true} if the array contains the value, {@code false}
     *         otherwise.
     */
    public static <T> boolean contains(T[] haystack, T needle,
        Comparator<T> comparator) {
        for (T element : haystack) {
            if (comparator.compare(element, needle) == 0) {
                return true;
            }
        }
        return false;
    }
}
