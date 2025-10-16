package com.glektarssza.gtnh_customizer.mixins.late.thaumcraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

import thaumcraft.common.entities.monster.EntityWisp;

/**
 * Mixin for the {@code EntityWisp} class.
 */
@Mixin(value = EntityWisp.class)
public class EntityWispMixin {
    /**
     * A shadow of the {@code targetedEntity} field.
     */
    @Shadow(remap = false)
    private Entity targetedEntity;

    /**
     * Mixin for the {@code attackEntityFrom} method.
     */
    @Inject(method = "attackEntityFrom", at = @At(value = "FIELD", target = "Lthaumcraft/common/entities/monster/EntityWisp;targetedEntity:Lnet/minecraft/entity/Entity;", opcode = Opcodes.PUTFIELD, shift = Shift.AFTER, remap = false), cancellable = true)
    public void attackEntityFrom$disableIfConfigured(DamageSource damageSource,
        float amount,
        CallbackInfoReturnable<Boolean> cir) {
        if (this.targetedEntity == null) {
            return;
        }
        if (!(targetedEntity instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase target = (EntityLivingBase) targetedEntity;
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            this.targetedEntity = null;
        }
    }

    /**
     * Mixin for the {@code updateEntityActionState} method.
     */
    @Inject(method = "updateEntityActionState", at = @At(value = "FIELD", target = "Lthaumcraft/common/entities/monster/EntityWisp;targetedEntity:Lnet/minecraft/entity/Entity;", opcode = Opcodes.PUTFIELD, shift = Shift.AFTER, remap = false), cancellable = true)
    public void updateEntityActionState$disableIfConfigured(CallbackInfo ci) {
        if (this.targetedEntity == null) {
            return;
        }
        if (!(targetedEntity instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase target = (EntityLivingBase) targetedEntity;
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            this.targetedEntity = null;
        }
    }
}
