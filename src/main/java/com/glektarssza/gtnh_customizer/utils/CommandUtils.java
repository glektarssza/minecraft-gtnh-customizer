package com.glektarssza.gtnh_customizer.utils;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import net.minecraft.server.MinecraftServer;

import net.minecraftforge.common.DimensionManager;

import com.glektarssza.gtnh_customizer.api.v2.exception.ValueOutOfBoundsException;

/**
 * Utilities for commands.
 */
public final class CommandUtils {
    /**
     * No permissions required to run a command.
     */
    public static final int PERMISSION_NONE = 0;

    /**
     * Moderator permissions required to run a command.
     */
    public static final int PERMISSION_MODERATOR = 1;

    /**
     * Game master/operator permissions required to run a command.
     */
    public static final int PERMISSION_GAMEMASTER = 2;

    /**
     * Admin permissions required to run a command.
     */
    public static final int PERMISSION_ADMIN = 3;

    /**
     * Owner permissions required to run a command.
     */
    public static final int PERMISSION_OWNER = 4;

    /**
     * A regular expression for matching parseable boolean values.
     */
    private static final Pattern BOOLEAN_MATCHER = Pattern
        .compile("true|false|1|0", Pattern.CASE_INSENSITIVE);

    /**
     * A regular expression for matching parseable byte integer values.
     */
    private static final Pattern BYTE_MATCHER = Pattern
        .compile("-?\\d{1,3}");

    /**
     * A regular expression for matching parseable short integer values.
     */
    private static final Pattern SHORT_MATCHER = Pattern
        .compile("-?\\d{1,5}");

    /**
     * A regular expression for matching parseable integer values.
     */
    private static final Pattern INTEGER_MATCHER = Pattern
        .compile("-?\\d{1,10}");

    /**
     * A regular expression for matching parseable long integer values.
     */
    private static final Pattern LONG_MATCHER = Pattern
        .compile("-?\\d{1,19}");

    /**
     * A regular expression for matching parseable float values.
     */
    private static final Pattern FLOAT_MATCHER = Pattern
        .compile("-?\\d+(\\.\\d*)?");

    /**
     * A regular expression for matching parseable double values.
     */
    private static final Pattern DOUBLE_MATCHER = Pattern
        .compile("-?\\d+(\\.\\d*)?");

    /**
     * A regular expression for matching parseable number values.
     */
    private static final Pattern NUMERIC_MATCHER = Pattern
        .compile("-?\\d+(\\.\\d*)?");

    /**
     * A regular expression for matching parseable coordinate values.
     */
    private static final Pattern COORDINATE_MATCHER = Pattern
        .compile("(~(-?\\d+(\\.\\d*)?)?)|(-?\\d+(\\.\\d*)?)");

    /**
     * A regular expression for matching parseable relative coordinate values.
     */
    private static final Pattern RELATIVE_COORDINATE_MATCHER = Pattern
        .compile("~(-?\\d+(\\.\\d*)?)?");

    /**
     * Check if an argument is a boolean.
     *
     * @param args The arguments being passed to the command.
     * @param index The index to check.
     *
     * @return {@code true} if the argument being checked matches a boolean;
     *         {@code false} otherwise.
     */
    public static boolean isBooleanArgument(final String[] args,
        final int index) {
        if (index >= args.length) {
            return false;
        }
        return CommandUtils.BOOLEAN_MATCHER.matcher(args[index]).matches();
    }

    /**
     * Check if an argument is a byte.
     *
     * @param args The arguments being passed to the command.
     * @param index The index to check.
     *
     * @return {@code true} if the argument being checked matches a byte;
     *         {@code false} otherwise.
     */
    public static boolean isByteArgument(final String[] args, final int index) {
        if (index >= args.length) {
            return false;
        }
        return CommandUtils.BYTE_MATCHER.matcher(args[index]).matches();
    }

    /**
     * Check if an argument is a short.
     *
     * @param args The arguments being passed to the command.
     * @param index The index to check.
     *
     * @return {@code true} if the argument being checked matches a short;
     *         {@code false} otherwise.
     */
    public static boolean isShortArgument(final String[] args,
        final int index) {
        if (index >= args.length) {
            return false;
        }
        return CommandUtils.SHORT_MATCHER.matcher(args[index]).matches();
    }

    /**
     * Check if an argument is an integer.
     *
     * @param args The arguments being passed to the command.
     * @param index The index to check.
     *
     * @return {@code true} if the argument being checked matches an integer;
     *         {@code false} otherwise.
     */
    public static boolean isIntegerArgument(final String[] args,
        final int index) {
        if (index >= args.length) {
            return false;
        }
        return CommandUtils.INTEGER_MATCHER.matcher(args[index]).matches();
    }

    /**
     * Check if an argument is a float.
     *
     * @param args The arguments being passed to the command.
     * @param index The index to check.
     *
     * @return {@code true} if the argument being checked matches a float;
     *         {@code false} otherwise.
     */
    public static boolean isFloatArgument(final String[] args,
        final int index) {
        if (index >= args.length) {
            return false;
        }
        return CommandUtils.FLOAT_MATCHER.matcher(args[index]).matches();
    }

