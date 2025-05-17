package com.glektarssza.gtnh_customizer.mixins.late.xaeros.client;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.client.entity.EntityClientPlayerMP;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.glektarssza.gtnh_customizer.GTNHCustomizer;

import xaero.common.minimap.waypoints.Waypoint;
import xaero.common.minimap.waypoints.WaypointWorld;
import xaero.common.minimap.waypoints.WaypointsManager;

/**
 * Mixin for the {@link WaypointsManager} class.
 */
@Mixin(WaypointsManager.class)
public class WaypointsManagerMixins {
    /**
     * The regex pattern to use to match parameters for replacement in commands.
     */
    private static final Pattern COMMAND_PARAMETER_PATTERN = Pattern
        .compile("\\{([a-zA-Z]+)\\}");

    /**
     * Redirect the invocation of the
     * {@link EntityClientPlayerMP#sendChatMessage} method to this mixin.
     *
     * @param player The player on which the method was invoked.
     * @param originalTeleportCommand The teleport command being invoked.
     * @param
     */
    @Redirect(method = "teleportToWaypoint(Lxaero/common/minimap/waypoints/Waypoint;Lxaero/common/minimap/waypoints/WaypointWorld;Lnet/minecraft/client/gui/GuiScreen;Z)V", at = @At(value = "INVOKE", target = "net.minecraft.client.entity.EntityClientPlayerMP.sendChatMessage(Ljava/lang/String;)V"), remap = false)
    private void teleportToWaypoint$overrideSendTeleportCommand(
        EntityClientPlayerMP player, String originalTeleportCommand,
        @Local(name = "x") int x, @Local(name = "z") int z,
        @Local(name = "selected") Waypoint selected,
        @Local(name = "displayedWorld") WaypointWorld displayedWorld,
        @Local(name = "tpCommandPrefix") String tpCommandPrefix) {
        if (!COMMAND_PARAMETER_PATTERN.matcher(tpCommandPrefix).find()) {
            // -- There's no parameters in the command prefix so don't bother
            player.sendChatMessage(originalTeleportCommand);
            return;
        }
        String dimensionId = displayedWorld.getContainer().getSubId();
        if (dimensionId.startsWith("dim%")) {
            try {
                int dimId = Integer.parseInt(dimensionId.substring(4));
                dimensionId = String.format("%d", dimId);
            } catch (NumberFormatException t) {
                GTNHCustomizer.LOGGER.warn(
                    "Non-integer dimension ID '{}', teleport command is probably going to fail!",
                    dimensionId.substring(4));
            }
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", player.getDisplayName());
        params.put("n", player.getDisplayName());
        params.put("dimension", dimensionId);
        params.put("dim", dimensionId);
        params.put("d", dimensionId);
        params.put("x", String.format("%d", selected.getX()));
        params.put("y", String.format("%d", selected.getY()));
        params.put("z", String.format("%d", selected.getZ()));
        params.put("rotation",
            selected.isRotation() ? String.format("%d", selected.getYaw())
                : "");
        params.put("rot",
            selected.isRotation() ? String.format("%d", selected.getYaw())
                : "");
        params.put("yaw",
            selected.isRotation() ? String.format("%d", selected.getYaw())
                : "");
        params.put("r",
            selected.isRotation() ? String.format("%d", selected.getYaw())
                : "");
        Matcher matcher = COMMAND_PARAMETER_PATTERN
            .matcher(tpCommandPrefix);
        StringBuffer teleportCommandBuffer = new StringBuffer();
        while (matcher.find()) {
            if (matcher.groupCount() < 1) {
                continue;
            }
            String paramName = matcher.group(1);
            if (!params.containsKey(paramName)) {
                continue;
            }
            matcher.appendReplacement(
                teleportCommandBuffer, params.get(paramName));
        }
        matcher.appendTail(teleportCommandBuffer);
        GTNHCustomizer.LOGGER.debug("Final teleport command: {}",
            teleportCommandBuffer.toString());
        player.sendChatMessage(teleportCommandBuffer.toString());
    }
}
