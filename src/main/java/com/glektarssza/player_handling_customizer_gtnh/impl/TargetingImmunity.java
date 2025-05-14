package com.glektarssza.player_handling_customizer_gtnh.impl;

import com.glektarssza.player_handling_customizer_gtnh.api.ITargetingImmunity;

/**
 * A concrete implementation of the {@link ITargetingImmunity} interface.
 */
public class TargetingImmunity implements ITargetingImmunity {

    /**
     * The entity source that this instance grants immunity to damage from.
     */
    private String entityType;

    /**
     * Create a new instance.
     */
    public TargetingImmunity() {
        this.entityType = null;
    }

    /**
     * Create a new instance.
     *
     * @param entityType The entity type to create the new instance with.
     */
    public TargetingImmunity(String entityType) {
        this.entityType = entityType;
    }

    /**
     * Get whether this instance has an entity type from which this instance
     * grants immunity from.
     *
     * @return Whether this instance has an entity type from which this instance
     *         grants immunity from.
     */
    @Override
    public boolean hasEntityType() {
        return this.entityType != null;
    }

    /**
     * Get the entity source that this instance grants immunity to damage from.
     *
     * @return The entity source that this instance grants immunity to damage
     *         from.
     */
    @Override
    public String getEntityType() {
        return this.entityType;
    }

    /**
     * Set the entity type that this instance grants immunity to damage from.
     *
     * @param entityType The entity type that this instance grants immunity to
     *        damage from.
     */
    @Override
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
