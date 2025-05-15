package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import java.util.List;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.api.ITargetingImmunity;
import com.glektarssza.gtnh_customizer.utils.ImmunityUtils;
import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

/**
 * Mixin for the {@code EntityAITarget} class.
 */
@Mixin(EntityAITarget.class)
public class EntityAITargetMixin {

    /**
     * A shadow of the {@code taskOwner} field.
     */
    @Shadow
    private EntityCreature taskOwner;

    /**
     * Mixin for the {@code isSuitableTarget} method.
     */
    @SuppressWarnings("unused")
    @Inject(method = "isSuitableTarget", at = @At("RETURN"), cancellable = true)
    private void isSuitableTarget(EntityLivingBase target,
        boolean includeInvincibles,
        CallbackInfoReturnable<Boolean> cir) {
        EntityAITarget self = (EntityAITarget) (Object) this;
        EntityLiving attacker = taskOwner;
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities)
            || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(false);
        }
    }
}
