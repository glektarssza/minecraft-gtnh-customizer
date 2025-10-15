package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

/**
 * Mixin for the {@code EntityAICreeperSwell} class.
 */
@Mixin(EntityAICreeperSwell.class)
public class EntityAICreeperSwellMixin {
    /**
     * A shadow of the {@code swellingCreeper} field.
     */
    @Shadow
    private EntityCreeper swellingCreeper;

    /**
     * Mixin for the {@code shouldExecute} method.
     */
    @SuppressWarnings("unused")
    @Inject(method = "shouldExecute", at = @At("RETURN"), cancellable = true)
    private void shouldExecute$disableIfConfigured(
        CallbackInfoReturnable<Boolean> cir) {
        EntityLiving attacker = this.swellingCreeper;
        EntityLivingBase target = this.swellingCreeper.getAttackTarget();
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(false);
        }
    }
}
