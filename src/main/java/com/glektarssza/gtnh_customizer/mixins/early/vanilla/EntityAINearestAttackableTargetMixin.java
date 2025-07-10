package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

/**
 * Mixin for the {@code EntityAINearestAttackableTarget} class.
 */
@Mixin(EntityAINearestAttackableTarget.class)
public abstract class EntityAINearestAttackableTargetMixin {
    /**
     * A shadow of the {@code targetEntity} field.
     */
    @Shadow
    private EntityLivingBase targetEntity;

    /**
     * Mixin for the {@code shouldExecute} method.
     */
    @SuppressWarnings("unused")
    @Inject(method = "shouldExecute", at = @At("RETURN"), cancellable = true)
    private void shouldExecute$disableIfConfigured(
        CallbackInfoReturnable<Boolean> cir) {
        EntityAINearestAttackableTarget self = (EntityAINearestAttackableTarget) (Object) this;
        EntityLivingBase target = this.targetEntity;
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            this.targetEntity = null;
            cir.setReturnValue(false);
        }
    }
}
