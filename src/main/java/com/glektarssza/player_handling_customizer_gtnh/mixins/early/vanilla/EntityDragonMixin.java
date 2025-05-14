package com.glektarssza.player_handling_customizer_gtnh.mixins.early.vanilla;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.player_handling_customizer_gtnh.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer_gtnh.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer_gtnh.utils.PlayerUtils;

/**
 * Mixin for the {@code EntityDragon} class.
 */
@Mixin(EntityDragon.class)
public class EntityDragonMixin {

    /**
     * A shadow of the {@code target} field.
     */
    @Shadow()
    Entity target;

    /**
     * Mixin for the {@code attackEntitiesInList} method.
     */
    @SuppressWarnings("rawtypes")
    @Inject(method = "attackEntitiesInList", at = @At("HEAD"), cancellable = true)
    private void attackEntitiesInList(List targetEntities, CallbackInfo ci) {
        EntityDragon self = (EntityDragon) (Object) this;
        EntityLiving attacker = (EntityLiving) self;
        for (int i = 0; i < targetEntities.size(); ++i) {
            Entity target = (Entity) targetEntities.get(i);
            EntityPlayer player = null;
            if (target instanceof EntityLivingBase) {
                if (target instanceof EntityPlayer) {
                    player = (EntityPlayer) target;
                }
                if (player != null) {
                    List<ITargetingImmunity> immunities = PlayerUtils
                        .getPlayerTargetingImmunities(player);
                    if (ImmunityUtils
                        .entityMatchesAnyTargetingImmunity(attacker, immunities)
                        || PlayerUtils.getIsPlayerGloballyImmune(player)) {
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
        EntityEnderman self = (EntityEnderman) (Object) this;
        EntityLiving attacker = (EntityLiving) self;
        EntityPlayer player = null;
        if (this.target instanceof EntityPlayer) {
            player = (EntityPlayer) this.target;
        }
        if (player == null) {
            return;
        }
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities)
            || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            this.target = null;
        }
    }
}
