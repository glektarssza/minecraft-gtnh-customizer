package com.glektarssza.gtnh_customizer.mixins.late.xaeros.client;

import java.lang.invoke.MethodHandles;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import xaero.common.gui.GuiWaypoints;
import xaero.common.minimap.waypoints.WaypointWorld;
import xaero.common.minimap.waypoints.WaypointWorldConnectionManager;
import xaero.common.minimap.waypoints.WaypointWorldRootContainer;
import xaero.common.minimap.waypoints.WaypointsManager;

import com.glektarssza.gtnh_customizer.Tags;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

/**
 * Mixin for the {@link GuiWaypoints} class.
 */
@Mixin(GuiWaypoints.class)
public abstract class GuiWaypointsMixins {
    /**
     * The logger for this class.
     */
    @Nonnull
    @SuppressWarnings("unused")
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
            MethodHandles.lookup().lookupClass().getSimpleName())));

    /**
     * Get the root world container for the given waypoint world.
     *
     * @param world The waypoint world to get the root world container for.
     *
     * @return The root world container for the given waypoint world.
     */
    private static WaypointWorldRootContainer getRootWorldContainer(
        WaypointWorld world) {
        return (WaypointWorldRootContainer) world.getContainer()
            .getRootContainer();
    }

    /**
     * A shadow of the {@code waypointsManager} private field.
     */
    @Shadow(remap = false)
    private WaypointsManager waypointsManager;

    /**
     * A shadow of the {@code displayedWorld} private field.
     */
    @Shadow(remap = false)
    private WaypointWorld displayedWorld;

    @Inject(method = "canTeleport", at = @At("TAIL"), cancellable = true, remap = false)
    private void canTeleport$extendCheckToSubWorlds(
        CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            return;
        }
        WaypointWorldConnectionManager subConnectionManager = getRootWorldContainer(
            this.displayedWorld).getSubWorldConnections();
        boolean hasConnection = subConnectionManager.isConnected(displayedWorld,
            this.waypointsManager.getAutoWorld());
        cir.setReturnValue(hasConnection);
    }
}
