package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import serverutils.lib.client.ClientUtils;
import serverutils.net.MessageEditNBTRequest;

/**
 * Mixin for the {@link MessageEditNBTRequest} class.
 */
@Mixin(MessageEditNBTRequest.class)
public class MessageEditNBTRequestMixin {
    /**
     * Mixin for the {@code editNBT} method.
     */
    @SideOnly(Side.CLIENT)
    @Inject(method = "editNBT", at = @At("TAIL"), remap = false)
    private static void editNBT$handleRaycastNull(
        CallbackInfo ci, @Local LocalRef<MovingObjectPosition> ray) {
        if (ray.get() == null) {
            return;
        }
        if (ray.get().typeOfHit == MovingObjectType.MISS
            && Minecraft.getMinecraft().thePlayer.inventory
                .getCurrentItem() != null) {
            ClientUtils.execClientCommand("/nbtedit item");
        }
    }
}
