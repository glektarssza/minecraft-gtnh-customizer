package com.glektarssza.player_handling_customizer_gtnh.config;

import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.client.gui.GuiScreen;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;

import com.glektarssza.player_handling_customizer_gtnh.Tags;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

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
        super(parentScreen,
            (List<IConfigElement>) (Object) Config.getTopLevelCategories()
                .stream()
                .map((category) -> new ConfigElement<ConfigCategory>(category))
                .collect(Collectors.toList()),
            Tags.MOD_ID,
            false, false,
            String.format("%s.cfg", Tags.MOD_ID));
    }

    @Override
    public void onGuiClosed() {
        Config.save();
        super.onGuiClosed();
    }
}
