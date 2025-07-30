package com.glektarssza.gtnh_customizer.mixins.late.enderzoo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

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
@Mixin(value = EntityEnderminy.class, remap = false)
public abstract class EntityEnderminyMixin extends EntityMob {
    /**
     * Make Java happy.
     */
    private EntityEnderminyMixin(World theWorld) {
        super(theWorld);
    }

    /**
     * Mixin for the {@code findPlayerToAttack} method.
     */
    @Inject(method = "findPlayerToAttack", at = @At("RETURN"), cancellable = true)
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
    @Inject(method = "shouldAttackPlayer", at = @At("RETURN"), cancellable = true)
    public void shouldAttackPlayer$adjustReturnValue(EntityPlayer player,
        CallbackInfoReturnable<Boolean> cir) {
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * Mixin for the {@code teleportTo} method.
     */
    @Inject(method = "teleportTo", at = @At("HEAD"), cancellable = true)
    public void teleportTo$disableIfConfigured(double x, double y, double z,
        CallbackInfoReturnable<Boolean> cir) {
        if (Config.getPreventEnderMobTeleportation()) {
            cir.setReturnValue(false);
        }
    }

    /**
     * Mixin for the {@code doGroupArgo} method.
     */
    @Inject(method = "doGroupArgo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getEntitiesWithinAABB(Ljava/lang/Class;Lnet/minecraft/util/AxisAlignedBB;)Ljava/util/List;", remap = true), cancellable = true)
    public void doGroupArgo$cancelIfTargetingImmunePlayer(CallbackInfo ci) {
        if (this.entityToAttack == null) {
            return;
        }
        if (!(this.entityToAttack instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase target = (EntityLivingBase) this.entityToAttack;
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            ci.cancel();
        }
    }
}
