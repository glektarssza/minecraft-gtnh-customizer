package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

/**
 * Mixin for the {@code EntityGhast} class.
 */
@Mixin(EntityGhast.class)
public class EntityGhastMixin {
    /**
     * A shadow of the {@code targetedEntity} field.
     */
    @Shadow
    private Entity targetedEntity;

    /**
     * Mixin for the {@code updateEntityActionState} method.
     */
    @Inject(method = "updateEntityActionState", at = @At(value = "INVOKE_ASSIGN", target = "net.minecraft.world.World.getClosestVulnerablePlayerToEntity(Lnet/minecraft/entity/Entity;D)Lnet/minecraft/entity/player/EntityPlayer;"), cancellable = false)
    public void overrideTargetedEntity(CallbackInfo ci) {
        Entity target = this.targetedEntity;
        if (target == null) {
            return;
        }
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) target;

        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            this.targetedEntity = null;
        }
    }
}
