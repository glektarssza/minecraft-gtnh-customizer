package com.glektarssza.gtnh_customizer.mixins.extensions.vanilla;

import com.glektarssza.gtnh_customizer.api.v1.exceptions.InvalidStateException;

/**
 *
 */
public interface ITextBoxAccessor extends IFontRendererAccessor {
    public default boolean isBackgroundDrawingEnabled() {
        throw new InvalidStateException("Should never be in this state!");
    }
}
