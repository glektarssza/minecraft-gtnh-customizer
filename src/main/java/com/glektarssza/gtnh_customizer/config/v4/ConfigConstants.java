package com.glektarssza.gtnh_customizer.config.v4;

import net.minecraftforge.common.config.Configuration;

public class ConfigConstants {
    /**
     * The version of the configuration format.
     */
    public static final String CONFIG_VERSION = "4";

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
    public static final String CATEGORY_GENERAL_LANG_KEY = "gtnh_customizer.config.category_general";

    /**
     * The globally immune players property name.
     */
    public static final String PROPERTY_GLOBALLY_IMMUNE_PLAYERS_NAME = "globally_immune_players";

    /**
     * The globally immune players property path.
     */
    public static final String PROPERTY_GLOBALLY_IMMUNE_PLAYERS_PATH = String
        .format("%s%s%s", CATEGORY_GENERAL_NAME,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_GLOBALLY_IMMUNE_PLAYERS_NAME);

    /**
     * The globally immune players property comment.
     */
    public static final String PROPERTY_GLOBALLY_IMMUNE_PLAYERS_COMMENT = "A list of players who are globally immune to being targeted.";

    /**
     * The globally immune players property language key.
     */
    public static final String PROPERTY_GLOBALLY_IMMUNE_PLAYERS_LANG_KEY = "gtnh_customizer.config.category_general.globally_immune_players";

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
    public static final String CATEGORY_COMMAND_LANG_KEY = "gtnh_customizer.config.category_command";

    /**
     * Whether the {@code repair} command should ignore liquids property name.
     */
    public static final String PROPERTY_REPAIR_COMMAND_IGNORES_LIQUIDS_NAME = "repair_ignores_liquids";

    /**
     * Whether the {@code repair} command should ignore liquids property path.
     */
    public static final String PROPERTY_REPAIR_COMMAND_IGNORES_LIQUIDS_PATH = String
        .format("%s%s%s", CATEGORY_GENERAL_NAME,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_REPAIR_COMMAND_IGNORES_LIQUIDS_NAME);

    /**
     * Whether the {@code repair} command should ignore liquids property
     * comment.
     */
    public static final String PROPERTY_REPAIR_COMMAND_IGNORES_LIQUIDS_COMMENT = "Whether the 'repair' command ignores liquids when looking for contains to repair items inside of.";

    /**
     * Whether the {@code repair} command should ignore liquids property
     * language key.
     */
    public static final String PROPERTY_REPAIR_COMMAND_IGNORES_LIQUIDS_LANG_KEY = "gtnh_customizer.config.category_general.repair_ignores_liquids";

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
    public static final String CATEGORY_DEBUGGING_LANG_KEY = "gtnh_customizer.config.category_debugging";

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
    public static final String PROPERTY_DEBUG_LOGGING_LANG_KEY = "gtnh_customizer.config.category_debugging.verbose_logging";
}
