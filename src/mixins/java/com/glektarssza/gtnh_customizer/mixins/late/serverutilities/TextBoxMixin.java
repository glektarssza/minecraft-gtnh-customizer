package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

import java.util.Optional;

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
     * Map a key code to a number pad character, if possible.
     *
     * @param keyCode The key code to map.
     *
     * @return The key character, if one can be mapped.
     */
    private Optional<Character> mapNumpadKeyCodeToChar(int keyCode) {
        switch (keyCode) {
            case Keyboard.KEY_NUMPAD0:
                return Optional.of('0');
            case Keyboard.KEY_NUMPAD1:
                return Optional.of('1');
            case Keyboard.KEY_NUMPAD2:
                return Optional.of('2');
            case Keyboard.KEY_NUMPAD3:
                return Optional.of('3');
            case Keyboard.KEY_NUMPAD4:
                return Optional.of('4');
            case Keyboard.KEY_NUMPAD5:
                return Optional.of('5');
            case Keyboard.KEY_NUMPAD6:
                return Optional.of('6');
            case Keyboard.KEY_NUMPAD7:
                return Optional.of('7');
            case Keyboard.KEY_NUMPAD8:
                return Optional.of('8');
            case Keyboard.KEY_NUMPAD9:
                return Optional.of('9');
            case Keyboard.KEY_NUMPADCOMMA:
                return Optional.of(',');
            case Keyboard.KEY_NUMPADEQUALS:
                return Optional.of('=');
            default:
                return Optional.empty();
        }
    }

    /**
     * A shadow of the {@code validText} private field.
     */
    @Shadow(remap = false)
    private boolean validText;

    /**
     * The injection for the {@code keyPressed} method.
     */
    @Inject(method = "keyPressed", order = 900, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ChatAllowedCharacters;isAllowedCharacter(C)Z", remap = true), cancellable = true, remap = false)
    public void keyPressed$handleNumpad(int keyCode, char keyChar,
        CallbackInfoReturnable<Boolean> cir) {
        TextBox self = (TextBox) (Object) this;
        mapNumpadKeyCodeToChar(keyCode).ifPresent((numpadKeyChar) -> {
            if (ChatAllowedCharacters.isAllowedCharacter(numpadKeyChar)) {
                self.writeText(
                    TypeHelpers
                        .castToNonNull(Character.toString(numpadKeyChar)));
                cir.setReturnValue(true);
            }
        });
    }

    /**
     * The injection for the {@code keyPressed} method.
     */
    @Inject(method = "keyPressed", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ChatAllowedCharacters;isAllowedCharacter(C)Z", remap = true), cancellable = true, remap = false)
    public void keyPressed$handleExtraKeyBindings(int keyCode, char keyChar,
        CallbackInfoReturnable<Boolean> cir) {
        TextBox self = (TextBox) (Object) this;
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
