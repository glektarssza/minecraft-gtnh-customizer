package com.glektarssza.gtnh_customizer.mixins.late.enderzoo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

import crazypants.enderzoo.entity.EntityEnderminy;

/**
 * A mixin for the {@link EntityEnderminy} class.
 */
@Mixin(EntityEnderminy.class)
public class EntityEnderminyMixin {
    /**
     * Mixin for the {@code findPlayerToAttack} method.
     */
    @Inject(method = "findPlayerToAttack", at = @At("RETURN"), cancellable = true, remap = true)
    public void findPlayerToAttack$adjustReturnValue(
        CallbackInfoReturnable<Entity> cir) {
        Entity returnValue = cir.getReturnValue();
        if (returnValue == null) {
            return;
        }
        if (!(returnValue instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase target = (EntityLivingBase) returnValue;
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(null);
        }
    }

    /**
     * Mixin for the {@code shouldAttackPlayer} method.
     */
    @Inject(method = "shouldAttackPlayer", at = @At("RETURN"), cancellable = true, remap = false)
    public void shouldAttackPlayer$adjustReturnValue(EntityPlayer player,
        CallbackInfoReturnable<Boolean> cir) {
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * Mixin for the {@code teleportTo} method.
     */
    @Inject(method = "teleportTo", at = @At("HEAD"), cancellable = true, remap = false)
    public void teleportTo$disableIfConfigured(double x, double y, double z,
        CallbackInfoReturnable<Boolean> cir) {
        if (Config.getPreventEnderMobTeleportation()) {
            cir.setReturnValue(false);
        }
    }

    /**
     * Mixin for the {@code doGroupArgo} method.
     */
    @Inject(method = "doGroupArgo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getEntitiesWithinAABB(Ljava/lang/Class;Lnet/minecraft/util/AxisAlignedBB;)Ljava/util/List;", remap = true), cancellable = true, remap = false)
    public void doGroupArgo$cancelIfTargetingImmunePlayer(CallbackInfo ci) {
        EntityEnderminy self = (EntityEnderminy) (Object) this;
        Entity entityToAttack = self.getEntityToAttack();
        if (entityToAttack == null) {
            return;
        }
        if (!(entityToAttack instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase target = (EntityLivingBase) entityToAttack;
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            ci.cancel();
        }
    }
}