    /**
     * Check if an argument is a double.
     *
     * @param args The arguments being passed to the command.
     * @param index The index to check.
     *
     * @return {@code true} if the argument being checked matches a double;
     *         {@code false} otherwise.
     */
    public static boolean isDoubleArgument(final String[] args,
        final int index) {
        if (index >= args.length) {
            return false;
        }
        return CommandUtils.DOUBLE_MATCHER.matcher(args[index]).matches();
    }

    /**
     * Check if an argument is a numeric value.
     *
     * @param args The arguments being passed to the command.
     * @param index The index to check.
     *
     * @return {@code true} if the argument being checked matches a numeric
     *         value; {@code false} otherwise.
     */
    public static boolean isNumericArgument(final String[] args,
        final int index) {
        return CommandUtils.NUMERIC_MATCHER.matcher(args[index]).matches();
    }

    /**
     * Check if an argument is a coordinate value.
     *
     * @param args The arguments being passed to the command.
     * @param index The index to check.
     *
     * @return {@code true} if the argument being checked matches a coordinate
     *         value; {@code false} otherwise.
     */
    public static boolean isCoordinateArgument(final String[] args,
        final int index) {
        return CommandUtils.COORDINATE_MATCHER.matcher(args[index]).matches();
    }

    /**
     * Check if an argument is a relative coordinate value.
     *
     * @param args The arguments being passed to the command.
     * @param index The index to check.
     *
     * @return {@code true} if the argument being checked matches a relative
     *         coordinate value; {@code false} otherwise.
     */
    public static boolean isRelativeCoordinateArgument(final String[] args,
        final int index) {
        return CommandUtils.RELATIVE_COORDINATE_MATCHER.matcher(args[index])
            .matches();
    }

    /**
     * Get a list of the usernames of all players currently connected to the
     * game.
     *
     * @return A list of the usernames of all players currently connected to the
     *         game.
     */
    public static String[] getAllPlayerUsernames() {
        return MinecraftServer.getServer().getConfigurationManager()
            .getAllUsernames();
    }

    /**
     * Get a list of the usernames of all players currently connected to the
     * game.
     *
     * @return A list of the usernames of all players currently connected to the
     *         game.
     */
    public static List<String> getAllPlayerUsernamesIterable() {
        return Arrays
            .asList(MinecraftServer.getServer().getConfigurationManager()
                .getAllUsernames());
    }

    /**
     * Get a truncated list of the usernames of all players currently connected
     * to the game.
     *
     * @param limit The maximum number of items to return.
     *
     * @return A list of the usernames of all players currently connected to the
     *         game.
     */
    public static String[] getTruncatedPlayerUsernameList(final int limit) {
        return (String[]) Arrays.stream(CommandUtils.getAllPlayerUsernames())
            .limit(limit)
            .toArray();
    }

    /**
     * Get a truncated list of the usernames of all players currently connected
     * to the game.
     *
     * @param limit The maximum number of items to return.
     *
     * @return A list of the usernames of all players currently connected to the
     *         game.
     */
    public static List<String> getTruncatedPlayerUsernameIterable(
        final int limit) {
        return Arrays.stream(CommandUtils.getAllPlayerUsernames()).limit(limit)
            .collect(Collectors.toList());
    }

    /**
     * Get a list of all registered dimension IDs.
     *
     * @return A list of all registered dimension IDs.
     */
    public static Integer[] getAllDimensionIDs() {
        return DimensionManager.getIDs();
    }

    /**
     * Get a list of all registered dimension IDs.
     *
     * @return A list of all registered dimension IDs.
     */
    public static List<Integer> getAllDimensionIDsIterable() {
        return Arrays.asList(DimensionManager.getIDs());
    }

    /**
     * Get a truncated list of registered dimension IDs.
     *
     * @param limit The maximum number of items to return.
     *
     * @return A truncated list of registered dimension IDs.
     */
    public static Integer[] getTruncatedDimensionIDsList(final int limit) {
        return Arrays.stream(CommandUtils.getAllDimensionIDs()).limit(limit)
            .toArray(Integer[]::new);
    }

    /**
     * Get a truncated list of registered dimension IDs.
     *
     * @param limit The maximum number of items to return.
     *
     * @return A truncated list of registered dimension IDs.
     */
    public static List<Integer> getTruncatedDimensionIDsIterable(
        final int limit) {
        return Arrays.stream(CommandUtils.getAllDimensionIDs()).limit(limit)
            .collect(Collectors.toList());
    }

    /**
     * Get a list of all registered dimension names.
     *
     * @return A list of all registered dimension names.
     */
    public static String[] getAllDimensionNames() {
        return (String[]) CommandUtils.getAllDimensionIDsIterable()
            .parallelStream()
            .map(id -> DimensionManager.getProvider(id).getDimensionName())
            .toArray();
    }

