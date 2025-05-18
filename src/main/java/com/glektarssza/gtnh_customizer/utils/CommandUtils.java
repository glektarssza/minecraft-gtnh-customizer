package com.glektarssza.gtnh_customizer.utils;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import net.minecraftforge.common.DimensionManager;

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
     * A regular expression for matching parsable boolean values.
     */
    private static final Pattern BOOLEAN_MATCHER = Pattern
        .compile("true|false|1|0", Pattern.CASE_INSENSITIVE);

    /**
     * A regular expression for matching parsable int values.
     */
    private static final Pattern BYTE_MATCHER = Pattern
        .compile("-?\\d{1,3}");

    /**
     * A regular expression for matching parsable int values.
     */
    private static final Pattern SHORT_MATCHER = Pattern
        .compile("-?\\d{1,5}");

    /**
     * A regular expression for matching parsable int values.
     */
    private static final Pattern INTEGER_MATCHER = Pattern
        .compile("-?\\d{1,10}");

    /**
     * A regular expression for matching parsable float values.
     */
    private static final Pattern FLOAT_MATCHER = Pattern
        .compile("-?\\d+(\\.\\d*)?");

    /**
     * A regular expression for matching parsable double values.
     */
    private static final Pattern DOUBLE_MATCHER = Pattern
        .compile("-?\\d+(\\.\\d*)?");

    /**
     * A regular expression for matching parsable number values.
     */
    private static final Pattern NUMERIC_MATCHER = Pattern
        .compile("-?\\d+(\\.\\d*)?");

    /**
     * A regular expression for matching parsable coordinate values.
     */
    private static final Pattern COORDINATE_MATCHER = Pattern
        .compile("(~(-?\\d+(\\.\\d*)?)?)|(-?\\d+(\\.\\d*)?)");

    /**
     * A regular expression for matching parsable relative coordinate values.
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
    public static boolean isBooleanArgument(String[] args, int index) {
        if (index >= args.length) {
            return false;
        }
        return BOOLEAN_MATCHER.matcher(args[index]).matches();
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
    public static boolean isByteArgument(String[] args, int index) {
        if (index >= args.length) {
            return false;
        }
        return BYTE_MATCHER.matcher(args[index]).matches();
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
    public static boolean isShortArgument(String[] args, int index) {
        if (index >= args.length) {
            return false;
        }
        return SHORT_MATCHER.matcher(args[index]).matches();
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
    public static boolean isIntegerArgument(String[] args, int index) {
        if (index >= args.length) {
            return false;
        }
        return INTEGER_MATCHER.matcher(args[index]).matches();
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
    public static boolean isFloatArgument(String[] args, int index) {
        if (index >= args.length) {
            return false;
        }
        return FLOAT_MATCHER.matcher(args[index]).matches();
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
    public static boolean isDoubleArgument(String[] args, int index) {
        if (index >= args.length) {
            return false;
        }
        return DOUBLE_MATCHER.matcher(args[index]).matches();
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
    public static boolean isNumericArgument(String[] args, int index) {
        return NUMERIC_MATCHER.matcher(args[index]).matches();
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
    public static boolean isCoordinateArgument(String[] args, int index) {
        return COORDINATE_MATCHER.matcher(args[index]).matches();
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
    public static boolean isRelativeCoordinateArgument(String[] args,
        int index) {
        return RELATIVE_COORDINATE_MATCHER.matcher(args[index]).matches();
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
    public static String[] getTruncatedPlayerUsernameList(int limit) {
        return (String[]) Arrays.stream(getAllPlayerUsernames()).limit(limit)
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
    public static List<String> getTruncatedPlayerUsernameIterable(int limit) {
        return Arrays.stream(getAllPlayerUsernames()).limit(limit)
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
    public static Integer[] getTruncatedDimensionIDsList(int limit) {
        return Arrays.stream(getAllDimensionIDs()).limit(limit)
            .toArray(Integer[]::new);
    }

    /**
     * Get a truncated list of registered dimension IDs.
     *
     * @param limit The maximum number of items to return.
     *
     * @return A truncated list of registered dimension IDs.
     */
    public static List<Integer> getTruncatedDimensionIDsIterable(int limit) {
        return Arrays.stream(getAllDimensionIDs()).limit(limit)
            .collect(Collectors.toList());
    }

    /**
     * Get a list of all registered dimension names.
     *
     * @return A list of all registered dimension names.
     */
    public static String[] getAllDimensionNames() {
        return (String[]) getAllDimensionIDsIterable().parallelStream()
            .map((id) -> DimensionManager.getProvider(id).getDimensionName())
            .toArray();
    }

    /**
     * Get a list of all registered dimension IDs.
     *
     * @return A list of all registered dimension IDs.
     */
    public static List<String> getAllDimensionNamesIterable() {
        return getAllDimensionIDsIterable().parallelStream()
            .map((id) -> DimensionManager.getProvider(id).getDimensionName())
            .collect(Collectors.toList());
    }

    /**
     * Get a truncated list of registered dimension names.
     *
     * @return A truncated list of registered dimension names.
     */
    public static String[] getTruncatedDimensionNamesList(int limit) {
        return (String[]) getTruncatedDimensionIDsIterable(limit)
            .parallelStream()
            .map((id) -> DimensionManager.getProvider(id).getDimensionName())
            .toArray();
    }

    /**
     * Get a truncated list of registered dimension IDs.
     *
     * @return A truncated list of registered dimension IDs.
     */
    public static List<String> getTruncatedDimensionNamesIterable(int limit) {
        return getTruncatedDimensionIDsIterable(limit).parallelStream()
            .map((id) -> DimensionManager.getProvider(id).getDimensionName())
            .collect(Collectors.toList());
    }

    /**
     * Get the ID of first known dimension with the given name.
     *
     * @param name The name of the dimension to get the ID of.
     *
     * @return The ID of first known dimension with the given name.
     */
    public static int findDimensionIDFromName(String name) {
        return getAllDimensionIDsIterable()
            .stream().map((id) -> DimensionManager.getProvider(id))
            .filter((provider) -> provider.getDimensionName().equals(name))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException()).dimensionId;
    }

    public static byte parseByteArgument(ICommandSender sender,
        String arg)
        throws NumberFormatException {
        return parseByteArgument(sender, arg, 10);
    }

    public static byte parseByteArgument(ICommandSender sender,
        String arg, int radix)
        throws NumberFormatException {
        if (!INTEGER_MATCHER.matcher(arg).matches()) {
            throw new NumberFormatException(String.format(
                "Value '%s' does not match the format of an integer", arg));
        }
        return Byte.parseByte(arg, radix);
    }

    public static byte parseByteArgument(ICommandSender sender,
        String arg, byte min, byte max)
        throws NumberFormatException, NumberOutOfRangeException {
        return parseByteArgument(sender, arg, 10, min, max);
    }

    public static byte parseByteArgument(ICommandSender sender,
        String arg, int radix, byte min, byte max)
        throws NumberFormatException, NumberOutOfRangeException {
        byte result = parseByteArgument(sender, arg, radix);
        if (result < min || result > max) {
            throw new NumberOutOfRangeException(result, min, max);
        }
        return result;
    }

    public static byte parseOptionalByteArgument(ICommandSender sender,
        String arg, byte defaultValue) {
        try {
            return parseByteArgument(sender, arg);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static byte parseOptionalByteArgument(ICommandSender sender,
        String arg, int radix, byte defaultValue) {
        try {
            return parseByteArgument(sender, arg, radix);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static byte parseOptionalByteArgument(ICommandSender sender,
        String arg, byte min, byte max, byte defaultValue) {
        return parseOptionalByteArgument(sender, arg, 10, min, max,
            defaultValue);
    }

    public static byte parseOptionalByteArgument(ICommandSender sender,
        String arg, int radix, byte min, byte max, byte defaultValue) {
        try {
            return parseByteArgument(sender, arg, radix, min, max);
        } catch (NumberFormatException t) {
            return defaultValue;
        } catch (NumberOutOfRangeException t) {
            return defaultValue;
        }
    }

    public static byte parseBlockRelativeByteArgument(ICommandSender sender,
        String arg, byte baseValue)
        throws NumberFormatException {
        return parseBlockRelativeByteArgument(sender, arg, baseValue, 10);
    }

    public static byte parseBlockRelativeByteArgument(ICommandSender sender,
        String arg, byte baseValue, int radix)
        throws NumberFormatException {
        byte offset;
        if (arg.startsWith("~")) {
            offset = arg.length() == 1 ? 0
                : parseByteArgument(sender, arg.substring(1), radix);
        } else {
            return parseByteArgument(sender, arg, radix);
        }
        return (byte) (baseValue + offset);
    }

    public static byte parseBlockRelativeByteArgument(ICommandSender sender,
        String arg, byte baseValue, byte min, byte max)
        throws NumberFormatException {
        return parseBlockRelativeByteArgument(sender, arg, baseValue, 10,
            min, max);
    }

    public static byte parseBlockRelativeByteArgument(ICommandSender sender,
        String arg, byte baseValue, int radix, byte min, byte max)
        throws NumberFormatException {
        byte result = parseBlockRelativeByteArgument(sender, arg, baseValue,
            radix);
        if (result < min || result > max) {
            throw new NumberOutOfRangeException(result, min, max);
        }
        return result;
    }

    public static byte parseBlockRelativeOptionalByteArgument(
        ICommandSender sender, String arg, byte baseValue,
        byte defaultValue) {
        return parseBlockRelativeOptionalByteArgument(sender, arg, baseValue,
            10, defaultValue);
    }

    public static byte parseBlockRelativeOptionalByteArgument(
        ICommandSender sender, String arg, byte baseValue, int radix,
        byte defaultValue) {
        try {
            return parseBlockRelativeByteArgument(sender, arg, baseValue,
                radix);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static byte parseBlockRelativeOptionalByteArgument(
        ICommandSender sender, String arg, byte baseValue, byte min,
        byte max, byte defaultValue) {
        return parseBlockRelativeOptionalByteArgument(sender, arg, baseValue,
            10, min, max, defaultValue);
    }

    public static byte parseBlockRelativeOptionalByteArgument(
        ICommandSender sender, String arg, byte baseValue, int radix,
        byte min, byte max, byte defaultValue) {
        try {
            return parseBlockRelativeByteArgument(sender, arg, baseValue,
                radix, min, max);
        } catch (NumberFormatException t) {
            return defaultValue;
        } catch (NumberOutOfRangeException t) {
            return defaultValue;
        }
    }

    public static short parseShortArgument(ICommandSender sender,
        String arg)
        throws NumberFormatException {
        return parseShortArgument(sender, arg, 10);
    }

    public static short parseShortArgument(ICommandSender sender,
        String arg, int radix)
        throws NumberFormatException {
        if (!SHORT_MATCHER.matcher(arg).matches()) {
            throw new NumberFormatException(String.format(
                "Value '%s' does not match the format of an integer", arg));
        }
        return Short.parseShort(arg, radix);
    }

    public static short parseShortArgument(ICommandSender sender,
        String arg, short min, short max)
        throws NumberFormatException, NumberOutOfRangeException {
        return parseShortArgument(sender, arg, 10, min, max);
    }

    public static short parseShortArgument(ICommandSender sender,
        String arg, int radix, short min, short max)
        throws NumberFormatException, NumberOutOfRangeException {
        short result = parseShortArgument(sender, arg, radix);
        if (result < min || result > max) {
            throw new NumberOutOfRangeException(result, min, max);
        }
        return result;
    }

    public static short parseOptionalShortArgument(ICommandSender sender,
        String arg, short defaultValue) {
        try {
            return parseShortArgument(sender, arg);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static short parseOptionalShortArgument(ICommandSender sender,
        String arg, int radix, short defaultValue) {
        try {
            return parseShortArgument(sender, arg, radix);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static short parseOptionalShortArgument(ICommandSender sender,
        String arg, short min, short max, short defaultValue) {
        return parseOptionalShortArgument(sender, arg, 10, min, max,
            defaultValue);
    }

    public static short parseOptionalShortArgument(ICommandSender sender,
        String arg, int radix, short min, short max, short defaultValue) {
        try {
            return parseShortArgument(sender, arg, radix, min, max);
        } catch (NumberFormatException t) {
            return defaultValue;
        } catch (NumberOutOfRangeException t) {
            return defaultValue;
        }
    }

    public static short parseBlockRelativeShortArgument(ICommandSender sender,
        String arg, short baseValue)
        throws NumberFormatException {
        return parseBlockRelativeShortArgument(sender, arg, baseValue, 10);
    }

    public static short parseBlockRelativeShortArgument(ICommandSender sender,
        String arg, short baseValue, int radix)
        throws NumberFormatException {
        short offset;
        if (arg.startsWith("~")) {
            offset = arg.length() == 1 ? 0
                : parseShortArgument(sender, arg.substring(1), radix);
        } else {
            return parseShortArgument(sender, arg, radix);
        }
        return (short) (baseValue + offset);
    }

    public static short parseBlockRelativeShortArgument(ICommandSender sender,
        String arg, short baseValue, short min, short max)
        throws NumberFormatException {
        return parseBlockRelativeShortArgument(sender, arg, baseValue, 10,
            min, max);
    }

    public static short parseBlockRelativeShortArgument(ICommandSender sender,
        String arg, short baseValue, int radix, short min, short max)
        throws NumberFormatException {
        short result = parseBlockRelativeShortArgument(sender, arg, baseValue,
            radix);
        if (result < min || result > max) {
            throw new NumberOutOfRangeException(result, min, max);
        }
        return result;
    }

    public static short parseBlockRelativeOptionalShortArgument(
        ICommandSender sender, String arg, short baseValue,
        short defaultValue) {
        return parseBlockRelativeOptionalShortArgument(sender, arg, baseValue,
            10, defaultValue);
    }

    public static short parseBlockRelativeOptionalShortArgument(
        ICommandSender sender, String arg, short baseValue, int radix,
        short defaultValue) {
        try {
            return parseBlockRelativeShortArgument(sender, arg, baseValue,
                radix);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static short parseBlockRelativeOptionalShortArgument(
        ICommandSender sender, String arg, short baseValue, short min,
        short max, short defaultValue) {
        return parseBlockRelativeOptionalShortArgument(sender, arg, baseValue,
            10, min, max, defaultValue);
    }

    public static short parseBlockRelativeOptionalShortArgument(
        ICommandSender sender, String arg, short baseValue, int radix,
        short min, short max, short defaultValue) {
        try {
            return parseBlockRelativeShortArgument(sender, arg, baseValue,
                radix, min, max);
        } catch (NumberFormatException t) {
            return defaultValue;
        } catch (NumberOutOfRangeException t) {
            return defaultValue;
        }
    }

    public static int parseIntegerArgument(ICommandSender sender, String arg)
        throws NumberFormatException {
        return parseIntegerArgument(sender, arg, 10);
    }

    public static int parseIntegerArgument(ICommandSender sender, String arg,
        int radix)
        throws NumberFormatException {
        if (!INTEGER_MATCHER.matcher(arg).matches()) {
            throw new NumberFormatException(String.format(
                "Value '%s' does not match the format of an integer", arg));
        }
        return Integer.parseInt(arg, radix);
    }

    public static int parseIntegerArgument(ICommandSender sender, String arg,
        int min, int max)
        throws NumberFormatException, NumberOutOfRangeException {
        return parseIntegerArgument(sender, arg, 10, min, max);
    }

    public static int parseIntegerArgument(ICommandSender sender, String arg,
        int radix, int min, int max)
        throws NumberFormatException, NumberOutOfRangeException {
        int result = parseIntegerArgument(sender, arg, radix);
        if (result < min || result > max) {
            throw new NumberOutOfRangeException(result, min, max);
        }
        return result;
    }

    public static int parseOptionalIntegerArgument(ICommandSender sender,
        String arg, int defaultValue) {
        try {
            return parseIntegerArgument(sender, arg);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static int parseOptionalIntegerArgument(ICommandSender sender,
        String arg, int radix, int defaultValue) {
        try {
            return parseIntegerArgument(sender, arg, radix);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static int parseOptionalIntegerArgument(ICommandSender sender,
        String arg, int min, int max, int defaultValue) {
        return parseOptionalIntegerArgument(sender, arg, 10, min, max,
            defaultValue);
    }

    public static int parseOptionalIntegerArgument(ICommandSender sender,
        String arg, int radix, int min, int max, int defaultValue) {
        try {
            return parseIntegerArgument(sender, arg, radix, min, max);
        } catch (NumberFormatException t) {
            return defaultValue;
        } catch (NumberOutOfRangeException t) {
            return defaultValue;
        }
    }

    public static int parseBlockRelativeIntegerArgument(ICommandSender sender,
        String arg, int baseValue)
        throws NumberFormatException {
        return parseBlockRelativeIntegerArgument(sender, arg, baseValue, 10);
    }

    public static int parseBlockRelativeIntegerArgument(ICommandSender sender,
        String arg, int baseValue, int radix)
        throws NumberFormatException {
        int offset;
        if (arg.startsWith("~")) {
            offset = arg.length() == 1 ? 0
                : parseIntegerArgument(sender, arg.substring(1), radix);
        } else {
            return parseIntegerArgument(sender, arg, radix);
        }
        return baseValue + offset;
    }

    public static int parseBlockRelativeIntegerArgument(ICommandSender sender,
        String arg, int baseValue, int min, int max)
        throws NumberFormatException {
        return parseBlockRelativeIntegerArgument(sender, arg, baseValue, 10,
            min, max);
    }

    public static int parseBlockRelativeIntegerArgument(ICommandSender sender,
        String arg, int baseValue, int radix, int min, int max)
        throws NumberFormatException {
        int result = parseBlockRelativeIntegerArgument(sender, arg, baseValue,
            radix);
        if (result < min || result > max) {
            throw new NumberOutOfRangeException(result, min, max);
        }
        return result;
    }

    public static int parseBlockRelativeOptionalIntegerArgument(
        ICommandSender sender, String arg, int baseValue, int defaultValue) {
        return parseBlockRelativeOptionalIntegerArgument(sender, arg, baseValue,
            10, defaultValue);
    }

    public static int parseBlockRelativeOptionalIntegerArgument(
        ICommandSender sender, String arg, int baseValue, int radix,
        int defaultValue) {
        try {
            return parseBlockRelativeIntegerArgument(sender, arg, baseValue,
                radix);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static int parseBlockRelativeOptionalIntegerArgument(
        ICommandSender sender, String arg, int baseValue, int min, int max,
        int defaultValue) {
        return parseBlockRelativeOptionalIntegerArgument(sender, arg, baseValue,
            10, min, max, defaultValue);
    }

    public static int parseBlockRelativeOptionalIntegerArgument(
        ICommandSender sender, String arg, int baseValue, int radix, int min,
        int max, int defaultValue) {
        try {
            return parseBlockRelativeIntegerArgument(sender, arg, baseValue,
                radix, min, max);
        } catch (NumberFormatException t) {
            return defaultValue;
        } catch (NumberOutOfRangeException t) {
            return defaultValue;
        }
    }

    public static float parseFloatArgument(ICommandSender sender, String arg)
        throws NumberFormatException {
        return parseFloatArgument(sender, arg, false);
    }

    public static float parseFloatArgument(ICommandSender sender, String arg,
        boolean centerToBlock)
        throws NumberFormatException {
        if (!FLOAT_MATCHER.matcher(arg).matches()) {
            throw new NumberFormatException(String.format(
                "Value '%s' does not match the format of a float", arg));
        }
        return Float.parseFloat(arg) + (centerToBlock ? 0.5f : 0.0f);
    }

    public static float parseFloatArgument(ICommandSender sender, String arg,
        float min, float max)
        throws NumberFormatException, NumberOutOfRangeException {
        return parseFloatArgument(sender, arg, false, min, max);
    }

    public static float parseFloatArgument(ICommandSender sender, String arg,
        boolean centerToBlock, float min, float max)
        throws NumberFormatException, NumberOutOfRangeException {
        float result = parseFloatArgument(sender, arg, centerToBlock);
        if (result < min || result > max) {
            throw new NumberOutOfRangeException(result, min, max);
        }
        return result;
    }

    public static float parseOptionalFloatArgument(ICommandSender sender,
        String arg, float defaultValue) {
        try {
            return parseFloatArgument(sender, arg);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static float parseOptionalFloatArgument(ICommandSender sender,
        String arg, boolean centerToBlock, float defaultValue) {
        try {
            return parseFloatArgument(sender, arg, centerToBlock);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static float parseOptionalFloatArgument(ICommandSender sender,
        String arg, float min, float max, float defaultValue) {
        return parseOptionalFloatArgument(sender, arg, false, min, max,
            defaultValue);
    }

    public static float parseOptionalFloatArgument(ICommandSender sender,
        String arg, boolean centerToBlock, float min, float max,
        float defaultValue) {
        try {
            return parseFloatArgument(sender, arg, centerToBlock, min, max);
        } catch (NumberFormatException t) {
            return defaultValue;
        } catch (NumberOutOfRangeException t) {
            return defaultValue;
        }
    }

    public static float parseBlockRelativeFloatArgument(ICommandSender sender,
        String arg, float baseValue)
        throws NumberFormatException {
        return parseBlockRelativeFloatArgument(sender, arg, baseValue, false);
    }

    public static float parseBlockRelativeFloatArgument(ICommandSender sender,
        String arg, float baseValue, boolean centerToBlock)
        throws NumberFormatException {
        float offset;
        if (arg.startsWith("~")) {
            offset = arg.length() == 1 ? 0.0f
                : parseFloatArgument(sender, arg.substring(1));
        } else {
            return parseFloatArgument(sender, arg)
                + (centerToBlock ? 0.5f : 0.0f);
        }
        return baseValue + offset + (centerToBlock ? 0.5f : 0.0f);
    }

    public static float parseBlockRelativeFloatArgument(ICommandSender sender,
        String arg, float baseValue, float min, float max)
        throws NumberFormatException {
        return parseBlockRelativeFloatArgument(sender, arg, baseValue, false,
            min, max);
    }

    public static float parseBlockRelativeFloatArgument(ICommandSender sender,
        String arg, float baseValue, boolean centerToBlock, float min,
        float max)
        throws NumberFormatException {
        float result = parseBlockRelativeFloatArgument(sender, arg, baseValue,
            centerToBlock);
        if (result < min || result > max) {
            throw new NumberOutOfRangeException(result, min, max);
        }
        return result;
    }

    public static float parseBlockRelativeOptionalFloatArgument(
        ICommandSender sender, String arg, float baseValue,
        float defaultValue) {
        return parseBlockRelativeOptionalFloatArgument(sender, arg, baseValue,
            false, defaultValue);
    }

    public static float parseBlockRelativeOptionalFloatArgument(
        ICommandSender sender, String arg, float baseValue,
        boolean centerToBlock, float defaultValue) {
        try {
            return parseBlockRelativeFloatArgument(sender, arg, baseValue,
                centerToBlock);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static float parseBlockRelativeOptionalFloatArgument(
        ICommandSender sender, String arg, float baseValue, float min,
        float max, float defaultValue) {
        return parseBlockRelativeOptionalFloatArgument(sender, arg, baseValue,
            false, min, max, defaultValue);
    }

    public static float parseBlockRelativeOptionalFloatArgument(
        ICommandSender sender, String arg, float baseValue,
        boolean centerToBlock, float min, float max, float defaultValue) {
        try {
            return parseBlockRelativeFloatArgument(sender, arg, baseValue,
                centerToBlock, min, max);
        } catch (NumberFormatException t) {
            return defaultValue;
        } catch (NumberOutOfRangeException t) {
            return defaultValue;
        }
    }

    public static double parseDoubleArgument(ICommandSender sender, String arg)
        throws NumberFormatException {
        return parseDoubleArgument(sender, arg, false);
    }

    public static double parseDoubleArgument(ICommandSender sender, String arg,
        boolean centerToBlock)
        throws NumberFormatException {
        if (!DOUBLE_MATCHER.matcher(arg).matches()) {
            throw new NumberFormatException(String.format(
                "Value '%s' does not match the format of a double", arg));
        }
        return Double.parseDouble(arg) + (centerToBlock ? 0.5f : 0.0f);
    }

    public static double parseDoubleArgument(ICommandSender sender, String arg,
        double min, double max)
        throws NumberFormatException, NumberOutOfRangeException {
        return parseDoubleArgument(sender, arg, false, min, max);
    }

    public static double parseDoubleArgument(ICommandSender sender, String arg,
        boolean centerToBlock, double min, double max)
        throws NumberFormatException, NumberOutOfRangeException {
        double result = parseDoubleArgument(sender, arg, centerToBlock);
        if (result < min || result > max) {
            throw new NumberOutOfRangeException(result, min, max);
        }
        return result;
    }

    public static double parseOptionalDoubleArgument(ICommandSender sender,
        String arg, double defaultValue) {
        try {
            return parseDoubleArgument(sender, arg);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static double parseOptionalDoubleArgument(ICommandSender sender,
        String arg, boolean centerToBlock, double defaultValue) {
        try {
            return parseDoubleArgument(sender, arg, centerToBlock);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static double parseOptionalDoubleArgument(ICommandSender sender,
        String arg, double min, double max, double defaultValue) {
        return parseOptionalDoubleArgument(sender, arg, false, min, max,
            defaultValue);
    }

    public static double parseOptionalDoubleArgument(ICommandSender sender,
        String arg, boolean centerToBlock, double min, double max,
        double defaultValue) {
        try {
            return parseDoubleArgument(sender, arg, centerToBlock, min, max);
        } catch (NumberFormatException t) {
            return defaultValue;
        } catch (NumberOutOfRangeException t) {
            return defaultValue;
        }
    }

    public static double parseBlockRelativeDoubleArgument(ICommandSender sender,
        String arg, double baseValue)
        throws NumberFormatException {
        return parseBlockRelativeDoubleArgument(sender, arg, baseValue, false);
    }

    public static double parseBlockRelativeDoubleArgument(ICommandSender sender,
        String arg, double baseValue, boolean centerToBlock)
        throws NumberFormatException {
        double offset;
        if (arg.startsWith("~")) {
            offset = arg.length() == 1 ? 0.0
                : parseDoubleArgument(sender, arg.substring(1));
        } else {
            return parseDoubleArgument(sender, arg)
                + (centerToBlock ? 0.5f : 0.0f);
        }
        return baseValue + offset + (centerToBlock ? 0.5f : 0.0f);
    }

    public static double parseBlockRelativeDoubleArgument(ICommandSender sender,
        String arg, double baseValue, double min, double max)
        throws NumberFormatException {
        return parseBlockRelativeDoubleArgument(sender, arg, baseValue, false,
            min, max);
    }

    public static double parseBlockRelativeDoubleArgument(ICommandSender sender,
        String arg, double baseValue, boolean centerToBlock, double min,
        double max)
        throws NumberFormatException {
        double result = parseBlockRelativeDoubleArgument(sender, arg, baseValue,
            centerToBlock);
        if (result < min || result > max) {
            throw new NumberOutOfRangeException(result, min, max);
        }
        return result;
    }

    public static double parseBlockRelativeOptionalDoubleArgument(
        ICommandSender sender, String arg, double baseValue,
        double defaultValue) {
        return parseBlockRelativeOptionalDoubleArgument(sender, arg, baseValue,
            false, defaultValue);
    }

    public static double parseBlockRelativeOptionalDoubleArgument(
        ICommandSender sender, String arg, double baseValue,
        boolean centerToBlock, double defaultValue) {
        try {
            return parseBlockRelativeDoubleArgument(sender, arg, baseValue,
                centerToBlock);
        } catch (NumberFormatException t) {
            return defaultValue;
        }
    }

    public static double parseBlockRelativeOptionalDoubleArgument(
        ICommandSender sender, String arg, double baseValue, double min,
        double max, double defaultValue) {
        return parseBlockRelativeOptionalDoubleArgument(sender, arg, baseValue,
            false, min, max, defaultValue);
    }

    public static double parseBlockRelativeOptionalDoubleArgument(
        ICommandSender sender, String arg, double baseValue,
        boolean centerToBlock, double min, double max, double defaultValue) {
        try {
            return parseBlockRelativeDoubleArgument(sender, arg, baseValue,
                centerToBlock, min, max);
        } catch (NumberFormatException t) {
            return defaultValue;
        } catch (NumberOutOfRangeException t) {
            return defaultValue;
        }
    }
}
