package com.glektarssza.gtnh_customizer.config;

import java.io.File;
import java.nio.file.Files;
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

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

import com.glektarssza.gtnh_customizer.GTNHCustomizer;
import com.glektarssza.gtnh_customizer.config.v6.ConfigConstants;
import com.glektarssza.gtnh_customizer.utils.ImmutableTuple;
import com.glektarssza.gtnh_customizer.utils.exceptions.KeyAlreadyExistsException;

/**
 * The main configuration for the mod.
 */
public class Config {
    /**
     * The configuration instance.
     */
    private static Configuration CONFIG_INSTANCE;

    /**
     * A map of configuration migrations to apply to move between configuration
     * versions.
     *
     * Keys are in the format of {@code fromVersion:toVersion}.
     */
    private static final Map<String, ImmutableTuple<String, Consumer<Configuration>>> MIGRATIONS = new HashMap<String, ImmutableTuple<String, Consumer<Configuration>>>();

    /**
     * A list of players who are globally immune.
     */
    private static final List<String> globallyImmunePlayers = new ArrayList<String>();

    /**
     * Whether the {@code repair} command ignores liquids when raycasting to
     * look for containers to repair items inside of.
     */
    private static boolean repairCommandIgnoresLiquids = false;

    /**
     * Whether Tinker's Construct Slime Saplings can be bone mealed.
     */
    private static boolean tconstructSlimeSaplingsBoneMealable = true;

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
    public static UUID CONFIG_ID = UUID.randomUUID();

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
     * Get whether Tinker's Construct Slime Saplings should be able to be bone
     * mealed.
     *
     * @return Whether Tinker's Construct Slime Saplings should be able to be
     *         bone mealed.
     */
    public static boolean getTConstructSlimeSaplingBoneMealable() {
        return tconstructSlimeSaplingsBoneMealable;
    }

    /**
     * Set whether Tinker's Construct Slime Saplings should be able to be bone
     * mealed.
     *
     * @param value Whether Tinker's Construct Slime Saplings should be able to
     *        be bone mealed.
     */
    public static void setTConstructSlimeSaplingBoneMealable(boolean value) {
        tconstructSlimeSaplingsBoneMealable = value;
    }

    /**
     * Reset whether Tinker's Construct Slime Saplings should be able to be bone
     * mealed.
     */
    public static void resetTConstructSlimeSaplingBoneMealable() {
        setTConstructSlimeSaplingBoneMealable(false);
    }