    /**
     * Get a list of all registered dimension IDs.
     *
     * @return A list of all registered dimension IDs.
     */
    public static List<String> getAllDimensionNamesIterable() {
        return CommandUtils.getAllDimensionIDsIterable().parallelStream()
            .map(id -> DimensionManager.getProvider(id).getDimensionName())
            .collect(Collectors.toList());
    }

    /**
     * Get a truncated list of registered dimension names.
     *
     * @return A truncated list of registered dimension names.
     */
    public static String[] getTruncatedDimensionNamesList(final int limit) {
        return (String[]) CommandUtils.getTruncatedDimensionIDsIterable(limit)
            .parallelStream()
            .map(id -> DimensionManager.getProvider(id).getDimensionName())
            .toArray();
    }

    /**
     * Get a truncated list of registered dimension IDs.
     *
     * @return A truncated list of registered dimension IDs.
     */
    public static List<String> getTruncatedDimensionNamesIterable(
        final int limit) {
        return CommandUtils.getTruncatedDimensionIDsIterable(limit)
            .parallelStream()
            .map(id -> DimensionManager.getProvider(id).getDimensionName())
            .collect(Collectors.toList());
    }

    /**
     * Get the ID of first known dimension with the given name.
     *
     * @param name The name of the dimension to get the ID of.
     *
     * @return The ID of first known dimension with the given name.
     */
    public static int findDimensionIDFromName(final String name) {
        return CommandUtils.getAllDimensionIDsIterable()
            .stream().map(DimensionManager::getProvider)
            .filter(provider -> provider.getDimensionName().equals(name))
            .findFirst()
            .orElseThrow(NoSuchElementException::new).dimensionId;
    }

    public static byte parseByteArgument(final String arg)
        throws NumberFormatException {
        return CommandUtils.parseByteArgument(arg, 10);
    }

    public static byte parseByteArgument(final String arg, final int radix)
        throws NumberFormatException {
        if (!CommandUtils.INTEGER_MATCHER.matcher(arg).matches()) {
            throw new NumberFormatException(String.format(
                "Value '%s' does not match the format of an integer", arg));
        }
        return Byte.parseByte(arg, radix);
    }

    public static byte parseByteArgument(final String arg, final byte min,
        final byte max)
        throws NumberFormatException, ValueOutOfBoundsException {
        return CommandUtils.parseByteArgument(arg, 10, min, max);
    }

    public static byte parseByteArgument(final String arg, final int radix,
        final byte min,
        final byte max)
        throws NumberFormatException, ValueOutOfBoundsException {
        final byte result = CommandUtils.parseByteArgument(arg, radix);
        if (result < min || result > max) {
            throw new ValueOutOfBoundsException(result, min, max);
        }
        return result;
    }

