package com.glektarssza.gtnh_customizer.mixins.extensions.vanilla;

import net.minecraft.client.gui.FontRenderer;

import com.glektarssza.gtnh_customizer.api.v1.exceptions.InvalidStateException;

public interface IFontRendererAccessor {
    public default FontRenderer getFontRenderer() {
        throw new InvalidStateException("Should never be in this state!");
    }
}
