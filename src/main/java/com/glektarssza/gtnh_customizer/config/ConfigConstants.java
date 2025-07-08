package com.glektarssza.gtnh_customizer.config;

import net.minecraftforge.common.config.Configuration;

public class ConfigConstants {
    /**
     * The version of the configuration format.
     */
    public static final String CONFIG_VERSION = "7";

    /**
     * The gameplay tweaks configuration category name.
     */
    public static final String CATEGORY_GAMEPLAY_NAME = "gameplay";

    /**
     * The gameplay tweaks configuration category path.
     */
    public static final String CATEGORY_GAMEPLAY_PATH = CATEGORY_GAMEPLAY_NAME;

    /**
     * The gameplay tweaks configuration category comment.
     */
    public static final String CATEGORY_GAMEPLAY_COMMENT = "Gameplay-related tweaks";

    /**
     * The gameplay tweaks configuration category language key.
     */
    public static final String CATEGORY_GAMEPLAY_LANG_KEY = String
        .format("gtnh_customizer%sconfig%scategory_%s",
            Configuration.CATEGORY_SPLITTER,
            Configuration.CATEGORY_SPLITTER,
            CATEGORY_GAMEPLAY_NAME);

    /**
     * The globally immune players property name.
     */
    public static final String PROPERTY_GLOBALLY_IMMUNE_PLAYERS_NAME = "globally_immune_players";

