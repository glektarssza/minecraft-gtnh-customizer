package com.glektarssza.gtnh_customizer.mixins.late.specialmobs;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

import toast.specialMobs.entity.ghast.Entity_SpecialGhast;

/**
 * Mixin for the {@code Entity_SpecialGhast} class.
 */
@Mixin(value = Entity_SpecialGhast.class, remap = false)
public class Entity_SpecialGhastMixin {
    /**
     * Mixin for the {@code updateEntityTarget} method.
     */
    @Inject(method = "updateEntityTarget", at = @At("TAIL"), cancellable = true, remap = false)
    public void updateEntityTarget$disableIfConfigured(CallbackInfo ci) {
        Entity_SpecialGhast self = (Entity_SpecialGhast) (Object) this;
        EntityLivingBase target = null;
        if (self.targetedEntity == null) {
            return;
        }
        if (!(self.targetedEntity instanceof EntityLivingBase)) {
            return;
        }
        target = (EntityLivingBase) self.targetedEntity;
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            self.targetedEntity = null;
        }
    }
}
