package com.glektarssza.player_handling_customizer_gtnh.api;

import net.minecraft.nbt.NBTTagCompound;

/**
 * An interface that represents immunity from physical events.
 */
public interface IPhysicalImmunity extends IImmunity<NBTTagCompound> {

    /**
     * Get the type of damage this instance represents immunity from.
     *
     * @return The type of damage this instance represents immunity from.
     */
    String getDamageType();

    /**
     * Set the type of damage this instance represents immunity from.
     *
     * @param damageType The type of damage this instance represents immunity
     *        from.
     */
    void setDamageType(String damageType);

    /**
     * Get whether this instance applies to direct damage sources.
     *
     * @return Whether this instance applies to direct damage sources.
     */
    boolean getAppliesToDirectDamage();

    /**
     * Set whether this instance applies to direct damage sources.
     *
     * @param value Whether this instance applies to direct damage sources.
     */
    void setAppliesToDirectDamage(boolean value);

    /**
     * Get whether this instance applies to indirect damage sources.
     *
     * @return Whether this instance applies to indirect damage sources.
     */
    boolean getAppliesToIndirectDamage();

    /**
     * Set whether this instance applies to indirect damage sources.
     *
     * @param value Whether this instance applies to indirect damage sources.
     */
    void setAppliesToIndirectDamage(boolean value);
}
