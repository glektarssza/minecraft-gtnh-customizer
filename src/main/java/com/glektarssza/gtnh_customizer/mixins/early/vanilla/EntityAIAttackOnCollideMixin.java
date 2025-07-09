package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

/**
 * Mixin for the {@code EntityAIAttackOnCollide} class.
 */
@Mixin(EntityAIAttackOnCollide.class)
public class EntityAIAttackOnCollideMixin {
    /**
     * A shadow of the {@code attacker} field.
     */
    @Shadow
    private EntityCreature attacker;

    /**
     * Mixin for the {@code shouldExecute} method.
     */
    @SuppressWarnings("unused")
    @Inject(method = "shouldExecute", at = @At("RETURN"), cancellable = true)
    private void shouldExecute$disableIfConfigured(
        CallbackInfoReturnable<Boolean> cir) {
        EntityAIAttackOnCollide self = (EntityAIAttackOnCollide) (Object) this;
        EntityLiving attacker = this.attacker;
        EntityLivingBase target = this.attacker.getAttackTarget();
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(false);
        }
    }
}
