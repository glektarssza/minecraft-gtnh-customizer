package com.glektarssza.player_handling_customizer_gtnh.mixins.early.vanilla;

import java.util.List;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.player_handling_customizer_gtnh.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer_gtnh.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer_gtnh.utils.PlayerUtils;

/**
 * Mixin for the {@code EntityAIAttackOnCollide} class.
 */
@Mixin(EntityAIAttackOnCollide.class)
public class EntityAIAttackOnCollideMixin {

    /**
     * A shadow of the {@code attacker} field.
     */
    @Shadow
    private EntityCreature attacker;

    /**
     * Mixin for the {@code shouldExecute} method.
     */
    @SuppressWarnings("unused")
    @Inject(method = "shouldExecute", at = @At("RETURN"), cancellable = true)
    private void shouldExecute(CallbackInfoReturnable<Boolean> cir) {
        EntityAIAttackOnCollide self = (EntityAIAttackOnCollide) (Object) this;
        EntityLiving attacker = this.attacker;
        EntityLivingBase target = this.attacker.getAttackTarget();
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities)
            || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(false);
        }
    }
}
