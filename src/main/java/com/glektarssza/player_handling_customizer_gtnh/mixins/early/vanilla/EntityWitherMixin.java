package com.glektarssza.player_handling_customizer_gtnh.mixins.early.vanilla;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.player_handling_customizer_gtnh.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer_gtnh.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer_gtnh.utils.PlayerUtils;

/**
 * Mixin for the {@code EntityWither} class.
 */
@Mixin(EntityWither.class)
public class EntityWitherMixin {
    /**
     * Mixin for the {@code attackEntityWithRangedAttack} method.
     */
    @Inject(method = "attackEntityWithRangedAttack", at = @At("TAIL"), cancellable = true)
    private void attackEntityWithRangedAttack(EntityLivingBase target,
        float p_82196_2_, CallbackInfo ci) {
        EntityWither self = (EntityWither) (Object) this;
        EntityLiving attacker = self;
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities) || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            ci.cancel();
        }
    }
}
