package com.glektarssza.player_handling_customizer_gtnh.mixins.late.specialmobs;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.player_handling_customizer_gtnh.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer_gtnh.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer_gtnh.utils.PlayerUtils;

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
    public void shouldAttackPlayer(EntityPlayer player,
        CallbackInfoReturnable<Boolean> cir) {
        EntityEnderCreeper self = (EntityEnderCreeper) (Object) this;
        EntityLivingBase attacker = self;
        if (!cir.getReturnValue()) {
            return;
        }
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities)
            || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(false);
        }
    }
}
