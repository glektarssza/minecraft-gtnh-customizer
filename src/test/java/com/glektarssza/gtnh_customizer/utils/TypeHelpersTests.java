package com.glektarssza.gtnh_customizer.utils;

import java.util.Locale;
import java.util.Random;

import javax.annotation.RegEx;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import net.datafaker.Faker;

import com.glektarssza.gtnh_customizer.api.annotations.NotNull;

/**
 * Unit tests for the {@link TypeHelpers} class.
 */
public class TypeHelpersTests {
    /**
     * A pattern which matches against unsigned long integers.
     */
    @RegEx
    @NotNull
    public static final String UNSIGNED_LONG_INTEGER_PATTERN = "^[[:digit:]]{1,21}[lL]?$";

    /**
     * A pattern which matches against signed long integers.
     */
    @RegEx
    @NotNull
    public static final String SIGNED_LONG_INTEGER_PATTERN = "^-?[[:digit:]]{1,20}[lL]?$";

    /**
     * The fake data source.
     */
    @NotNull
    @SuppressWarnings("unused")
    private static Faker fakeData = new Faker(Locale.CANADA);

    /**
     * Method to run before all tests.
     */
    @BeforeAll
    public static void beforeAll() {
        final String rawFakerSeed = System.getenv().getOrDefault("FAKER_SEED",
            System.getProperty("FAKER_SEED"));
        if (rawFakerSeed != null
            && (rawFakerSeed.matches(UNSIGNED_LONG_INTEGER_PATTERN)
                || rawFakerSeed.matches(SIGNED_LONG_INTEGER_PATTERN))) {
            try {
                final long fakerSeed = Long.parseLong(rawFakerSeed, 10);
                fakeData = new Faker(Locale.CANADA, new Random(fakerSeed));
            } catch (final Throwable t) {
                // -- Do nothing
            }
        }
    }

    /**
     * Test that the {@link TypeHelpers#castToNonNull(Object, String)} method
     * throws a {@link NullPointerException} when passed a {@code null} value.
     */
    @Test
    public void testCastToNonNullThrowsOnNull() {
        // -- Given
        final Object value = null;

        // -- When
        final ThrowingCallable func = () -> {
            TypeHelpers.castToNonNull(value, "Object is null!");
        };

        // -- Then
        Assertions.assertThatCode(func).isInstanceOf(NullPointerException.class)
            .hasMessage("Object is null!");
    }

    /**
     * Test that the {@link TypeHelpers#castToNonNull(Object, String)} method
     * does not throw a {@link NullPointerException} when passed a
     * {@code non-null} value.
     */
    @Test
    public void testCastToNonNullDoesNotThrowOnNonNull() {
        // -- Given
        final Object value = new Object();

        // -- When
        final ThrowingCallable func = () -> {
            TypeHelpers.castToNonNull(value, "Object is null!");
        };

        // -- Then
        Assertions.assertThatCode(func).doesNotThrowAnyException();
    }
}
