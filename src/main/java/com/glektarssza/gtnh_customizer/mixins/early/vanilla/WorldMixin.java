package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveHandler;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.api.immunity.ITargetingImmunity;
import com.glektarssza.gtnh_customizer.utils.IWorldExtensions;
import com.glektarssza.gtnh_customizer.utils.ImmunityUtils;
import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

/**
 * Mixin for the {@code World} class.
 */
@Mixin(World.class)
public abstract class WorldMixin extends World implements IWorldExtensions {
    /**
     * Constructor so Java stops complaining.
     */
    public WorldMixin(ISaveHandler saveHandler, String p_i45368_2_,
        WorldSettings worldSettings, WorldProvider worldProvider,
        Profiler profiler) {
        super(saveHandler, p_i45368_2_, worldSettings, worldProvider, profiler);
    }

    /**
     * Constructor so Java stops complaining.
     */
    public WorldMixin(ISaveHandler saveHandler, String p_i45368_2_,
        WorldProvider worldProvider, WorldSettings worldSettings,
        Profiler profiler) {
        super(saveHandler, p_i45368_2_, worldProvider, worldSettings, profiler);
    }

    /**
     * Mixin for the {@code getClosestVulnerablePlayerToEntity} method.
     */
    @Inject(method = "getClosestVulnerablePlayerToEntity", at = @At("RETURN"), cancellable = true)
    private void getClosestVulnerablePlayerToEntity(Entity entityIn,
        double distance,
        CallbackInfoReturnable<EntityPlayer> cir) {
        if (!(entityIn instanceof EntityLiving)) {
            return;
        }
        EntityLiving attacker = (EntityLiving) entityIn;
        EntityPlayer player = cir.getReturnValue();
        if (player == null) {
            return;
        }
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities)
            || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(null);
        }
    }

    /**
     * Get the render distance of the world.
     *
     * @return The render distance of the world.
     */
    @Override
    public int getRenderDistance() {
        return this.func_152379_p();
    }
}
