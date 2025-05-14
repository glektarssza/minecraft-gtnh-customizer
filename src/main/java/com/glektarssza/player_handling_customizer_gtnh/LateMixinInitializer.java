package com.glektarssza.player_handling_customizer_gtnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

/**
 * The late-stage mixin initializer.
 */
@LateMixin
public class LateMixinInitializer implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.player-handling-customizer.late.json";
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
        return mixins;
    }
}
