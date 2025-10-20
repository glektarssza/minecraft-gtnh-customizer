package com.glektarssza.gtnh_customizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;

/**
 * The early-stage mixin initializer.
 */
public class EarlyMixinInitializer implements IEarlyMixinLoader {
    /**
     * Get the name of the mixin config file.
     *
     * @returns The name of the mixin config file.
     */
    @Override
    public String getMixinConfig() {
        return "mixins.gtnh_customizer.early.json";
    }

    /**
     * Get the list of additional mixins to load based on the loaded mods.
     *
     * @param loadedMods The list of mods currently loaded.
     *
     * @returns The list of additional mixins to load based on the loaded mods.
     */
    @Override
    @Nonnull
    public List<String> getMixins(Set<String> loadedMods) {
        List<String> mixins = new ArrayList<String>();
        // -- Vanilla mixins
        mixins.add("vanilla.EntityAIArrowAttackMixin");
        mixins.add("vanilla.EntityAIAttackOnCollideMixin");
        mixins.add("vanilla.EntityAICreeperSwellMixin");
        mixins.add("vanilla.EntityAIHurtByTargetMixin");
        mixins.add("vanilla.EntityAINearestAttackableTargetMixin");
        mixins.add("vanilla.EntityAIOwnerHurtByTargetMixin");
        mixins.add("vanilla.EntityAIOwnerHurtTargetMixin");
        mixins.add("vanilla.EntityAITargetMixin");
        mixins.add("vanilla.EntityCreatureMixin");
        mixins.add("vanilla.EntityDragonMixin");
        mixins.add("vanilla.EntityEndermanMixin");
        mixins.add("vanilla.EntityGhastMixin");
        mixins.add("vanilla.EntityLivingMixin");
        mixins.add("vanilla.EntityMobMixin");
        mixins.add("vanilla.EntityPigZombieMixin");
        mixins.add("vanilla.EntitySilverfishMixin");
        mixins.add("vanilla.EntitySpiderMixin");
        mixins.add("vanilla.EntityWitherMixin");
        mixins.add("vanilla.WorldMixin");
        return mixins;
    }
}
