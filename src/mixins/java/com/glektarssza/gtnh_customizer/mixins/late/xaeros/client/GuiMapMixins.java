package com.glektarssza.gtnh_customizer.mixins.late.xaeros.client;

import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.common.DimensionManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import xaero.map.gui.GuiMap;
import xaero.map.region.LeveledRegion;

@Mixin(GuiMap.class)
public class GuiMapMixins {
    @Shadow(remap = false)
    private int mouseBlockPosX;

    @Shadow(remap = false)
    private int mouseBlockPosZ;

    @Inject(method = "drawScreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", remap = false), remap = false, cancellable = false)
    public void drawScreen$addMousedOverBiome(
        CallbackInfo ci,
        @Local(name = "mc") Minecraft mc,
        @Local(name = "reg") LeveledRegion<?> reg) {
        GuiMap self = (GuiMap) (Object) this;
        String biomeString = null;
        int dimensionID = reg.getDim().getDimId();
        WorldProvider dimensionProvider = DimensionManager
            .getProvider(dimensionID);
        if (dimensionProvider == null) {
            return;
        }
        BiomeGenBase biomeGen = dimensionProvider
            .getBiomeGenForCoords(this.mouseBlockPosX, this.mouseBlockPosZ);
        if (biomeGen == null) {
            return;
        }
        biomeString = biomeGen.biomeName;
        if (biomeString == null) {
            return;
        }
        self.drawCenteredString(mc.fontRenderer, biomeString,
            self.width / 2, mc.fontRenderer.FONT_HEIGHT + 4, -1);
    }
}
