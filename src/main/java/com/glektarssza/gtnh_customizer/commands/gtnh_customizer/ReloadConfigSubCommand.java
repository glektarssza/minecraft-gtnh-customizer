package com.glektarssza.gtnh_customizer.commands.gtnh_customizer;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;

import com.glektarssza.gtnh_customizer.Tags;
import com.glektarssza.gtnh_customizer.api.v1.command.ISubCommand;
import com.glektarssza.gtnh_customizer.api.v2.annotation.NotNull;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

/**
 * A sub-command to reload the mod configuration.
 */
public class ReloadConfigSubCommand implements ISubCommand {
    /**
     * The logger for this class.
     */
    @NotNull
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
            MethodHandles.lookup().lookupClass().getSimpleName())));

    /**
     * The {@link CommandBase} that this instance belongs to.
     */
    @NotNull
    private final CommandBase commandBase;

    /**
     * Create a new instance.
     *
     * @param base The {@link CommandBase} that the new instance belongs to.
     */
    public ReloadConfigSubCommand(@NotNull final CommandBase base) {
        this.commandBase = base;
    }

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
    public String getCommandUsage(final ICommandSender sender) {
        return new ChatComponentTranslation(
            "gtnh_customizer.commands.gtnh_customizer.reload_config.usage")
            .getUnformattedTextForChat();
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
    public List<String> addTabCompletionOptions(
        final ICommandSender sender, final String[] args) {
        return null;
    }

    /**
     * Process the command invocation.
     *
     * @param sender The thing sending the command.
     * @param args The command arguments.
     */
    @Override
    public void processCommand(final ICommandSender sender,
        final String[] args) {
        try {
            Config.save();
            Config.load();
            CommandBase.func_152373_a(sender, this.getCommandBase(),
                "gtnh_customizer.commands.gtnh_customizer.info.success.reload_config",
                new Object[0]);
            return;
        } catch (final Throwable t) {
            ReloadConfigSubCommand.LOGGER
                .error("Failed to synchronize mod configuration!");
            ReloadConfigSubCommand.LOGGER.error(t);
        }
        throw new CommandException(
            "gtnh_customizer.commands.gtnh_customizer.error.reload_config.failed",
            new Object[0]);
    }
}
