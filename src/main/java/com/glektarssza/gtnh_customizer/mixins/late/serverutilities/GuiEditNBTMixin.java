package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import serverutils.client.gui.GuiEditNBT;
import serverutils.lib.gui.GuiBase;
import serverutils.lib.gui.GuiWrapper;

/**
 * Mixin for the {@link GuiEditNBT} class.
 */
@Mixin(GuiEditNBT.class)
public abstract class GuiEditNBTMixin extends GuiBase {
    /**
     * A shadow of the {@code shouldClose} private field.
     */
    @Shadow(remap = false)
    private int shouldClose;

    /**
     * Make Java happy again.
     */
    private GuiEditNBTMixin() {
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
        if (super.keyPressed(keyCode, keyChar)) {
            return true;
        }
        GuiEditNBT self = (GuiEditNBT) (Object) this;
        GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
        if ((keyCode == Keyboard.KEY_RETURN
            || keyCode == Keyboard.KEY_NUMPADENTER)
            && currentScreen != null && currentScreen instanceof GuiWrapper &&
            ((GuiWrapper) currentScreen).getGui() == self) {
            shouldClose = 1;
            ((GuiWrapper) currentScreen).getGui().closeGui();
            return true;
        }
        return false;
    }
}
