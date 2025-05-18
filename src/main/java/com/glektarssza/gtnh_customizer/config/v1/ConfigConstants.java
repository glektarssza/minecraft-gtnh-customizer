package com.glektarssza.gtnh_customizer.config.v1;

import net.minecraftforge.common.config.Configuration;

public class ConfigConstants {
    /**
     * The version of the configuration format.
     */
    public static final String CONFIG_VERSION = "1";

    /**
     * The general configuration category name.
     */
    public static final String CATEGORY_GENERAL_NAME = "general";

    /**
     * The general configuration category path.
     */
    public static final String CATEGORY_GENERAL_PATH = "general";

    /**
     * The globally immune players property name.
     */
    public static final String PROPERTY_GLOBALLY_IMMUNE_PLAYERS_NAME = "immunePlayers";

    /**
     * The globally immune players property path.
     */
    public static final String PROPERTY_GLOBALLY_IMMUNE_PLAYERS_PATH = String
        .format("%s%s%s", CATEGORY_GENERAL_PATH,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_GLOBALLY_IMMUNE_PLAYERS_NAME);
}
