package com.glektarssza.gtnh_customizer.config.v5;

import net.minecraftforge.common.config.Configuration;

public class ConfigConstants {
    /**
     * The version of the configuration format.
     */
    public static final String CONFIG_VERSION = "5";

    /**
     * The general configuration category name.
     */
    public static final String CATEGORY_GENERAL_NAME = "general";

    /**
     * The general configuration category path.
     */
    public static final String CATEGORY_GENERAL_PATH = CATEGORY_GENERAL_NAME;

    /**
     * The general configuration category comment.
     */
    public static final String CATEGORY_GENERAL_COMMENT = "General settings";

    /**
     * The general configuration category language key.
     */
    public static final String CATEGORY_GENERAL_LANG_KEY = String
        .format("gtnh_customizer.config.category_%s", CATEGORY_GENERAL_NAME);

    /**
     * The globally immune players property name.
     */
    public static final String PROPERTY_GLOBALLY_IMMUNE_PLAYERS_NAME = "globally_immune_players";

    /**
     * The globally immune players property path.
     */
    public static final String PROPERTY_GLOBALLY_IMMUNE_PLAYERS_PATH = String
        .format("%s%s%s", CATEGORY_GENERAL_PATH,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_GLOBALLY_IMMUNE_PLAYERS_NAME);

    /**
     * The globally immune players property comment.
     */
    public static final String PROPERTY_GLOBALLY_IMMUNE_PLAYERS_COMMENT = "A list of players who are globally immune to being targeted.";

    /**
     * The globally immune players property language key.
     */
    public static final String PROPERTY_GLOBALLY_IMMUNE_PLAYERS_LANG_KEY = String
        .format("%s%s%s", CATEGORY_GENERAL_LANG_KEY,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_GLOBALLY_IMMUNE_PLAYERS_NAME);

    /**
     * The command configuration category name.
     */
    public static final String CATEGORY_COMMAND_NAME = "command";

    /**
     * The command configuration category path.
     */
    public static final String CATEGORY_COMMAND_PATH = CATEGORY_COMMAND_NAME;

    /**
     * The command configuration category comment.
     */
    public static final String CATEGORY_COMMAND_COMMENT = "Command settings";

    /**
     * The command configuration category language key.
     */
    public static final String CATEGORY_COMMAND_LANG_KEY = String
        .format("gtnh_customizer.config.category_%s", CATEGORY_COMMAND_PATH);

    /**
     * Whether the {@code repair} command should ignore liquids property name.
     */
    public static final String PROPERTY_REPAIR_COMMAND_IGNORES_LIQUIDS_NAME = "repair_ignores_liquids";

    /**
     * Whether the {@code repair} command should ignore liquids property path.
     */
    public static final String PROPERTY_REPAIR_COMMAND_IGNORES_LIQUIDS_PATH = String
        .format("%s%s%s", CATEGORY_COMMAND_PATH,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_REPAIR_COMMAND_IGNORES_LIQUIDS_NAME);

    /**
     * Whether the {@code repair} command should ignore liquids property
     * language key.
     */
    public static final String PROPERTY_REPAIR_COMMAND_IGNORES_LIQUIDS_LANG_KEY = String
        .format("%s%s%s", CATEGORY_COMMAND_LANG_KEY,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_REPAIR_COMMAND_IGNORES_LIQUIDS_NAME);

    /**
     * Whether the {@code repair} command should ignore liquids property
     * comment.
     */
    public static final String PROPERTY_REPAIR_COMMAND_IGNORES_LIQUIDS_COMMENT = "Whether the 'repair' command ignores liquids when looking for contains to repair items inside of.";

    /**
     * The maximum number of blocks the {@code extinguish} command should
     * process property name.
     */
    public static final String PROPERTY_EXTINGUISH_COMMAND_MAX_VOLUME_NAME = "extinguish_max_volume";

    /**
     * The maximum number of blocks the {@code extinguish} command should
     * process property path.
     */
    public static final String PROPERTY_EXTINGUISH_COMMAND_MAX_VOLUME_PATH = String
        .format("%s%s%s", CATEGORY_COMMAND_PATH,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_EXTINGUISH_COMMAND_MAX_VOLUME_NAME);

    /**
     * Whether the {@code repair} command should ignore liquids property
     * language key.
     */
    public static final String PROPERTY_EXTINGUISH_COMMAND_MAX_VOLUME_LANG_KEY = String
        .format("%s%s%s", CATEGORY_COMMAND_LANG_KEY,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_EXTINGUISH_COMMAND_MAX_VOLUME_NAME);

    /**
     * The maximum number of blocks the {@code extinguish} command should
     * process property comment.
     */
    public static final String PROPERTY_EXTINGUISH_COMMAND_MAX_VOLUME_COMMENT = "The maximum number of blocks the 'extinguish' command will process.";

    /**
     * The debugging configuration category name.
     */
    public static final String CATEGORY_DEBUGGING_NAME = "debugging";

    /**
     * The debugging configuration category path.
     */
    public static final String CATEGORY_DEBUGGING_PATH = CATEGORY_DEBUGGING_NAME;

    /**
     * The debugging configuration category comment.
     */
    public static final String CATEGORY_DEBUGGING_COMMENT = "Debugging settings";

    /**
     * The debugging configuration category language key.
     */
    public static final String CATEGORY_DEBUGGING_LANG_KEY = String
        .format("gtnh_customizer.config.category_%s", CATEGORY_DEBUGGING_PATH);

    /**
     * The verbose logging property name.
     */
    public static final String PROPERTY_DEBUG_LOGGING_NAME = "verbose_logging";

    /**
     * The verbose logging property path.
     */
    public static final String PROPERTY_DEBUG_LOGGING_PATH = String
        .format("%s%s%s", CATEGORY_DEBUGGING_PATH,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_DEBUG_LOGGING_NAME);

    /**
     * The verbose logging property comment.
     */
    public static final String PROPERTY_DEBUG_LOGGING_COMMENT = "Enable verbose logging.";

    /**
     * The verbose logging property language key.
     */
    public static final String PROPERTY_DEBUG_LOGGING_LANG_KEY = String
        .format("%s%s%s", CATEGORY_DEBUGGING_LANG_KEY,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_DEBUG_LOGGING_NAME);
}
