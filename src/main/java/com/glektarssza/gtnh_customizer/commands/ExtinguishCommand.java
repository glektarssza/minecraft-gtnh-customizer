package com.glektarssza.gtnh_customizer.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.utils.CommandUtils;
import com.glektarssza.gtnh_customizer.utils.ExtendedWorld;

/**
 * A command which provides the ability to extinguish blocks which are on fire.
 */
public class ExtinguishCommand extends CommandBase {
    /**
     * Just a quick helper constant that can be fed to
     * {@link World#playAuxSFXAtEntity} to play the {@code random.fizz} sound
     * effect at the given world position.
     */
    public static final int RANDOM_FIZZ_AUX_SFX_COMMAND = 1004;

    /**
     * Get the command name.
     *
     * @return The command name.
     */
    @Override
    public String getCommandName() {
        return "extinguish";
    }

    /**
     * Get a list of aliases the command can be called by.
     *
     * @return A list of aliases the command can be called by.
     */
    @Override
    public List<String> getCommandAliases() {
        return Collections.emptyList();
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
        return new ChatComponentTranslation(
            "gtnh_customizer.commands.extinguish.usage", new Object[] {
                String.format("%d",
                    Minecraft.getMinecraft().gameSettings.renderDistanceChunks
                        * 16)
            }).getFormattedText();
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
        return false;
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
        int index = args.length - 1;
        switch (index) {
            case 0:
                // -- First argument is the radius around the player or the
                // -- starting X coordinate of the cube to affect
                return Arrays.asList("32", "~");
            case 1:
                // -- Second argument is the starting Y coordinate of the cube
                // -- to affect
                return Collections.singletonList("~");
            case 2:
                // -- Third argument is the starting Z coordinate of the cube
                // -- to affect
                return Collections.singletonList("~");
            case 3:
                // -- Fourth argument is the ending X coordinate of the cube
                // -- to affect
                return Collections.singletonList("~");
            case 4:
                // -- Fifth argument is the ending Y coordinate of the cube
                // -- to affect
                return Collections.singletonList("~");
            case 5:
                // -- Sixth argument is the ending Z coordinate of the cube
                // -- to affect
                return Collections.singletonList("~");
        }
        return null;
    }

    /**
     * Process the command invocation.
     *
     * @param sender The thing sending the command.
     * @param args The command arguments.
     */
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        Integer radius = null;
        Long startingBlockXPos = null;
        Long startingBlockYPos = null;
        Long startingBlockZPos = null;
        Long endingBlockXPos = null;
        Long endingBlockYPos = null;
        Long endingBlockZPos = null;
        if (args.length <= 1) {
            if (args.length == 0) {
                radius = ((ExtendedWorld) sender.getEntityWorld())
                    .getRenderDistance() * 16;
            } else {
                try {
                    radius = Integer.parseUnsignedInt(args[0], 10);
                } catch (NumberFormatException ex) {
                    throw new WrongUsageException(
                        "gtnh_customizer.commands.extinguish.error.invalid_radius",
                        new Object[] {
                            String.format("%d", args[0])
                        });
                }
            }
            ChunkCoordinates coords = sender.getPlayerCoordinates();
            startingBlockXPos = (long) (coords.posX - radius);
            startingBlockYPos = (long) (coords.posY - radius);
            startingBlockZPos = (long) (coords.posZ - radius);
            endingBlockXPos = (long) (coords.posX + radius);
            endingBlockYPos = (long) (coords.posY + radius);
            endingBlockZPos = (long) (coords.posZ + radius);
        } else if (args.length == 6) {
            ChunkCoordinates coords = sender.getPlayerCoordinates();
            startingBlockXPos = CommandUtils.parseBlockRelativeLongArgument(
                sender, args[0], coords.posX);
            startingBlockYPos = CommandUtils.parseBlockRelativeLongArgument(
                sender, args[1], coords.posY);
            startingBlockZPos = CommandUtils.parseBlockRelativeLongArgument(
                sender, args[2], coords.posZ);
            endingBlockXPos = CommandUtils.parseBlockRelativeLongArgument(
                sender, args[3], coords.posX);
            endingBlockYPos = CommandUtils.parseBlockRelativeLongArgument(
                sender, args[4], coords.posY);
            endingBlockZPos = CommandUtils.parseBlockRelativeLongArgument(
                sender, args[5], coords.posZ);
            // -- Ensure starting position is the lower of the two positions
            if (endingBlockXPos < startingBlockXPos) {
                long temp = startingBlockXPos;
                startingBlockXPos = endingBlockXPos;
                endingBlockXPos = temp;
            }
            if (endingBlockYPos < startingBlockYPos) {
                long temp = startingBlockYPos;
                startingBlockYPos = endingBlockYPos;
                endingBlockYPos = temp;
            }
            if (endingBlockZPos < startingBlockZPos) {
                long temp = startingBlockZPos;
                startingBlockZPos = endingBlockZPos;
                endingBlockZPos = temp;
            }
        } else if (args.length < 6) {
            throw new WrongUsageException(
                "gtnh_customizer.commands.extinguish.error.not_enough_arguments",
                new Object[0]);
        } else {
            throw new WrongUsageException(
                "gtnh_customizer.commands.extinguish.error.wrong_usage",
                new Object[0]);
        }
        long volume = (endingBlockXPos - startingBlockXPos)
            * (endingBlockYPos - startingBlockYPos)
            * (endingBlockZPos - startingBlockZPos);
        if (volume > Config.getExtinguishCommandMaxVolume()) {
            throw new WrongUsageException(
                "gtnh_customizer.commands.extinguish.error.volume_too_large",
                new Object[] {
                    String.format("%d", volume), String.format("%d",
                        Config.getExtinguishCommandMaxVolume())
                });
        }
        World world = sender.getEntityWorld();
        for (long x = startingBlockXPos; x < endingBlockXPos; x += 1) {
            for (long y = startingBlockYPos; y < endingBlockYPos; y += 1) {
                for (long z = startingBlockZPos; z < endingBlockZPos; z += 1) {
                    Block block = world.getBlock((int) x, (int) y, (int) z);
                    if (block == Blocks.fire) {
                        world.playAuxSFXAtEntity(null,
                            RANDOM_FIZZ_AUX_SFX_COMMAND, (int) x, (int) y,
                            (int) z,
                            // -- This is some kind of "extra data" parameter
                            // -- whose meaning is unique to the exact command
                            // -- being executed. In this case it does...
                            // -- Nothing! :<
                            0);
                        world.setBlockToAir((int) x, (int) y, (int) z);
                    }
                }
            }
        }
        sender.addChatMessage(new ChatComponentTranslation(
            "gtnh_customizer.commands.extinguish.info.success")
            .setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)
                .setItalic(true)));
        if (radius == null) {
            func_152373_a(sender, this,
                "gtnh_customizer.commands.extinguish.info.admin_notify.zone",
                new Object[] {
                    sender.getCommandSenderName(),
                    String.format("%d", startingBlockXPos),
                    String.format("%d", startingBlockYPos),
                    String.format("%d", startingBlockZPos),
                    String.format("%d", endingBlockXPos),
                    String.format("%d", endingBlockYPos),
                    String.format("%d", endingBlockZPos)
                });
        } else {
            func_152373_a(sender, this,
                "gtnh_customizer.commands.extinguish.info.admin_notify.radius",
                new Object[] {
                    sender.getCommandSenderName(),
                    String.format("%d", radius)
                });
        }
    }
}