    /**
     * Toggle whether Tinker's Construct Slime Saplings should be able to be
     * bone mealed.
     */
    public static void toggleTConstructSlimeSaplingBoneMealable() {
        setTConstructSlimeSaplingBoneMealable(
            !getTConstructSlimeSaplingBoneMealable());
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
    public static List<ConfigCategory> getTopLevelCategories() {
        if (CONFIG_INSTANCE == null) {
            return Collections.emptyList();
        }
        List<ConfigCategory> list = new ArrayList<>();
        List<String> children = new ArrayList<>();
        for (String name : CONFIG_INSTANCE.getCategoryNames()) {
            if (CONFIG_INSTANCE.getCategory(name).parent == null) {
                children.add(name);
            }
        }
        for (String category : children) {
            if (category.contains(Configuration.CATEGORY_SPLITTER)) {
                continue;
            }
            list.add(CONFIG_INSTANCE.getCategory(category));
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
            com.glektarssza.gtnh_customizer.config.v1.ConfigConstants.CONFIG_VERSION,
            com.glektarssza.gtnh_customizer.config.v2.ConfigConstants.CONFIG_VERSION,
            (configInstance) -> {
                if (MigrationUtils.hasPropertyByPath(configInstance,
                    com.glektarssza.gtnh_customizer.config.v1.ConfigConstants.PROPERTY_GLOBALLY_IMMUNE_PLAYERS_PATH)) {
                    MigrationUtils.renameProperty(configInstance,
                        com.glektarssza.gtnh_customizer.config.v1.ConfigConstants.PROPERTY_GLOBALLY_IMMUNE_PLAYERS_PATH,
                        com.glektarssza.gtnh_customizer.config.v2.ConfigConstants.PROPERTY_GLOBALLY_IMMUNE_PLAYERS_NAME);
                }
            });
        registerMigration(
            com.glektarssza.gtnh_customizer.config.v2.ConfigConstants.CONFIG_VERSION,
            com.glektarssza.gtnh_customizer.config.v3.ConfigConstants.CONFIG_VERSION,
            (configInstance) -> {
                if (MigrationUtils.hasPropertyByPath(configInstance,
                    com.glektarssza.gtnh_customizer.config.v2.ConfigConstants.PROPERTY_GLOBALLY_IMMUNE_PLAYERS_PATH)) {
                    MigrationUtils.renameProperty(configInstance,
                        com.glektarssza.gtnh_customizer.config.v2.ConfigConstants.PROPERTY_GLOBALLY_IMMUNE_PLAYERS_PATH,
                        com.glektarssza.gtnh_customizer.config.v3.ConfigConstants.PROPERTY_GLOBALLY_IMMUNE_PLAYERS_NAME);
                }
            });
        registerMigration(
            com.glektarssza.gtnh_customizer.config.v5.ConfigConstants.CONFIG_VERSION,
            com.glektarssza.gtnh_customizer.config.v6.ConfigConstants.CONFIG_VERSION,
            (configInstance) -> {
                if (MigrationUtils.hasPropertyByPath(configInstance,
                    com.glektarssza.gtnh_customizer.config.v5.ConfigConstants.PROPERTY_GLOBALLY_IMMUNE_PLAYERS_PATH)) {
                    configInstance
                        .setCategoryComment(
                            com.glektarssza.gtnh_customizer.config.v6.ConfigConstants.CATEGORY_GAMEPLAY_PATH,
                            com.glektarssza.gtnh_customizer.config.v6.ConfigConstants.CATEGORY_GAMEPLAY_COMMENT)
                        .setCategoryLanguageKey(
                            com.glektarssza.gtnh_customizer.config.v6.ConfigConstants.CATEGORY_GAMEPLAY_PATH,
                            com.glektarssza.gtnh_customizer.config.v6.ConfigConstants.CATEGORY_GAMEPLAY_LANG_KEY)
                        .setCategoryRequiresMcRestart(
                            com.glektarssza.gtnh_customizer.config.v6.ConfigConstants.CATEGORY_GAMEPLAY_PATH,
                            false)
                        .setCategoryRequiresMcRestart(
                            com.glektarssza.gtnh_customizer.config.v6.ConfigConstants.CATEGORY_GAMEPLAY_PATH,
                            false);
                    MigrationUtils.moveProperty(configInstance,
                        com.glektarssza.gtnh_customizer.config.v5.ConfigConstants.PROPERTY_GLOBALLY_IMMUNE_PLAYERS_PATH,
                        com.glektarssza.gtnh_customizer.config.v6.ConfigConstants.CATEGORY_GAMEPLAY_PATH);
                    // -- This SHOULD be `true` but let's be safe about things
                    if (configInstance.getCategory(
                        com.glektarssza.gtnh_customizer.config.v5.ConfigConstants.CATEGORY_GENERAL_PATH)
                        .isEmpty()) {
                        configInstance
                            .removeCategory(configInstance.getCategory(
                                com.glektarssza.gtnh_customizer.config.v5.ConfigConstants.CATEGORY_GENERAL_PATH));
                    }
                }
                if (MigrationUtils.hasPropertyByPath(configInstance,
                    com.glektarssza.gtnh_customizer.config.v5.ConfigConstants.PROPERTY_REPAIR_COMMAND_IGNORES_LIQUIDS_PATH)) {
                    MigrationUtils.renameProperty(configInstance,
                        com.glektarssza.gtnh_customizer.config.v5.ConfigConstants.PROPERTY_REPAIR_COMMAND_IGNORES_LIQUIDS_PATH,
                        com.glektarssza.gtnh_customizer.config.v6.ConfigConstants.PROPERTY_REPAIR_COMMAND_RAYCAST_IGNORES_LIQUIDS_NAME);
                }
            });
        if (CONFIG_INSTANCE != null) {
            return;
        }

        CONFIG_INSTANCE = new Configuration(new File(configDir, fileName),
            ConfigConstants.CONFIG_VERSION);
    }

    /**
     * Load the configuration data from disk.
     */
    public static void load() {
        if (CONFIG_INSTANCE == null) {
            GTNHCustomizer.LOGGER.error("Cannot load configuration!");
            GTNHCustomizer.LOGGER
                .error("Configuration has not been initialized yet!");
            return;
        }
        CONFIG_INSTANCE.load();
        if (!CONFIG_INSTANCE.getLoadedConfigVersion()
            .equals(ConfigConstants.CONFIG_VERSION)) {
            GTNHCustomizer.LOGGER.warn("Your configuration is out of date!");
            GTNHCustomizer.LOGGER.warn(
                "We're running version '{}' but you have version '{}'",
                ConfigConstants.CONFIG_VERSION,
                CONFIG_INSTANCE.getLoadedConfigVersion());
            GTNHCustomizer.LOGGER.warn("Attempting to migrate!");
            try {
                applyConfigMigrations(CONFIG_INSTANCE.getLoadedConfigVersion(),
                    ConfigConstants.CONFIG_VERSION, CONFIG_INSTANCE);
                save();
            } catch (NoSuchElementException t) {
                GTNHCustomizer.LOGGER
                    .info(
                        "No migrations available from version '{}' to version '{}', assuming migration is not required!",
                        CONFIG_INSTANCE.getLoadedConfigVersion(),
                        ConfigConstants.CONFIG_VERSION);
            } catch (Throwable t) {
                GTNHCustomizer.LOGGER
                    .warn(
                        "Could not migrate configuration from version '{}' to version '{}'!",
                        CONFIG_INSTANCE.getLoadedConfigVersion(),
                        ConfigConstants.CONFIG_VERSION);
                GTNHCustomizer.LOGGER
                    .warn(
                        "Here's a stack trace for you to use if you want to file a bug report about migrations failing:");
                GTNHCustomizer.LOGGER
                    .warn(t);
                GTNHCustomizer.LOGGER
                    .warn(
                        "Any customizations you've made are probably about to get nuked!");
                File backupLocation = new File(String.format("%s.bak",
                    CONFIG_INSTANCE.getConfigFile()
                        .getAbsolutePath()));
                GTNHCustomizer.LOGGER
                    .warn(
                        "Copying your current configuration into '{}' as a backup...",
                        backupLocation.getAbsolutePath());
                try {
                    Files.copy(CONFIG_INSTANCE.getConfigFile().toPath(),
                        backupLocation.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
                } catch (Throwable tt) {
                    GTNHCustomizer.LOGGER
                        .warn(
                            "Failed to generate a backup of your current configuration!");
                    GTNHCustomizer.LOGGER
                        .warn(
                            "Here's a stack trace for you to use if you want to diagnose what happened:");
                    GTNHCustomizer.LOGGER
                        .warn(tt);
                    GTNHCustomizer.LOGGER
                        .warn(
                            "Please do NOT file a bug report about failing to create a backup, this is almost certainly NOT the mod developer's fault!");
                    GTNHCustomizer.LOGGER
                        .warn(
                            "Proceeding anyway, sorry!");
                }
                CONFIG_INSTANCE.getCategoryNames().stream()
                    .forEach((categoryName) -> CONFIG_INSTANCE.removeCategory(
                        CONFIG_INSTANCE.getCategory(categoryName)));
            }
        }
        CONFIG_INSTANCE
            .setCategoryComment(ConfigConstants.CATEGORY_GAMEPLAY_PATH,
                ConfigConstants.CATEGORY_GAMEPLAY_COMMENT)
            .setCategoryLanguageKey(ConfigConstants.CATEGORY_GAMEPLAY_PATH,
                ConfigConstants.CATEGORY_GAMEPLAY_LANG_KEY)
            .setCategoryRequiresMcRestart(
                ConfigConstants.CATEGORY_GAMEPLAY_PATH,
                false)
            .setCategoryRequiresMcRestart(
                ConfigConstants.CATEGORY_GAMEPLAY_PATH,
                false);

        setImmunePlayers(CONFIG_INSTANCE.get(
            ConfigConstants.CATEGORY_GAMEPLAY_PATH,
            ConfigConstants.PROPERTY_GLOBALLY_IMMUNE_PLAYERS_NAME,
            new String[0],
            ConfigConstants.PROPERTY_GLOBALLY_IMMUNE_PLAYERS_COMMENT)
            .setLanguageKey(
                ConfigConstants.PROPERTY_GLOBALLY_IMMUNE_PLAYERS_LANG_KEY)
            .setRequiresMcRestart(false).setRequiresWorldRestart(false)
            .getStringList());

        CONFIG_INSTANCE
            .setCategoryComment(
                ConfigConstants.CATEGORY_GAMEPLAY_TCONSTRUCT_PATH,
                ConfigConstants.CATEGORY_GAMEPLAY_TCONSTRUCT_COMMENT)
            .setCategoryLanguageKey(
                ConfigConstants.CATEGORY_GAMEPLAY_TCONSTRUCT_PATH,
                ConfigConstants.CATEGORY_GAMEPLAY_TCONSTRUCT_LANG_KEY)
            .setCategoryRequiresMcRestart(
                ConfigConstants.CATEGORY_GAMEPLAY_TCONSTRUCT_PATH,
                false)
            .setCategoryRequiresMcRestart(
                ConfigConstants.CATEGORY_GAMEPLAY_TCONSTRUCT_PATH,
                false);

        setTConstructSlimeSaplingBoneMealable(CONFIG_INSTANCE.get(
            ConfigConstants.CATEGORY_GAMEPLAY_TCONSTRUCT_PATH,
            ConfigConstants.PROPERTY_TCONSTRUCT_SLIME_SAPLING_BONE_MEALABLE_NAME,
            true,
            ConfigConstants.PROPERTY_TCONSTRUCT_SLIME_SAPLING_BONE_MEALABLE_COMMENT)
            .setLanguageKey(
                ConfigConstants.PROPERTY_TCONSTRUCT_SLIME_SAPLING_BONE_MEALABLE_LANG_KEY)
            .setRequiresMcRestart(false).setRequiresWorldRestart(false)
            .getBoolean());

        CONFIG_INSTANCE
            .setCategoryComment(ConfigConstants.CATEGORY_COMMAND_PATH,
                ConfigConstants.CATEGORY_COMMAND_COMMENT)
            .setCategoryLanguageKey(ConfigConstants.CATEGORY_COMMAND_PATH,
                ConfigConstants.CATEGORY_COMMAND_LANG_KEY)
            .setCategoryRequiresMcRestart(ConfigConstants.CATEGORY_COMMAND_PATH,
                false)
            .setCategoryRequiresMcRestart(ConfigConstants.CATEGORY_COMMAND_PATH,
                false);

        setRepairCommandRaycastIgnoresLiquids(CONFIG_INSTANCE.get(
            ConfigConstants.CATEGORY_COMMAND_PATH,
            ConfigConstants.PROPERTY_REPAIR_COMMAND_RAYCAST_IGNORES_LIQUIDS_NAME,
            false,
            ConfigConstants.PROPERTY_REPAIR_COMMAND_RAYCAST_IGNORES_LIQUIDS_COMMENT)
            .setLanguageKey(
                ConfigConstants.PROPERTY_REPAIR_COMMAND_RAYCAST_IGNORES_LIQUIDS_LANG_KEY)
            .setRequiresMcRestart(false).setRequiresWorldRestart(false)
            .getBoolean());

        setExtinguishCommandMaxVolume(CONFIG_INSTANCE.get(
            ConfigConstants.CATEGORY_COMMAND_PATH,
            ConfigConstants.PROPERTY_EXTINGUISH_COMMAND_MAX_VOLUME_NAME,
            Integer.MAX_VALUE,
            ConfigConstants.PROPERTY_EXTINGUISH_COMMAND_MAX_VOLUME_COMMENT)
            .setLanguageKey(
                ConfigConstants.PROPERTY_EXTINGUISH_COMMAND_MAX_VOLUME_LANG_KEY)
            .setRequiresMcRestart(false).setRequiresWorldRestart(false)
            .getInt());

        CONFIG_INSTANCE
            .setCategoryComment(ConfigConstants.CATEGORY_DEBUGGING_PATH,
                ConfigConstants.CATEGORY_DEBUGGING_COMMENT)
            .setCategoryLanguageKey(ConfigConstants.CATEGORY_DEBUGGING_PATH,
                ConfigConstants.CATEGORY_DEBUGGING_LANG_KEY)
            .setCategoryRequiresMcRestart(
                ConfigConstants.CATEGORY_DEBUGGING_PATH,
                false)
            .setCategoryRequiresMcRestart(
                ConfigConstants.CATEGORY_DEBUGGING_PATH,
                false);

        setVerboseLoggingEnabled(CONFIG_INSTANCE.get(
            ConfigConstants.CATEGORY_DEBUGGING_PATH,
            ConfigConstants.PROPERTY_DEBUG_LOGGING_NAME,
            false,
            ConfigConstants.PROPERTY_DEBUG_LOGGING_COMMENT)
            .setLanguageKey(ConfigConstants.PROPERTY_DEBUG_LOGGING_LANG_KEY)
            .setRequiresMcRestart(false).setRequiresWorldRestart(false)
            .getBoolean());
    }

    /**
     * Save the configuration data to disk.
     */
    public static void save() {
        if (CONFIG_INSTANCE == null) {
            GTNHCustomizer.LOGGER.error("Cannot save configuration!");
            GTNHCustomizer.LOGGER
                .error("Configuration has not been initialized yet!");
            return;
        }
        if (CONFIG_INSTANCE.hasChanged()) {
            CONFIG_INSTANCE.save();
        }
    }

    /**
     * Synchronize the mod configuration.
     */
    public static void sync() {
        // -- Save the updated config to disk FIRST
        save();
        // -- THEN reload it into ourselves
        load();
    }

    /**
     * Apply migrations from previous configuration versions to the current
     * configuration version.
     *
     * @param fromVersion The version being migrated from.
     * @param toVersion The version being migrated to.
     * @param configInstance The configuration instance being migrated.
     *
     * @throws NoSuchElementException Thrown if no migration route exists from
     *         the old configuration version to the new configuration version.
     */
    private static void applyConfigMigrations(String fromVersion,
        String toVersion, Configuration configInstance)
        throws NoSuchElementException {
        LinkedList<ImmutableTuple<String, Consumer<Configuration>>> migrators = new LinkedList<ImmutableTuple<String, Consumer<Configuration>>>();
        HashSet<String> alreadyMigratedVersions = new HashSet<String>();
        String lastMigratedVersion = fromVersion;
        while (MIGRATIONS.containsKey(lastMigratedVersion)
            && !lastMigratedVersion.equals(toVersion)) {
            migrators.push(MIGRATIONS.get(lastMigratedVersion));
            migrators
                .sort((a, b) -> Integer.parseInt(a.getFirst(), 10)
                    - Integer.parseInt(b.getFirst(), 10));
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
        Configuration currentVersion = MigrationUtils
            .cloneConfiguration(CONFIG_INSTANCE);
        for (ImmutableTuple<String, Consumer<Configuration>> migrator : migrators) {
            migrator.getSecond().accept(currentVersion);
        }
        CONFIG_INSTANCE = currentVersion;
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
    private static void registerMigration(String fromVersion,
        String toVersion,
        Consumer<Configuration> migrator) throws KeyAlreadyExistsException {
        if (MIGRATIONS.containsKey(fromVersion)) {
            throw new KeyAlreadyExistsException(
                String.format(
                    "Migration already exists from configuration '%s' version to configuration version '%s'",
                    fromVersion,
                    toVersion));
        }
        MIGRATIONS.put(fromVersion,
            new ImmutableTuple<String, Consumer<Configuration>>(toVersion,
                migrator));
    }
}
