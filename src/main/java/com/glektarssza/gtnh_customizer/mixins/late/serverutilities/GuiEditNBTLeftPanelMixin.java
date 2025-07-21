package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;

import net.minecraftforge.common.util.Constants.NBT;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import com.glektarssza.gtnh_customizer.utils.extensions.IButtonNBTListExtensions;
import com.glektarssza.gtnh_customizer.utils.extensions.IGuiEditNBTExtensions;

import serverutils.client.gui.GuiEditNBT;
import serverutils.client.gui.GuiEditNBT.ButtonNBT;
import serverutils.client.gui.GuiEditNBT.ButtonNBTList;
import serverutils.lib.gui.Panel;

/**
 * Mixin to the anonymous inner class that provides the upper left button panel
 * for the {@link GuiEditNBT} class.
 */
@Mixin(targets = "serverutils.client.gui.GuiEditNBT$2")
public abstract class GuiEditNBTLeftPanelMixin extends Panel {
    /**
     * Make Java happy.
     *
     * @param parent The parent panel that owns this panel.
     */
    public GuiEditNBTLeftPanelMixin(Panel parent) {
        super(parent);
    }

    /**
     * Inject into the {@code addWidgets} method.
     */
    @Inject(method = "addWidgets", at = @At("TAIL"), remap = false)
    public void addWidgets$injectExtraButtons() {
        GuiEditNBT guiEditNBT = (GuiEditNBT) this.parent;
        ButtonNBT selected = ((IGuiEditNBTExtensions) guiEditNBT).getSelected();
        if (selected instanceof ButtonNBTList) {
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_COMPOUND)) {
                this.add(guiEditNBT.newTag(this, "Compound", GuiEditNBT.NBT_MAP,
                    NBTTagCompound::new));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_LIST)) {
                this.add(
                    guiEditNBT.newTag(this, "List", GuiEditNBT.NBT_LIST,
                        NBTTagList::new));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_STRING)) {
                this.add(
                    guiEditNBT.newTag(this, "String", GuiEditNBT.NBT_STRING,
                        () -> new NBTTagString("")));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_BYTE)) {
                this.add(
                    guiEditNBT.newTag(this, "Byte", GuiEditNBT.NBT_BYTE,
                        () -> new NBTTagByte((byte) 0)));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_SHORT)) {
                this.add(
                    guiEditNBT.newTag(this, "Short", GuiEditNBT.NBT_SHORT,
                        () -> new NBTTagShort((short) 0)));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_INT)) {
                this.add(
                    guiEditNBT.newTag(this, "Int", GuiEditNBT.NBT_INT,
                        () -> new NBTTagInt((int) 0)));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_LONG)) {
                this.add(
                    guiEditNBT.newTag(this, "Long", GuiEditNBT.NBT_LONG,
                        () -> new NBTTagLong((long) 0)));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_FLOAT)) {
                this.add(
                    guiEditNBT.newTag(this, "Float", GuiEditNBT.NBT_FLOAT,
                        () -> new NBTTagFloat((byte) 0)));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_DOUBLE)) {
                this.add(
                    guiEditNBT.newTag(this, "Double", GuiEditNBT.NBT_DOUBLE,
                        () -> new NBTTagDouble((double) 0)));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_BYTE_ARRAY)) {
                this.add(
                    guiEditNBT.newTag(this, "Byte Array",
                        GuiEditNBT.NBT_BYTE_ARRAY,
                        () -> new NBTTagByteArray(new byte[0])));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_INT_ARRAY)) {
                this.add(
                    guiEditNBT.newTag(this, "Int Array",
                        GuiEditNBT.NBT_INT_ARRAY,
                        () -> new NBTTagIntArray(new int[0])));
            }
        }
    }
}
