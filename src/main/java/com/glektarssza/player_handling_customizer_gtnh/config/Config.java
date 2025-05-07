package com.glektarssza.player_handling_customizer_gtnh.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

import com.glektarssza.player_handling_customizer_gtnh.PlayerHandlingCustomizer;

/**
 * The main configuration for the mod.
 */
public class Config {
    /**
     * The configuration instance.
     */
    private static Configuration configInstance;

    /**
     * The version of the configuration format.
     */
    public static final String CONFIG_VERSION = "1";

    /**
     * The general configuration category.
     */
    private static final String CATEGORY_GENERAL = "general";

    /**
     * A list of players who are globally immune.
     */
    private static List<String> immunePlayers = new ArrayList<String>();

    /**
     * Get the globally immune players.
     *
     * @return The globally immune players.
     */
    public static String[] getImmunePlayers() {
        String[] ret = new String[immunePlayers.size()];
        immunePlayers.toArray(ret);
        return ret;
    }

    /**
     * Set the globally immune players.
     *
     * @param players A list of player UUIDs or names to set as globally immune.
     */
    public static void setImmunePlayers(String[] players) {
        clearImmunePlayers();
        immunePlayers.addAll(Arrays.asList(players));
    }

    /**
     * Clear the globally immune players.
     */
    public static void clearImmunePlayers() {
        immunePlayers.clear();
    }

    /**
     * Get the main level configuration categories.
     *
     * @return A list of the main level configuration categories.
     */
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
     */
    public static void init(File configDir, String fileName) {
        if (configInstance != null) {
            return;
        }

        configInstance = new Configuration(new File(configDir, fileName),
            CONFIG_VERSION);

        configInstance
            .setCategoryComment(CATEGORY_GENERAL,
                "The general configuration category.")
            .setCategoryLanguageKey(CATEGORY_GENERAL,
                "player_handling_customizer.config.basic_category")
            .setCategoryRequiresMcRestart(CATEGORY_GENERAL, false);

        configInstance
            .get(CATEGORY_GENERAL, "immunePlayers", new String[0])
            .setLanguageKey("player_handling_customizer.config.immune_players")
            .setShowInGui(true)
            .setRequiresMcRestart(false)
            .setRequiresWorldRestart(false);
    }

    /**
     * Load the configuration data from disk.
     */
    public static void load() {
        if (configInstance == null) {
            PlayerHandlingCustomizer.LOGGER.error(
                "Cannot load configuration!");
            PlayerHandlingCustomizer.LOGGER
                .error("Configuration has not been initialized yet!");
            return;
        }
        configInstance.load();
        String[] currentImmunePlayers = new String[immunePlayers.size()];
        immunePlayers.toArray(currentImmunePlayers);
        currentImmunePlayers = configInstance
            .get(CATEGORY_GENERAL, "immunePlayers", currentImmunePlayers)
            .getStringList();
        setImmunePlayers(currentImmunePlayers);
    }

    /**
     * Save the configuration data to disk.
     */
    public static void save() {
        if (configInstance == null) {
            PlayerHandlingCustomizer.LOGGER.error(
                "Cannot save configuration!");
            PlayerHandlingCustomizer.LOGGER
                .error("Configuration has not been initialized yet!");
            return;
        }
        if (configInstance.hasChanged()) {
            configInstance.save();
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
}
