package com.glektarssza.player_handling_customizer_gtnh.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;

/**
 * The core mod plugin for the mod.
 */
@MCVersion("1.7.10")
public class PlayerHandlingCustomizerCore
    implements IFMLLoadingPlugin, IEarlyMixinLoader {

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        // -- No operation
    }

    @Override
    public String getMixinConfig() {
        return "mixins.player-handling-customizer.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
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
        mixins.add("vanilla.EntityMobMixin");
        mixins.add("vanilla.EntityPigZombieMixin");
        mixins.add("vanilla.EntitySilverfishMixin");
        mixins.add("vanilla.EntitySpiderMixin");
        mixins.add("vanilla.EntityWitherMixin");
        mixins.add("vanilla.WorldMixin");
        return mixins;
    }
}
