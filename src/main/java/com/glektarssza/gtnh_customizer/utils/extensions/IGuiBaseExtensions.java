package com.glektarssza.gtnh_customizer.utils.extensions;

import cofh.lib.gui.GuiBase;

/**
 * Extension methods for the {@link GuiBase} class.
 */
public interface IGuiBaseExtensions {
    /**
     * Check whether this GUI is the currently focused GUI.
     *
     * @return {@code true} if this GUI is the currently focused GUI;
     *         {@code} false otherwise.
     */
    public boolean isFocused();
}
