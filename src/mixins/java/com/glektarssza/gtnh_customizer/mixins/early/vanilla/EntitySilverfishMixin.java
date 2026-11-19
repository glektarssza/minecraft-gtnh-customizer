package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.glektarssza.gtnh_customizer.utils.PlayerUtils;

/**
 * Mixin for the {@code EntitySilverfish} class.
 */
@Mixin(EntitySilverfish.class)
public abstract class EntitySilverfishMixin extends EntityMob {
    /**
     * A constructor to make Java happy.
     *
     * @param world The game world.
     */
    private EntitySilverfishMixin(final World world) {
        super(world);
    }

    /**
     * Mixin for the {@code findPlayerToAttack} method.
     *
     * @param cir The callback info.
     */
    @Inject(method = "findPlayerToAttack", at = @At("RETURN"), cancellable = true)
    public void findPlayerToAttack$disableIfConfigured(
        final CallbackInfoReturnable<Entity> cir) {
        final Entity target = cir.getReturnValue();
        if (target == null) {
            return;
        }
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        final EntityPlayer player = (EntityPlayer) target;
        if (PlayerUtils.getIsPlayerGloballyImmune(player)) {
            cir.setReturnValue(null);
        }
    }

    /**
     * Mixin for the {@link BlockSilverfish#func_150196_a func_150196_a} method.
     *
     * @param block The block to check.
     *
     * @return Whether the Silverfish is allowed to infest the block.
     */
    @Redirect(method = "updateEntityActionState", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockSilverfish;func_150196_a(Lnet/minecraft/block/Block;)Z"))
    public boolean updateEntityActionState$respectMobGriefing(
        final Block block) {
        return BlockSilverfish.func_150196_a(block)
            && this.worldObj.getGameRules()
                .getGameRuleBooleanValue("mobGriefing");
    }
}
