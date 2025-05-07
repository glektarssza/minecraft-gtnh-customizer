package com.glektarssza.player_handling_customizer_gtnh.api;

import net.minecraft.nbt.NBTBase;

/**
 * An interface that defines an immunity of some kind.
 */
public interface IImmunity<T extends NBTBase> {
    /**
     * Get the type of immunity represented by this instance.
     *
     * @return The type of immunity represented by this instance.
     */
    ImmunityType getImmunityType();

    /**
     * Get whether this instance has an entity type from which this instance
     * grants immunity from.
     *
     * @return Whether this instance has an entity type from which this instance
     *         grants immunity from.
     */
    boolean hasEntityType();

    /**
     * Get the entity type that this instance grants immunity to damage from.
     *
     * @return The entity type that this instance grants immunity to damage
     *         from.
     */
    String getEntityType();

    /**
     * Set the entity type that this instance grants immunity to damage from.
     *
     * @param entityType The entity type that this instance grants immunity to
     *        damage from.
     */
    void setEntityType(String entityType);

    T serializeNBT();

    void deserializeNBT(T nbtData);
}
