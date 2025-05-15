package com.glektarssza.gtnh_customizer.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;

import net.minecraftforge.common.DimensionManager;

import com.glektarssza.gtnh_customizer.utils.CommandUtils;
import com.glektarssza.gtnh_customizer.utils.ImmutableTuple;

/**
 * A command for listing dimensions and their IDs.
 */
public class ListDimensionsCommand extends CommandBase {
    /**
     * Get the command name.
     *
     * @return The command name.
     */
    @Override
    public String getCommandName() {
        return "list-dimensions";
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
        return "gtnh_customizer.commands.list_dimensions.usage";
    }

    /**
     * Get the required permission level to run the command.
     *
     * @return The required permission level to run the command.
     */
    @Override
    public int getRequiredPermissionLevel() {
        return CommandUtils.PERMISSION_NONE;
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
        return Collections.emptyList();
    }

    /**
     * Process the command invocation.
     *
     * @param sender The thing sending the command.
     * @param args The command arguments.
     */
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        Integer limit = null;
        List<String> filters = new ArrayList<String>();
        for (int i = 0; i < args.length; i++) {
            try {
                limit = Integer.parseInt(args[0], 10);
            } catch (Throwable t) {
                filters.add(args[i]);
            }
        }
        if (limit == null) {
            limit = 512;
        }
        if (limit < 0) {
            throw new CommandException(
                "gtnh_customizer.commands.list_dimensions.error.invalid_limit",
                new Object[] {
                    limit
                });
        }

        List<ImmutableTuple<Integer, String>> dimensions = CommandUtils
            .getAllDimensionIDsIterable()
            .parallelStream()
            .map((id) -> new ImmutableTuple<Integer, String>(
                id, DimensionManager.getProvider(id).getDimensionName()))
            .filter((tuple) -> filters.stream()
                .anyMatch((filter) -> tuple.getSecond().contains(filter)))
            .limit(limit)
            .collect(Collectors.toList());

        sender.addChatMessage(new ChatComponentTranslation(
            "gtnh_customizer.commands.list_dimensions.info.header",
            new Object[0]));
        for (int i = 0; i < dimensions.size(); i++) {
            sender.addChatMessage(new ChatComponentTranslation(
                "gtnh_customizer.commands.list_dimensions.info.entry",
                new Object[] {
                    dimensions.get(i).getFirst(),
                    dimensions.get(i).getSecond()
                }));
        }
    }
}
