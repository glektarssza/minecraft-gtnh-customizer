package com.glektarssza.gtnh_customizer.config;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

import com.glektarssza.gtnh_customizer.Tags;
import com.glektarssza.gtnh_customizer.api.exceptions.args.ArgumentNullException;
import com.glektarssza.gtnh_customizer.api.exceptions.collections.KeyAlreadyExistsException;
import com.glektarssza.gtnh_customizer.config.categories.Commands;
import com.glektarssza.gtnh_customizer.config.categories.Debugging;
import com.glektarssza.gtnh_customizer.config.categories.Gameplay;
import com.glektarssza.gtnh_customizer.utils.ImmutableTuple;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

/**
 * The main configuration for the mod.
 */
public class Config {
    /**
     * The logger for this class.
     */
    @Nonnull
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
            MethodHandles.lookup().lookupClass().getSimpleName())));

    /**
     * The current version of the configuration.
     */
    @Nonnull
    public static final Number CONFIG_VERSION = 10;

    /**
     * The base localization language key.
     */
    @Nonnull
    public static final String LANG_KEY_BASE = "gtnh_customizer.config";

    /**
     * The base localization language key for categories.
     */
    @Nonnull
    public static final String LANG_KEY_CATEGORY_BASE = TypeHelpers
        .castToNonNull(String
            .join(Configuration.CATEGORY_SPLITTER, LANG_KEY_BASE,
                "categories"));

    /**
     * A map of configuration migrations to apply to move between configuration
     * versions.
     *
     * Keys are in the format of {@code fromVersion:toVersion}.
     */
    @Nonnull
    private static final Map<Number, ImmutableTuple<Number, Consumer<Configuration>>> MIGRATIONS = new HashMap<Number, ImmutableTuple<Number, Consumer<Configuration>>>();

    /**
     * The configuration instance.
     */
    @Nullable
    private static Configuration configInstance = null;

    /**
     * The current version of the {@link Config#configInstance configuration
     * instance}.
     */
    @Nullable
    private static Number configInstanceVersion = null;

    /**
     * A list of players who are globally immune.
     */
    @Nonnull
    private static final List<String> globallyImmunePlayers = new ArrayList<String>();

    /**
     * Whether to prevent Enderman and Enderman-like teleportation.
     */
    private static boolean preventEnderMobTeleport = true;

    /**
     * Whether the {@code repair} command ignores liquids when raycasting to
     * look for containers to repair items inside of.
     */
    private static boolean repairCommandIgnoresLiquids = false;

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
    private static boolean verboseLoggingEnabled = false;

    /**
     * A random UUID which uniquely IDs this run of Minecraft.
     */
    @Nonnull
    public static final UUID CONFIG_ID = TypeHelpers
        .castToNonNull(UUID.randomUUID());

    /**
     * Get the globally immune players.
     *
     * @return The globally immune players.
     */
    public static String[] getGloballyImmunePlayers() {
        String[] ret = new String[globallyImmunePlayers.size()];
        globallyImmunePlayers.toArray(ret);
        return ret;
    }

    /**
     * Set the globally immune players.
     *
     * @param players A list of player UUIDs or names to set as globally immune.
     */
    public static void setImmunePlayers(String[] players) {
        clearImmunePlayers();
        globallyImmunePlayers.addAll(Arrays.asList(players));
    }

    /**
     * Clear the globally immune players.
     */
    public static void clearImmunePlayers() {
        globallyImmunePlayers.clear();
    }

    /**
     * Reset the globally immune players.
     */
    public static void resetImmunePlayers() {
        clearImmunePlayers();
    }

    /**
     * Get whether to prevent Enderman and Enderman-like teleportation.
     *
     * @return Whether to prevent Enderman and Enderman-like mob teleportation.
     */
    public static boolean getPreventEnderMobTeleportation() {
        return preventEnderMobTeleport;
    }

    /**
     * Set whether to prevent Enderman and Enderman-like teleportation.
     *
     * @param value Whether to prevent Enderman and Enderman-like teleportation.
     */
    public static void setPreventEnderMobTeleportation(boolean value) {
        preventEnderMobTeleport = value;
    }

    /**
     * Reset whether to prevent Enderman and Enderman-like teleportation.
     */
    public static void resetPreventEnderMobTeleportation() {
        setPreventEnderMobTeleportation(true);
    }

    /**
     * Toggle whether to prevent Enderman and Enderman-like teleportation.
     */
    public static void togglePreventEnderMobTeleportation() {
        setPreventEnderMobTeleportation(!getPreventEnderMobTeleportation());
    }

    /**
     * Get whether Tinker's Construct Slime Saplings should be able to have bone
     * meal applied.
     *
     * @return Whether Tinker's Construct Slime Saplings should be able to have
     *         bone meal applied.
     */
    public static boolean getTConstructCanBoneMealSlimeSaplings() {
        return tconstructCanBoneMealSlimeSaplings;
    }

    /**
     * Set whether Tinker's Construct Slime Saplings should be able to have bone
     * meal applied.
     *
     * @param value Whether Tinker's Construct Slime Saplings should be able to
     *        have bone meal applied.
     */
    public static void setTConstructCanBoneMealSlimeSaplings(
        boolean value) {
        tconstructCanBoneMealSlimeSaplings = value;
    }

    /**
     * Reset whether Tinker's Construct Slime Saplings should be able to have
     * bone meal applied.
     */
    public static void resetTConstructCanBoneMealSlimeSaplings() {
        setTConstructCanBoneMealSlimeSaplings(true);
    }

    /**
     * Toggle whether Tinker's Construct Slime Saplings should be able to have
     * bone meal applied.
     */
    public static void toggleTConstructCanBoneMealSlimeSaplings() {
        setTConstructCanBoneMealSlimeSaplings(
            !getTConstructCanBoneMealSlimeSaplings());
    }

    /**
     * Get whether Thaumcraft Greatwood Saplings should be able to have bone
     * meal applied.
     *
     * @return Whether Thaumcraft Greatwood Saplings should be able to have bone
     *         meal applied.
     */
    public static boolean getThaumcraftCanBoneMealGreatwoodSaplings() {
        return thaumcraftCanBoneMealGreatwoodSaplings;
    }

    /**
     * Set whether Thaumcraft Greatwood Saplings should be able to have bone
     * meal applied.
     *
     * @param value Whether Thaumcraft Greatwood Saplings should be able to have
     *        bone meal applied.
     */
    public static void setThaumcraftCanBoneMealGreatwoodSaplings(
        boolean value) {
        thaumcraftCanBoneMealGreatwoodSaplings = value;
    }

    /**
     * Reset whether Thaumcraft Greatwood Saplings should be able to have bone
     * meal applied.
     */
    public static void resetThaumcraftCanBoneMealGreatwoodSaplings() {
        setThaumcraftCanBoneMealGreatwoodSaplings(true);
    }

    /**
     * Toggle whether Thaumcraft Greatwood Saplings should be able to have bone
     * meal applied.
     */
    public static void toggleThaumcraftCanBoneMealGreatwoodSaplings() {
        setThaumcraftCanBoneMealGreatwoodSaplings(
            !getThaumcraftCanBoneMealGreatwoodSaplings());
    }

    /**
     * Get whether Thaumcraft Silverwood Saplings should be able to have bone
     * meal applied.
     *
     * @return Whether Thaumcraft Silverwood Saplings should be able to have
     *         bone meal applied.
     */
    public static boolean getThaumcraftCanBoneMealSilverwoodSaplings() {
        return thaumcraftCanBoneMealSilverwoodSaplings;
    }

    /**
     * Set whether Thaumcraft Silverwood Saplings should be able to have bone
     * meal applied.
     *
     * @param value Whether Thaumcraft Silverwood Saplings should be able to
     *        have bone meal applied.
     */
    public static void setThaumcraftCanBoneMealSilverwoodSaplings(
        boolean value) {
        thaumcraftCanBoneMealSilverwoodSaplings = value;
    }

    /**
     * Reset whether Thaumcraft Silverwood Saplings should be able to have bone
     * meal applied.
     */
    public static void resetThaumcraftCanBoneMealSilverwoodSaplings() {
        setThaumcraftCanBoneMealSilverwoodSaplings(true);
    }

    /**
     * Toggle whether Thaumcraft Silverwood Saplings should be able to have bone
     * meal applied.
     */
    public static void toggleThaumcraftCanBoneMealSilverwoodSaplings() {
        setThaumcraftCanBoneMealSilverwoodSaplings(
            !getThaumcraftCanBoneMealSilverwoodSaplings());
    }

    /**
     * Get whether to show the hovered biome in Xaero's World Map.
     *
     * @return Whether to show the hovered biome in Xaero's World Map.
     */
    public static boolean getXaerosWorldMapShowHoveredBiome() {
        return xaerosWorldMapShowHoveredBiome;
    }

    /**
     * Set whether to show the hovered biome in Xaero's World Map.
     *
     * @param value Whether to show the hovered biome in Xaero's World Map.
     */
    public static void setXaerosWorldMapShowHoveredBiome(
        boolean value) {
        xaerosWorldMapShowHoveredBiome = value;
    }

    /**
     * Reset whether to show the hovered biome in Xaero's World Map.
     */
    public static void resetXaerosWorldMapShowHoveredBiome() {
        setXaerosWorldMapShowHoveredBiome(true);
    }

    /**
     * Toggle whether to show the hovered biome in Xaero's World Map.
     */
    public static void toggleXaerosWorldMapShowHoveredBiome() {
        setXaerosWorldMapShowHoveredBiome(
            !getXaerosWorldMapShowHoveredBiome());
    }

    /**
     * Get whether the {@code repair} command raycast while looking for
     * containers ignores liquids.
     *
     * @return Whether the {@code repair} command raycast while looking for
     *         containers ignores liquids.
     */
    public static boolean getRepairCommandRaycastIgnoresLiquids() {
        return repairCommandIgnoresLiquids;
    }

    /**
     * Set whether the {@code repair} command raycast while looking for
     * containers ignores liquids.
     *
     * @param value Whether the {@code repair} command raycast while looking for
     *        containers ignores liquids.
     */
    public static void setRepairCommandRaycastIgnoresLiquids(boolean value) {
        repairCommandIgnoresLiquids = value;
    }

    /**
     * Reset whether the {@code repair} command raycast while looking for
     * containers ignores liquids.
     */
    public static void resetRepairCommandRaycastIgnoresLiquids() {
        setRepairCommandRaycastIgnoresLiquids(false);
    }

    /**
     * Toggle whether the {@code repair} command raycast while looking for
     * containers ignores liquids.
     */
    public static void toggleRepairCommandRaycastIgnoresLiquids() {
        setRepairCommandRaycastIgnoresLiquids(
            !getRepairCommandRaycastIgnoresLiquids());
    }

    /**
     * Get the maximum number of blocks the {@code extinguish} command should
     * process.
     *
     * @return The maximum number of blocks the {@code extinguish} command
     *         should process.
     */
    public static int getExtinguishCommandMaxVolume() {
        return extinguishCommandMaxVolume;
    }

    /**
     * Set the maximum number of blocks the {@code extinguish} command should
     * process.
     *
     * @param value The maximum number of blocks the {@code extinguish} command
     *        should process.
     */
    public static void setExtinguishCommandMaxVolume(int value) {
        extinguishCommandMaxVolume = value;
    }

    /**
     * Reset the maximum number of blocks the {@code extinguish} command should
     * process.
     */
    public static void resetExtinguishCommandMaxVolume() {
        setExtinguishCommandMaxVolume(Integer.MAX_VALUE);
    }

    /**
     * Get whether verbose logging is enabled.
     *
     * @return Whether verbose logging is enabled.
     */
    public static boolean getVerboseLoggingEnabled() {
        return verboseLoggingEnabled;
    }

    /**
     * Set whether verbose logging is enabled.
     *
     * @param value Whether verbose logging is enabled.
     */
    public static void setVerboseLoggingEnabled(boolean value) {
        verboseLoggingEnabled = value;
    }

    /**
     * Reset whether verbose logging is enabled.
     */
    public static void resetVerboseLoggingEnabled() {
        setVerboseLoggingEnabled(false);
    }

    /**
     * Toggle whether verbose logging is enabled.
     */
    public static void toggleVerboseLoggingEnabled() {
        setVerboseLoggingEnabled(!getVerboseLoggingEnabled());
    }

    /**
     * Get the main level configuration categories.
     *
     * @return A list of the main level configuration categories.
     */
    @SuppressWarnings("null")
    public static List<ConfigCategory> getTopLevelCategories() {
        if (configInstance == null) {
            return Collections.emptyList();
        }
        List<ConfigCategory> list = new ArrayList<>();
        List<String> children = new ArrayList<>();
        for (String name : configInstance.getCategoryNames()) {
            if (configInstance.getCategory(name).parent == null) {
                children.add(name);
            }
        }
        for (String category : children) {
            if (category.contains(Configuration.CATEGORY_SPLITTER)) {
                continue;
            }
            list.add(configInstance.getCategory(category));
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
    public static void init(File configDir, String fileName)
        throws KeyAlreadyExistsException, NoSuchElementException {
        // -- Register migrations
        registerMigration(
            1, 2, (configInstance) -> {
                if (MigrationUtils.hasPropertyByPath(configInstance,
                    "general.immunePlayers")) {
                    MigrationUtils.renameProperty(configInstance,
                        "general.immunePlayers", "globallyImmunePlayers");
                }
            });
        registerMigration(
            2, 3, (configInstance) -> {
                if (MigrationUtils.hasPropertyByPath(configInstance,
                    "general.globallyImmunePlayers")) {
                    MigrationUtils.renameProperty(configInstance,
                        "general.globallyImmunePlayers",
                        "globally_immune_players");
                }
            });
        registerMigration(
            5, 6, (configInstance) -> {
                if (MigrationUtils.hasPropertyByPath(configInstance,
                    "general.globally_immune_players")) {
                    configInstance
                        .setCategoryComment(
                            "gameplay", "Gameplay-related tweaks")
                        .setCategoryLanguageKey(
                            "gameplay",
                            "gtnh_customizer.config.category_gameplay")
                        .setCategoryRequiresMcRestart(
                            "gameplay", false)
                        .setCategoryRequiresMcRestart(
                            "gameplay", false);
                    MigrationUtils.moveProperty(configInstance,
                        "general.globally_immune_players", "gameplay");
                    // -- This SHOULD be `true` but let's be safe about things
                    if (configInstance.getCategory("general").isEmpty()) {
                        configInstance
                            .removeCategory(
                                configInstance.getCategory("general"));
                    }
                }
                if (MigrationUtils.hasPropertyByPath(configInstance,
                    "command.repair_ignores_liquids")) {
                    MigrationUtils.renameProperty(configInstance,
                        "command.repair_ignores_liquids",
                        "repair_raycast_ignores_liquids");
                }
            });
        registerMigration(
            6, 7, (configInstance) -> {
                if (MigrationUtils.hasPropertyByPath(configInstance,
                    "gameplay.tconstruct.slime_sapling_bone_mealable")) {
                    MigrationUtils.renameProperty(configInstance,
                        "gameplay.tconstruct.slime_sapling_bone_mealable",
                        "can_bone_meal_slime_saplings");
                }
            });
        registerMigration(
            7, 8, (configInstance) -> {
                if (MigrationUtils.hasCategoryByPath(configInstance,
                    "command")) {
                    MigrationUtils.renameCategory(configInstance, "command",
                        "commands");
                }
            });
        if (configInstance != null) {
            return;
        }

        configInstance = new Configuration(new File(configDir, fileName),
            Integer.toString(CONFIG_VERSION.intValue(), 10), false);
        configInstanceVersion = CONFIG_VERSION.intValue();
    }

    /**
     * Refresh the configuration data from the in-memory data.
     */
    @SuppressWarnings("null")
    public static void refresh() {
        if (configInstance == null) {
            LOGGER.error("Cannot load configuration!");
            LOGGER.error("Configuration has not been initialized yet!");
            return;
        }
        Number parsedConfigVersion;
        try {
            parsedConfigVersion = Integer
                .parseInt(configInstance.getLoadedConfigVersion(), 10);
        } catch (Throwable t) {
            LOGGER.error("Failed to parse in-memory configuration version:", t);
            LOGGER.error(
                "Got a string '{}' that could not be parsed as an integer",
                configInstance.getLoadedConfigVersion());
            return;
        }
        if (!parsedConfigVersion.equals(CONFIG_VERSION)) {
            LOGGER.error("Cannot load configuration!");
            LOGGER.error(
                "In-memory configuration version of '{}' does not equal expected configuration version of '{}'!",
                parsedConfigVersion, CONFIG_VERSION);
            return;
        }
        // -- Load updated values
        new Gameplay().loadValues(configInstance);
        new Debugging().loadValues(configInstance);
        new Commands().loadValues(configInstance);
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
    public static Path backUpConfig(@Nullable Configuration config)
        throws ArgumentNullException, IOException {
        if (config == null) {
            throw new ArgumentNullException("config",
                "Cannot create a backup of a null configuration");
        }
        LOGGER.debug("Creating a backup of configuration file '{}'...",
            config.getConfigFile().getAbsolutePath());
        File backupLocation = new File(String.format("%s.bak",
            config.getConfigFile().getAbsolutePath()));
        LOGGER.debug("Copying main configuration to backup location '{}'...",
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
    public static Path recreateConfig(@Nullable Configuration config) {
        return recreateConfig(config, true);
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
    public static Path recreateConfig(@Nullable Configuration config,
        boolean backup) {
        @Nullable
        Path backupPath = null;
        if (backup) {
            try {
                backupPath = backUpConfig(config);
            } catch (Throwable t) {
                LOGGER.warn(
                    "Failed to backup your current configuration!");
                LOGGER.warn(
                    "Here's a stack trace for you to use if you want to diagnose what happened:",
                    t);
                LOGGER.warn(
                    "Please do NOT file a bug report about failing to create a backup, this is almost certainly NOT the mod developer's fault!");
                LOGGER.warn("Proceeding anyway, sorry!");
            }
        }
        if (config != null) {
            config.getCategoryNames().stream()
                .forEach((categoryName) -> config.removeCategory(
                    config.getCategory(categoryName)));
        }
        return backupPath;
    }

    /**
     * Load the configuration data from disk.
     */
    @SuppressWarnings("null")
    public static void load() {
        if (configInstance == null) {
            LOGGER.error("Cannot load configuration!");
            LOGGER.error("Configuration has not been initialized yet!");
            return;
        }
        configInstance.load();
        try {
            configInstanceVersion = Integer
                .parseInt(configInstance.getLoadedConfigVersion(), 10);
        } catch (NumberFormatException ex) {
            LOGGER.error(
                "Got a value of '{}' that could not be parsed as an integer",
                configInstance.getLoadedConfigVersion());
            LOGGER.warn(
                "Unable to determine the version of your configuration!");
            LOGGER.warn(
                "Assuming your configuration is corrupted, backing it up and recreating!");
            LOGGER.warn(
                "Any customizations you've made are probably about to get nuked!");
            Path backupPath = recreateConfig(configInstance);
            if (backupPath != null) {
                LOGGER.warn(
                    "Your old config was backed up to '{}'", backupPath);
            }
            configInstance.load();
        }
        if (!configInstanceVersion.equals(CONFIG_VERSION)) {
            LOGGER.warn("Your configuration is out of date!");
            LOGGER.warn("We're running version '{}' but you have version '{}'",
                CONFIG_VERSION, configInstance.getLoadedConfigVersion());
            LOGGER.warn("Attempting to migrate!");
            try {
                applyConfigMigrations(
                    Integer.parseInt(configInstance.getLoadedConfigVersion(),
                        10),
                    CONFIG_VERSION);
            } catch (NoSuchElementException t) {
                LOGGER.info(
                    "No migrations available from version '{}' to version '{}', assuming migration is not required!",
                    configInstance.getLoadedConfigVersion(), CONFIG_VERSION);
            } catch (Throwable t) {
                LOGGER.warn(
                    "Could not migrate configuration from version '{}' to version '{}'!",
                    configInstance.getLoadedConfigVersion(), CONFIG_VERSION);
                LOGGER.warn(
                    "Here's a stack trace for you to use if you want to file a bug report about migrations failing:",
                    t);
                LOGGER.warn(
                    "Any customizations you've made are probably about to get nuked!");
                Path backupPath = recreateConfig(configInstance);
                if (backupPath != null) {
                    LOGGER.warn(
                        "Your old config was backed up to '{}'", backupPath);
                }
                configInstance.load();
            }
        }

        // -- Register all our configurations
        new Gameplay().registerForgeConfigCategory(configInstance, true);
        new Debugging().registerForgeConfigCategory(configInstance, true);
        new Commands().registerForgeConfigCategory(configInstance, true);

        // -- Load updated values
        new Gameplay().loadValues(configInstance);
        new Debugging().loadValues(configInstance);
        new Commands().loadValues(configInstance);

        if (getVerboseLoggingEnabled()) {
            LOGGER.debug("Loaded mod configuration! Settings are:");
            LOGGER.debug("=== Gameplay ===");
            LOGGER.debug("Globally Immune Players:");
            for (String player : getGloballyImmunePlayers()) {
                LOGGER.debug(String.format("* %s", player));
            }
            LOGGER.debug(String.format("Prevent Ender Mob Teleportation: %s",
                getPreventEnderMobTeleportation()));
            LOGGER.debug("");
            LOGGER.debug("=== Commands ===");
            LOGGER.debug(String.format("Repair Raycast Ignores Liquids: %s",
                getRepairCommandRaycastIgnoresLiquids()));
            LOGGER.debug(String.format("Extinguish Max Block Volume: %s",
                getExtinguishCommandMaxVolume()));
            LOGGER.debug("");
            LOGGER.debug("=== Debugging ===");
            LOGGER.debug(String.format("Verbose Logging: %s",
                getVerboseLoggingEnabled()));
            LOGGER.debug("");
            LOGGER.debug("=== Gameplay - Tinker's Construct ===");
            LOGGER.debug(String.format("Can Bone Meal Slime Saplings: %s",
                getTConstructCanBoneMealSlimeSaplings()));
            LOGGER.debug("");
            LOGGER.debug("=== Gameplay - Thaumcraft ===");
            LOGGER.debug(String.format("Can Bone Meal Greatwood Saplings: %s",
                getTConstructCanBoneMealSlimeSaplings()));
            LOGGER.debug(String.format("Can Bone Meal Silverwood: %s",
                getTConstructCanBoneMealSlimeSaplings()));
            LOGGER.debug("");
            LOGGER.debug("=== Gameplay - Xaero's World Map ===");
            LOGGER
                .debug(String.format("Show Hovered Over Biome in World Map: %s",
                    getXaerosWorldMapShowHoveredBiome()));
            LOGGER.debug("");
        }
    }

    /**
     * Get whether the configuration has changed.
     *
     * @return Whether the configuration has changed.
     */
    public static boolean hasChanged() {
        return configInstance == null ? false : configInstance.hasChanged();
    }

    /**
     * Save the configuration data to disk.
     */
    public static void save() {
        if (configInstance == null) {
            LOGGER.error("Cannot save configuration!");
            LOGGER.error("Configuration has not been initialized yet!");
            return;
        }
        configInstance.save();
    }

    /**
     * Synchronize the mod configuration.
     */
    public static void sync() {
        load();
        save();
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
    private static void applyConfigMigrations(@Nonnull Number fromVersion,
        @Nonnull Number toVersion)
        throws NoSuchElementException, NumberFormatException {
        LinkedList<ImmutableTuple<Number, Consumer<Configuration>>> migrators = new LinkedList<ImmutableTuple<Number, Consumer<Configuration>>>();
        HashSet<Number> alreadyMigratedVersions = new HashSet<Number>();
        Number lastMigratedVersion = fromVersion;
        while (MIGRATIONS.containsKey(lastMigratedVersion)
            && !lastMigratedVersion.equals(toVersion)) {
            migrators.push(MIGRATIONS.get(lastMigratedVersion));
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
        Configuration currentInstance = MigrationUtils
            .cloneConfiguration(configInstance);
        Number currentVersion = configInstanceVersion;
        for (ImmutableTuple<Number, Consumer<Configuration>> migrator : migrators) {
            migrator.getSecond().accept(currentInstance);
            currentVersion = migrator.getFirst().intValue();
        }
        configInstance = currentInstance;
        configInstanceVersion = currentVersion;
        if (hasChanged()) {
            save();
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
    private static void registerMigration(@Nonnull Number fromVersion,
        @Nonnull Number toVersion, Consumer<Configuration> migrator)
        throws KeyAlreadyExistsException {
        if (MIGRATIONS.containsKey(fromVersion)) {
            throw new KeyAlreadyExistsException(fromVersion,
                String.format(
                    "Migration already exists from configuration '%d' version to configuration version '%d'",
                    fromVersion,
                    toVersion));
        }
        MIGRATIONS.put(fromVersion,
            new ImmutableTuple<Number, Consumer<Configuration>>(toVersion,
                migrator));
    }
}
