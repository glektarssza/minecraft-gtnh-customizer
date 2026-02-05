package com.glektarssza.gtnh_customizer.mixins.late.xaeros.client;

import javax.annotation.Nonnull;

import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.common.DimensionManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import xaero.map.WorldMap;
import xaero.map.gui.GuiMap;
import xaero.map.region.LeveledRegion;

import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

@Mixin(GuiMap.class)
public class GuiMapMixins {
    @Shadow
    private int mouseBlockPosX;

    @Shadow
    private int mouseBlockPosZ;

    /**
     * Draw the biome name hovered over by the mouse to the top of the display,
     * beneath the coordinates if they are being rendered or at the very top of
     * they are not.
     *
     * @param biomeName The biome name to render.
     */
    private void drawBiomeName(@Nonnull String biomeName) {
        GuiMap self = (GuiMap) (Object) this;
        Minecraft mc = Minecraft.getMinecraft();
        self.drawCenteredString(mc.fontRenderer, biomeName, self.width / 2,
            (WorldMap.settings.coordinates ? mc.fontRenderer.FONT_HEIGHT : 0)
                + 4,
            -1);
    }

    @Inject(method = "drawScreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", slice = "shouldDrawCoordsCheck", ordinal = 0, remap = false), cancellable = false, slice = @Slice(id = "shouldDrawCoordsCheck", from = @At(value = "FIELD", target = "Lxaero/map/settings/ModSettings;coordinates:Z")))
    public void drawScreen$addMousedOverBiome(CallbackInfo ci,
        @Local(name = "reg") LeveledRegion<?> reg) {
        if (!Config.getXaerosWorldMapShowHoveredBiome()) {
            return;
        }
        if (reg == null) {
            this.drawBiomeName(TypeHelpers.castToNonNull(I18n.format(
                "gtnh_customizer.xaeros_world_map.biome_unknown")));
            return;
        }
        int dimensionID = reg.getDim().getDimId();
        WorldProvider dimensionProvider = DimensionManager
            .getProvider(dimensionID);
        if (dimensionProvider == null) {
            this.drawBiomeName(TypeHelpers.castToNonNull(I18n.format(
                "gtnh_customizer.xaeros_world_map.biome_unknown")));
            return;
        }
        BiomeGenBase biomeGen = dimensionProvider
            .getBiomeGenForCoords(this.mouseBlockPosX, this.mouseBlockPosZ);
        if (biomeGen == null) {
            this.drawBiomeName(TypeHelpers.castToNonNull(I18n.format(
                "gtnh_customizer.xaeros_world_map.biome_unknown")));
            return;
        }
        String biomeName = biomeGen.biomeName;
        if (biomeName == null) {
            this.drawBiomeName(TypeHelpers.castToNonNull(I18n.format(
                "gtnh_customizer.xaeros_world_map.biome_unknown")));
            return;
        }
        this.drawBiomeName(TypeHelpers.castToNonNull(biomeGen.biomeName));
    }
}
