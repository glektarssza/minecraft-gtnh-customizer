package com.glektarssza.gtnh_customizer.mixins.late.serverutilities;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MovingObjectPosition;

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
    private static void editNBT$handleRaycastMiss(
        CallbackInfo ci, @Local LocalRef<MovingObjectPosition> ray) {
        if (ray.get() == null) {
            return;
        }
        switch (ray.get().typeOfHit) {
            case BLOCK:
                if (Minecraft
                    .getMinecraft().theWorld.getTileEntity(ray.get().blockX,
                        ray.get().blockY, ray.get().blockZ) != null) {
                    // -- Block has a valid tile entity attached, do NOT fall
                    // -- through to the "miss" case
                    break;
                }
                // -- Block had no tile entity, treat it as a "miss"
            case MISS:
                if (Minecraft.getMinecraft().thePlayer.inventory
                    .getCurrentItem() != null) {
                    ClientUtils.execClientCommand("/nbtedit item");
                }
            default:
                // -- Do nothing
        }
    }
}
