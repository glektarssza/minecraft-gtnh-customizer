package com.glektarssza.gtnh_customizer.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentTranslation;

import com.glektarssza.gtnh_customizer.commands.gtnh_customizer.GloballyImmunePlayersSubCommand;
import com.glektarssza.gtnh_customizer.commands.gtnh_customizer.ReloadConfigSubCommand;
import com.glektarssza.gtnh_customizer.utils.CommandUtils;

/**
 * A command which provides the ability to manage the mod.
 */
public class GTNHCustomizerCommand extends CommandBase {
    /**
     * An enumeration of valid sub-commands.
     */
    public enum SubCommand {
        /**
         * Manipulate the list of globally immune players.
         */
        GloballyImmunePlayers("globally_immune_players"),

        /**
         * Reload the mod configuration.
         */
        ReloadConfig("reload_config");

        /**
         * The value to be used in commands when represent this instance.
         */
        public final String commandValue;

        /**
         * Create a new instance.
         *
         * @param commandValue The value to use in commands when representing
         *        the new instance.
         */
        private SubCommand(String commandValue) {
            this.commandValue = commandValue;
        }

        /**
         * Get the {@link SubCommand} that corresponds to the given
         * {@link #commandValue}.
         *
         * @param argument The {@link #commandValue} to search for.
         *
         * @return The corresponding {@link SubCommand}.
         */
        public static SubCommand getFromCommandArgument(String argument)
            throws NoSuchElementException {
            return Arrays.stream(SubCommand.values())
                .filter((item) -> item.commandValue.equalsIgnoreCase(argument))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format(
                    "No SubCommand with command value of '%s'", argument)));
        }

        /**
         * Try to get the {@link SubCommand} that corresponds to the given
         * {@link #commandValue}.
         *
         * @param argument The {@link #commandValue} to search for.
         *
         * @return The corresponding {@link SubCommand}, if available;
         *         {@code null} otherwise.
         */
        @Nullable
        public static SubCommand tryGetFromCommandArgument(
            String argument) {
            return Arrays.stream(SubCommand.values())
                .filter((item) -> item.commandValue.equalsIgnoreCase(argument))
                .findFirst()
                .orElse(null);
        }
    }

    /**
     * Get the command name.
     *
     * @return The command name.
     */
    @Override
    public String getCommandName() {
        return "gtnh_customizer";
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
            "gtnh_customizer.commands.gtnh_customizer.usage")
            .getUnformattedTextForChat();
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
     * Check if the given index in the given list of arguments is meant to be a
     * subcommand target.
     *
     * @param args The arguments being passed to the command.
     * @param index The index being checked.
     *
     * @return {@code true} if the index should be for a subcommand target;
     *         {@code false} otherwise.
     */
    public boolean isSubCommandTypeIndex(String[] args, int index) {
        return index == 0;
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
                // -- First argument is item target
                return Arrays.stream(SubCommand.values())
                    .map((subCommand) -> subCommand.toString())
                    .map((subCommand) -> subCommand.toLowerCase())
                    .filter((subCommand) -> args[0] == null || args[0].isEmpty()
                        || subCommand.startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
            default:
                final SubCommand subCommand = SubCommand
                    .tryGetFromCommandArgument(args[0]);
                if (subCommand == null) {
                    return null;
                }
                final String[] subCommandArgs = Arrays.copyOfRange(args, 1,
                    args.length);
                switch (subCommand) {
                    case ReloadConfig:
                        return new ReloadConfigSubCommand()
                            .addTabCompletionOptions(sender, subCommandArgs);
                    case GloballyImmunePlayers:
                        return new GloballyImmunePlayersSubCommand()
                            .addTabCompletionOptions(sender, subCommandArgs);
                    default:
                        return null;
                }
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
        if (args.length > 0) {
            final SubCommand subCommand = SubCommand
                .tryGetFromCommandArgument(args[0]);
            if (subCommand == null) {
                throw new WrongUsageException(
                    "gtnh_customizer.commands.gtnh_customizer.error.invalid_sub_command",
                    new Object[] {
                        args[0]
                    });
            }
            final String[] subCommandArgs = Arrays.copyOfRange(args, 1,
                args.length);
            switch (subCommand) {
                case ReloadConfig:
                    new ReloadConfigSubCommand().processCommand(sender,
                        subCommandArgs);
                case GloballyImmunePlayers:
                    new GloballyImmunePlayersSubCommand().processCommand(sender,
                        subCommandArgs);
                default:
                    throw new WrongUsageException(
                        "gtnh_customizer.commands.gtnh_customizer.error.unsupported_sub_command",
                        new Object[] {
                            subCommand.commandValue
                        });
            }
        }
        throw new WrongUsageException(
            "gtnh_customizer.commands.gtnh_customizer.error.wrong_usage",
            new Object[0]);
    }
}
