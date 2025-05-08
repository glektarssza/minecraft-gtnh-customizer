package com.glektarssza.player_handling_customizer_gtnh.mixins.late.aether;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.gildedgames.the_aether.entities.ai.zephyr.ZephyrAIShootTarget;
import com.gildedgames.the_aether.entities.hostile.EntityZephyr;

import com.glektarssza.player_handling_customizer_gtnh.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer_gtnh.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer_gtnh.utils.PlayerUtils;

/**
 * Mixin for the {@code ZephyrAIShootTarget} class.
 */
@Mixin(ZephyrAIShootTarget.class)
public class ZephyrAIShootTargetMixin {
    /**
     * A shadow of the {@code zephyr} field.
     */
    @Shadow(remap = false)
    private EntityZephyr zephyr;

    /**
     * Mixin for the {@code shouldExecute} method.
     */
    @Inject(method = "shouldExecute", at = @At("HEAD"), cancellable = true)
    public void shouldExecute(CallbackInfoReturnable<Boolean> cir) {
        EntityLiving attacker = (EntityLiving) this.zephyr;
        EntityLivingBase target = (EntityLivingBase) this.zephyr
            .getAttackTarget();
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities) || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(false);
        }
    }
}
