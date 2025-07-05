package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

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

    /**
     * The injection for the {@code keyPressed} method.
     */
    @Inject(method = "keyPressed", at = @At(value = "HEAD"), cancellable = true, remap = false)
    public void keyPressed$handleNumpadEnter(int keyCode, char keyChar,
        CallbackInfoReturnable<Boolean> cir) {
        TextBox self = (TextBox) (Object) this;
        if (!self.isFocused()) {
            return;
        } else if (keyCode == Keyboard.KEY_NUMPADENTER) {
            if (validText) {
                self.setFocused(false);
                self.onEnterPressed();
            }
            cir.setReturnValue(true);
        }
    }
}
