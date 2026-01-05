package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

import org.apache.commons.lang3.NotImplementedException;
import org.lwjgl.input.Keyboard;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import serverutils.lib.gui.TextBox;

/**
 * Mixin for the {@link TextBox} class.
 */
@Mixin(TextBox.class)
public class TextBoxMixin {
    /**
     * A shadow of the {@code validText} private field.
     */
    @Shadow(remap = false)
    private boolean validText;

    @Shadow()
    public void writeText(String textToWrite) {
        throw new NotImplementedException("This should never happen!");
    }

    /**
     * The injection for the {@code keyPressed} method.
     */
    @Inject(method = "keyPressed", at = @At(value = "HEAD"), cancellable = true, remap = false)
    public void keyPressed$handleHotkeys(int keyCode, char keyChar,
        CallbackInfoReturnable<Boolean> cir) {
        TextBox self = (TextBox) (Object) this;
        if (!self.isFocused()) {
            return;
        }
        if (keyCode == Keyboard.KEY_RETURN
            || keyCode == Keyboard.KEY_NUMPADENTER) {
            if (validText) {
                self.setFocused(false);
                self.onEnterPressed();
            }
            cir.setReturnValue(true);
        }
        if (keyCode == Keyboard.KEY_ESCAPE) {
            if (validText) {
                self.closeGui(false);
            }
            cir.setReturnValue(true);
        }
    }

    /**
     * The injection for the {@code keyPressed} method.
     */
    @Inject(method = "keyPressed", at = @At(value = "TAIL"), cancellable = true, remap = false)
    public void keyPressed$handleNumpad(int keyCode, char keyChar,
        CallbackInfoReturnable<Boolean> cir) {
        TextBox self = (TextBox) (Object) this;
        if (!self.isFocused()) {
            return;
        }
        if (keyCode == Keyboard.KEY_NUMPAD0) {
            this.writeText(Character.toString('0'));
            cir.setReturnValue(true);
            return;
        }
        if (keyCode == Keyboard.KEY_NUMPAD1) {
            this.writeText(Character.toString('1'));
            cir.setReturnValue(true);
            return;
        }
        if (keyCode == Keyboard.KEY_NUMPAD2) {
            this.writeText(Character.toString('2'));
            cir.setReturnValue(true);
            return;
        }
        if (keyCode == Keyboard.KEY_NUMPAD3) {
            this.writeText(Character.toString('3'));
            cir.setReturnValue(true);
            return;
        }
        if (keyCode == Keyboard.KEY_NUMPAD4) {
            this.writeText(Character.toString('4'));
            cir.setReturnValue(true);
            return;
        }
        if (keyCode == Keyboard.KEY_NUMPAD5) {
            this.writeText(Character.toString('5'));
            cir.setReturnValue(true);
            return;
        }
        if (keyCode == Keyboard.KEY_NUMPAD6) {
            this.writeText(Character.toString('6'));
            cir.setReturnValue(true);
            return;
        }
        if (keyCode == Keyboard.KEY_NUMPAD7) {
            this.writeText(Character.toString('7'));
            cir.setReturnValue(true);
            return;
        }
        if (keyCode == Keyboard.KEY_NUMPAD8) {
            this.writeText(Character.toString('8'));
            cir.setReturnValue(true);
            return;
        }
        if (keyCode == Keyboard.KEY_NUMPAD9) {
            this.writeText(Character.toString('9'));
            cir.setReturnValue(true);
            return;
        }
        if (keyCode == Keyboard.KEY_NUMPADCOMMA) {
            this.writeText(Character.toString(','));
            cir.setReturnValue(true);
            return;
        }
        if (keyCode == Keyboard.KEY_NUMPADEQUALS) {
            this.writeText(Character.toString('='));
            cir.setReturnValue(true);
            return;
        }
    }
}
