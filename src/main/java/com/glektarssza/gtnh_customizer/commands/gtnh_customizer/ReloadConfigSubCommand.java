package com.glektarssza.gtnh_customizer.commands.gtnh_customizer;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;

import com.glektarssza.gtnh_customizer.Tags;
import com.glektarssza.gtnh_customizer.api.command.ISubCommand;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.utils.TypeHelpers;

public class ReloadConfigSubCommand implements ISubCommand {
    /**
     * The logger for this class.
     */
    @Nonnull
    private static final Logger LOGGER = TypeHelpers
        .castToNonNull(LogManager.getLogger(String.format("%s:%s", Tags.MOD_ID,
            MethodHandles.lookup().lookupClass().getSimpleName())));

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
    public List<String> addTabCompletionOptions(ICommandSender sender,
        String[] args) {
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
        try {
            Config.sync();
        } catch (Throwable t) {
            LOGGER.error("Failed to synchronize mod configuration!");
            LOGGER.error(t);
        }
        throw new CommandException(
            "gtnh_customizer.commands.gtnh_customizer.error.reload_config.failed",
            new Object[0]);
    }
}
