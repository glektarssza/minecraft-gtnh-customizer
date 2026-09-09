package com.glektarssza.gtnh_customizer.commands.gtnh_customizer;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;

import com.glektarssza.gtnh_customizer.Tags;
import com.glektarssza.gtnh_customizer.api.command.ISubCommand;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.utils.CommandUtils;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

public class GloballyImmunePlayersSubCommand implements ISubCommand {
    /**
     * An enumeration of valid sub-commands.
     */
    public enum SubCommand {
        /**
         * Add a player to the list.
         */
        Add("add"),

        /**
         * Remove a player from the list.
         */
        Remove("remove"),

        /**
         * Clear the list.
         */
        Clear("clear");

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
     * The logger for this class.
     */
    @Nonnull
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
            MethodHandles.lookup().lookupClass().getSimpleName())));

    /**
     * The {@link CommandBase} that this instance belongs to.
     */
    @Nonnull
    private CommandBase commandBase;

    /**
     * Create a new instance.
     *
     * @param base The {@link CommandBase} that the new instance belongs to.
     */
    public GloballyImmunePlayersSubCommand(@Nonnull CommandBase base) {
        this.commandBase = base;
    }

    @Nonnull
    @Override
    public CommandBase getCommandBase() {
        return this.commandBase;
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
            "gtnh_customizer.commands.gtnh_customizer.globally_immune_players.usage")
            .getUnformattedTextForChat();
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
    public boolean isUsernameIndex(String[] args, int index) {
        return index == 1;
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
        final int index = args.length - 1;
        final String arg = args[index];
        switch (index) {
            case 0:
                // -- First argument is action to take
                return Arrays.stream(SubCommand.values())
                    .map((subCommand) -> subCommand.commandValue)
                    .filter((subCommand) -> args[0] == null || args[0].isEmpty()
                        || subCommand.startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
            case 1:
                // -- Second argument is a player to act on
                return CommandUtils.getTruncatedPlayerUsernameIterable(512)
                    .stream().filter((playerName) -> arg == null
                        || arg.isEmpty() || playerName.startsWith(arg))
                    .collect(Collectors.toList());
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
        EntityPlayerMP victim;
        SubCommand action;
        if (args.length > 0) {
            action = SubCommand.tryGetFromCommandArgument(args[0]);
            if (action == null) {
                throw new CommandException(
                    "gtnh_customizer.commands.gtnh_customizer.error.unsupported_sub_command",
                    new Object[] {
                        args[0]
                    });
            }
        } else {
            action = null;
        }
        if (args.length > 1) {
            victim = CommandBase.getPlayer(sender, args[1]);
        } else {
            victim = CommandBase.getCommandSenderAsPlayer(sender);
        }
        if (action != null && victim != null) {
            UUID playerId = victim.getGameProfile().getId();
            switch (action) {
                case Add:
                    Config.addImmunePlayer(
                        playerId == null ? victim.getGameProfile().getName()
                            : playerId.toString().toLowerCase());
                    try {
                        Config.save();
                    } catch (Throwable t) {
                        LOGGER
                            .warn("Failed to save updated mod configuration!");
                        LOGGER.warn(t);
                    }
                    CommandBase.func_152373_a(sender, this.getCommandBase(),
                        "gtnh_customizer.commands.gtnh_customizer.info.success.globally_immune_players.added",
                        new Object[] {
                            victim.getDisplayName()
                        });
                    return;
                case Remove:
                    Config.removeImmunePlayer(
                        playerId == null ? victim.getGameProfile().getName()
                            : playerId.toString().toLowerCase());
                    try {
                        Config.save();
                    } catch (Throwable t) {
                        LOGGER
                            .warn("Failed to save updated mod configuration!");
                        LOGGER.warn(t);
                    }
                    CommandBase.func_152373_a(sender, this.getCommandBase(),
                        "gtnh_customizer.commands.gtnh_customizer.info.success.globally_immune_players.removed",
                        new Object[] {
                            victim.getDisplayName()
                        });
                    return;
                case Clear:
                    Config.clearImmunePlayers();
                    try {
                        Config.save();
                    } catch (Throwable t) {
                        LOGGER
                            .warn("Failed to save updated mod configuration!");
                        LOGGER.warn(t);
                    }
                    CommandBase.func_152373_a(sender, this.getCommandBase(),
                        "gtnh_customizer.commands.gtnh_customizer.info.success.globally_immune_players.cleared",
                        new Object[] {
                            victim.getDisplayName()
                        });
                    return;
                default:
                    throw new CommandException(
                        "gtnh_customizer.commands.gtnh_customizer.error.invalid_sub_command",
                        new Object[] {
                            action.commandValue
                        });
            }
        }
        throw new CommandException(
            "gtnh_customizer.commands.gtnh_customizer.error.not_enough_arguments",
            new Object[0]);
    }
}
