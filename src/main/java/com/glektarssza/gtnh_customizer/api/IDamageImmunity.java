package com.glektarssza.gtnh_customizer.api;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;

import net.minecraftforge.common.util.Constants.NBT;

/**
 * An interface that represents immunity from damage events.
 */
public interface IDamageImmunity extends IPhysicalImmunity {

    /**
     * Get the type of immunity represented by this instance.
     *
     * @return The type of immunity represented by this instance.
     */
    @Override
    default ImmunityType getImmunityType() {
        return ImmunityType.Damage;
    }

    /**
     * Serialize this instance into NBT data.
     *
     * @return NBT data representing this instance.
     */
    @Override
    default NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagString immunityType = ImmunityType
            .toNBTString(this.getImmunityType());
        String entityType = null;
        if (immunityType == null) {
            return nbt;
        }
        if (this.hasEntityType()) {
            entityType = this.getEntityType();
        }
        nbt.setTag("immunityType", immunityType);
        nbt.setString("damageType", this.getDamageType());
        if (entityType != null) {
            nbt.setString("entityType", entityType);
        }
        nbt.setBoolean("appliesToDirect", this.getAppliesToDirectDamage());
        nbt.setBoolean("appliesToIndirect", this.getAppliesToIndirectDamage());
        return nbt;
    }

    /**
     * Deserialize NBT data into this instance.
     *
     * @param nbt The NBT data to deserialize into this instance.
     */
    @Override
    default void deserializeNBT(NBTTagCompound nbt) {
        if (!nbt.hasKey("immunityType", NBT.TAG_STRING)) {
            return;
        }
        ImmunityType type = ImmunityType
            .fromNBTString((NBTTagString) nbt.getTag("immunityType"));
        if (type != ImmunityType.Damage) {
            return;
        }
        if (!nbt.hasKey("damageType", NBT.TAG_STRING)) {
            return;
        }
        this.setDamageType(nbt.getString("damageType"));
        if (nbt.hasKey("entityType", NBT.TAG_STRING)) {
            this.setEntityType(nbt.getString("entityType"));
        } else {
            this.setEntityType(null);
        }
        if (nbt.hasKey("appliesToDirect", NBT.TAG_ANY_NUMERIC)) {
            this.setAppliesToDirectDamage(nbt.getBoolean("appliesToDirect"));
        } else {
            this.setAppliesToDirectDamage(false);
        }
        if (nbt.hasKey("appliesToIndirect", NBT.TAG_ANY_NUMERIC)) {
            this.setAppliesToDirectDamage(nbt.getBoolean("appliesToIndirect"));
        } else {
            this.setAppliesToDirectDamage(false);
        }
    }
}
