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
import com.glektarssza.gtnh_customizer.config.categories.Commands;
import com.glektarssza.gtnh_customizer.config.categories.Debugging;
import com.glektarssza.gtnh_customizer.config.categories.Gameplay;
import com.glektarssza.gtnh_customizer.utils.ImmutableTuple;
import com.glektarssza.gtnh_customizer.utils.exceptions.KeyAlreadyExistsException;

/**
 * The main configuration for the mod.
 */
public class Config {
    /**
     * The current version of the configuration.
     */
    public static String CONFIG_VERSION = "9";

    /**
     * The base localization language key.
     */
    public static String LANG_KEY_BASE = "gtnh_customizer.config";

    /**
     * The base localization language key for categories.
     */
    public static String LANG_KEY_CATEGORY_BASE = String
        .join(Configuration.CATEGORY_SPLITTER, LANG_KEY_BASE, "categories");

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
            "1", "2", (configInstance) -> {
                if (MigrationUtils.hasPropertyByPath(configInstance,
                    "general.immunePlayers")) {
                    MigrationUtils.renameProperty(configInstance,
                        "general.immunePlayers", "globallyImmunePlayers");
                }
            });
        registerMigration(
            "2", "3", (configInstance) -> {
                if (MigrationUtils.hasPropertyByPath(configInstance,
                    "general.globallyImmunePlayers")) {
                    MigrationUtils.renameProperty(configInstance,
                        "general.globallyImmunePlayers",
                        "globally_immune_players");
                }
            });
        registerMigration(
            "5", "6", (configInstance) -> {
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
            "6", "7", (configInstance) -> {
                if (MigrationUtils.hasPropertyByPath(configInstance,
                    "gameplay.tconstruct.slime_sapling_bone_mealable")) {
                    MigrationUtils.renameProperty(configInstance,
                        "gameplay.tconstruct.slime_sapling_bone_mealable",
                        "can_bone_meal_slime_saplings");
                }
            });
        registerMigration(
            "7", "8", (configInstance) -> {
                if (MigrationUtils.hasCategoryByPath(configInstance,
                    "command")) {
                    MigrationUtils.renameCategory(configInstance, "command",
                        "commands");
                }
            });
        if (CONFIG_INSTANCE != null) {
            return;
        }

        CONFIG_INSTANCE = new Configuration(new File(configDir, fileName),
            CONFIG_VERSION, false);
    }

    /**
     * Refresh the configuration data from the in-memory data.
     */
    public static void refresh() {
        if (CONFIG_INSTANCE == null) {
            GTNHCustomizer.getLogger().error("Cannot load configuration!");
            GTNHCustomizer.getLogger()
                .error("Configuration has not been initialized yet!");
            return;
        }
        if (!CONFIG_INSTANCE.getLoadedConfigVersion().equals(CONFIG_VERSION)) {
            GTNHCustomizer.getLogger().error("Cannot load configuration!");
            GTNHCustomizer.getLogger().error(
                "In-memory configuration version of '{}' does not equal expected configuration version of '{}'!",
                CONFIG_INSTANCE.getLoadedConfigVersion(), CONFIG_VERSION);
            return;
        }
        // -- Load updated values
        new Gameplay().loadValues(CONFIG_INSTANCE);
        new Debugging().loadValues(CONFIG_INSTANCE);
        new Commands().loadValues(CONFIG_INSTANCE);
    }

    /**
     * Load the configuration data from disk.
     */
    public static void load() {
        if (CONFIG_INSTANCE == null) {
            GTNHCustomizer.getLogger().error("Cannot load configuration!");
            GTNHCustomizer.getLogger()
                .error("Configuration has not been initialized yet!");
            return;
        }
        CONFIG_INSTANCE.load();
        if (!CONFIG_INSTANCE.getLoadedConfigVersion()
            .equals(CONFIG_VERSION)) {
            GTNHCustomizer.getLogger()
                .warn("Your configuration is out of date!");
            GTNHCustomizer.getLogger().warn(
                "We're running version '{}' but you have version '{}'",
                CONFIG_VERSION, CONFIG_INSTANCE.getLoadedConfigVersion());
            GTNHCustomizer.getLogger().warn("Attempting to migrate!");
            try {
                applyConfigMigrations(CONFIG_INSTANCE.getLoadedConfigVersion(),
                    CONFIG_VERSION, CONFIG_INSTANCE);
            } catch (NoSuchElementException t) {
                GTNHCustomizer.getLogger()
                    .info(
                        "No migrations available from version '{}' to version '{}', assuming migration is not required!",
                        CONFIG_INSTANCE.getLoadedConfigVersion(),
                        CONFIG_VERSION);
            } catch (Throwable t) {
                GTNHCustomizer.getLogger()
                    .warn(
                        "Could not migrate configuration from version '{}' to version '{}'!",
                        CONFIG_INSTANCE.getLoadedConfigVersion(),
                        CONFIG_VERSION);
                GTNHCustomizer.getLogger()
                    .warn(
                        "Here's a stack trace for you to use if you want to file a bug report about migrations failing:");
                GTNHCustomizer.getLogger()
                    .warn(t);
                GTNHCustomizer.getLogger()
                    .warn(
                        "Any customizations you've made are probably about to get nuked!");
                File backupLocation = new File(String.format("%s.bak",
                    CONFIG_INSTANCE.getConfigFile()
                        .getAbsolutePath()));
                GTNHCustomizer.getLogger()
                    .warn(
                        "Copying your current configuration into '{}' as a backup...",
                        backupLocation.getAbsolutePath());
                try {
                    Files.copy(CONFIG_INSTANCE.getConfigFile().toPath(),
                        backupLocation.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
                } catch (Throwable tt) {
                    GTNHCustomizer.getLogger()
                        .warn(
                            "Failed to generate a backup of your current configuration!");
                    GTNHCustomizer.getLogger()
                        .warn(
                            "Here's a stack trace for you to use if you want to diagnose what happened:");
                    GTNHCustomizer.getLogger()
                        .warn(tt);
                    GTNHCustomizer.getLogger()
                        .warn(
                            "Please do NOT file a bug report about failing to create a backup, this is almost certainly NOT the mod developer's fault!");
                    GTNHCustomizer.getLogger()
                        .warn(
                            "Proceeding anyway, sorry!");
                }
                CONFIG_INSTANCE.getCategoryNames().stream()
                    .forEach((categoryName) -> CONFIG_INSTANCE.removeCategory(
                        CONFIG_INSTANCE.getCategory(categoryName)));
            }
        }

        // -- Register all our configurations
        new Gameplay().registerForgeConfigCategory(CONFIG_INSTANCE, true);
        new Debugging().registerForgeConfigCategory(CONFIG_INSTANCE, true);
        new Commands().registerForgeConfigCategory(CONFIG_INSTANCE, true);

        // -- Load updated values
        new Gameplay().loadValues(CONFIG_INSTANCE);
        new Debugging().loadValues(CONFIG_INSTANCE);
        new Commands().loadValues(CONFIG_INSTANCE);
    }

    /**
     * Get whether the configuration has changed.
     *
     * @return Whether the configuration has changed.
     */
    public static boolean hasChanged() {
        return CONFIG_INSTANCE == null ? false : CONFIG_INSTANCE.hasChanged();
    }

    /**
     * Save the configuration data to disk.
     */
    public static void save() {
        if (CONFIG_INSTANCE == null) {
            GTNHCustomizer.getLogger().error("Cannot save configuration!");
            GTNHCustomizer.getLogger()
                .error("Configuration has not been initialized yet!");
            return;
        }
        CONFIG_INSTANCE.save();
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
     * @param configInstance The configuration instance being migrated.
     *
     * @throws NoSuchElementException Thrown if no migration route exists from
     *         the old configuration version to the new configuration version.
     */
    private static void applyConfigMigrations(String fromVersion,
        String toVersion, Configuration configInstance)
        throws NoSuchElementException, NumberFormatException {
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
