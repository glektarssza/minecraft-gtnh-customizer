package com.glektarssza.player_handling_customizer_gtnh.mixins.early.vanilla;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.player_handling_customizer_gtnh.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer_gtnh.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer_gtnh.utils.PlayerUtils;

/**
 * Mixin for the {@code World} class.
 */
@Mixin(World.class)
public class WorldMixin {

    /**
     * Mixin for the {@code getClosestVulnerablePlayerToEntity} method.
     */
    @Inject(method = "getClosestVulnerablePlayerToEntity", at = @At("RETURN"), cancellable = true)
    public void getClosestVulnerablePlayerToEntity(Entity entityIn,
        double distance,
        CallbackInfoReturnable<EntityPlayer> cir) {
        if (!(entityIn instanceof EntityLiving)) {
            return;
        }
        EntityLiving attacker = (EntityLiving) entityIn;
        EntityPlayer player = cir.getReturnValue();
        if (player == null) {
            return;
        }
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities)
            || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(null);
        }
    }
}
