package com.glektarssza.gtnh_customizer.api.v2.command;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

/**
 * An interface which defines the shape of a sub-command.
 */
public interface ISubCommand {
    /**
     * Get the {@link CommandBase} this instance belongs to.
     *
     * @return Get the {@link CommandBase} this instance belongs to.
     */
    @Nonnull
    CommandBase getCommandBase();

    /**
     * Get the usage of the command.
     *
     * @param sender The thing sending the command.
     *
     * @return The usage of the command.
     */
    String getCommandUsage(ICommandSender sender);

    /**
     * Get a list of possible completions for the current state of the command.
     *
     * @param sender The thing sending the command.
     * @param args The current arguments being completed.
     *
     * @return A list of possible completions for the current state of the
     *         command.
     */
    List<String> addTabCompletionOptions(ICommandSender sender,
        String[] args);

    /**
     * Process the command invocation.
     *
     * @param sender The thing sending the command.
     * @param args The command arguments.
     */
    void processCommand(ICommandSender sender, String[] args);
}
