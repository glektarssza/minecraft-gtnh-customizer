package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

import org.lwjgl.input.Keyboard;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import com.glektarssza.gtnh_customizer.utils.extensions.IGuiBaseExtensions;
import com.glektarssza.gtnh_customizer.utils.extensions.IGuiEditNBTExtensions;

import serverutils.client.gui.GuiEditNBT;
import serverutils.client.gui.GuiEditNBT.ButtonNBT;
import serverutils.client.gui.GuiEditNBT.ButtonNBTMap;
import serverutils.lib.gui.GuiBase;

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
        if (keyCode == Keyboard.KEY_RETURN
            || keyCode == Keyboard.KEY_NUMPADENTER) {
            shouldClose = 1;
            self.closeGui();
            return true;
        }
        if (keyCode == Keyboard.KEY_ESCAPE) {
            shouldClose = 2;
            self.closeGui();
            return true;
        }
        if (keyCode == Keyboard.KEY_DELETE &&
            this.selected != this.buttonNBTRoot && selected.key != null) {
            this.selected.parent.setTag(selected.key, null);
            this.selected.parent.updateChildren(false);
            this.selected = selected.parent;
            return true;
        }
        if (keyCode == Keyboard.KEY_LEFT) {
            // TODO: Collapse inner thing
            return true;
        }
        if (keyCode == Keyboard.KEY_RIGHT) {
            // TODO: Open inner thing
            return true;
        }
        if (keyCode == Keyboard.KEY_UP) {
            // TODO: Previous element
            return true;
        }
        if (keyCode == Keyboard.KEY_DOWN) {
            // TODO: Next element
            return true;
        }
        if (super.keyPressed(keyCode, keyChar)) {
            return true;
        }
        return false;
    }
}
