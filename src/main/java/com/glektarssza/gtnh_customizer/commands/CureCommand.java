package com.glektarssza.gtnh_customizer.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

import com.glektarssza.gtnh_customizer.utils.CommandUtils;
import com.glektarssza.gtnh_customizer.utils.PotionUtils;

/**
 * A command which provides the ability to cure various status effects from the
 * invoking or target player.
 */
public class CureCommand extends CommandBase {
    /**
     * An enumeration of valid status effect groupings.
     */
    public enum StatusEffectGroup {
        /**
         * All negative status effects.
         */
        Negative("negative"),

        /**
         * All positive status effects.
         */
        Positive("positive"),

        /**
         * All modded status effects.
         */
        Modded("modded"),

        /**
         * All vanilla status effects.
         */
        Vanilla("vanilla"),

        /**
         * All status effects.
         */
        All("all");

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
        private StatusEffectGroup(String commandValue) {
            this.commandValue = commandValue;
        }

        /**
         * Get the {@link StatusEffectGroup} that corresponds to the given
         * {@link #commandValue}.
         *
         * @param argument The {@link #commandValue} to search for.
         *
         * @return The corresponding {@link StatusEffectGroup}.
         */
        public static StatusEffectGroup getFromCommandArgument(String argument)
            throws NoSuchElementException {
            return Arrays.stream(StatusEffectGroup.values())
                .filter((item) -> item.commandValue.equalsIgnoreCase(argument))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format(
                    "No StatusEffectGroup with command value of '%s'",
                    argument)));
        }

        /**
         * Try to get the {@link StatusEffectGroup} that corresponds to the
         * given {@link #commandValue}.
         *
         * @param argument The {@link #commandValue} to search for.
         *
         * @return The corresponding {@link StatusEffectGroup}, if available;
         *         {@code null} otherwise.
         */
        @Nullable
        public static StatusEffectGroup tryGetFromCommandArgument(
            String argument) {
            return Arrays.stream(StatusEffectGroup.values())
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
        return "cure";
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
            "gtnh_customizer.commands.cure.usage").getUnformattedTextForChat();
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
        return index == 0 && !isEffectTypeIndex(args, index);
    }

    /**
     * Check if the given index in the given list of arguments is meant to be an
     * item target.
     *
     * @param args The arguments being passed to the command.
     * @param index The index being checked.
     *
     * @return {@code true} if the index should be for an item target;
     *         {@code false} otherwise.
     */
    public boolean isEffectTypeIndex(String[] args, int index) {
        if (index < 0 || index >= args.length) {
            return false;
        }
        return Arrays.stream(StatusEffectGroup.values())
            .filter((item) -> item.commandValue.equalsIgnoreCase(args[index]))
            .findFirst().isPresent();
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
                // -- First argument is one of victim or item target
                try {
                    victim = getPlayer(sender, args[0]);
                } catch (Throwable t) {
                    // -- Does nothing
                }
                result = CommandUtils
                    .getTruncatedPlayerUsernameIterable(512);
                if (victim != null) {
                    result.addAll(0,
                        Arrays.stream(StatusEffectGroup.values())
                            .map(StatusEffectGroup::toString)
                            .map(String::toLowerCase)
                            .collect(Collectors.toList()));
                }
                return result;
            case 1:
                // -- Second argument is an item target
                if (!isUsernameIndex(args, 0)) {
                    return null;
                }
                result = new ArrayList<String>(
                    Arrays.stream(StatusEffectGroup.values())
                        .map(StatusEffectGroup::toString)
                        .map(String::toLowerCase)
                        .collect(Collectors.toList()));
                return result;
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
        final EntityPlayerMP victim;
        final StatusEffectGroup target;
        final boolean victimIsSender;
        if (args.length > 1 && isUsernameIndex(args, 0)) {
            victimIsSender = false;
            victim = getPlayer(sender, args[0]);
            target = StatusEffectGroup.tryGetFromCommandArgument(args[1]);
            if (target == null) {
                throw new WrongUsageException(
                    "gtnh_customizer.commands.repair.error.invalid_item_target",
                    new Object[] {
                        args[1],
                        Arrays.stream(StatusEffectGroup.values())
                            .map(StatusEffectGroup::toString)
                            .map(String::toLowerCase)
                            .collect(Collectors.joining(", "))
                    });
            }
        } else if (args.length > 0) {
            victimIsSender = true;
            victim = getCommandSenderAsPlayer(sender);
            target = StatusEffectGroup.tryGetFromCommandArgument(args[0]);
            if (target == null) {
                throw new WrongUsageException(
                    "gtnh_customizer.commands.repair.error.invalid_item_target",
                    new Object[] {
                        args[0],
                        Arrays.stream(StatusEffectGroup.values())
                            .map(StatusEffectGroup::toString)
                            .map(String::toLowerCase)
                            .collect(Collectors.joining(", "))
                    });
            }
        } else {
            victimIsSender = true;
            victim = getCommandSenderAsPlayer(sender);
            target = StatusEffectGroup.Negative;
        }
        if (victim != null && target != null) {
            Collection<PotionEffect> statusEffects = new ArrayList<PotionEffect>();
            statusEffects.addAll(victim.getActivePotionEffects());
            switch (target) {
                case Negative:
                    statusEffects.removeIf(
                        (effect) -> !Potion.potionTypes[effect.getPotionID()]
                            .isBadEffect());
                    break;
                case Positive:
                    statusEffects.removeIf(
                        (effect) -> Potion.potionTypes[effect.getPotionID()]
                            .isBadEffect());
                    break;
                case Modded:
                    statusEffects.removeIf(PotionUtils::isVanillaByEffect);
                    break;
                case Vanilla:
                    statusEffects.removeIf(PotionUtils::isModdedByEffect);
                    break;
                case All:
                    break;
            }
            statusEffects.removeIf(
                (effect) -> !victim.isPotionActive(effect.getPotionID()));
            statusEffects
                .forEach((effect) -> victim
                    .removePotionEffect(effect.getPotionID()));
            sender.addChatMessage(new ChatComponentTranslation(
                "gtnh_customizer.commands.cure.info.success")
                .setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)
                    .setItalic(true)));
            if (victimIsSender) {
                func_152373_a(sender, this,
                    "gtnh_customizer.commands.cure.info.admin_notify.self",
                    new Object[] {
                        sender.getCommandSenderName(),
                        statusEffects.size(),
                        new ChatComponentTranslation(
                            "gtnh_customizer.commands.cure.args."
                                + target.commandValue)
                            .getUnformattedText()
                    });
            } else {
                func_152373_a(sender, this,
                    "gtnh_customizer.commands.cure.info.admin_notify.other",
                    new Object[] {
                        sender.getCommandSenderName(),
                        statusEffects.size(),
                        new ChatComponentTranslation(
                            "gtnh_customizer.commands.cure.args."
                                + target.commandValue)
                            .getUnformattedText(),
                        victim.getDisplayName()
                    });
            }
            return;
        }
        throw new WrongUsageException(
            "gtnh_customizer.commands.cure.error.wrong_usage",
            new Object[0]);
    }
}
