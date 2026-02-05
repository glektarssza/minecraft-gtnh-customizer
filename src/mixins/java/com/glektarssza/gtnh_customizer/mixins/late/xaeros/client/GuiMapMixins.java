package com.glektarssza.gtnh_customizer.mixins.late.xaeros.client;

import java.lang.invoke.MethodHandles;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.biome.BiomeGenBase;

import cpw.mods.fml.relauncher.Side;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import xaero.map.MapProcessor;
import xaero.map.WorldMap;
import xaero.map.gui.GuiMap;
import xaero.map.region.LeveledRegion;

import com.glektarssza.gtnh_customizer.GTNHCustomizer;
import com.glektarssza.gtnh_customizer.Tags;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

@Mixin(GuiMap.class)
public class GuiMapMixins {
    /**
     * The logger for this class.
     */
    @SuppressWarnings("unused")
    @Nonnull
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
            MethodHandles.lookup().lookupClass().getSimpleName())));

    /**
     * A shadow of the {@code mouseBlockPosX} field.
     */
    @Shadow
    private int mouseBlockPosX;

    /**
     * A shadow of the {@code mouseBlockPosZ} field.
     */
    @Shadow
    private int mouseBlockPosZ;

    /**
     *
     */
    @Shadow
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
        self.drawCenteredString(mc.fontRenderer, biomeName, self.width / 2,
            (WorldMap.settings.coordinates ? mc.fontRenderer.FONT_HEIGHT : 0)
                + 4,
            -1);
    }

    @Inject(method = "drawScreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", slice = "shouldDrawCoordsCheck", ordinal = 0, remap = false), cancellable = false, slice = @Slice(id = "shouldDrawCoordsCheck", from = @At(value = "FIELD", target = "Lxaero/map/settings/ModSettings;coordinates:Z")))
    public void drawScreen$addMousedOverBiome(CallbackInfo ci,
        @Local(name = "reg") LeveledRegion<?> reg) {
        if (GTNHCustomizer.getProxy().getSide() != Side.CLIENT) {
            GTNHCustomizer.emitTrackedWarning(LOGGER,
                "XAERO_WORLD_MAP_NOT_CLIENT_SIDE",
                (logger) -> logger.warn(
                    "Xaero's World Map mixin is being called from the server side!"));
        }
        if (!Config.getXaerosWorldMapShowHoveredBiome()) {
            return;
        }
        WorldClient world = this.mapProcessor.getWorld();
        if (world == null) {
            this.drawBiomeName(TypeHelpers.castToNonNull(I18n.format(
                "gtnh_customizer.xaeros_world_map.biome_unknown")));
            return;
        }
        BiomeGenBase biomeGen = world.getBiomeGenForCoords(this.mouseBlockPosX,
            this.mouseBlockPosZ);
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
