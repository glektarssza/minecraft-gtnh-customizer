package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

/**
 * Mixin for the {@code EntityEnderman} class.
 */
@Mixin(EntityEnderman.class)
public class EntityEndermanMixin {
    /**
     * Mixin for the {@code findPlayerToAttack} method.
     */
    @Inject(method = "findPlayerToAttack", at = @At("RETURN"), cancellable = true)
    public void findPlayerToAttack(CallbackInfoReturnable<Entity> cir) {
        Entity returnValue = cir.getReturnValue();
        EntityPlayer player = null;
        if (returnValue instanceof EntityPlayer) {
            player = (EntityPlayer) returnValue;
        }
        if (player == null) {
            return;
        }
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(null);
        }
    }

    /**
     * Mixin for the {@code shouldAttackPlayer} method.
     */
    @Inject(method = "shouldAttackPlayer", at = @At("RETURN"), cancellable = true)
    public void shouldAttackPlayer(EntityPlayer player,
        CallbackInfoReturnable<Boolean> cir) {
        if (player == null) {
            return;
        }
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(false);
        }
    }
}
