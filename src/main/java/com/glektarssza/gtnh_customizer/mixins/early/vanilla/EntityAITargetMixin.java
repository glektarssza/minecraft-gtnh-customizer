package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

/**
 * Mixin for the {@code EntityAITarget} class.
 */
@Mixin(EntityAITarget.class)
public class EntityAITargetMixin {
    /**
     * Mixin for the {@code isSuitableTarget} method.
     */
    @Inject(method = "isSuitableTarget", at = @At("RETURN"), cancellable = true)
    private void isSuitableTarget$disableIfConfigured(EntityLivingBase target,
        boolean includeInvincibles,
        CallbackInfoReturnable<Boolean> cir) {
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(false);
        }
    }
}
