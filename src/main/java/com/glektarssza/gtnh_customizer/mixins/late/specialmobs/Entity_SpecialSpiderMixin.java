package com.glektarssza.gtnh_customizer.mixins.late.specialmobs;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.api.ITargetingImmunity;
import com.glektarssza.gtnh_customizer.utils.ImmunityUtils;
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
        Entity_SpecialSpider self = (Entity_SpecialSpider) (Object) this;
        EntityLiving attacker = self;
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
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities)
            || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(null);
        }
    }
}
