package com.glektarssza.gtnh_customizer.extensions;

import serverutils.client.gui.GuiEditNBT;
import serverutils.client.gui.GuiEditNBT.ButtonNBT;

/**
 * Extensions for the {@link GuiEditNBT} class.
 */
public interface IGuiEditNBTExtensions {
    /**
     * Get the currently selected NBT element.
     *
     * @return The currently selected NBT element.
     */
    public ButtonNBT getSelected();
}
