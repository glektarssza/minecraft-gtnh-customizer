package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

/**
 * Mixin for the {@link EntityMob} class.
 */
@Mixin(EntityMob.class)
public class EntityMobMixin {
    /**
     * Mixin for the {@code findPlayerToAttack} method.
     */
    @Inject(method = "findPlayerToAttack", at = @At("RETURN"), cancellable = true)
    public void findPlayerToAttack(CallbackInfoReturnable<Entity> cir) {
        Entity target = cir.getReturnValue();
        if (target == null) {
            return;
        }
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(null);
        }
    }

    /**
     * Mixin for the {@code attackEntityFrom} method.
     */
    @Inject(method = "attackEntityFrom", at = @At("RETURN"), cancellable = true)
    public void attackEntityFrom$disableIfConfigured(DamageSource damageSource,
        float amount,
        CallbackInfoReturnable<Boolean> cir) {
        EntityMob self = (EntityMob) (Object) this;
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
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            self.setTarget(null);
        }
    }
}
