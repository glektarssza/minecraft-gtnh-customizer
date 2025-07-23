package com.glektarssza.gtnh_customizer.utils.extensions;

import serverutils.client.gui.GuiEditNBT.ButtonNBTList;

/**
 * Extension methods for the {@link ButtonNBTList} class.
 */
public interface IButtonNBTListExtensions {
    /**
     * Check if this list is a list of a given type of NBT tags.
     *
     * @return {@code true} if this list is a list of the given NBT tag type or
     *         an empty list, {@code false} otherwise.
     */
    public boolean isListOf(int nbtType);

    /**
     * Check if this contains any elements.
     *
     * @return {@code true} if this does not contain any elements, {@code false}
     *         otherwise.
     */
    public boolean isEmpty();
}
