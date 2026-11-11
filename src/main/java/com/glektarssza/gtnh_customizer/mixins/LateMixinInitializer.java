package com.glektarssza.gtnh_customizer.mixins;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

import com.glektarssza.gtnh_customizer.Tags;
import com.glektarssza.gtnh_customizer.api.v2.annotation.NotNull;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

/**
 * The late-stage mixin initializer.
 */
@LateMixin
public class LateMixinInitializer implements ILateMixinLoader {
    /**
     * The logger for this class.
     */
    @NotNull
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
            MethodHandles.lookup().lookupClass().getSimpleName())));

    /**
     * Get the name of the mixin config file.
     *
     * @returns The name of the mixin config file.
     */
    @Override
    public String getMixinConfig() {
        return "mixins.gtnh_customizer.late.json";
    }

    /**
     * Get the list of additional mixins to load based on the loaded mods.
     *
     * @param loadedMods The list of mods currently loaded.
     *
     * @returns The list of additional mixins to load based on the loaded mods.
     */
    @Override
    @SuppressWarnings("null")
    public List<String> getMixins(final Set<String> loadedMods) {
        final List<String> mixins = new ArrayList<String>();
        if (loadedMods.contains("SpecialMobs")) {
            LateMixinInitializer.LOGGER
                .debug("Found Special Mobs, adding mixins!");
            mixins.add("specialmobs.Entity_SpecialCaveSpiderMixin");
            mixins.add("specialmobs.Entity_SpecialGhastMixin");
            mixins.add("specialmobs.Entity_SpecialSpiderMixin");
            mixins.add("specialmobs.EntityEnderCreeperMixin");
        }
        if (loadedMods.contains("Thaumcraft")) {
            LateMixinInitializer.LOGGER
                .debug("Found Thaumcraft, adding mixins!");
            mixins.add("thaumcraft.EntityWispMixin");
        }
        if (loadedMods.contains("XaeroMinimap")
            && FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            LateMixinInitializer.LOGGER.debug(
                "Found Xaero's Minimap and running on a client, adding mixins!");
            mixins.add("xaeros.client.GuiWaypointsMixins");
            mixins.add("xaeros.client.WaypointsManagerMixins");
        }
        if (loadedMods.contains("XaeroWorldMap")
            && FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            LateMixinInitializer.LOGGER.debug(
                "Found Xaero's World Map and running on a client, adding mixins!");
            mixins.add("xaeros.client.GuiMapMixins");
        }
        if (loadedMods.contains("TConstruct")) {
            LateMixinInitializer.LOGGER
                .debug("Found Tinker's Construct, adding mixins!");
            mixins.add("tconstruct.SlimeSaplingMixin");
        }
        if (loadedMods.contains("serverutilities")
            && FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            LateMixinInitializer.LOGGER.debug(
                "Found server utilities and running on a client, adding mixins!");
            mixins.add("serverutilities.GuiBaseMixin");
            mixins.add("serverutilities.GuiEditConfigValueMixin");
            mixins.add("serverutilities.GuiEditNBTMixin");
            mixins.add("serverutilities.TextBoxMixin");
        }
        if (loadedMods.contains("EnderZoo")) {
            LateMixinInitializer.LOGGER
                .debug("Found Ender Zoo, adding mixins!");
            mixins.add("enderzoo.EntityEnderminyMixin");
        }
        if (loadedMods.contains("journeymap")) {
            LateMixinInitializer.LOGGER
                .debug("Found Journeymap, adding mixins!");
            mixins.add("journeymap.WaypointEditorMixin");
        }
        LateMixinInitializer.LOGGER.debug("Final mixin list: {}",
            mixins.stream().collect(Collectors.joining(", ")));
        return mixins;
    }
}
