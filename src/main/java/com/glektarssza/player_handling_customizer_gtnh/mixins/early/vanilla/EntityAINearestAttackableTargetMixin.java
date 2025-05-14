package com.glektarssza.player_handling_customizer_gtnh.mixins.early.vanilla;

import java.util.List;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
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
 * Mixin for the {@code EntityAINearestAttackableTarget} class.
 */
@Mixin(EntityAINearestAttackableTarget.class)
public abstract class EntityAINearestAttackableTargetMixin
    extends EntityAITarget {

    /**
     * Constructor to shut Java up.
     */
    public EntityAINearestAttackableTargetMixin(EntityCreature taskOwner,
        boolean shouldCheckSight) {
        super(taskOwner, shouldCheckSight);

    }

    /**
     * Constructor to shut Java up.
     */
    public EntityAINearestAttackableTargetMixin(EntityCreature taskOwner,
        boolean shouldCheckSight,
        boolean nearbyOnly) {
        super(taskOwner, shouldCheckSight, nearbyOnly);

    }

    /**
     * A shadow of the {@code targetEntity} field.
     */
    @Shadow
    private EntityLivingBase targetEntity;

    /**
     * Mixin for the {@code shouldExecute} method.
     */
    @SuppressWarnings("unused")
    @Inject(method = "shouldExecute", at = @At("RETURN"), cancellable = true)
    private void shouldExecute(CallbackInfoReturnable<Boolean> cir) {
        EntityAINearestAttackableTargetMixin self = (EntityAINearestAttackableTargetMixin) (Object) this;
        EntityLiving attacker = this.taskOwner;
        EntityLivingBase target = this.targetEntity;
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;
        List<ITargetingImmunity> immunities = PlayerUtils
            .getPlayerTargetingImmunities(player);
        if (ImmunityUtils.entityMatchesAnyTargetingImmunity(attacker,
            immunities)
            || PlayerUtils.getIsPlayerGloballyImmune(player)) {
            this.targetEntity = null;
            cir.setReturnValue(false);
        }
    }
}
