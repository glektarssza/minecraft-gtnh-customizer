package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import java.util.List;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.gtnh_customizer.api.immunity.ITargetingImmunity;
import com.glektarssza.gtnh_customizer.utils.ImmunityUtils;
import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

/**
 * Mixin for the {@link EntityLiving} class.
 */
@Mixin(EntityLiving.class)
public class EntityLivingMixin {
    /**
     * Mixin for the {@link EntityLiving#setAttackTarget} method.
     */
    @Inject(method = "setAttackTarget", at = @At(value = "HEAD"), cancellable = true)
    public void overrideSetAttackTarget(EntityLivingBase target,
        CallbackInfo ci) {
        EntityCreature self = (EntityCreature) (Object) this;
        EntityLiving attacker = self;
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
            immunities)
            || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            ci.cancel();
        }
    }
}
