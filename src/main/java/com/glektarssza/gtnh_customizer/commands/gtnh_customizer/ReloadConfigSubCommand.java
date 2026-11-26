package com.glektarssza.gtnh_customizer.commands.gtnh_customizer;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;

import com.glektarssza.gtnh_customizer.GTNHCustomizer;
import com.glektarssza.gtnh_customizer.api.v2.annotation.NotNull;
import com.glektarssza.gtnh_customizer.api.v2.command.ISubCommand;
import com.glektarssza.gtnh_customizer.config.Config;

/**
 * A sub-command to reload the mod configuration.
 */
public class ReloadConfigSubCommand implements ISubCommand {
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
            GTNHCustomizer.getLogger()
                .error("Failed to synchronize mod configuration!");
            GTNHCustomizer.getLogger().error(t);
        }
        throw new CommandException(
            "gtnh_customizer.commands.gtnh_customizer.error.reload_config.failed",
            new Object[0]);
    }
}
