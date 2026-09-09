package com.glektarssza.gtnh_customizer.mixins.late.xaeros.client;

import java.lang.invoke.MethodHandles;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.biome.BiomeGenBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import xaero.map.MapProcessor;
import xaero.map.WorldMap;
import xaero.map.gui.GuiMap;
import xaero.map.region.MapBlock;
import xaero.map.region.MapRegion;
import xaero.map.region.MapTile;
import xaero.map.region.MapTileChunk;

import com.glektarssza.gtnh_customizer.GTNHCustomizer;
import com.glektarssza.gtnh_customizer.Tags;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

@Mixin(GuiMap.class)
public class GuiMapMixins {
    /**
     * The logger for this class.
     */
    @Nonnull
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
            MethodHandles.lookup().lookupClass().getSimpleName())));

    /**
     * A shadow of the {@code mouseBlockPosX} field.
     */
    @Shadow(remap = false)
    private int mouseBlockPosX;

    /**
     * A shadow of the {@code mouseBlockPosZ} field.
     */
    @Shadow(remap = false)
    private int mouseBlockPosZ;

    /**
     *
     */
    @Shadow(remap = false)
    private MapProcessor mapProcessor;

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
        int additionalHeightUnits = 0;
        if (WorldMap.settings.coordinates) {
            additionalHeightUnits++;
        }
        if (WorldMap.settings.debug) {
            additionalHeightUnits++;
        }
        self.drawCenteredString(mc.fontRenderer, biomeName, self.width / 2,
            (4 + mc.fontRenderer.FONT_HEIGHT) * additionalHeightUnits, -1);
    }

    @Inject(method = "drawScreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", slice = "shouldDrawCoordsCheck", ordinal = 0, remap = false), cancellable = false, slice = @Slice(id = "shouldDrawCoordsCheck", from = @At(value = "FIELD", target = "Lxaero/map/settings/ModSettings;coordinates:Z", remap = false)))
    public void drawScreen$addMousedOverBiome(CallbackInfo ci) {
        if (!Config.getXaerosWorldMapShowHoveredBiome()) {
            return;
        }
        final MapRegion xaeroLeafRegion = this.mapProcessor.getMapRegion(
            this.mouseBlockPosX >> 9, this.mouseBlockPosZ >> 9, false);
        final MapTileChunk xaeroChunk = xaeroLeafRegion == null ? null
            : xaeroLeafRegion.getChunk(this.mouseBlockPosX >> 6 & 7,
                this.mouseBlockPosZ >> 6 & 7);
        final MapTile xaeroMapTile = xaeroChunk == null ? null
            : xaeroChunk.getTile(this.mouseBlockPosX >> 4 & 3,
                this.mouseBlockPosZ >> 4 & 3);
        final MapBlock xaeroBlock = xaeroMapTile == null ? null
            : xaeroMapTile.getBlock(this.mouseBlockPosX & 15,
                this.mouseBlockPosZ & 15);
        if (xaeroBlock == null) {
            GTNHCustomizer.emitTrackedWarning(LOGGER,
                TypeHelpers.castToNonNull(
                    String.format("%s:no_xaero_block", LOGGER.getName())),
                (logger) -> {
                    logger.warn(
                        "Failed to retrieve block data from Xaero's World Map!");
                });
            this.drawBiomeName(TypeHelpers.castToNonNull(I18n.format(
                "gtnh_customizer.xaeros_world_map.biome_unknown")));
            return;
        }
        if (xaeroBlock.getBiome() < 0) {
            GTNHCustomizer.emitTrackedWarning(LOGGER,
                TypeHelpers.castToNonNull(
                    String.format("%s:invalid_biome_id", LOGGER.getName())),
                (logger) -> {
                    logger.warn(
                        "Biome ID is not valid!");
                });
            this.drawBiomeName(TypeHelpers.castToNonNull(I18n.format(
                "gtnh_customizer.xaeros_world_map.biome_unknown")));
            return;
        }
        final BiomeGenBase biome = BiomeGenBase.getBiome(xaeroBlock.getBiome());
        if (biome.biomeName == null) {
            GTNHCustomizer.emitTrackedWarning(LOGGER,
                TypeHelpers.castToNonNull(
                    String.format("%s:missing_biome_name", LOGGER.getName())),
                (logger) -> {
                    logger.warn("Biome name does not exist!");
                });
            this.drawBiomeName(TypeHelpers.castToNonNull(I18n.format(
                "gtnh_customizer.xaeros_world_map.biome_unknown")));
            return;
        }
        this.drawBiomeName(TypeHelpers.castToNonNull(biome.biomeName));
    }
}
