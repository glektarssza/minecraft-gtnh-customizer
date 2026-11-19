package com.glektarssza.gtnh_customizer.mixins.extensions.vanilla;

import com.glektarssza.gtnh_customizer.api.v2.exception.InvalidStateException;

/**
 *
 */
public interface ITextBoxAccessor extends IFontRendererAccessor {
    default boolean isBackgroundDrawingEnabled() {
        throw new InvalidStateException("Should never be in this state!");
    }
}
