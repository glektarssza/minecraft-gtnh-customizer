package com.glektarssza.player_handling_customizer_gtnh.api;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants.NBT;

/**
 * An interface that represents immunity from knockback events.
 */
public interface IKnockbackImmunity extends IImmunity<NBTTagCompound> {

    /**
     * Get the type of immunity represented by this instance.
     *
     * @return The type of immunity represented by this instance.
     */
    @Override
    default ImmunityType getImmunityType() {
        return ImmunityType.Hurt;
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
        if (entityType != null) {
            nbt.setString("entityType", entityType);
        }
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
        if (type != ImmunityType.Knockback) {
            return;
        }
        if (nbt.hasKey("entityType", NBT.TAG_STRING)) {
            this.setEntityType(nbt.getString("entityType"));
        } else {
            this.setEntityType(null);
        }
    }
}
