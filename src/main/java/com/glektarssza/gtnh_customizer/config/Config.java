package com.glektarssza.gtnh_customizer.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

import com.glektarssza.gtnh_customizer.GTNHCustomizer;
import com.glektarssza.gtnh_customizer.utils.KeyExistsException;

/**
 * The main configuration for the mod.
 */
public class Config {

    /**
     * The configuration instance.
     */
    private static Configuration CONFIG_INSTANCE;

    /**
     * The version of the configuration format.
     */
    public static final String CONFIG_VERSION = "2";

    /**
     * The general configuration category.
     */
    private static final String CATEGORY_GENERAL = "general";

    /**
     * A list of players who are globally immune.
     */
    private static final List<String> globallyImmunePlayers = new ArrayList<String>();

    /**
     * A map of configuration migrations to apply to move between configuration
     * versions.
     *
     * Keys are in the format of {@code fromVersion:toVersion}.
     */
    private static final Map<String, Consumer<Configuration>> configMigrations = new HashMap<String, Consumer<Configuration>>();

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
     * @throws KeyExistsException Thrown if a duplicate configuration migration
     *         is registered.
     * @throws NoSuchElementException Thrown if configuration migration is
     *         required and no migration route exists from the old configuration
     *         version to the new configuration version.
     */
    public static void init(File configDir, String fileName)
        throws KeyExistsException, NoSuchElementException {
        // -- Register migrations
        registerMigration("1", "2", (configInstance) -> {
            configInstance.renameProperty(CATEGORY_GENERAL, "immunePlayers",
                "globallyImmunePlayers");
        });

        if (CONFIG_INSTANCE != null) {
            return;
        }

        CONFIG_INSTANCE = new Configuration(new File(configDir, fileName),
            CONFIG_VERSION);

        CONFIG_INSTANCE
            .setCategoryComment(CATEGORY_GENERAL,
                "The general configuration category.")
            .setCategoryLanguageKey(CATEGORY_GENERAL,
                "gtnh_customizer.config.category_general")
            .setCategoryRequiresMcRestart(CATEGORY_GENERAL, false);

        CONFIG_INSTANCE
            .get(CATEGORY_GENERAL, "globallyImmunePlayers", new String[0])
            .setLanguageKey(
                "gtnh_customizer.config.category_general.globally_immune_players")
            .setShowInGui(true)
            .setRequiresMcRestart(false)
            .setRequiresWorldRestart(false);
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
        if (!CONFIG_INSTANCE.getLoadedConfigVersion().equals(CONFIG_VERSION)) {
            applyConfigMigrations(CONFIG_INSTANCE.getLoadedConfigVersion(),
                CONFIG_VERSION, CONFIG_INSTANCE);
        }
        String[] currentImmunePlayers = new String[globallyImmunePlayers
            .size()];
        globallyImmunePlayers.toArray(currentImmunePlayers);
        currentImmunePlayers = CONFIG_INSTANCE
            .get(CATEGORY_GENERAL, "globallyImmunePlayers",
                currentImmunePlayers)
            .getStringList();
        setImmunePlayers(currentImmunePlayers);
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
     *
     * @param configDir The directory the configuration file will live in.
     * @param fileName The name of the file to save the configuration to.
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
        throws NoSuchElementException {
        if (!configMigrations
            .containsKey(String.format("%s:%s", fromVersion, toVersion))) {
            throw new NoSuchElementException(String.format(
                "No available migration route from configuration versionn '%s' to configuration version '%s'!",
                fromVersion, toVersion));
        }
        configMigrations.get(String.format("%s:%s", fromVersion, toVersion))
            .accept(configInstance);
    }

    /**
     * Register a function which can migrate from a given configuration version
     * to a given configuration version.
     *
     * @param fromVersion The version which will be migrated from.
     * @param toVersion The version which will be migrated to.
     * @param migrator The function which will perform the migration.
     *
     * @throws KeyExistsException Thrown if a migration already exists from the
     *         old configuration version to the new configuration version.
     */
    private static void registerMigration(String fromVersion, String toVersion,
        Consumer<Configuration> migrator) throws KeyExistsException {
        if (configMigrations.containsKey(
            String.format("%s:%s", fromVersion, toVersion))) {
            throw new KeyExistsException(
                String.format(
                    "Migration already exists from configuration '%s' version to configuration version '%s'",
                    fromVersion,
                    toVersion));
        }
        configMigrations.put(String.format("%s:%s", fromVersion, toVersion),
            migrator);

    }
}
