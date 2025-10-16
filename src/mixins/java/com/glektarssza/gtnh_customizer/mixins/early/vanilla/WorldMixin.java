package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.utils.IWorldExtensions;
import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

/**
 * Mixin for the {@code World} class.
 */
@Mixin(World.class)
public abstract class WorldMixin implements IWorldExtensions {
    /**
     * A shadow of the {@code func_152379_p} method.
     */
    @Shadow
    protected int func_152379_p() {
        throw new UnsupportedOperationException();
    }

    /**
     * Mixin for the {@code getClosestVulnerablePlayerToEntity} method.
     */
    @Inject(method = "getClosestVulnerablePlayerToEntity", at = @At("RETURN"), cancellable = true)
    private void getClosestVulnerablePlayerToEntity$disableIfConfigured(
        Entity entityIn,
        double distance,
        CallbackInfoReturnable<EntityPlayer> cir) {
        if (!(entityIn instanceof EntityLiving)) {
            return;
        }
        EntityPlayer player = cir.getReturnValue();
        if (player == null) {
            return;
        }
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(null);
        }
    }

    /**
     * Get the render distance of the world.
     *
     * @return The render distance of the world.
     */
    @Override
    public int getRenderDistance() {
        return this.func_152379_p();
    }
}