    /**
     * The globally immune players property path.
     */
    public static final String PROPERTY_GLOBALLY_IMMUNE_PLAYERS_PATH = String
        .format("%s%s%s",
            CATEGORY_GAMEPLAY_PATH,
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
        .format("%s%s%s",
            CATEGORY_GAMEPLAY_LANG_KEY,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_GLOBALLY_IMMUNE_PLAYERS_NAME);

    /**
     * The Tinker's Construct gameplay tweaks configuration category name.
     */
    public static final String CATEGORY_GAMEPLAY_TCONSTRUCT_NAME = "tconstruct";

    /**
     * The Tinker's Construct tweaks configuration category path.
     */
    public static final String CATEGORY_GAMEPLAY_TCONSTRUCT_PATH = String
        .format(
            "%s%s%s",
            CATEGORY_GAMEPLAY_PATH,
            Configuration.CATEGORY_SPLITTER,
            CATEGORY_GAMEPLAY_TCONSTRUCT_NAME);

    /**
     * The Tinker's Construct tweaks configuration category comment.
     */
    public static final String CATEGORY_GAMEPLAY_TCONSTRUCT_COMMENT = "Tinker's Construct gameplay-related tweaks";

    /**
     * The Tinker's Construct tweaks configuration category language key.
     */
    public static final String CATEGORY_GAMEPLAY_TCONSTRUCT_LANG_KEY = String
        .format("%s%scategory_%s",
            CATEGORY_GAMEPLAY_LANG_KEY,
            Configuration.CATEGORY_SPLITTER,
            CATEGORY_GAMEPLAY_TCONSTRUCT_NAME);

    /**
     * The allow slime sapling bone mealing property name.
     */
    public static final String PROPERTY_TCONSTRUCT_CAN_BONE_MEAL_SLIME_SAPLINGS_NAME = "can_bone_meal_slime_saplings";

    /**
     * The allow slime sapling bone mealing property path.
     */
    public static final String PROPERTY_TCONSTRUCT_CAN_BONE_MEAL_SLIME_SAPLINGS_PATH = String
        .format("%s%s%s",
            CATEGORY_GAMEPLAY_TCONSTRUCT_PATH,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_TCONSTRUCT_CAN_BONE_MEAL_SLIME_SAPLINGS_NAME);

    /**
     * The allow slime sapling bone mealing property comment.
     */
    public static final String PROPERTY_TCONSTRUCT_CAN_BONE_MEAL_SLIME_SAPLINGS_COMMENT = "Whether to allow Tinker's Construct Slime Saplings to have bone meal applied.";

    /**
     * The allow slime sapling bone mealing property language key.
     */
    public static final String PROPERTY_TCONSTRUCT_CAN_BONE_MEAL_SLIME_SAPLINGS_LANG_KEY = String
        .format("%s%s%s",
            CATEGORY_GAMEPLAY_TCONSTRUCT_LANG_KEY,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_TCONSTRUCT_CAN_BONE_MEAL_SLIME_SAPLINGS_NAME);

    /**
     * The Thaumcraft gameplay tweaks configuration category name.
     */
    public static final String CATEGORY_GAMEPLAY_THAUMCRAFT_NAME = "thaumcraft";

    /**
     * The Thaumcraft tweaks configuration category path.
     */
    public static final String CATEGORY_GAMEPLAY_THAUMCRAFT_PATH = String
        .format(
            "%s%s%s",
            CATEGORY_GAMEPLAY_PATH,
            Configuration.CATEGORY_SPLITTER,
            CATEGORY_GAMEPLAY_THAUMCRAFT_NAME);

    /**
     * The Thaumcraft tweaks configuration category comment.
     */
    public static final String CATEGORY_GAMEPLAY_THAUMCRAFT_COMMENT = "Thaumcraft gameplay-related tweaks";

    /**
     * The Tinker's Construct tweaks configuration category language key.
     */
    public static final String CATEGORY_GAMEPLAY_THAUMCRAFT_LANG_KEY = String
        .format("%s%scategory_%s",
            CATEGORY_GAMEPLAY_LANG_KEY,
            Configuration.CATEGORY_SPLITTER,
            CATEGORY_GAMEPLAY_THAUMCRAFT_NAME);

    /**
     * The can bone meal Thaumcraft Greatwood saplings property name.
     */
    public static final String PROPERTY_THAUMCRAFT_CAN_BONE_MEAL_GREATWOOD_SAPLINGS_NAME = "can_bone_meal_greatwood_saplings";

    /**
     * The can bone meal Thaumcraft Greatwood saplings property path.
     */
    public static final String PROPERTY_THAUMCRAFT_CAN_BONE_MEAL_GREATWOOD_SAPLINGS_PATH = String
        .format("%s%s%s",
            CATEGORY_GAMEPLAY_THAUMCRAFT_PATH,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_THAUMCRAFT_CAN_BONE_MEAL_GREATWOOD_SAPLINGS_NAME);

    /**
     * The can bone meal Thaumcraft Greatwood saplings property comment.
     */
    public static final String PROPERTY_THAUMCRAFT_CAN_BONE_MEAL_GREATWOOD_SAPLINGS_COMMENT = "Whether to allow Thaumcraft Greatwood Saplings to have bone meal applied.";

    /**
     * The can bone meal Thaumcraft Greatwood saplings property language key.
     */
    public static final String PROPERTY_THAUMCRAFT_CAN_BONE_MEAL_GREATWOOD_SAPLINGS_LANG_KEY = String
        .format("%s%s%s",
            CATEGORY_GAMEPLAY_THAUMCRAFT_LANG_KEY,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_THAUMCRAFT_CAN_BONE_MEAL_GREATWOOD_SAPLINGS_NAME);

    /**
     * The can bone meal Thaumcraft Silverwood saplings property name.
     */
    public static final String PROPERTY_THAUMCRAFT_CAN_BONE_MEAL_SILVERWOOD_SAPLINGS_NAME = "can_bone_meal_silverwood_saplings";

    /**
     * The can bone meal Thaumcraft Silverwood saplings property path.
     */
    public static final String PROPERTY_THAUMCRAFT_CAN_BONE_MEAL_SILVERWOOD_SAPLINGS_PATH = String
        .format("%s%s%s",
            CATEGORY_GAMEPLAY_THAUMCRAFT_PATH,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_THAUMCRAFT_CAN_BONE_MEAL_SILVERWOOD_SAPLINGS_NAME);

    /**
     * The can bone meal Thaumcraft Silverwood saplings property comment.
     */
    public static final String PROPERTY_THAUMCRAFT_CAN_BONE_MEAL_SILVERWOOD_SAPLINGS_COMMENT = "Whether to allow Thaumcraft Silverwood Saplings to have bone meal applied.";

    /**
     * The can bone meal Thaumcraft Silverwood saplings property language key.
     */
    public static final String PROPERTY_THAUMCRAFT_CAN_BONE_MEAL_SILVERWOOD_SAPLINGS_LANG_KEY = String
        .format("%s%s%s",
            CATEGORY_GAMEPLAY_THAUMCRAFT_LANG_KEY,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_THAUMCRAFT_CAN_BONE_MEAL_SILVERWOOD_SAPLINGS_NAME);

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
        .format("gtnh_customizer%sconfig%scategory_%s",
            Configuration.CATEGORY_SPLITTER,
            Configuration.CATEGORY_SPLITTER,
            CATEGORY_COMMAND_PATH);

    /**
     * Whether the {@code repair} command raycast while looking for containers
     * should ignore liquids property name.
     */
    public static final String PROPERTY_REPAIR_COMMAND_RAYCAST_IGNORES_LIQUIDS_NAME = "repair_raycast_ignores_liquids";

    /**
     * Whether the {@code repair} command raycast while looking for containers
     * should ignore liquids property path.
     */
    public static final String PROPERTY_REPAIR_COMMAND_RAYCAST_IGNORES_LIQUIDS_PATH = String
        .format("%s%s%s", CATEGORY_COMMAND_PATH,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_REPAIR_COMMAND_RAYCAST_IGNORES_LIQUIDS_NAME);

    /**
     * Whether the {@code repair} command raycast while looking for containers
     * should ignore liquids property language key.
     */
    public static final String PROPERTY_REPAIR_COMMAND_RAYCAST_IGNORES_LIQUIDS_LANG_KEY = String
        .format("%s%s%s", CATEGORY_COMMAND_LANG_KEY,
            Configuration.CATEGORY_SPLITTER,
            PROPERTY_REPAIR_COMMAND_RAYCAST_IGNORES_LIQUIDS_NAME);

    /**
     * Whether the {@code repair} command raycast while looking for containers
     * should ignore liquids property comment.
     */
    public static final String PROPERTY_REPAIR_COMMAND_RAYCAST_IGNORES_LIQUIDS_COMMENT = "Whether the 'repair' command ignores liquids when raycasting to look for containers to repair items inside of.";

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
        .format("gtnh_customizer%sconfig%scategory_%s",
            Configuration.CATEGORY_SPLITTER,
            Configuration.CATEGORY_SPLITTER,
            CATEGORY_DEBUGGING_PATH);

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
