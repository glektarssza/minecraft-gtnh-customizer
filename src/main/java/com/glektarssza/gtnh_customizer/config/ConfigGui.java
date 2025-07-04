package com.glektarssza.gtnh_customizer.config;

import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;

import com.glektarssza.gtnh_customizer.Tags;

/**
 * The configuration GUI.
 */
public class ConfigGui extends GuiConfig {

    /**
     * Create a new instance.
     *
     * @param parentScreen The parent screen.
     */
    @SuppressWarnings({
        "rawtypes", "unchecked"
    })
    public ConfigGui(GuiScreen parentScreen) {
        super(
            parentScreen,
            (List<IConfigElement>) (Object) Config.getTopLevelCategories()
                .stream()
                .map((category) -> new ConfigElement<ConfigCategory>(category))
                .collect(Collectors.toList()),
            Tags.MOD_ID,
            Config.CONFIG_ID.toString(),
            false,
            false,
            I18n.format("gtnh_customizer.config.ui.title", Tags.MOD_ID));
    }

    @Override
    public void onGuiClosed() {
        Config.save();
        super.onGuiClosed();
    }
}
