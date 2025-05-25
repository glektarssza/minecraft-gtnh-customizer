package com.glektarssza.gtnh_customizer.impl;

import com.glektarssza.gtnh_customizer.api.immunity.IKnockbackImmunity;

/**
 * A concrete implementation of the {@link IKnockbackImmunity} interface.
 */
public class KnockbackImmunity implements IKnockbackImmunity {

    /**
     * The entity source that this instance grants immunity to damage from.
     */
    private String entityType;

    /**
     * Create a new instance.
     */
    public KnockbackImmunity() {
        this.entityType = null;
    }

    /**
     * Create a new instance.
     *
     * @param entityType The entity type to create the new instance with.
     */
    public KnockbackImmunity(String entityType) {
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
