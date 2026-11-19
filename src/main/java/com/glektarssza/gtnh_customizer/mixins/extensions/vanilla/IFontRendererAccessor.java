package com.glektarssza.gtnh_customizer.mixins.extensions.vanilla;

import net.minecraft.client.gui.FontRenderer;

import com.glektarssza.gtnh_customizer.api.v2.exception.InvalidStateException;

public interface IFontRendererAccessor {
    default FontRenderer getFontRenderer() {
        throw new InvalidStateException("Should never be in this state!");
    }
}
