package com.glektarssza.gtnh_customizer.mixins.early.accessor.vanilla;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * An accessor mixin for the {@link GuiTextField} class.
 */
@Mixin(GuiTextField.class)
public interface ITextBoxFontAccessor {
    @Accessor(value = "field_146211_a")
    public FontRenderer getFontRenderer();

    @Accessor(value = "enableBackgroundDrawing")
    public boolean isBackgroundDrawingEnabled();
}
