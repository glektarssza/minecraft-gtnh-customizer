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

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.gtnh_customizer.utils.extensions.IButtonNBTListExtensions;
import com.glektarssza.gtnh_customizer.utils.extensions.IGuiEditNBTExtensions;

import serverutils.client.gui.GuiEditNBT;
import serverutils.client.gui.GuiEditNBT.ButtonNBT;
import serverutils.client.gui.GuiEditNBT.ButtonNBTList;
import serverutils.lib.gui.Panel;

public abstract class GuiEditNBTLeftPanelMixin extends Panel {
    /**
     * Make Java happy again.
     *
     * @param panel The panel that owns this instance.
     */
    public GuiEditNBTLeftPanelMixin(Panel panel) {
        super(panel);
    }

    /**
     * Inject into the {@code addWidgets} method.
     *
     * @param ci The callback info.
     */
    @Inject(method = "addWidgets", at = @At("TAIL"), remap = false)
    public void addWidgets$injectExtraButtons(CallbackInfo ci) {
        GuiEditNBT guiEditNBT = (GuiEditNBT) this.parent;
        Panel self = guiEditNBT.panelTopLeft;
        ButtonNBT selected = ((IGuiEditNBTExtensions) guiEditNBT).getSelected();
        if ((selected instanceof ButtonNBTList)
            && !((IButtonNBTListExtensions) selected).isEmpty()) {
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_COMPOUND)) {
                self.add(guiEditNBT.newTag(self, "Compound",
                    GuiEditNBT.NBT_MAP,
                    NBTTagCompound::new));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_LIST)) {
                self.add(
                    guiEditNBT.newTag(self, "List", GuiEditNBT.NBT_LIST,
                        NBTTagList::new));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_STRING)) {
                self.add(guiEditNBT.newTag(self, "String",
                    GuiEditNBT.NBT_STRING,
                    () -> new NBTTagString("")));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_BYTE)) {
                self.add(
                    guiEditNBT.newTag(self, "Byte", GuiEditNBT.NBT_BYTE,
                        () -> new NBTTagByte((byte) 0)));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_SHORT)) {
                self.add(
                    guiEditNBT.newTag(self, "Short", GuiEditNBT.NBT_SHORT,
                        () -> new NBTTagShort((short) 0)));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_INT)) {
                self.add(
                    guiEditNBT.newTag(self, "Int", GuiEditNBT.NBT_INT,
                        () -> new NBTTagInt((int) 0)));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_LONG)) {
                self.add(
                    guiEditNBT.newTag(self, "Long", GuiEditNBT.NBT_LONG,
                        () -> new NBTTagLong((long) 0)));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_FLOAT)) {
                self.add(
                    guiEditNBT.newTag(self, "Float", GuiEditNBT.NBT_FLOAT,
                        () -> new NBTTagFloat((byte) 0)));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_DOUBLE)) {
                self.add(guiEditNBT.newTag(self, "Double",
                    GuiEditNBT.NBT_DOUBLE,
                    () -> new NBTTagDouble((double) 0)));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_BYTE_ARRAY)) {
                self.add(guiEditNBT.newTag(self, "Byte Array",
                    GuiEditNBT.NBT_BYTE_ARRAY,
                    () -> new NBTTagByteArray(new byte[0])));
            }
            if (((IButtonNBTListExtensions) selected)
                .isListOf(NBT.TAG_INT_ARRAY)) {
                self.add(guiEditNBT.newTag(self, "Int Array",
                    GuiEditNBT.NBT_INT_ARRAY,
                    () -> new NBTTagIntArray(new int[0])));
            }
        }
    }
}
