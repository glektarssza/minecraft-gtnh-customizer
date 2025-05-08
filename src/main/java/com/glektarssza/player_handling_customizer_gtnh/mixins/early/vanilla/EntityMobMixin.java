package com.glektarssza.player_handling_customizer_gtnh.mixins.early.vanilla;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.player_handling_customizer_gtnh.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer_gtnh.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer_gtnh.utils.PlayerUtils;

/**
 * Mixin for the {@code EntityMob} class.
 */
@Mixin(EntityMob.class)
public class EntityMobMixin {
    /**
     * Mixin for the {@code findPlayerToAttack} method.
     */
    @Inject(method = "findPlayerToAttack", at = @At("RETURN"), cancellable = true)
    public void findPlayerToAttack(CallbackInfoReturnable<Entity> cir) {
        EntityMob self = (EntityMob) (Object) this;
        EntityLiving attacker = self;
        Entity target = cir.getReturnValue();
        if (target == null) {
            return;
        }
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities) || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(null);
        }
    }

    /**
     * Mixin for the {@code attackEntityFrom} method.
     */
    @Inject(method = "attackEntityFrom", at = @At("RETURN"), cancellable = true)
    public void attackEntityFrom(DamageSource damageSource, float amount,
        CallbackInfoReturnable<Boolean> cir) {
        EntityMob self = (EntityMob) (Object) this;
        EntityLiving attacker = self;
        Entity targetEntity = self.getEntityToAttack();
        if (targetEntity == null) {
            return;
        }
        if (!(targetEntity instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase target = (EntityLivingBase) targetEntity;
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities) || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            self.setTarget(null);
        }
    }
}
