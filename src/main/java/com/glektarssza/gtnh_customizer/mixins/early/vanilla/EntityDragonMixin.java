package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

/**
 * Mixin for the {@code EntityDragon} class.
 */
@Mixin(EntityDragon.class)
public class EntityDragonMixin {
    /**
     * A shadow of the {@code target} field.
     */
    @Shadow()
    private Entity target;

    /**
     * Mixin for the {@code attackEntitiesInList} method.
     */
    @SuppressWarnings("rawtypes")
    @Inject(method = "attackEntitiesInList", at = @At("HEAD"), cancellable = true)
    private void attackEntitiesInList(List targetEntities, CallbackInfo ci) {
        EntityDragon self = (EntityDragon) (Object) this;
        for (int i = 0; i < targetEntities.size(); ++i) {
            Entity target = (Entity) targetEntities.get(i);
            EntityPlayer player = null;
            if (target instanceof EntityLivingBase) {
                if (target instanceof EntityPlayer) {
                    player = (EntityPlayer) target;
                }
                if (player != null) {
                    if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
                        continue;
                    }
                }
                target.attackEntityFrom(DamageSource.causeMobDamage(self),
                    10.0F);
            }
        }
        ci.cancel();
    }

    /**
     * Mixin for the {@code setNewTarget} method.
     */
    @Inject(method = "setNewTarget", at = @At("TAIL"), cancellable = true)
    private void setNewTarget(CallbackInfo ci) {
        EntityPlayer player = null;
        if (this.target instanceof EntityPlayer) {
            player = (EntityPlayer) this.target;
        }
        if (player == null) {
            return;
        }
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            this.target = null;
        }
    }
}
