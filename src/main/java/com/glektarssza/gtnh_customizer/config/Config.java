package com.glektarssza.gtnh_customizer.config;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

import com.glektarssza.gtnh_customizer.Tags;
import com.glektarssza.gtnh_customizer.api.v1.exceptions.args.ArgumentNullException;
import com.glektarssza.gtnh_customizer.api.v1.exceptions.collections.KeyAlreadyExistsException;
import com.glektarssza.gtnh_customizer.api.v2.annotation.NotNull;
import com.glektarssza.gtnh_customizer.api.v2.annotation.Nullable;
import com.glektarssza.gtnh_customizer.config.categories.Commands;
import com.glektarssza.gtnh_customizer.config.categories.Debugging;
import com.glektarssza.gtnh_customizer.config.categories.Gameplay;
import com.glektarssza.gtnh_customizer.utils.ImmutableTuple;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

/**
 * The main configuration for the mod.
 */
public final class Config {
    /**
     * The logger for this class.
     */
    @NotNull
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
            MethodHandles.lookup().lookupClass().getSimpleName())));

    /**
     * The current version of the configuration.
     */
    @NotNull
    public static final Number CONFIG_VERSION = 10;

    /**
     * The base localization language key.
     */
    @NotNull
    public static final String LANG_KEY_BASE = "gtnh_customizer.config";

    /**
     * The base localization language key for categories.
     */
    @NotNull
    public static final String LANG_KEY_CATEGORY_BASE = TypeHelpers
        .castToNonNull(String
            .join(Configuration.CATEGORY_SPLITTER, Config.LANG_KEY_BASE,
                "categories"));

    /**
     * A map of configuration migrations to apply to move between configuration
     * versions.
     *
     * <p>
     * Keys are in the format of {@code fromVersion:toVersion}.
     * </p>
     */
    @NotNull
    private static final Map<Number, ImmutableTuple<Number, Consumer<Configuration>>> MIGRATIONS = new HashMap<Number, ImmutableTuple<Number, Consumer<Configuration>>>();

    /**
     * The configuration instance.
     */
    @Nullable
    private static Configuration configInstance;

    /**
     * The current version of the {@link Config#configInstance configuration
     * instance}.
     */
    @Nullable
    private static Number configInstanceVersion;

    /**
     * The commands configuration object.
     */
    @NotNull
    private static final Commands commandsConfig = new Commands();

    /**
     * The gameplay configuration object.
     */
    @NotNull
    private static final Gameplay gameplayConfig = new Gameplay();

    /**
     * The debugging configuration object.
     */
    @NotNull
    private static final Debugging debuggingConfig = new Debugging();

    /**
     * A list of players who are globally immune.
     */
    @NotNull
    private static final List<String> globallyImmunePlayers = new ArrayList<String>();

    /**
     * Whether to prevent Enderman and Enderman-like teleportation.
     */
    private static boolean preventEnderMobTeleport = true;

    /**
     * Whether the {@code repair} command ignores liquids when raycasting to
     * look for containers to repair items inside of.
     */
    private static boolean repairCommandIgnoresLiquids;

    /**
     * Whether Tinker's Construct Slime Saplings can be bone mealed.
     */
    private static boolean tconstructCanBoneMealSlimeSaplings = true;

    /**
     * Whether Thaumcraft Greatwood Saplings can be bone mealed.
     */
    private static boolean thaumcraftCanBoneMealGreatwoodSaplings = true;

    /**
     * Whether Thaumcraft Silverwood Saplings can be bone mealed.
     */
    private static boolean thaumcraftCanBoneMealSilverwoodSaplings = true;

    /**
     * Whether to show the hovered biome in Xaero's World Map.
     */
    private static boolean xaerosWorldMapShowHoveredBiome = true;

    /**
     * The maximum number of blocks the {@code extinguish} command should
     * process.
     */
    private static int extinguishCommandMaxVolume = Integer.MAX_VALUE;

    /**
     * Whether verbose logging is enabled.
     */
    private static boolean verboseLoggingEnabled;

    /**
     * A random UUID which uniquely IDs this run of Minecraft.
     */
    @NotNull
    public static final UUID CONFIG_ID = TypeHelpers
        .castToNonNull(UUID.randomUUID());

    /**
     * Get the globally immune players.
     *
     * @return The globally immune players.
     */
    public static String[] getGloballyImmunePlayers() {
        final String[] ret = new String[Config.globallyImmunePlayers.size()];
        Config.globallyImmunePlayers.toArray(ret);
        return ret;
    }

    /**
     * Set the globally immune players.
     *
     * @param players A list of player UUIDs or names to set as globally immune.
     */
    public static void setImmunePlayers(final String[] players) {
        Config.clearImmunePlayers();
        Config.globallyImmunePlayers.addAll(Arrays.asList(players));
        Config.gameplayConfig.globallyImmunePlayers
            .saveValue(Config.configInstance);
    }

    /**
     * Set the globally immune players.
     *
     * @param player A list of player UUIDs or names to set as globally immune.
     */
    public static void addImmunePlayer(final String player) {
        Config.globallyImmunePlayers.add(player);
        Config.gameplayConfig.globallyImmunePlayers
            .saveValue(Config.configInstance);
    }

    /**
     * Set the globally immune players.
     *
     * @param players A list of player UUIDs or names to set as globally immune.
     */
    public static void addAllImmunePlayer(final Collection<String> players) {
        Config.globallyImmunePlayers.addAll(players);
        Config.gameplayConfig.globallyImmunePlayers
            .saveValue(Config.configInstance);
    }

    /**
     * Set the globally immune players.
     *
     * @param player A list of player UUIDs or names to set as globally immune.
     */
    public static void removeImmunePlayer(final String player) {
        Config.globallyImmunePlayers.remove(player);
        Config.gameplayConfig.globallyImmunePlayers
            .saveValue(Config.configInstance);
    }

    /**
     * Set the globally immune players.
     *
     * @param players A list of player UUIDs or names to set as globally immune.
     */
    public static void removeAllImmunePlayer(final Collection<String> players) {
        Config.globallyImmunePlayers.removeAll(players);
        Config.gameplayConfig.globallyImmunePlayers
            .saveValue(Config.configInstance);
    }

    /**
     * Clear the globally immune players.
     */
    public static void clearImmunePlayers() {
        Config.globallyImmunePlayers.clear();
        Config.gameplayConfig.globallyImmunePlayers
            .saveValue(Config.configInstance);
    }

    /**
     * Reset the globally immune players.
     */
    public static void resetImmunePlayers() {
        Config.clearImmunePlayers();
    }

    /**
     * Get whether to prevent Enderman and Enderman-like teleportation.
     *
     * @return Whether to prevent Enderman and Enderman-like mob teleportation.
     */
    public static boolean getPreventEnderMobTeleportation() {
        return Config.preventEnderMobTeleport;
    }

    /**
     * Set whether to prevent Enderman and Enderman-like teleportation.
     *
     * @param value Whether to prevent Enderman and Enderman-like teleportation.
     */
    public static void setPreventEnderMobTeleportation(final boolean value) {
        Config.preventEnderMobTeleport = value;
        Config.gameplayConfig.preventEnderModTeleportation
            .saveValue(Config.configInstance);
    }

    /**
     * Reset whether to prevent Enderman and Enderman-like teleportation.
     */
    public static void resetPreventEnderMobTeleportation() {
        Config.setPreventEnderMobTeleportation(true);
    }

    /**
     * Toggle whether to prevent Enderman and Enderman-like teleportation.
     */
    public static void togglePreventEnderMobTeleportation() {
        Config.setPreventEnderMobTeleportation(
            !Config.getPreventEnderMobTeleportation());
    }

    /**
     * Get whether Tinker's Construct Slime Saplings should be able to have bone
     * meal applied.
     *
     * @return Whether Tinker's Construct Slime Saplings should be able to have
     *         bone meal applied.
     */
    public static boolean getTConstructCanBoneMealSlimeSaplings() {
        return Config.tconstructCanBoneMealSlimeSaplings;
    }

    /**
     * Set whether Tinker's Construct Slime Saplings should be able to have bone
     * meal applied.
     *
     * @param value Whether Tinker's Construct Slime Saplings should be able to
     *        have bone meal applied.
     */
    public static void setTConstructCanBoneMealSlimeSaplings(
        final boolean value) {
        Config.tconstructCanBoneMealSlimeSaplings = value;
        Config.gameplayConfig.tconstruct.canBoneMealSlimeSaplings
            .saveValue(Config.configInstance);
    }

    /**
     * Reset whether Tinker's Construct Slime Saplings should be able to have
     * bone meal applied.
     */
    public static void resetTConstructCanBoneMealSlimeSaplings() {
        Config.setTConstructCanBoneMealSlimeSaplings(true);
    }

    /**
     * Toggle whether Tinker's Construct Slime Saplings should be able to have
     * bone meal applied.
     */
    public static void toggleTConstructCanBoneMealSlimeSaplings() {
        Config.setTConstructCanBoneMealSlimeSaplings(
            !Config.getTConstructCanBoneMealSlimeSaplings());
    }

    /**
     * Get whether Thaumcraft Greatwood Saplings should be able to have bone
     * meal applied.
     *
     * @return Whether Thaumcraft Greatwood Saplings should be able to have bone
     *         meal applied.
     */
    public static boolean getThaumcraftCanBoneMealGreatwoodSaplings() {
        return Config.thaumcraftCanBoneMealGreatwoodSaplings;
    }

    /**
     * Set whether Thaumcraft Greatwood Saplings should be able to have bone
     * meal applied.
     *
     * @param value Whether Thaumcraft Greatwood Saplings should be able to have
     *        bone meal applied.
     */
    public static void setThaumcraftCanBoneMealGreatwoodSaplings(
        final boolean value) {
        Config.thaumcraftCanBoneMealGreatwoodSaplings = value;
        Config.gameplayConfig.thaumcraft.canBoneMealGreatwoodSaplings
            .saveValue(Config.configInstance);
    }

    /**
     * Reset whether Thaumcraft Greatwood Saplings should be able to have bone
     * meal applied.
     */
    public static void resetThaumcraftCanBoneMealGreatwoodSaplings() {
        Config.setThaumcraftCanBoneMealGreatwoodSaplings(true);
    }

    /**
     * Toggle whether Thaumcraft Greatwood Saplings should be able to have bone
     * meal applied.
     */
    public static void toggleThaumcraftCanBoneMealGreatwoodSaplings() {
        Config.setThaumcraftCanBoneMealGreatwoodSaplings(
            !Config.getThaumcraftCanBoneMealGreatwoodSaplings());
    }

    /**
     * Get whether Thaumcraft Silverwood Saplings should be able to have bone
     * meal applied.
     *
     * @return Whether Thaumcraft Silverwood Saplings should be able to have
     *         bone meal applied.
     */
    public static boolean getThaumcraftCanBoneMealSilverwoodSaplings() {
        return Config.thaumcraftCanBoneMealSilverwoodSaplings;
    }

    /**
     * Set whether Thaumcraft Silverwood Saplings should be able to have bone
     * meal applied.
     *
     * @param value Whether Thaumcraft Silverwood Saplings should be able to
     *        have bone meal applied.
     */
    public static void setThaumcraftCanBoneMealSilverwoodSaplings(
        final boolean value) {
        Config.thaumcraftCanBoneMealSilverwoodSaplings = value;
        Config.gameplayConfig.thaumcraft.canBoneMealSilverwoodSaplings
            .saveValue(Config.configInstance);
    }

    /**
     * Reset whether Thaumcraft Silverwood Saplings should be able to have bone
     * meal applied.
     */
    public static void resetThaumcraftCanBoneMealSilverwoodSaplings() {
        Config.setThaumcraftCanBoneMealSilverwoodSaplings(true);
    }

    /**
     * Toggle whether Thaumcraft Silverwood Saplings should be able to have bone
     * meal applied.
     */
    public static void toggleThaumcraftCanBoneMealSilverwoodSaplings() {
        Config.setThaumcraftCanBoneMealSilverwoodSaplings(
            !Config.getThaumcraftCanBoneMealSilverwoodSaplings());
    }

    /**
     * Get whether to show the hovered biome in Xaero's World Map.
     *
     * @return Whether to show the hovered biome in Xaero's World Map.
     */
    public static boolean getXaerosWorldMapShowHoveredBiome() {
        return Config.xaerosWorldMapShowHoveredBiome;
    }

    /**
     * Set whether to show the hovered biome in Xaero's World Map.
     *
     * @param value Whether to show the hovered biome in Xaero's World Map.
     */
    public static void setXaerosWorldMapShowHoveredBiome(
        final boolean value) {
        Config.xaerosWorldMapShowHoveredBiome = value;
        Config.gameplayConfig.xaerosWorldMap.showHoveredBiome
            .saveValue(Config.configInstance);
    }

    /**
     * Reset whether to show the hovered biome in Xaero's World Map.
     */
    public static void resetXaerosWorldMapShowHoveredBiome() {
        Config.setXaerosWorldMapShowHoveredBiome(true);
    }

    /**
     * Toggle whether to show the hovered biome in Xaero's World Map.
     */
    public static void toggleXaerosWorldMapShowHoveredBiome() {
        Config.setXaerosWorldMapShowHoveredBiome(
            !Config.getXaerosWorldMapShowHoveredBiome());
    }

    /**
     * Get whether the {@code repair} command raycast while looking for
     * containers ignores liquids.
     *
     * @return Whether the {@code repair} command raycast while looking for
     *         containers ignores liquids.
     */
    public static boolean getRepairCommandRaycastIgnoresLiquids() {
        return Config.repairCommandIgnoresLiquids;
    }

    /**
     * Set whether the {@code repair} command raycast while looking for
     * containers ignores liquids.
     *
     * @param value Whether the {@code repair} command raycast while looking for
     *        containers ignores liquids.
     */
    public static void setRepairCommandRaycastIgnoresLiquids(
        final boolean value) {
        Config.repairCommandIgnoresLiquids = value;
        Config.commandsConfig.repairRaycastIgnoresLiquids
            .saveValue(Config.configInstance);
    }

    /**
     * Reset whether the {@code repair} command raycast while looking for
     * containers ignores liquids.
     */
    public static void resetRepairCommandRaycastIgnoresLiquids() {
        Config.setRepairCommandRaycastIgnoresLiquids(false);
    }

    /**
     * Toggle whether the {@code repair} command raycast while looking for
     * containers ignores liquids.
     */
    public static void toggleRepairCommandRaycastIgnoresLiquids() {
        Config.setRepairCommandRaycastIgnoresLiquids(
            !Config.getRepairCommandRaycastIgnoresLiquids());
    }

    /**
     * Get the maximum number of blocks the {@code extinguish} command should
     * process.
     *
     * @return The maximum number of blocks the {@code extinguish} command
     *         should process.
     */
    public static int getExtinguishCommandMaxVolume() {
        return Config.extinguishCommandMaxVolume;
    }

    /**
     * Set the maximum number of blocks the {@code extinguish} command should
     * process.
     *
     * @param value The maximum number of blocks the {@code extinguish} command
     *        should process.
     */
    public static void setExtinguishCommandMaxVolume(final int value) {
        Config.extinguishCommandMaxVolume = value;
        Config.commandsConfig.extinguishMaxVolume
            .saveValue(Config.configInstance);
    }

    /**
     * Reset the maximum number of blocks the {@code extinguish} command should
     * process.
     */
    public static void resetExtinguishCommandMaxVolume() {
        Config.setExtinguishCommandMaxVolume(Integer.MAX_VALUE);
    }

    /**
     * Get whether verbose logging is enabled.
     *
     * @return Whether verbose logging is enabled.
     */
    public static boolean getVerboseLoggingEnabled() {
        return Config.verboseLoggingEnabled;
    }

    /**
     * Set whether verbose logging is enabled.
     *
     * @param value Whether verbose logging is enabled.
     */
    public static void setVerboseLoggingEnabled(final boolean value) {
        Config.verboseLoggingEnabled = value;
        Config.debuggingConfig.verboseLogging.saveValue(Config.configInstance);
    }

    /**
     * Reset whether verbose logging is enabled.
     */
    public static void resetVerboseLoggingEnabled() {
        Config.setVerboseLoggingEnabled(false);
    }

    /**
     * Toggle whether verbose logging is enabled.
     */
    public static void toggleVerboseLoggingEnabled() {
        Config.setVerboseLoggingEnabled(!Config.getVerboseLoggingEnabled());
    }

    /**
     * Get the main level configuration categories.
     *
     * @return A list of the main level configuration categories.
     */
    @SuppressWarnings("null")
    public static List<ConfigCategory> getTopLevelCategories() {
        if (Config.configInstance == null) {
            return Collections.emptyList();
        }
        final List<ConfigCategory> list = new ArrayList<>();
        final List<String> children = new ArrayList<>();
        for (final String name : Config.configInstance.getCategoryNames()) {
            if (Config.configInstance.getCategory(name).parent == null) {
                children.add(name);
            }
        }
        for (final String category : children) {
            if (category.contains(Configuration.CATEGORY_SPLITTER)) {
                continue;
            }
            list.add(Config.configInstance.getCategory(category));
        }
        return list;
    }

    /**
     * Initialize the configuration.
     *
     * Multiple calls to this instance will have no effect.
     *
     * @param configDir The directory the configuration file will live in.
     * @param fileName The name of the file to save the configuration to.
     *
     * @throws KeyAlreadyExistsException Thrown if a duplicate configuration
     *         migration is registered.
     * @throws NoSuchElementException Thrown if configuration migration is
     *         required and no migration route exists from the old configuration
     *         version to the new configuration version.
     */
    public static void init(final File configDir, final String fileName)
        throws KeyAlreadyExistsException, NoSuchElementException {
        // -- Register migrations
        Config.registerMigration(
            1, 2, instance -> {
                if (MigrationUtils.hasPropertyByPath(instance,
                    "general.immunePlayers")) {
                    MigrationUtils.renameProperty(instance,
                        "general.immunePlayers", "globallyImmunePlayers");
                }
            });
        Config.registerMigration(
            2, 3, instance -> {
                if (MigrationUtils.hasPropertyByPath(instance,
                    "general.globallyImmunePlayers")) {
                    MigrationUtils.renameProperty(instance,
                        "general.globallyImmunePlayers",
                        "globally_immune_players");
                }
            });
        Config.registerMigration(
            5, 6, instance -> {
                if (MigrationUtils.hasPropertyByPath(instance,
                    "general.globally_immune_players")) {
                    instance
                        .setCategoryComment(
                            "gameplay", "Gameplay-related tweaks")
                        .setCategoryLanguageKey(
                            "gameplay",
                            "gtnh_customizer.config.category_gameplay")
                        .setCategoryRequiresMcRestart(
                            "gameplay", false)
                        .setCategoryRequiresMcRestart(
                            "gameplay", false);
                    MigrationUtils.moveProperty(instance,
                        "general.globally_immune_players", "gameplay");
                    // -- This SHOULD be `true` but let's be safe about things
                    if (instance.getCategory("general").isEmpty()) {
                        instance
                            .removeCategory(
                                instance.getCategory("general"));
                    }
                }
                if (MigrationUtils.hasPropertyByPath(Config.configInstance,
                    "command.repair_ignores_liquids")) {
                    MigrationUtils.renameProperty(Config.configInstance,
                        "command.repair_ignores_liquids",
                        "repair_raycast_ignores_liquids");
                }
            });
        Config.registerMigration(
            6, 7, instance -> {
                if (MigrationUtils.hasPropertyByPath(instance,
                    "gameplay.tconstruct.slime_sapling_bone_mealable")) {
                    MigrationUtils.renameProperty(instance,
                        "gameplay.tconstruct.slime_sapling_bone_mealable",
                        "can_bone_meal_slime_saplings");
                }
            });
        Config.registerMigration(
            7, 8, instance -> {
                if (MigrationUtils.hasCategoryByPath(instance,
                    "command")) {
                    MigrationUtils.renameCategory(instance, "command",
                        "commands");
                }
            });
        if (Config.configInstance != null) {
            return;
        }

        Config.configInstance = new Configuration(new File(configDir, fileName),
            Integer.toString(Config.CONFIG_VERSION.intValue(), 10), false);
        Config.configInstanceVersion = Config.CONFIG_VERSION.intValue();
    }

    /**
     * Refresh the configuration data from the in-memory data.
     */
    @SuppressWarnings("null")
    public static void refresh() {
        if (Config.configInstance == null) {
            Config.LOGGER.error("Cannot load configuration!");
            Config.LOGGER.error("Configuration has not been initialized yet!");
            return;
        }
        final Number parsedConfigVersion;
        try {
            parsedConfigVersion = Integer
                .parseInt(Config.configInstance.getLoadedConfigVersion(), 10);
        } catch (final Throwable t) {
            Config.LOGGER
                .error("Failed to parse in-memory configuration version:", t);
            Config.LOGGER.error(
                "Got a string '{}' that could not be parsed as an integer",
                Config.configInstance.getLoadedConfigVersion());
            return;
        }
        if (!parsedConfigVersion.equals(Config.CONFIG_VERSION)) {
            Config.LOGGER.error("Cannot load configuration!");
            Config.LOGGER.error(
                "In-memory configuration version of '{}' does not equal expected configuration version of '{}'!",
                parsedConfigVersion, Config.CONFIG_VERSION);
            return;
        }
        // -- Load updated values
        Config.commandsConfig.loadValues(Config.configInstance);
        Config.gameplayConfig.loadValues(Config.configInstance);
        Config.debuggingConfig.loadValues(Config.configInstance);
    }

    /**
     * Create a backup of the given configuration.
     *
     * @param config The configuration to create a backup of.
     *
     * @return The path to the created backup.
     *
     * @throws IOException Thrown if the backup failed to be created.
     * @throws ArgumentNullException Thrown if the <code>config</code> argument
     *         is <code>null</code>.
     */
    @Nullable
    public static Path backUpConfig(@Nullable final Configuration config)
        throws ArgumentNullException, IOException {
        if (config == null) {
            throw new ArgumentNullException("config",
                "Cannot create a backup of a null configuration");
        }
        Config.LOGGER.debug("Creating a backup of configuration file '{}'...",
            config.getConfigFile().getAbsolutePath());
        final File backupLocation = new File(String.format("%s.bak",
            config.getConfigFile().getAbsolutePath()));
        Config.LOGGER.debug(
            "Copying main configuration to backup location '{}'...",
            backupLocation.getAbsolutePath());
        Files.copy(config.getConfigFile().toPath(),
            backupLocation.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return backupLocation.toPath();
    }

    /**
     * Recreate the given configuration from scratch, creating a backup if
     * possible.
     *
     * @param config The configuration to recreate.
     *
     * @return The path to the created backup, if one was requested and was
     *         successfully created, <code>null</code> otherwise.
     */
    @Nullable
    public static Path recreateConfig(@Nullable final Configuration config) {
        return Config.recreateConfig(config, true);
    }

    /**
     * Recreate the given configuration from scratch, optionally creating a
     * backup.
     *
     * @param config The configuration to recreate.
     * @param backup Whether to create a backup.
     *
     * @return The path to the created backup, if one was requested and was
     *         successfully created, <code>null</code> otherwise.
     */
    @Nullable
    public static Path recreateConfig(@Nullable final Configuration config,
        final boolean backup) {
        @Nullable
        Path backupPath = null;
        if (backup) {
            try {
                backupPath = Config.backUpConfig(config);
            } catch (final Throwable t) {
                Config.LOGGER.warn(
                    "Failed to backup your current configuration!");
                Config.LOGGER.warn(
                    "Here's a stack trace for you to use if you want to diagnose what happened:",
                    t);
                Config.LOGGER.warn(
                    "Please do NOT file a bug report about failing to create a backup, this is almost certainly NOT the mod developer's fault!");
                Config.LOGGER.warn("Proceeding anyway, sorry!");
            }
        }
        if (config != null) {
            config.getCategoryNames().stream()
                .forEach(categoryName -> config.removeCategory(
                    config.getCategory(categoryName)));
        }
        return backupPath;
    }

    /**
     * Load the configuration data from disk.
     */
    @SuppressWarnings("null")
    public static void load() {
        if (Config.configInstance == null) {
            Config.LOGGER.error("Cannot load configuration!");
            Config.LOGGER.error("Configuration has not been initialized yet!");
            return;
        }
        Config.configInstance.load();
        try {
            Config.configInstanceVersion = Integer
                .parseInt(Config.configInstance.getLoadedConfigVersion());
        } catch (final NumberFormatException _) {
            Config.LOGGER.error(
                "Got a value of '{}' that could not be parsed as an integer",
                Config.configInstance.getLoadedConfigVersion());
            Config.LOGGER.warn(
                "Unable to determine the version of your configuration!");
            Config.LOGGER.warn(
                "Assuming your configuration is corrupted, backing it up and recreating!");
            Config.LOGGER.warn(
                "Any customizations you've made are probably about to get nuked!");
            final Path backupPath = Config
                .recreateConfig(Config.configInstance);
            if (backupPath != null) {
                Config.LOGGER.warn(
                    "Your old config was backed up to '{}'", backupPath);
            }
            Config.configInstance.load();
        }
        if (!Config.configInstanceVersion.equals(Config.CONFIG_VERSION)) {
            Config.LOGGER.warn("Your configuration is out of date!");
            Config.LOGGER.warn(
                "We're running version '{}' but you have version '{}'",
                Config.CONFIG_VERSION,
                Config.configInstance.getLoadedConfigVersion());
            Config.LOGGER.warn("Attempting to migrate!");
            try {
                Config.applyConfigMigrations(
                    Integer.parseInt(
                        Config.configInstance.getLoadedConfigVersion(),
                        10),
                    Config.CONFIG_VERSION);
            } catch (final NoSuchElementException t) {
                Config.LOGGER.info(
                    "No migrations available from version '{}' to version '{}', assuming migration is not required!",
                    Config.configInstance.getLoadedConfigVersion(),
                    Config.CONFIG_VERSION);
            } catch (final Throwable t) {
                Config.LOGGER.warn(
                    "Could not migrate configuration from version '{}' to version '{}'!",
                    Config.configInstance.getLoadedConfigVersion(),
                    Config.CONFIG_VERSION);
                Config.LOGGER.warn(
                    "Here's a stack trace for you to use if you want to file a bug report about migrations failing:",
                    t);
                Config.LOGGER.warn(
                    "Any customizations you've made are probably about to get nuked!");
                final Path backupPath = Config
                    .recreateConfig(Config.configInstance);
                if (backupPath != null) {
                    Config.LOGGER.warn(
                        "Your old config was backed up to '{}'", backupPath);
                }
                Config.configInstance.load();
            }
        }

        // -- Register all our configurations
        Config.commandsConfig.registerForgeConfigCategory(Config.configInstance,
            true);
        Config.gameplayConfig.registerForgeConfigCategory(Config.configInstance,
            true);
        Config.debuggingConfig
            .registerForgeConfigCategory(Config.configInstance, true);

        // -- Load updated values
        Config.commandsConfig.loadValues(Config.configInstance);
        Config.gameplayConfig.loadValues(Config.configInstance);
        Config.debuggingConfig.loadValues(Config.configInstance);

        if (Config.getVerboseLoggingEnabled()) {
            Config.LOGGER.debug("Loaded mod configuration! Settings are:");
            Config.LOGGER.debug("=== Gameplay ===");
            Config.LOGGER.debug("Globally Immune Players:");
            for (final String player : Config.getGloballyImmunePlayers()) {
                Config.LOGGER.debug(String.format("* %s", player));
            }
            Config.LOGGER
                .debug(String.format("Prevent Ender Mob Teleportation: %s",
                    Config.getPreventEnderMobTeleportation()));
            Config.LOGGER.debug("");
            Config.LOGGER.debug("=== Commands ===");
            Config.LOGGER
                .debug(String.format("Repair Raycast Ignores Liquids: %s",
                    Config.getRepairCommandRaycastIgnoresLiquids()));
            Config.LOGGER.debug(String.format("Extinguish Max Block Volume: %s",
                Config.getExtinguishCommandMaxVolume()));
            Config.LOGGER.debug("");
            Config.LOGGER.debug("=== Debugging ===");
            Config.LOGGER.debug(String.format("Verbose Logging: %s",
                Config.getVerboseLoggingEnabled()));
            Config.LOGGER.debug("");
            Config.LOGGER.debug("=== Gameplay - Tinker's Construct ===");
            Config.LOGGER
                .debug(String.format("Can Bone Meal Slime Saplings: %s",
                    Config.getTConstructCanBoneMealSlimeSaplings()));
            Config.LOGGER.debug("");
            Config.LOGGER.debug("=== Gameplay - Thaumcraft ===");
            Config.LOGGER
                .debug(String.format("Can Bone Meal Greatwood Saplings: %s",
                    Config.getTConstructCanBoneMealSlimeSaplings()));
            Config.LOGGER.debug(String.format("Can Bone Meal Silverwood: %s",
                Config.getTConstructCanBoneMealSlimeSaplings()));
            Config.LOGGER.debug("");
            Config.LOGGER.debug("=== Gameplay - Xaero's World Map ===");
            Config.LOGGER
                .debug(String.format("Show Hovered Over Biome in World Map: %s",
                    Config.getXaerosWorldMapShowHoveredBiome()));
            Config.LOGGER.debug("");
        }
    }

    /**
     * Get whether the configuration has changed.
     *
     * @return Whether the configuration has changed.
     */
    public static boolean hasChanged() {
        return Config.configInstance == null ? false
            : Config.configInstance.hasChanged();
    }

    /**
     * Save the configuration data to disk.
     */
    @SuppressWarnings("null")
    public static void save() {
        if (Config.configInstance == null) {
            Config.LOGGER.error("Cannot save configuration!");
            Config.LOGGER.error("Configuration has not been initialized yet!");
            return;
        }
        Config.commandsConfig.saveValues(Config.configInstance);
        Config.gameplayConfig.saveValues(Config.configInstance);
        Config.debuggingConfig.saveValues(Config.configInstance);
        Config.configInstance.save();
    }

    /**
     * Synchronize the mod configuration.
     *
     * @deprecated Use {@link Config#load load} and {@link Config#save save}
     *             separately.
     */
    @Deprecated
    public static void sync() {
        Config.save();
        Config.load();
    }

    /**
     * Apply migrations from previous configuration versions to the current
     * configuration version.
     *
     * @param fromVersion The version being migrated from.
     * @param toVersion The version being migrated to.
     *
     * @throws NoSuchElementException Thrown if no migration route exists from
     *         the old configuration version to the new configuration version.
     * @throws NumberFormatException Thrown if any configuration versions are
     *         not valid numbers.
     */
    private static void applyConfigMigrations(@NotNull final Number fromVersion,
        @NotNull final Number toVersion)
        throws NoSuchElementException, NumberFormatException {
        final LinkedList<ImmutableTuple<Number, Consumer<Configuration>>> migrators = new LinkedList<ImmutableTuple<Number, Consumer<Configuration>>>();
        final HashSet<Number> alreadyMigratedVersions = new HashSet<Number>();
        Number lastMigratedVersion = fromVersion;
        while (Config.MIGRATIONS.containsKey(lastMigratedVersion)
            && !lastMigratedVersion.equals(toVersion)) {
            migrators.push(Config.MIGRATIONS.get(lastMigratedVersion));
            migrators
                .sort((a, b) -> a.getFirst().intValue()
                    - b.getFirst().intValue());
            alreadyMigratedVersions.add(lastMigratedVersion);
            lastMigratedVersion = migrators.peekLast().getFirst();
            if (alreadyMigratedVersions.contains(lastMigratedVersion)) {
                throw new RuntimeException("Cyclic migration detected!");
            }
        }
        if (!lastMigratedVersion.equals(toVersion)) {
            throw new NoSuchElementException(String.format(
                "No available migration route from configuration version '%s' to configuration version '%s'!",
                fromVersion, toVersion));
        }
        final Configuration currentInstance = MigrationUtils
            .cloneConfiguration(Config.configInstance);
        Number currentVersion = Config.configInstanceVersion;
        for (final ImmutableTuple<Number, Consumer<Configuration>> migrator : migrators) {
            migrator.getSecond().accept(currentInstance);
            currentVersion = migrator.getFirst().intValue();
        }
        Config.configInstance = currentInstance;
        Config.configInstanceVersion = currentVersion;
        if (Config.hasChanged()) {
            Config.save();
        }
    }

    /**
     * Register a function which can migrate from a given configuration version
     * to a given configuration version.
     *
     * @param fromVersion The version which will be migrated from.
     * @param toVersion The version which will be migrated to.
     * @param migrator The function which will perform the migration.
     *
     * @throws KeyAlreadyExistsException Thrown if a migration already exists
     *         from the old configuration version to the new configuration
     *         version.
     */
    @SuppressWarnings("null")
    private static void registerMigration(@NotNull final Number fromVersion,
        @NotNull final Number toVersion, final Consumer<Configuration> migrator)
        throws KeyAlreadyExistsException {
        if (Config.MIGRATIONS.containsKey(fromVersion)) {
            throw new KeyAlreadyExistsException(fromVersion,
                String.format(
                    "Migration already exists from configuration '%d' version to configuration version '%d'",
                    fromVersion,
                    toVersion));
        }
        Config.MIGRATIONS.put(fromVersion,
            new ImmutableTuple<Number, Consumer<Configuration>>(toVersion,
                migrator));
    }

    /**
     * Create a new instance.
     */
    private Config() {
        // -- Does nothing.
    }
}
