package com.glektarssza.gtnh_customizer.mixins.late.tconstruct;

import net.minecraft.entity.player.PlayerCapabilities;

import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.glektarssza.gtnh_customizer.config.Config;

import tconstruct.blocks.slime.SlimeSapling;

/**
 * Mixin for the {@link SlimeSapling} class.
 */
@Mixin(SlimeSapling.class)
public class SlimeSaplingMixin {
    @Redirect(method = "Ltconstruct/blocks/slime/SlimeSapling;boneFertilize(Lnet/minecraft/world/World;IIILjava/util/Random;Lnet/minecraft/entity/player/EntityPlayer;)Z", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerCapabilities;isCreativeMode:Z", opcode = Opcodes.GETFIELD))
    private boolean overwriteGetPlayerIsCreative(PlayerCapabilities self) {
        if (Config.getTConstructSlimeSaplingBoneMealable()) {
            return true;
        }
        return self.isCreativeMode;
    }
}
