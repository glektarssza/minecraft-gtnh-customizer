package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.gtnh_customizer.KeyBindings;
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
}
