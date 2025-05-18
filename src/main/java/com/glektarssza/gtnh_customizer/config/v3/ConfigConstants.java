package com.glektarssza.gtnh_customizer.config.v3;

import net.minecraftforge.common.config.Configuration;

public class ConfigConstants {
    /**
     * The version of the configuration format.
     */
    public static final String CONFIG_VERSION = "3";

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
