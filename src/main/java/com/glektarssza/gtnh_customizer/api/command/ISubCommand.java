package com.glektarssza.gtnh_customizer.api.command;

import java.util.List;

import net.minecraft.command.ICommandSender;

public interface ISubCommand {
    /**
     * Get the usage of the command.
     *
     * @param sender The thing sending the command.
     *
     * @return The usage of the command.
     */
    public String getCommandUsage(ICommandSender sender);

    /**
     * Get a list of possible completions for the current state of the command.
     *
     * @param sender The thing sending the command.
     * @param args The current arguments being completed.
     *
     * @return A list of possible completions for the current state of the
     *         command.
     */
    public List<String> addTabCompletionOptions(ICommandSender sender,
        String[] args);

    /**
     * Process the command invocation.
     *
     * @param sender The thing sending the command.
     * @param args The command arguments.
     */
    public void processCommand(ICommandSender sender, String[] args);
}
