package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import com.glektarssza.gtnh_customizer.extensions.IGuiBaseExtensions;

import serverutils.lib.gui.GuiBase;
import serverutils.lib.gui.IGuiWrapper;

/**
 * Mixins for the {@link GuiBase} class.
 */
@Mixin(GuiBase.class)
public abstract class GuiBaseMixin implements IGuiBaseExtensions {
    /**
     * Check whether this screen is the currently focused screen.
     *
     * @return {@code true} if this screen is the currently focused screen;
     *         {@code} false otherwise.
     */
    @Unique
    @Override
    public boolean isFocused() {
        GuiBase self = (GuiBase) (Object) this;
        GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
        if (currentScreen == null) {
            return false;
        }
        if (currentScreen instanceof IGuiWrapper) {
            return ((IGuiWrapper) currentScreen).getGui() == self;
        }
        return false;
    }
}
