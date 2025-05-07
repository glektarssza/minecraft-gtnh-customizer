package com.glektarssza.player_handling_customizer_gtnh.mixins.early.vanilla;

import net.minecraft.entity.ai.EntityAIHurtByTarget;

import org.spongepowered.asm.mixin.Mixin;

/**
 * Mixin for the {@code EntityAIHurtByTarget} class.
 */
@Mixin(EntityAIHurtByTarget.class)
public class EntityAIHurtByTargetMixin {
    // -- Right now this does nothing as it inherits from EntityAITarget
}
