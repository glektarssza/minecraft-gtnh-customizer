package com.glektarssza.gtnh_customizer.mixins.late.specialmobs;

import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

import toast.specialMobs.entity.creeper.EntityEnderCreeper;

/**
 * Mixin for the {@code EntityEnderCreeper} class.
 */
@Mixin(EntityEnderCreeper.class)
public class EntityEnderCreeperMixin {
    /**
     * Mixin for the {@code shouldAttackPlayer} method.
     */
    @Inject(method = "shouldAttackPlayer", at = @At("RETURN"), cancellable = true, remap = false)
    public void shouldAttackPlayer$disableIfConfigured(EntityPlayer player,
        CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            return;
        }
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(false);
        }
    }
}
