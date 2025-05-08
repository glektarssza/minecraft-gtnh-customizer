package com.glektarssza.player_handling_customizer_gtnh.api;

import net.minecraft.nbt.NBTTagString;

/**
 * An enumeration of known types of immunities.
 */
public enum ImmunityType {
    /**
     * Immunity to damage events.
     */
    Damage,

    /**
     * Immunity to hurt events.
     */
    Hurt,

    /**
     * Immunity to knockback events.
     */
    Knockback,

    /**
     * Immunity to being targeted by entities.
     */
    Targeting;

    /**
     * Parse a NBT string tag into an enumeration value.
     *
     * @param value The NBT string tag to parse.
     *
     * @return An enumeration value that matches the NBT string tag;
     *         {@code null} if none match.
     */
    public static ImmunityType fromNBTString(NBTTagString value) {
        switch (value.func_150285_a_().toLowerCase()) {
            case "damage":
                return Damage;
            case "hurt":
                return Hurt;
            case "knockback":
                return Knockback;
            case "targeting":
                return Targeting;
            default:
                return null;
        }
    }

    /**
     * Get the given enumeration value as a NBT string tag.
     *
     * @param value The value to get as a NBT string tag.
     *
     * @return The NBT string tag equivalent of the value if available;
     *         {@code null} otherwise.
     */
    public static NBTTagString toNBTString(ImmunityType value) {
        switch (value) {
            case Damage:
                return new NBTTagString("damage");
            case Hurt:
                return new NBTTagString("hurt");
            case Knockback:
                return new NBTTagString("knockback");
            case Targeting:
                return new NBTTagString("targeting");
            default:
                return null;
        }
    }
}
