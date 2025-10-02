package com.glektarssza.gtnh_customizer.mixins;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

/**
 * The late-stage mixin initializer.
 */
@LateMixin
public class LateMixinInitializer implements ILateMixinLoader {
    /**
     * Get the name of the mixin config file.
     *
     * @returns The name of the mixin config file.
     */
    @Override
    public String getMixinConfig() {
        return "mixins.gtnh_customizer.late.json";
    }

    /**
     * Get the list of additional mixins to load based on the loaded mods.
     *
     * @param loadedMods - The list of mods current loaded.
     *
     * @returns The list of additional mixins to load based on the loaded mods.
     */
    @Override
    @Nonnull
    public List<String> getMixins(Set<String> loadedMods) {
        List<String> mixins = new ArrayList<String>();
        if (loadedMods.contains("SpecialMobs")) {
            mixins.add("specialmobs.Entity_SpecialCaveSpiderMixin");
            mixins.add("specialmobs.Entity_SpecialGhastMixin");
            mixins.add("specialmobs.Entity_SpecialSpiderMixin");
            mixins.add("specialmobs.EntityEnderCreeperMixin");
        }
        if (loadedMods.contains("Thaumcraft")) {
            mixins.add("thaumcraft.EntityWispMixin");
        }
        if (loadedMods.contains("XaeroMinimap")
            && FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            mixins.add("xaeros.client.WaypointsManagerMixins");
        }
        if (loadedMods.contains("TConstruct")) {
            mixins.add("tconstruct.SlimeSaplingMixin");
        }
        if (loadedMods.contains("serverutilities")
            && FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            mixins.add("serverutilities.GuiBaseMixin");
            mixins.add("serverutilities.GuiEditConfigValueMixin");
            mixins.add("serverutilities.GuiEditNBTMixin");
            mixins.add("serverutilities.TextBoxMixin");
        }
        if (loadedMods.contains("EnderZoo")) {
            mixins.add("enderzoo.EntityEnderminyMixin");
        }
        return mixins;
    }
}
