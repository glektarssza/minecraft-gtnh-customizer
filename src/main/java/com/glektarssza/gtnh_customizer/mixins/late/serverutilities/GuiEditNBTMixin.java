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

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.gtnh_customizer.KeyBindings;
import com.glektarssza.gtnh_customizer.utils.extensions.IButtonNBTListExtensions;
import com.glektarssza.gtnh_customizer.utils.extensions.IGuiBaseExtensions;
import com.glektarssza.gtnh_customizer.utils.extensions.IGuiEditNBTExtensions;

import serverutils.client.gui.GuiEditNBT;
import serverutils.client.gui.GuiEditNBT.ButtonNBT;
import serverutils.client.gui.GuiEditNBT.ButtonNBTList;
import serverutils.client.gui.GuiEditNBT.ButtonNBTMap;
import serverutils.lib.gui.GuiBase;
import serverutils.lib.gui.Panel;

/**
 * Mixin for the {@link GuiEditNBT} class.
 */
@Mixin(GuiEditNBT.class)
public abstract class GuiEditNBTMixin extends GuiBase
    implements IGuiEditNBTExtensions {
    /**
     * A shadow of the {@code shouldClose} private field.
     */
    @Shadow(remap = false)
    private int shouldClose;

    /**
     * A shadow of the {@code selected} private field.
     */
    @Shadow(remap = false)
    private ButtonNBT selected;

    /**
     * A shadow of the {@code buttonNBTRoot} private field.
     */
    @Shadow(remap = false)
    @Final
    private ButtonNBTMap buttonNBTRoot;

    /**
     * Make Java happy again.
     */
    private GuiEditNBTMixin() {
        super();
    }

    /**
     * Get the currently selected NBT element.
     *
     * @return The currently selected NBT element.
     */
    @Override
    @Unique
    public ButtonNBT getSelected() {
        return this.selected;
    }

    /**
     * The injection for the {@code keyPressed} method.
     *
     * @param keyCode - The key code pressed.
     * @param keyChar - The key character pressed.
     *
     * @returns {@code true} the event was handled, {@code} false otherwise.
     */
    @Override
    public boolean keyPressed(int keyCode, char keyChar) {
        GuiEditNBT self = (GuiEditNBT) (Object) this;
        if (!((IGuiBaseExtensions) self).isFocused()) {
            return false;
        }
        if (keyCode == KeyBindings.ACCEPT_NBT_EDITS.getKeyCode()
            || keyCode == KeyBindings.ACCEPT_NBT_EDITS_ALT.getKeyCode()) {
            shouldClose = 1;
            self.closeGui();
            return true;
        }
        if (keyCode == KeyBindings.CANCEL_NBT_EDITS.getKeyCode()) {
            shouldClose = 2;
            self.closeGui();
            return true;
        }
        if (keyCode == KeyBindings.DELETE_NBT_TAG.getKeyCode() &&
            this.selected != this.buttonNBTRoot) {
            this.selected.parent.setTag(selected.key, null);
            this.selected.parent.updateChildren(false);
            this.selected = selected.parent;
            self.panelNbt.refreshWidgets();
            self.panelTopLeft.refreshWidgets();
            self.panelTopRight.refreshWidgets();
            return true;
        }
        if (super.keyPressed(keyCode, keyChar)) {
            return true;
        }
        return false;
    }

    /**
     * The injection for the inner inner lambda inside the {@code newTag}
     * method.
     *
     * @param ci - The callback information.
     */
    @Inject(method = "lambda$newTag$0(Lserverutils/client/gui/GuiEditNBT$ButtonNBTMap;Ljava/util/function/Supplier;Lserverutils/lib/config/ConfigValue;Z)V", at = @At(value = "INVOKE", target = "Lserverutils/lib/gui/Panel;refreshWidgets()V", shift = Shift.AFTER), cancellable = false, remap = false)
    public void newTag$lambda$0$refreshTopLeftPanel(CallbackInfo ci) {
        GuiEditNBT self = (GuiEditNBT) (Object) this;
        self.panelTopLeft.refreshWidgets();
        self.panelTopRight.refreshWidgets();
    }

    /**
     * The injection for the inner lambda inside the {@code newTag} method.
     *
     * @param ci - The callback information.
     */
    @Inject(method = "lambda$newTag$1(Ljava/util/function/Supplier;Lserverutils/lib/gui/SimpleButton;Lserverutils/lib/util/misc/MouseButton;)V", at = @At(value = "INVOKE", target = "Lserverutils/lib/gui/Panel;refreshWidgets()V", shift = Shift.AFTER), cancellable = false, remap = false)
    public void newTag$lambda$1$refreshTopLeftPanel(CallbackInfo ci) {
        GuiEditNBT self = (GuiEditNBT) (Object) this;
        self.panelTopLeft.refreshWidgets();
        self.panelTopRight.refreshWidgets();
    }

    /**
     * Inject into the {@code addWidgets} method.
     *
     * @param ci The callback info.
     */
    @Inject(method = "Lserverutils/client/gui/GuiEditNBT$2;addWidgets", at = @At("TAIL"), remap = false)
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
