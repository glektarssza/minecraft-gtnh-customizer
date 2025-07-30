package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

import net.minecraft.nbt.NBTTagList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import com.glektarssza.gtnh_customizer.utils.NBTUtils;
import com.glektarssza.gtnh_customizer.utils.extensions.IButtonNBTListExtensions;

import serverutils.client.gui.GuiEditNBT.ButtonNBTList;

/**
 * A mixin for the {@link ButtonNBTList} class.
 */
@Mixin(ButtonNBTList.class)
public abstract class ButtonNBTListMixin implements IButtonNBTListExtensions {
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
    @Override
    @Unique
    public boolean isListOf(int nbtType) {
        return NBTUtils.isListOfNBTType(this.list, nbtType)
            || this.isEmpty();
    }

    /**
     * Check if this contains any elements.
     *
     * @return {@code true} if this does not contain any elements, {@code false}
     *         otherwise.
     */
    @Override
    @Unique
    public boolean isEmpty() {
        return NBTUtils.isNBTListEmpty(this.list);
    }
}
