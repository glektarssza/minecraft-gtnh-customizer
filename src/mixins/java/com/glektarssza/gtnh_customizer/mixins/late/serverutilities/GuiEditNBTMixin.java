package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import com.glektarssza.gtnh_customizer.extensions.IGuiBaseExtensions;
import com.glektarssza.gtnh_customizer.extensions.IGuiEditNBTExtensions;

import serverutils.client.gui.GuiEditNBT;
import serverutils.client.gui.GuiEditNBT.ButtonNBT;
import serverutils.client.gui.GuiEditNBT.ButtonNBTCollection;
import serverutils.client.gui.GuiEditNBT.ButtonNBTMap;
import serverutils.lib.gui.GuiBase;
import serverutils.lib.util.StringUtils;

/**
 * Mixin for the {@link GuiEditNBT} class.
 */
@Mixin(GuiEditNBT.class)
public abstract class GuiEditNBTMixin extends GuiBase
    implements IGuiEditNBTExtensions {
    /**
     * A shadow of the {@code shouldClose} private field.
     */
    @Shadow(remap = false)
    private int shouldClose;

    /**
     * A shadow of the {@code selected} private field.
     */
    @Shadow(remap = false)
    private ButtonNBT selected;

    /**
     * A shadow of the {@code buttonNBTRoot} private field.
     */
    @Shadow(remap = false)
    @Final
    private ButtonNBTMap buttonNBTRoot;

    /**
     * Get the currently selected NBT element.
     *
     * @return The currently selected NBT element.
     */
    @Override
    @Unique
    public ButtonNBT getSelected() {
        return this.selected;
    }

    /**
     * The injection for the {@code keyPressed} method.
     *
     * @param keyCode - The key code pressed.
     * @param keyChar - The key character pressed.
     *
     * @returns {@code true} the event was handled, {@code} false otherwise.
     */
    @Override
    public boolean keyPressed(int keyCode, char keyChar) {
        GuiEditNBT self = (GuiEditNBT) (Object) this;
        if (!((IGuiBaseExtensions) self).isFocused()) {
            return false;
        }
        if (keyCode == Keyboard.KEY_RETURN
            || keyCode == Keyboard.KEY_NUMPADENTER) {
            shouldClose = 1;
            self.closeGui();
            return true;
        }
        if (keyCode == Keyboard.KEY_ESCAPE) {
            shouldClose = 2;
            self.closeGui();
            return true;
        }
        if (keyCode == Keyboard.KEY_DELETE
            && this.selected != this.buttonNBTRoot) {
            this.selected.parent.setTag(selected.key, null);
            this.selected.parent.updateChildren(false);
            this.selected = selected.parent;
            return true;
        }
        if (keyCode == Keyboard.KEY_UP) {
            ButtonNBTCollection parent = this.selected.parent;
            List<ButtonNBT> parentChildren = new LinkedList<ButtonNBT>();
            if (parent != null) {
                List<String> parentKeys = new ArrayList<String>(
                    (parent.children
                        .keySet()));
                parentKeys.sort(StringUtils.IGNORE_CASE_COMPARATOR);
                for (String k : parentKeys) {
                    parentChildren.add(parent.children.get(k));
                }
            }
            int parentIndex = parentChildren.indexOf(this.selected);
            if (this.selected instanceof ButtonNBTCollection) {
                ButtonNBTCollection collection = (ButtonNBTCollection) this.selected;
                List<ButtonNBT> children = new LinkedList<ButtonNBT>();
                List<String> keys = new ArrayList<String>(
                    (collection.children
                        .keySet()));
                keys.sort(StringUtils.IGNORE_CASE_COMPARATOR);
                for (String k : keys) {
                    children.add(collection.children.get(k));
                }
                int childIndex = children.indexOf(this.selected);
                if (parent instanceof ButtonNBTCollection
                    && ((ButtonNBTCollection) parent).collapsed
                    && parentIndex > 0) {
                    selected = parentChildren.get(parentIndex - 1);
                } else if (parent instanceof ButtonNBTCollection
                    && !((ButtonNBTCollection) parent).collapsed
                    && childIndex > 0) {
                    selected = children.get(parentIndex - 1);
                } else if (parent instanceof ButtonNBTCollection
                    && !((ButtonNBTCollection) parent).collapsed
                    && childIndex == 0
                    && parentIndex > 0) {
                    selected = parentChildren.get(parentIndex - 1);
                }
            } else if (parentIndex > 0) {
                selected = parentChildren.get(parentIndex - 1);
            }
            return true;
        }
        if (keyCode == Keyboard.KEY_DOWN) {
            ButtonNBTCollection parent = this.selected.parent;
            List<ButtonNBT> parentChildren = new LinkedList<ButtonNBT>();
            if (parent != null) {
                List<String> parentKeys = new ArrayList<String>(
                    (parent.children
                        .keySet()));
                parentKeys.sort(StringUtils.IGNORE_CASE_COMPARATOR);
                for (String k : parentKeys) {
                    parentChildren.add(parent.children.get(k));
                }
            }
            int parentIndex = parentChildren.indexOf(this.selected);
            if (this.selected instanceof ButtonNBTCollection) {
                ButtonNBTCollection collection = (ButtonNBTCollection) this.selected;
                List<ButtonNBT> children = new LinkedList<ButtonNBT>();
                List<String> keys = new ArrayList<String>(
                    (collection.children
                        .keySet()));
                keys.sort(StringUtils.IGNORE_CASE_COMPARATOR);
                for (String k : keys) {
                    children.add(collection.children.get(k));
                }
                int childIndex = children.indexOf(this.selected);
                if (!collection.collapsed && children.size() > 0) {
                    selected = children.get(0);
                } else if (parent instanceof ButtonNBTCollection
                    && parent.collapsed
                    && parentIndex < parentChildren.size() - 1) {
                    selected = parentChildren.get(parentIndex + 1);
                } else if (parent instanceof ButtonNBTCollection
                    && !parent.collapsed && childIndex < children.size() - 1) {
                    selected = children.get(parentIndex + 1);
                } else if (parent instanceof ButtonNBTCollection
                    && !parent.collapsed && childIndex == children.size() - 1
                    && parentIndex < parentChildren.size() - 1) {
                    selected = parentChildren.get(parentIndex + 1);
                }
            } else if (parentIndex < parentChildren.size() - 1) {
                selected = parentChildren.get(parentIndex + 1);
            }
            return true;
        }
        if (keyCode == Keyboard.KEY_LEFT) {
            if (this.selected instanceof ButtonNBTCollection) {
                ButtonNBTCollection collection = (ButtonNBTCollection) this.selected;
                if (!collection.collapsed) {
                    if (isCtrlKeyDown()) {
                        collection.setCollapsedTree(false);
                    } else {
                        collection.setCollapsed(false);
                    }
                }
            }
            return true;
        }
        if (keyCode == Keyboard.KEY_RIGHT) {
            if (this.selected instanceof ButtonNBTCollection) {
                ButtonNBTCollection collection = (ButtonNBTCollection) this.selected;
                if (!collection.collapsed) {
                    if (isCtrlKeyDown()) {
                        collection.setCollapsedTree(true);
                    } else {
                        collection.setCollapsed(true);
                    }
                }
            }
            return true;
        }
        if (super.keyPressed(keyCode, keyChar)) {
            return true;
        }
        return false;
    }
}