    public static byte parseOptionalByteArgument(final String arg,
        final byte defaultValue) {
        try {
            return CommandUtils.parseByteArgument(arg);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static byte parseOptionalByteArgument(final String arg,
        final int radix,
        final byte defaultValue) {
        try {
            return CommandUtils.parseByteArgument(arg, radix);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static byte parseOptionalByteArgument(final String arg,
        final byte min, final byte max,
        final byte defaultValue) {
        return CommandUtils.parseOptionalByteArgument(arg, 10, min, max,
            defaultValue);
    }

    public static byte parseOptionalByteArgument(final String arg,
        final int radix,
        final byte min, final byte max, final byte defaultValue) {
        try {
            return CommandUtils.parseByteArgument(arg, radix, min, max);
        } catch (final NumberFormatException t) {
            return defaultValue;
        } catch (final ValueOutOfBoundsException t) {
            return defaultValue;
        }
    }

    public static byte parseBlockRelativeByteArgument(final String arg,
        final byte baseValue)
        throws NumberFormatException {
        return CommandUtils.parseBlockRelativeByteArgument(arg, baseValue, 10);
    }

    public static byte parseBlockRelativeByteArgument(final String arg,
        final byte baseValue, final int radix)
        throws NumberFormatException {
        byte offset;
        if (arg.startsWith("~")) {
            offset = arg.length() == 1 ? 0
                : CommandUtils.parseByteArgument(arg.substring(1), radix);
        } else {
            return CommandUtils.parseByteArgument(arg, radix);
        }
        if (offset < Byte.MAX_VALUE - baseValue) {
            return (byte) (baseValue + offset);
        }
        return Byte.MAX_VALUE;
    }

    public static byte parseBlockRelativeByteArgument(final String arg,
        final byte baseValue, final byte min, final byte max)
        throws NumberFormatException {
        return CommandUtils.parseBlockRelativeByteArgument(arg, baseValue, 10,
            min, max);
    }

    public static byte parseBlockRelativeByteArgument(final String arg,
        final byte baseValue, final int radix, final byte min, final byte max)
        throws NumberFormatException {
        final byte result = CommandUtils.parseBlockRelativeByteArgument(arg,
            baseValue,
            radix);
        if (result < min || result > max) {
            throw new ValueOutOfBoundsException(result, min, max);
        }
        return result;
    }

    public static byte parseBlockRelativeOptionalByteArgument(final String arg,
        final byte baseValue,
        final byte defaultValue) {
        return CommandUtils.parseBlockRelativeOptionalByteArgument(arg,
            baseValue,
            10, defaultValue);
    }

    public static byte parseBlockRelativeOptionalByteArgument(final String arg,
        final byte baseValue, final int radix,
        final byte defaultValue) {
        try {
            return CommandUtils.parseBlockRelativeByteArgument(arg, baseValue,
                radix);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static byte parseBlockRelativeOptionalByteArgument(final String arg,
        final byte baseValue, final byte min,
        final byte max, final byte defaultValue) {
        return CommandUtils.parseBlockRelativeOptionalByteArgument(arg,
            baseValue,
            10, min, max, defaultValue);
    }

    public static byte parseBlockRelativeOptionalByteArgument(final String arg,
        final byte baseValue, final int radix,
        final byte min, final byte max, final byte defaultValue) {
        try {
            return CommandUtils.parseBlockRelativeByteArgument(arg, baseValue,
                radix, min, max);
        } catch (final NumberFormatException t) {
            return defaultValue;
        } catch (final ValueOutOfBoundsException t) {
            return defaultValue;
        }
    }

    public static short parseShortArgument(final String arg)
        throws NumberFormatException {
        return CommandUtils.parseShortArgument(arg, 10);
    }

    public static short parseShortArgument(final String arg, final int radix)
        throws NumberFormatException {
        if (!CommandUtils.SHORT_MATCHER.matcher(arg).matches()) {
            throw new NumberFormatException(String.format(
                "Value '%s' does not match the format of an integer", arg));
        }
        return Short.parseShort(arg, radix);
    }

    public static short parseShortArgument(final String arg, final short min,
        final short max)
        throws NumberFormatException, ValueOutOfBoundsException {
        return CommandUtils.parseShortArgument(arg, 10, min, max);
    }

    public static short parseShortArgument(final String arg, final int radix,
        final short min,
        final short max)
        throws NumberFormatException, ValueOutOfBoundsException {
        final short result = CommandUtils.parseShortArgument(arg, radix);
        if (result < min || result > max) {
            throw new ValueOutOfBoundsException(result, min, max);
        }
        return result;
    }

    public static short parseOptionalShortArgument(final String arg,
        final short defaultValue) {
        try {
            return CommandUtils.parseShortArgument(arg);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static short parseOptionalShortArgument(final String arg,
        final int radix,
        final short defaultValue) {
        try {
            return CommandUtils.parseShortArgument(arg, radix);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static short parseOptionalShortArgument(final String arg,
        final short min,
        final short max, final short defaultValue) {
        return CommandUtils.parseOptionalShortArgument(arg, 10, min, max,
            defaultValue);
    }

    public static short parseOptionalShortArgument(final String arg,
        final int radix,
        final short min, final short max, final short defaultValue) {
        try {
            return CommandUtils.parseShortArgument(arg, radix, min, max);
        } catch (final NumberFormatException t) {
            return defaultValue;
        } catch (final ValueOutOfBoundsException t) {
            return defaultValue;
        }
    }

    public static short parseBlockRelativeShortArgument(final String arg,
        final short baseValue)
        throws NumberFormatException {
        return CommandUtils.parseBlockRelativeShortArgument(arg, baseValue, 10);
    }

    public static short parseBlockRelativeShortArgument(final String arg,
        final short baseValue, final int radix)
        throws NumberFormatException {
        short offset;
        if (arg.startsWith("~")) {
            offset = arg.length() == 1 ? 0
                : CommandUtils.parseShortArgument(arg.substring(1), radix);
        } else {
            return CommandUtils.parseShortArgument(arg, radix);
        }
        if (offset < Short.MAX_VALUE - baseValue) {
            return (short) (baseValue + offset);
        }
        return Short.MAX_VALUE;
    }

    public static short parseBlockRelativeShortArgument(final String arg,
        final short baseValue, final short min, final short max)
        throws NumberFormatException {
        return CommandUtils.parseBlockRelativeShortArgument(arg, baseValue, 10,
            min, max);
    }

    public static short parseBlockRelativeShortArgument(final String arg,
        final short baseValue, final int radix, final short min,
        final short max)
        throws NumberFormatException {
        final short result = CommandUtils.parseBlockRelativeShortArgument(arg,
            baseValue,
            radix);
        if (result < min || result > max) {
            throw new ValueOutOfBoundsException(result, min, max);
        }
        return result;
    }

    public static short parseBlockRelativeOptionalShortArgument(
        final String arg,
        final short baseValue,
        final short defaultValue) {
        return CommandUtils.parseBlockRelativeOptionalShortArgument(arg,
            baseValue,
            10, defaultValue);
    }

    public static short parseBlockRelativeOptionalShortArgument(
        final String arg,
        final short baseValue, final int radix,
        final short defaultValue) {
        try {
            return CommandUtils.parseBlockRelativeShortArgument(arg, baseValue,
                radix);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static short parseBlockRelativeOptionalShortArgument(
        final String arg,
        final short baseValue, final short min,
        final short max, final short defaultValue) {
        return CommandUtils.parseBlockRelativeOptionalShortArgument(arg,
            baseValue,
            10, min, max, defaultValue);
    }

    public static short parseBlockRelativeOptionalShortArgument(
        final String arg,
        final short baseValue, final int radix,
        final short min, final short max, final short defaultValue) {
        try {
            return CommandUtils.parseBlockRelativeShortArgument(arg, baseValue,
                radix, min, max);
        } catch (final NumberFormatException t) {
            return defaultValue;
        } catch (final ValueOutOfBoundsException t) {
            return defaultValue;
        }
    }

    public static int parseIntegerArgument(final String arg)
        throws NumberFormatException {
        return CommandUtils.parseIntegerArgument(arg, 10);
    }

    public static int parseIntegerArgument(final String arg,
        final int radix)
        throws NumberFormatException {
        if (!CommandUtils.INTEGER_MATCHER.matcher(arg).matches()) {
            throw new NumberFormatException(String.format(
                "Value '%s' does not match the format of an integer", arg));
        }
        return Integer.parseInt(arg, radix);
    }

    public static int parseIntegerArgument(final String arg,
        final int min, final int max)
        throws NumberFormatException, ValueOutOfBoundsException {
        return CommandUtils.parseIntegerArgument(arg, 10, min, max);
    }

    public static int parseIntegerArgument(final String arg,
        final int radix, final int min, final int max)
        throws NumberFormatException, ValueOutOfBoundsException {
        final int result = CommandUtils.parseIntegerArgument(arg, radix);
        if (result < min || result > max) {
            throw new ValueOutOfBoundsException(result, min, max);
        }
        return result;
    }

    public static int parseOptionalIntegerArgument(final String arg,
        final int defaultValue) {
        try {
            return CommandUtils.parseIntegerArgument(arg);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static int parseOptionalIntegerArgument(final String arg,
        final int radix,
        final int defaultValue) {
        try {
            return CommandUtils.parseIntegerArgument(arg, radix);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static int parseOptionalIntegerArgument(final String arg,
        final int min, final int max,
        final int defaultValue) {
        return CommandUtils.parseOptionalIntegerArgument(arg, 10, min, max,
            defaultValue);
    }

    public static int parseOptionalIntegerArgument(final String arg,
        final int radix,
        final int min, final int max, final int defaultValue) {
        try {
            return CommandUtils.parseIntegerArgument(arg, radix, min, max);
        } catch (final NumberFormatException t) {
            return defaultValue;
        } catch (final ValueOutOfBoundsException t) {
            return defaultValue;
        }
    }

    public static int parseBlockRelativeIntegerArgument(final String arg,
        final int baseValue)
        throws NumberFormatException {
        return CommandUtils.parseBlockRelativeIntegerArgument(arg, baseValue,
            10);
    }

    public static int parseBlockRelativeIntegerArgument(final String arg,
        final int baseValue, final int radix)
        throws NumberFormatException {
        int offset;
        if (arg.startsWith("~")) {
            offset = arg.length() == 1 ? 0
                : CommandUtils.parseIntegerArgument(arg.substring(1), radix);
        } else {
            return CommandUtils.parseIntegerArgument(arg, radix);
        }
        if (offset < Integer.MAX_VALUE - baseValue) {
            return baseValue + offset;
        }
        return Integer.MAX_VALUE;
    }

    public static int parseBlockRelativeIntegerArgument(final String arg,
        final int baseValue, final int min, final int max)
        throws NumberFormatException {
        return CommandUtils.parseBlockRelativeIntegerArgument(arg, baseValue,
            10,
            min, max);
    }

    public static int parseBlockRelativeIntegerArgument(final String arg,
        final int baseValue, final int radix, final int min, final int max)
        throws NumberFormatException {
        final int result = CommandUtils.parseBlockRelativeIntegerArgument(arg,
            baseValue,
            radix);
        if (result < min || result > max) {
            throw new ValueOutOfBoundsException(result, min, max);
        }
        return result;
    }

    public static int parseBlockRelativeOptionalIntegerArgument(
        final String arg,
        final int baseValue, final int defaultValue) {
        return CommandUtils.parseBlockRelativeOptionalIntegerArgument(arg,
            baseValue,
            10, defaultValue);
    }

    public static int parseBlockRelativeOptionalIntegerArgument(
        final String arg,
        final int baseValue, final int radix,
        final int defaultValue) {
        try {
            return CommandUtils.parseBlockRelativeIntegerArgument(arg,
                baseValue,
                radix);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static int parseBlockRelativeOptionalIntegerArgument(
        final String arg,
        final int baseValue, final int min, final int max,
        final int defaultValue) {
        return CommandUtils.parseBlockRelativeOptionalIntegerArgument(arg,
            baseValue,
            10, min, max, defaultValue);
    }

    public static int parseBlockRelativeOptionalIntegerArgument(
        final String arg,
        final int baseValue, final int radix, final int min,
        final int max, final int defaultValue) {
        try {
            return CommandUtils.parseBlockRelativeIntegerArgument(arg,
                baseValue,
                radix, min, max);
        } catch (final NumberFormatException t) {
            return defaultValue;
        } catch (final ValueOutOfBoundsException t) {
            return defaultValue;
        }
    }

    public static long parseLongArgument(final String arg)
        throws NumberFormatException {
        return CommandUtils.parseLongArgument(arg, 10);
    }

    public static long parseLongArgument(final String arg,
        final int radix)
        throws NumberFormatException {
        if (!CommandUtils.LONG_MATCHER.matcher(arg).matches()) {
            throw new NumberFormatException(String.format(
                "Value '%s' does not match the format of an long", arg));
        }
        return Long.parseLong(arg, radix);
    }

    public static long parseLongArgument(final String arg,
        final int min, final int max)
        throws NumberFormatException, ValueOutOfBoundsException {
        return CommandUtils.parseLongArgument(arg, 10, min, max);
    }

    public static long parseLongArgument(final String arg,
        final int radix, final long min, final long max)
        throws NumberFormatException, ValueOutOfBoundsException {
        final long result = CommandUtils.parseLongArgument(arg, radix);
        if (result < min || result > max) {
            throw new ValueOutOfBoundsException(result, min, max);
        }
        return result;
    }

    public static long parseOptionalLongArgument(final String arg,
        final long defaultValue) {
        try {
            return CommandUtils.parseLongArgument(arg);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static long parseOptionalLongArgument(final String arg,
        final int radix,
        final long defaultValue) {
        try {
            return CommandUtils.parseLongArgument(arg, radix);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static long parseOptionalLongArgument(final String arg,
        final long min, final long max,
        final long defaultValue) {
        return CommandUtils.parseOptionalLongArgument(arg, 10, min, max,
            defaultValue);
    }

    public static long parseOptionalLongArgument(final String arg,
        final int radix,
        final long min, final long max, final long defaultValue) {
        try {
            return CommandUtils.parseLongArgument(arg, radix, min, max);
        } catch (final NumberFormatException t) {
            return defaultValue;
        } catch (final ValueOutOfBoundsException t) {
            return defaultValue;
        }
    }

    public static long parseBlockRelativeLongArgument(final String arg,
        final long baseValue)
        throws NumberFormatException {
        return CommandUtils.parseBlockRelativeLongArgument(arg, baseValue, 10);
    }

    public static long parseBlockRelativeLongArgument(final String arg,
        final long baseValue, final int radix)
        throws NumberFormatException {
        long offset;
        if (arg.startsWith("~")) {
            offset = arg.length() == 1 ? 0
                : CommandUtils.parseLongArgument(arg.substring(1), radix);
        } else {
            return CommandUtils.parseLongArgument(arg, radix);
        }
        if (offset < Long.MAX_VALUE - baseValue) {
            return baseValue + offset;
        }
        return Long.MAX_VALUE;
    }

    public static long parseBlockRelativeLongArgument(final String arg,
        final long baseValue, final long min, final long max)
        throws NumberFormatException {
        return CommandUtils.parseBlockRelativeLongArgument(arg, baseValue, 10,
            min, max);
    }

    public static long parseBlockRelativeLongArgument(final String arg,
        final long baseValue, final int radix, final long min, final long max)
        throws NumberFormatException {
        final long result = CommandUtils.parseBlockRelativeLongArgument(arg,
            baseValue,
            radix);
        if (result < min || result > max) {
            throw new ValueOutOfBoundsException(result, min, max);
        }
        return result;
    }

    public static long parseBlockRelativeOptionalLongArgument(final String arg,
        final long baseValue, final long defaultValue) {
        return CommandUtils.parseBlockRelativeOptionalLongArgument(arg,
            baseValue,
            10, defaultValue);
    }

    public static long parseBlockRelativeOptionalLongArgument(final String arg,
        final long baseValue, final int radix,
        final long defaultValue) {
        try {
            return CommandUtils.parseBlockRelativeLongArgument(arg, baseValue,
                radix);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static long parseBlockRelativeOptionalLongArgument(final String arg,
        final long baseValue, final long min, final long max,
        final long defaultValue) {
        return CommandUtils.parseBlockRelativeOptionalLongArgument(arg,
            baseValue,
            10, min, max, defaultValue);
    }

    public static long parseBlockRelativeOptionalLongArgument(final String arg,
        final long baseValue, final int radix, final long min,
        final long max, final long defaultValue) {
        try {
            return CommandUtils.parseBlockRelativeLongArgument(arg, baseValue,
                radix, min, max);
        } catch (final NumberFormatException t) {
            return defaultValue;
        } catch (final ValueOutOfBoundsException t) {
            return defaultValue;
        }
    }

    public static float parseFloatArgument(final String arg)
        throws NumberFormatException {
        return CommandUtils.parseFloatArgument(arg, false);
    }

    public static float parseFloatArgument(final String arg,
        final boolean centerToBlock)
        throws NumberFormatException {
        if (!CommandUtils.FLOAT_MATCHER.matcher(arg).matches()) {
            throw new NumberFormatException(String.format(
                "Value '%s' does not match the format of a float", arg));
        }
        return Float.parseFloat(arg) + (centerToBlock ? 0.5f : 0.0f);
    }

    public static float parseFloatArgument(final String arg,
        final float min, final float max)
        throws NumberFormatException, ValueOutOfBoundsException {
        return CommandUtils.parseFloatArgument(arg, false, min, max);
    }

    public static float parseFloatArgument(final String arg,
        final boolean centerToBlock, final float min, final float max)
        throws NumberFormatException, ValueOutOfBoundsException {
        final float result = CommandUtils.parseFloatArgument(arg,
            centerToBlock);
        if (result < min || result > max) {
            throw new ValueOutOfBoundsException(result, min, max);
        }
        return result;
    }

    public static float parseOptionalFloatArgument(final String arg,
        final float defaultValue) {
        try {
            return CommandUtils.parseFloatArgument(arg);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static float parseOptionalFloatArgument(final String arg,
        final boolean centerToBlock, final float defaultValue) {
        try {
            return CommandUtils.parseFloatArgument(arg, centerToBlock);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static float parseOptionalFloatArgument(final String arg,
        final float min,
        final float max, final float defaultValue) {
        return CommandUtils.parseOptionalFloatArgument(arg, false, min, max,
            defaultValue);
    }

    public static float parseOptionalFloatArgument(final String arg,
        final boolean centerToBlock, final float min, final float max,
        final float defaultValue) {
        try {
            return CommandUtils.parseFloatArgument(arg, centerToBlock, min,
                max);
        } catch (final NumberFormatException t) {
            return defaultValue;
        } catch (final ValueOutOfBoundsException t) {
            return defaultValue;
        }
    }

    public static float parseBlockRelativeFloatArgument(final String arg,
        final float baseValue)
        throws NumberFormatException {
        return CommandUtils.parseBlockRelativeFloatArgument(arg, baseValue,
            false);
    }

    public static float parseBlockRelativeFloatArgument(final String arg,
        final float baseValue, final boolean centerToBlock)
        throws NumberFormatException {
        float offset;
        if (arg.startsWith("~")) {
            offset = arg.length() == 1 ? 0.0f
                : CommandUtils.parseFloatArgument(arg.substring(1));
        } else {
            return CommandUtils.parseFloatArgument(arg)
                + (centerToBlock ? 0.5f : 0.0f);
        }
        if (offset < Float.MAX_VALUE - baseValue) {
            return baseValue + offset + (centerToBlock ? 0.5f : 0.0f);
        }
        return Float.MAX_VALUE;
    }

    public static float parseBlockRelativeFloatArgument(final String arg,
        final float baseValue, final float min, final float max)
        throws NumberFormatException {
        return CommandUtils.parseBlockRelativeFloatArgument(arg, baseValue,
            false,
            min, max);
    }

    public static float parseBlockRelativeFloatArgument(final String arg,
        final float baseValue, final boolean centerToBlock, final float min,
        final float max)
        throws NumberFormatException {
        final float result = CommandUtils.parseBlockRelativeFloatArgument(arg,
            baseValue,
            centerToBlock);
        if (result < min || result > max) {
            throw new ValueOutOfBoundsException(result, min, max);
        }
        return result;
    }

    public static float parseBlockRelativeOptionalFloatArgument(
        final String arg,
        final float baseValue,
        final float defaultValue) {
        return CommandUtils.parseBlockRelativeOptionalFloatArgument(arg,
            baseValue,
            false, defaultValue);
    }

    public static float parseBlockRelativeOptionalFloatArgument(
        final String arg,
        final float baseValue,
        final boolean centerToBlock, final float defaultValue) {
        try {
            return CommandUtils.parseBlockRelativeFloatArgument(arg, baseValue,
                centerToBlock);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static float parseBlockRelativeOptionalFloatArgument(
        final String arg,
        final float baseValue, final float min,
        final float max, final float defaultValue) {
        return CommandUtils.parseBlockRelativeOptionalFloatArgument(arg,
            baseValue,
            false, min, max, defaultValue);
    }

    public static float parseBlockRelativeOptionalFloatArgument(
        final String arg,
        final float baseValue,
        final boolean centerToBlock, final float min, final float max,
        final float defaultValue) {
        try {
            return CommandUtils.parseBlockRelativeFloatArgument(arg, baseValue,
                centerToBlock, min, max);
        } catch (final NumberFormatException t) {
            return defaultValue;
        } catch (final ValueOutOfBoundsException t) {
            return defaultValue;
        }
    }

    public static double parseDoubleArgument(final String arg)
        throws NumberFormatException {
        return CommandUtils.parseDoubleArgument(arg, false);
    }

    public static double parseDoubleArgument(final String arg,
        final boolean centerToBlock)
        throws NumberFormatException {
        if (!CommandUtils.DOUBLE_MATCHER.matcher(arg).matches()) {
            throw new NumberFormatException(String.format(
                "Value '%s' does not match the format of a double", arg));
        }
        return Double.parseDouble(arg) + (centerToBlock ? 0.5f : 0.0f);
    }

    public static double parseDoubleArgument(final String arg,
        final double min, final double max)
        throws NumberFormatException, ValueOutOfBoundsException {
        return CommandUtils.parseDoubleArgument(arg, false, min, max);
    }

    public static double parseDoubleArgument(final String arg,
        final boolean centerToBlock, final double min, final double max)
        throws NumberFormatException, ValueOutOfBoundsException {
        final double result = CommandUtils.parseDoubleArgument(arg,
            centerToBlock);
        if (result < min || result > max) {
            throw new ValueOutOfBoundsException(result, min, max);
        }
        return result;
    }

    public static double parseOptionalDoubleArgument(final String arg,
        final double defaultValue) {
        try {
            return CommandUtils.parseDoubleArgument(arg);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static double parseOptionalDoubleArgument(final String arg,
        final boolean centerToBlock, final double defaultValue) {
        try {
            return CommandUtils.parseDoubleArgument(arg, centerToBlock);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static double parseOptionalDoubleArgument(final String arg,
        final double min,
        final double max, final double defaultValue) {
        return CommandUtils.parseOptionalDoubleArgument(arg, false, min, max,
            defaultValue);
    }

    public static double parseOptionalDoubleArgument(final String arg,
        final boolean centerToBlock, final double min, final double max,
        final double defaultValue) {
        try {
            return CommandUtils.parseDoubleArgument(arg, centerToBlock, min,
                max);
        } catch (final NumberFormatException t) {
            return defaultValue;
        } catch (final ValueOutOfBoundsException t) {
            return defaultValue;
        }
    }

    public static double parseBlockRelativeDoubleArgument(final String arg,
        final double baseValue)
        throws NumberFormatException {
        return CommandUtils.parseBlockRelativeDoubleArgument(arg, baseValue,
            false);
    }

    public static double parseBlockRelativeDoubleArgument(final String arg,
        final double baseValue, final boolean centerToBlock)
        throws NumberFormatException {
        double offset;
        if (arg.startsWith("~")) {
            offset = arg.length() == 1 ? 0.0
                : CommandUtils.parseDoubleArgument(arg.substring(1));
        } else {
            return CommandUtils.parseDoubleArgument(arg)
                + (centerToBlock ? 0.5f : 0.0f);
        }
        if (offset < Double.MAX_VALUE - baseValue) {
            return baseValue + offset + (centerToBlock ? 0.5f : 0.0f);
        }
        return Double.MAX_VALUE;
    }

    public static double parseBlockRelativeDoubleArgument(final String arg,
        final double baseValue, final double min, final double max)
        throws NumberFormatException {
        return CommandUtils.parseBlockRelativeDoubleArgument(arg, baseValue,
            false,
            min, max);
    }

    public static double parseBlockRelativeDoubleArgument(final String arg,
        final double baseValue, final boolean centerToBlock, final double min,
        final double max)
        throws NumberFormatException {
        final double result = CommandUtils.parseBlockRelativeDoubleArgument(arg,
            baseValue,
            centerToBlock);
        if (result < min || result > max) {
            throw new ValueOutOfBoundsException(result, min, max);
        }
        return result;
    }

    public static double parseBlockRelativeOptionalDoubleArgument(
        final String arg,
        final double baseValue,
        final double defaultValue) {
        return CommandUtils.parseBlockRelativeOptionalDoubleArgument(arg,
            baseValue,
            false, defaultValue);
    }

    public static double parseBlockRelativeOptionalDoubleArgument(
        final String arg,
        final double baseValue,
        final boolean centerToBlock, final double defaultValue) {
        try {
            return CommandUtils.parseBlockRelativeDoubleArgument(arg, baseValue,
                centerToBlock);
        } catch (final NumberFormatException t) {
            return defaultValue;
        }
    }

    public static double parseBlockRelativeOptionalDoubleArgument(
        final String arg,
        final double baseValue, final double min,
        final double max, final double defaultValue) {
        return CommandUtils.parseBlockRelativeOptionalDoubleArgument(arg,
            baseValue,
            false, min, max, defaultValue);
    }

    public static double parseBlockRelativeOptionalDoubleArgument(
        final String arg,
        final double baseValue,
        final boolean centerToBlock, final double min, final double max,
        final double defaultValue) {
        try {
            return CommandUtils.parseBlockRelativeDoubleArgument(arg, baseValue,
                centerToBlock, min, max);
        } catch (final NumberFormatException t) {
            return defaultValue;
        } catch (final ValueOutOfBoundsException t) {
            return defaultValue;
        }
    }
}
