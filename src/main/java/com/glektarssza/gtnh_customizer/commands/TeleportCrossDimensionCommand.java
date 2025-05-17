package com.glektarssza.gtnh_customizer.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

import net.minecraftforge.common.DimensionManager;

import com.glektarssza.gtnh_customizer.utils.CommandUtils;

/**
 * A command for teleporting the command sender or a target player across to
 * another player or a target set of coordinates in the same or another
 * dimension.
 */
public class TeleportCrossDimensionCommand extends CommandBase {
    /**
     * Get the command name.
     *
     * @return The command name.
     */
    @Override
    public String getCommandName() {
        return "teleport-cross-dimension";
    }

    /**
     * Get a list of aliases the command can be called by.
     *
     * @return A list of aliases the command can be called by.
     */
    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("tpxd", "tpd");
    }

    /**
     * Get the usage of the command.
     *
     * @param sender The thing sending the command.
     *
     * @return The usage of the command.
     */
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "gtnh_customizer.commands.teleport_cross_dimension.usage";
    }

    /**
     * Get the required permission level to run the command.
     *
     * @return The required permission level to run the command.
     */
    @Override
    public int getRequiredPermissionLevel() {
        return CommandUtils.PERMISSION_GAMEMASTER;
    }

    /**
     * Check if the given index in the given list of arguments is meant to be a
     * username.
     *
     * @param args The arguments being passed to the command.
     * @param index The index being checked.
     *
     * @return {@code true} if the index should be for a player username;
     *         {@code false} otherwise.
     */
    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        if (index == 0) {
            return !CommandUtils.isCoordinateArgument(args, index);
        }
        if (index == 1 && isUsernameIndex(args, 0)) {
            return !CommandUtils.isCoordinateArgument(args, index);
        }
        return false;
    }

    /**
     * Check if the given index in the given list of arguments is meant to be a
     * coordinate.
     *
     * @param args The arguments being passed to the command.
     * @param index The index being checked.
     *
     * @return {@code true} if the index should be for a coordinate;
     *         {@code false} otherwise.
     */
    public boolean isCoordinateIndex(String[] args, int index) {
        if (isUsernameIndex(args, 0) && isUsernameIndex(args, 1)) {
            return false;
        } else if (isUsernameIndex(args, 0)) {
            return index == 1 || index == 2 || index == 3;
        }
        return index == 0 || index == 1 || index == 2;
    }

    /**
     * Check if the given index in the given list of arguments is meant to be a
     * dimension identifier.
     *
     * @param args The arguments being passed to the command.
     * @param index The index being checked.
     *
     * @return {@code true} if the index should be for a dimension identifier;
     *         {@code false} otherwise.
     */
    public boolean isDimensionIndex(String[] args, int index) {
        if (isUsernameIndex(args, 0) && isUsernameIndex(args, 1)) {
            return false;
        } else if (isUsernameIndex(args, 0)) {
            return index == 4;
        }
        return index == 3;
    }

    /**
     * Check if the given index in the given list of arguments is meant to be a
     * yaw rotation.
     *
     * @param args The arguments being passed to the command.
     * @param index The index being checked.
     *
     * @return {@code true} if the index should be for a yaw rotation;
     *         {@code false} otherwise.
     */
    public boolean isYawIndex(String[] args, int index) {
        if (isUsernameIndex(args, 0) && isUsernameIndex(args, 1)) {
            return false;
        } else if (isUsernameIndex(args, 0)) {
            return index == 5;
        }
        return index == 4;
    }

    /**
     * Get a list of possible completions for the current state of the command.
     *
     * @param sender The thing sending the command.
     * @param args The current arguments being completed.
     *
     * @return A list of possible completions for the current state of the
     *         command.
     */
    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender,
        String[] args) {
        EntityPlayerMP victim = null;
        List<String> result = null;
        int index = args.length - 1;
        switch (index) {
            case 0:
                // -- First argument is one of victim, target, or X coordinate
                try {
                    victim = getPlayer(sender, args[0]);
                } catch (Throwable t) {
                    // -- Does nothing
                }
                result = CommandUtils
                    .getTruncatedPlayerUsernameIterable(512);
                if (victim != null) {
                    result.add(0, "~");
                }
                return result;
            case 1:
                // -- Second argument is one of target, X coordinate, Y
                // -- coordinate, or yaw
                if (isUsernameIndex(args, 0)) {
                    try {
                        victim = getPlayer(sender, args[0]);
                    } catch (Throwable t) {
                        return null;
                    }
                    result = CommandUtils
                        .getTruncatedPlayerUsernameIterable(512);
                    result.add(0, "~");
                    return result;
                }
                try {
                    victim = getCommandSenderAsPlayer(sender);
                } catch (Throwable t) {
                    return null;
                }
                if (isCoordinateIndex(args, 0)) {
                    return Collections.singletonList("~");
                }
                return null;
            case 2:
                // -- Third argument is one of yaw, Y position, or Z position
                if (isUsernameIndex(args, 0)) {
                    try {
                        victim = getPlayer(sender, args[0]);
                    } catch (Throwable t) {
                        return null;
                    }
                    if (isUsernameIndex(args, 1)) {
                        return Collections.singletonList("~");
                    }
                    if (isCoordinateIndex(args, 1)) {
                        return Collections.singletonList("~");
                    }
                    return null;
                }
                try {
                    victim = getCommandSenderAsPlayer(sender);
                } catch (Throwable t) {
                    return null;
                }
                if (isCoordinateIndex(args, 1)) {
                    return Collections
                        .singletonList("~");
                }
                return null;
            case 3:
                // -- Fourth argument is one of Z position or dimension ID
                if (isUsernameIndex(args, 0)) {
                    try {
                        victim = getPlayer(sender, args[0]);
                    } catch (Throwable t) {
                        return null;
                    }
                    if (isCoordinateIndex(args, index)) {
                        return Collections.singletonList("~");
                    }
                    return null;
                }
                try {
                    victim = getCommandSenderAsPlayer(sender);
                } catch (Throwable t) {
                    return null;
                }
                result = CommandUtils.getTruncatedDimensionIDsIterable(512)
                    .parallelStream().map((id) -> String.format("%d", id))
                    .collect(Collectors.toList());
                result.add(0, "~");
                return result;
            case 4:
                // -- Fifth argument is one of dimension ID or yaw
                if (isUsernameIndex(args, 0)) {
                    try {
                        victim = getPlayer(sender, args[0]);
                    } catch (Throwable t) {
                        return null;
                    }
                    result = CommandUtils.getTruncatedDimensionIDsIterable(512)
                        .parallelStream().map((id) -> String.format("%d", id))
                        .collect(Collectors.toList());
                    result.add(0, "~");
                    return result;
                }
                try {
                    victim = getCommandSenderAsPlayer(sender);
                } catch (Throwable t) {
                    return null;
                }
                return Collections.singletonList("~");
            case 5:
                // -- Sixth argument is yaw
                if (isUsernameIndex(args, 0)) {
                    try {
                        victim = getPlayer(sender, args[0]);
                    } catch (Throwable t) {
                        return null;
                    }
                    return Collections.singletonList("~");
                }
                return null;
            default:
                return null;
        }
    }

    /**
     * Process the command invocation.
     *
     * @param sender The thing sending the command.
     * @param args The command arguments.
     */
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        EntityPlayerMP victim = null;
        EntityPlayerMP target = null;
        if (args.length > 1 && isUsernameIndex(args, 0)
            && isUsernameIndex(args, 1)) {
            victim = getPlayer(sender, args[0]);
            target = getPlayer(sender, args[1]);
        } else if (args.length > 1 && isUsernameIndex(args, 0)
            && !isUsernameIndex(args, 1)) {
            victim = getPlayer(sender, args[0]);
        } else if (args.length > 0 && isUsernameIndex(args, 0)) {
            victim = getCommandSenderAsPlayer(sender);
            target = getPlayer(sender, args[0]);
        } else if (args.length > 0) {
            victim = getCommandSenderAsPlayer(sender);
        } else {
            throw new WrongUsageException(
                "gtnh_customizer.commands.teleport_cross_dimension.error.not_enough_arguments",
                new Object[0]);
        }
        if (victim != null && target != null) {
            Float yawOverride = null;
            if (args.length > 2) {
                try {
                    yawOverride = CommandUtils
                        .parseBlockRelativeFloatArgument(sender, args[2],
                            victim.rotationYaw, false);
                } catch (Throwable t) {
                    // -- Does nothing
                }
            }
            sendVictimToTarget(sender, victim, target, yawOverride);
            sender.addChatMessage(new ChatComponentTranslation(
                "gtnh_customizer.commands.teleport_cross_dimension.warning.sent_self_to_self")
                .setChatStyle(
                    new ChatStyle().setColor(EnumChatFormatting.GOLD)
                        .setItalic(true)));
            return;
        } else if (victim != null) {
            Double targetBlockPosX = null;
            Double targetBlockPosY = null;
            Double targetBlockPosZ = null;
            int targetDimension = victim.dimension;
            Float yawOverride = null;
            int offset = isUsernameIndex(args, 0) ? 1 : 0;
            if (args.length > (offset + 3)) {
                if (args[offset + 3].startsWith("~")) {
                    if (args[offset + 3].length() > 1) {
                        throw new CommandException(
                            "gtnh_customizer.commands.teleport_cross_dimension.error.unknown_dimension",
                            new Object[] {
                                args[offset + 3]
                            });
                    }
                    targetDimension = victim.dimension;
                } else {
                    try {
                        targetDimension = Integer.parseInt(args[offset + 3],
                            10);
                    } catch (Throwable t) {
                        throw new CommandException(
                            "gtnh_customizer.commands.teleport_cross_dimension.error.unknown_dimension",
                            new Object[] {
                                args[offset + 3]
                            });
                    }
                }
            }
            try {
                targetBlockPosX = CommandUtils.parseBlockRelativeDoubleArgument(
                    sender, args[offset + 0], victim.posX,
                    !args[offset + 1].startsWith("~")
                        && !args[offset + 1].contains("."));
                targetBlockPosY = CommandUtils.parseBlockRelativeDoubleArgument(
                    sender, args[offset + 1], victim.posY,
                    !args[offset + 1].startsWith("~")
                        && !args[offset + 1].contains("."),
                    0, DimensionManager.createProviderFor(targetDimension)
                        .getHeight());
                targetBlockPosZ = CommandUtils.parseBlockRelativeDoubleArgument(
                    sender, args[offset + 2], victim.posZ,
                    !args[offset + 1].startsWith("~")
                        && !args[offset + 1].contains("."));
            } catch (Throwable t) {
                throw new CommandException(
                    "gtnh_customizer.commands.teleport_cross_dimension.error.bad_destination",
                    new Object[] {
                        args[offset + 0],
                        args[offset + 1],
                        args[offset + 2]
                    });
            }
            if (args.length > (offset + 4)) {
                try {
                    yawOverride = CommandUtils
                        .parseBlockRelativeFloatArgument(sender,
                            args[offset + 4], victim.rotationYaw, false);
                } catch (Throwable t) {
                    // -- Does nothing
                }
            }
            sendVictimToLocation(sender, victim, targetBlockPosX,
                targetBlockPosY, targetBlockPosZ, targetDimension, yawOverride,
                victim.rotationPitch);
            return;
        }
        throw new WrongUsageException(
            "gtnh_customizer.commands.teleport_cross_dimension.error.wrong_usage",
            new Object[0]);
    }

    /**
     * Send the victim player to the target player's position.
     *
     * @param sender The thing sending the command.
     * @param victim The victim player to teleport.
     * @param target The target player to send the victim player to.
     * @param yawOverride The yaw to override the victim player's yaw with.
     */
    private void sendVictimToTarget(ICommandSender sender,
        EntityPlayerMP victim,
        EntityPlayerMP target, Float yawOverride) {
        this.sendVictimToLocation(sender, victim, target.posX, target.posY,
            target.posZ, target.dimension, yawOverride, target.rotationPitch);
    }

    /**
     * Send the victim player to the given target position in the given
     * dimension.
     *
     * @param sender The thing sending the command.
     * @param victim The victim player to teleport.
     * @param xPos The target X position.
     * @param yPos The target Y position.
     * @param zPos The target Z position.
     * @param dimension The target dimension.
     * @param yawOverride The yaw to override the victim player's yaw with.
     * @param pitchOverride The pitch to override the victim player's pitch
     *        with.
     */
    private void sendVictimToLocation(ICommandSender sender,
        EntityPlayerMP victim, double xPos, double yPos, double zPos,
        int dimension, Float yawOverride, Float pitchOverride) {
        if (victim.dimension != dimension) {
            MinecraftServer.getServer().getConfigurationManager()
                .transferPlayerToDimension(victim, dimension);
        }
        float yaw = victim.rotationYaw;
        float pitch = victim.rotationPitch;

        if (yawOverride != null) {
            yaw = yawOverride;
        }
        if (pitchOverride != null) {
            pitch = pitchOverride;
        }
        victim.playerNetServerHandler.setPlayerLocation(xPos, yPos, zPos, yaw,
            pitch);
        // -- Notify admin 'console'
        func_152373_a(sender, this,
            "gtnh_customizer.commands.teleport_cross_dimension.info.teleported",
            new Object[] {
                victim.getDisplayName(), (int) xPos, (int) yPos, (int) zPos,
                yaw, pitch,
                DimensionManager.getProvider(dimension).getDimensionName(),
                dimension
            });
    }
}
