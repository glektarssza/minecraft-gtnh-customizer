package com.glektarssza.gtnh_customizer.mixins.late.xaeros.client;

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

/**
 * Mixin for the {@link GuiWaypoints} class.
 */
@Mixin(GuiWaypoints.class)
public abstract class GuiWaypointsMixins {
    /**
     * Get the root world container for the given waypoint world.
     *
     * @param world The waypoint world to get the root world container for.
     *
     * @return The root world container for the given waypoint world.
     */
    private static WaypointWorldRootContainer getRootWorldContainer(
        final WaypointWorld world) {
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
        final CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            return;
        }
        final WaypointWorldConnectionManager subConnectionManager = GuiWaypointsMixins
            .getRootWorldContainer(
                this.displayedWorld)
            .getSubWorldConnections();
        final boolean hasConnection = subConnectionManager.isConnected(
            this.displayedWorld,
            this.waypointsManager.getAutoWorld());
        cir.setReturnValue(hasConnection);
    }
}
