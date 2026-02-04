package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.glektarssza.gtnh_customizer.utils.TypeHelpers;
import com.glektarssza.gtnh_customizer.utils.extensions.IGuiBaseExtensions;

import serverutils.lib.config.ConfigValue;
import serverutils.lib.config.ConfigValueInstance;
import serverutils.lib.gui.GuiBase;
import serverutils.lib.gui.TextBox;
import serverutils.lib.gui.misc.GuiEditConfigValue;
import serverutils.lib.gui.misc.IConfigValueEditCallback;

@Mixin(GuiEditConfigValue.class)
public abstract class GuiEditConfigValueMixin extends GuiBase {
    /**
     * A shadow of the {@code inst} private field.
     */
    @Shadow(remap = false)
    @Final
    private ConfigValueInstance inst;

    /**
     * A shadow of the {@code value} private field.
     */
    @Shadow(remap = false)
    @Final
    private ConfigValue value;

    /**
     * A shadow of the {@code callback} private field.
     */
    @Shadow(remap = false)
    @Final
    private IConfigValueEditCallback callback;

    /**
     * A shadow of the {@code textBox} private field.
     */
    @Shadow(remap = false)
    @Final
    private TextBox textBox;

    /**
     * Make Java happy again.
     */
    private GuiEditConfigValueMixin() {
        super();
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
        GuiEditConfigValue self = (GuiEditConfigValue) (Object) this;
        if (!((IGuiBaseExtensions) self).isFocused()) {
            return false;
        }
        if (keyCode == Keyboard.KEY_RETURN
            || keyCode == Keyboard.KEY_NUMPADENTER) {
            if (value.setValueFromString(Minecraft.getMinecraft().thePlayer,
                TypeHelpers.castToNonNull(textBox.getText()), false)) {
                callback.onCallback(TypeHelpers.castToNonNull(value), true);
            }

            if (getGui().parent instanceof GuiBase) {
                getGui().parent.closeContextMenu();
            }
            return true;
        }
        if (keyCode == Keyboard.KEY_ESCAPE) {
            callback.onCallback(TypeHelpers.castToNonNull(inst.getValue()),
                false);
            return true;
        }
        if (super.keyPressed(keyCode, keyChar)) {
            return true;
        }
        return false;
    }
}
