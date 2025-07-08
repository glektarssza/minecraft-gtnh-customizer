package com.glektarssza.gtnh_customizer.mixins.late.specialmobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

import toast.specialMobs.entity.spider.Entity_SpecialSpider;

/**
 * Mixin for the {@code Entity_SpecialSpider} class.
 */
@Mixin(value = Entity_SpecialSpider.class, remap = false)
public class Entity_SpecialSpiderMixin {
    /**
     * Mixin for the {@code findPlayerToAttack} method.
     */
    @Inject(method = "findPlayerToAttack", at = @At("RETURN"), cancellable = true, remap = false)
    public void findPlayerToAttack(CallbackInfoReturnable<Entity> cir) {
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
}
