package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

@Mixin(EntityPigZombie.class)
public class EntityPigZombieMixin {
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
     * Mixin for the {@code becomeAngryAt} method.
     */
    @Inject(method = "becomeAngryAt", at = @At("HEAD"), cancellable = true)
    public void becomeAngryAt$disableIfConfigured(Entity target,
        CallbackInfo ci) {
        if (target == null) {
            return;
        }
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            ci.cancel();
        }
    }
}
