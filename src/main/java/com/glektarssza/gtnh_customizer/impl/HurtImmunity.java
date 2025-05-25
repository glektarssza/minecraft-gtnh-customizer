package com.glektarssza.gtnh_customizer.impl;

import com.glektarssza.gtnh_customizer.api.immunity.IHurtImmunity;

/**
 * A concrete implementation of the {@link IHurtImmunity} interface.
 */
public class HurtImmunity implements IHurtImmunity {

    /**
     * The type of damage this instance represents immunity from.
     */
    private String damageType;

    /**
     * The entity source that this instance grants immunity to damage from.
     */
    private String entityType;

    /**
     * Whether this instance applies to direct damage sources.
     */
    private boolean appliesToDirect;

    /**
     * Whether this instance applies to indirect damage sources.
     */
    private boolean appliesToIndirect;

    /**
     * Create a new instance.
     */
    public HurtImmunity() {
        this.damageType = "generic";
        this.entityType = null;
        this.appliesToDirect = false;
        this.appliesToIndirect = false;
    }

    /**
     * Create a new instance.
     *
     * @param damageType The damage type to create the new instance with.
     */
    public HurtImmunity(String damageType) {
        this.damageType = damageType;
        this.entityType = null;
        this.appliesToDirect = false;
        this.appliesToIndirect = false;
    }

    /**
     * Create a new instance.
     *
     * @param damageType The damage type to create the new instance with.
     * @param entityType The entity type to create the new instance with.
     */
    public HurtImmunity(String damageType, String entityType) {
        this.damageType = damageType;
        this.entityType = entityType;
        this.appliesToDirect = false;
        this.appliesToIndirect = false;
    }

    /**
     * Create a new instance.
     *
     * @param damageType The damage type to create the new instance with.
     * @param entityType The entity type to create the new instance with.
     * @param appliesToDirect Whether the new instances applies to direct hurt
     *        sources.
     */
    public HurtImmunity(String damageType, String entityType,
        boolean appliesToDirect) {
        this.damageType = damageType;
        this.entityType = entityType;
        this.appliesToDirect = appliesToDirect;
        this.appliesToIndirect = false;
    }

    /**
     * Create a new instance.
     *
     * @param damageType The damage type to create the new instance with.
     * @param entityType The entity type to create the new instance with.
     * @param appliesToDirect Whether the new instances applies to direct hurt
     *        sources.
     * @param appliesToIndirect Whether the new instances applies to indirect
     *        hurt sources.
     */
    public HurtImmunity(String damageType, String entityType,
        boolean appliesToDirect, boolean appliesToIndirect) {
        this.damageType = damageType;
        this.entityType = entityType;
        this.appliesToDirect = appliesToDirect;
        this.appliesToIndirect = appliesToIndirect;
    }

    /**
     * Get the type of damage this instance represents immunity from.
     *
     * @return The type of damage this instance represents immunity from.
     */
    @Override
    public String getDamageType() {
        return this.damageType;
    }

    /**
     * Set the type of damage this instance represents immunity from.
     *
     * @param damageType The type of damage this instance represents immunity
     *        from.
     */
    @Override
    public void setDamageType(String damageType) {
        this.damageType = damageType;
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

    /**
     * Get whether this instance applies to direct damage sources.
     *
     * @return Whether this instance applies to direct damage sources.
     */
    @Override
    public boolean getAppliesToDirectDamage() {
        return this.appliesToDirect;
    }

    /**
     * Set whether this instance applies to direct damage sources.
     *
     * @param value Whether this instance applies to direct damage sources.
     */
    @Override
    public void setAppliesToDirectDamage(boolean value) {
        this.appliesToDirect = value;
    }

    /**
     * Get whether this instance applies to indirect damage sources.
     *
     * @return Whether this instance applies to indirect damage sources.
     */
    @Override
    public boolean getAppliesToIndirectDamage() {
        return this.appliesToIndirect;
    }

    /**
     * Set whether this instance applies to indirect damage sources.
     *
     * @param value Whether this instance applies to indirect damage sources.
     */
    @Override
    public void setAppliesToIndirectDamage(boolean value) {
        this.appliesToIndirect = value;
    }
}
