package com.glektarssza.player_handling_customizer_gtnh.config;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import cpw.mods.fml.client.IModGuiFactory;

/**
 * The factory for declaring what class to use to show the configuration GUI.
 */
public class GuiFactory implements IModGuiFactory {
    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return ConfigGui.class;
    }

    @Override
    public void initialize(Minecraft minecraftInstance) {
        // -- Does nothing
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        // -- Does nothing
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(
        RuntimeOptionCategoryElement element) {
        // -- Does nothing
        return null;
    }
}
