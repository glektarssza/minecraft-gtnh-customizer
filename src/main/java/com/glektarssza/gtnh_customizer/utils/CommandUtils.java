package com.glektarssza.gtnh_customizer.utils;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
     * A regular expression for matching parsable integer values.
     */
    private static final Pattern BYTE_MATCHER = Pattern
        .compile("-?\\d{1,3}");

    /**
     * A regular expression for matching parsable integer values.
     */
    private static final Pattern SHORT_MATCHER = Pattern
        .compile("-?\\d{1,5}");

    /**
     * A regular expression for matching parsable integer values.
     */
    private static final Pattern INTEGER_MATCHER = Pattern
        .compile("-?\\d{1,10}");

    /**
     * A regular expression for matching parsable float values.
     */
    private static final Pattern FLOAT_MATCHER = Pattern
        .compile("-?\\d+(\\.\\d*)");

    /**
     * A regular expression for matching parsable double values.
     */
    private static final Pattern DOUBLE_MATCHER = Pattern
        .compile("-?\\d+(\\.\\d*)");

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
        return isByteArgument(args, index)
            || isShortArgument(args, index)
            || isIntegerArgument(args, index)
            || isFloatArgument(args, index)
            || isDoubleArgument(args, index);
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
        return (Integer[]) Arrays.stream(getAllDimensionIDs()).limit(limit)
            .toArray();
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
}
