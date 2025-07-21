package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

import net.minecraft.nbt.NBTTagList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import com.glektarssza.gtnh_customizer.utils.NBTUtils;

import serverutils.client.gui.GuiEditNBT.ButtonNBTList;

@Mixin(ButtonNBTList.class)
public class ButtonNBTListMixin {
    /**
     * A shadow of the {@code list} private field.
     */
    @Shadow(remap = false)
    private NBTTagList list;

    /**
     * Check if this list is a list of a given type of NBT tags.
     *
     * @return {@code true} if this list is a list of the given NBT tag type or
     *         an empty list, {@code false} otherwise.
     */
    @Unique
    public boolean isListOf(int nbtType) {
        return NBTUtils.isListOfNBTType(this.list, nbtType)
            || NBTUtils.isNBTListEmpty(this.list);
    }
}
