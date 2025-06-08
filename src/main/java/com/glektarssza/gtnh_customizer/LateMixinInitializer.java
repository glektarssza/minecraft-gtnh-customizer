package com.glektarssza.gtnh_customizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

/**
 * The late-stage mixin initializer.
 */
@LateMixin
public class LateMixinInitializer implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.gtnh_customizer.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        List<String> mixins = new ArrayList<String>();
        if (loadedMods.contains("SpecialMobs")) {
            mixins.add("specialmobs.Entity_SpecialGhastMixin");
            mixins.add("specialmobs.Entity_SpecialCaveSpiderMixin");
            mixins.add("specialmobs.Entity_SpecialSpiderMixin");
            mixins.add("specialmobs.EntityEnderCreeperMixin");
        }
        if (loadedMods.contains("Thaumcraft")) {
            mixins.add("thaumcraft.EntityWispMixin");
        }
        if (loadedMods.contains("XaeroMinimap")
            && FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            mixins.add("xaeros.client.WaypointsManagerMixins");
        }
        if (loadedMods.contains("TConstruct")) {
            mixins.add("tconstruct.SlimeSaplingMixin");
        }
        return mixins;
    }
}
