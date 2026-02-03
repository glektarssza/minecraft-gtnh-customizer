package com.glektarssza.gtnh_customizer;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;

import cpw.mods.fml.client.registry.ClientRegistry;

public class KeyBindings {
    /**
     * The keybinding for deleting a NBT tag inside the Server Utilities
     * {@code /nbtedit} command.
     */
    public static final KeyBinding DELETE_NBT_TAG = new KeyBinding(
        "gtnh_customizer.keybindings.serverutilities.delete_nbt_tag",
        Keyboard.KEY_DELETE,
        "key.categories.gtnh_customizer");

    /**
     * The keybinding for accepting an the edits to some NBT data inside the
     * Server Utilities {@code /nbtedit} command.
     */
    public static final KeyBinding ACCEPT_NBT_EDITS = new KeyBinding(
        "gtnh_customizer.keybindings.serverutilities.accept_nbt_edits",
        Keyboard.KEY_RETURN,
        "key.categories.gtnh_customizer");

    /**
     * The alternate keybinding for accepting an the edits to some NBT data
     * inside the Server Utilities {@code /nbtedit} command.
     */
    public static final KeyBinding ACCEPT_NBT_EDITS_ALT = new KeyBinding(
        "gtnh_customizer.keybindings.serverutilities.accept_nbt_edits_alt",
        Keyboard.KEY_NUMPADENTER,
        "key.categories.gtnh_customizer");

    /**
     * The keybinding for canceling the the edits to some NBT data inside the
     * Server Utilities {@code /nbtedit} command.
     */
    public static final KeyBinding CANCEL_NBT_EDITS = new KeyBinding(
        "gtnh_customizer.keybindings.serverutilities.cancel_nbt_edits",
        Keyboard.KEY_ESCAPE,
        "key.categories.gtnh_customizer");

    /**
     * The keybinding for accepting an edited NBT value inside the Server
     * Utilities {@code /nbtedit} command.
     */
    public static final KeyBinding ACCEPT_NBT_VALUE_CHANGE = new KeyBinding(
        "gtnh_customizer.keybindings.serverutilities.accept_nbt_value_change",
        Keyboard.KEY_RETURN,
        "key.categories.gtnh_customizer");

    /**
     * The alternate keybinding for accepting the editing of a NBT value inside
     * the Server Utilities {@code /nbtedit} command.
     */
    public static final KeyBinding ACCEPT_NBT_VALUE_CHANGE_ALT = new KeyBinding(
        "gtnh_customizer.keybindings.serverutilities.accept_nbt_value_change_alt",
        Keyboard.KEY_NUMPADENTER,
        "key.categories.gtnh_customizer");

    /**
     * The keybinding for canceling the editing of a NBT value inside the Server
     * Utilities {@code /nbtedit} command.
     */
    public static final KeyBinding CANCEL_NBT_VALUE_CHANGE = new KeyBinding(
        "gtnh_customizer.keybindings.serverutilities.cancel_nbt_value_change",
        Keyboard.KEY_RETURN,
        "key.categories.gtnh_customizer");

    /**
     * Register this mod's key bindings.
     */
    public static void registerKeybinds() {
        ClientRegistry.registerKeyBinding(DELETE_NBT_TAG);
        ClientRegistry.registerKeyBinding(ACCEPT_NBT_EDITS);
        ClientRegistry.registerKeyBinding(ACCEPT_NBT_EDITS_ALT);
        ClientRegistry.registerKeyBinding(CANCEL_NBT_EDITS);
        ClientRegistry.registerKeyBinding(ACCEPT_NBT_VALUE_CHANGE);
        ClientRegistry.registerKeyBinding(ACCEPT_NBT_VALUE_CHANGE_ALT);
        ClientRegistry.registerKeyBinding(CANCEL_NBT_VALUE_CHANGE);
    }
}
