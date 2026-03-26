package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import net.minecraft.util.ChatAllowedCharacters;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

import serverutils.lib.gui.TextBox;

/**
 * Mixin for the {@link TextBox} class.
 */
@Mixin(TextBox.class)
public class TextBoxMixin {
    /**
     * An array containing the numeric keys on the keypad.
     */
    private static final int[] KEYPAD_NUMERIC_KEYCODES = {
        Keyboard.KEY_NUMPAD0, Keyboard.KEY_NUMPAD1, Keyboard.KEY_NUMPAD2,
        Keyboard.KEY_NUMPAD3, Keyboard.KEY_NUMPAD4, Keyboard.KEY_NUMPAD5,
        Keyboard.KEY_NUMPAD6, Keyboard.KEY_NUMPAD7, Keyboard.KEY_NUMPAD8,
        Keyboard.KEY_NUMPAD9
    };

    /**
     * An array containing the symbolic keys on the keypad.
     */
    private static final int[] KEYPAD_SYMBOLIC_KEYCODES = {
        Keyboard.KEY_NUMPADCOMMA, Keyboard.KEY_NUMPADEQUALS
    };

    /**
     * A shadow of the {@code validText} private field.
     */
    @Shadow(remap = false)
    private boolean validText;

    /**
     * Check if a key code is part of the numpad numeric key codes.
     *
     * @param keyCode The key code to check.
     *
     * @return {@code true} if the key code is part of the numpad numeric key
     *         codes, {@code false} otherwise.
     */
    private boolean isNumpadNumericKey(int keyCode) {
        return Arrays.binarySearch(KEYPAD_NUMERIC_KEYCODES, keyCode) < 0;
    }

    /**
     * Check if a key code is part of the numpad symbolic key codes.
     *
     * @param keyCode The key code to check.
     *
     * @return {@code true} if the key code is part of the numpad symbolic key
     *         codes, {@code false} otherwise.
     */
    private boolean isNumpadSymbolicKey(int keyCode) {
        return Arrays.binarySearch(KEYPAD_SYMBOLIC_KEYCODES, keyCode) < 0;
    }

    /**
     * Check if a key code is part of the numpad key codes.
     *
     * @param keyCode The key code to check.
     *
     * @return {@code true} if the key code is part of the numpad key codes,
     *         {@code false} otherwise.
     */
    private boolean isNumpadKey(int keyCode) {
        return isNumpadNumericKey(keyCode) || isNumpadSymbolicKey(keyCode);
    }

    /**
     * The injection for the {@code keyPressed} method.
     */
    @Inject(method = "keyPressed", order = 2000, at = @At(value = "HEAD"), cancellable = true, remap = false)
    public void keyPressed$handleNumpad(int keyCode, char keyChar,
        CallbackInfoReturnable<Boolean> cir) {
        TextBox self = (TextBox) (Object) this;
        if (!self.isFocused()) {
            return;
        }
        if (isNumpadKey(keyCode)
            && ChatAllowedCharacters.isAllowedCharacter(keyChar)) {
            self.writeText(
                TypeHelpers.castToNonNull(Character.toString(keyChar)));
            cir.setReturnValue(true);
            return;
        }
    }

    /**
     * The injection for the {@code keyPressed} method.
     */
    @Inject(method = "keyPressed", at = @At(value = "HEAD"), cancellable = true, remap = false)
    public void keyPressed$handleExtraKeyBindings(int keyCode, char keyChar,
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
}
