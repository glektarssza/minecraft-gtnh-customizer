package com.glektarssza.gtnh_customizer.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;

import cpw.mods.fml.common.Loader;

import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.utils.CommandUtils;

import xonin.backhand.api.core.BackhandUtils;

public class RepairCommand extends CommandBase {
    /**
     * An enumeration of valid repair targets.
     */
    public enum ItemTarget {
        /**
         * The item in the victim's main hand.
         */
        Hand("hand"),

        /**
         * The item in the victim's off hand.
         */
        Offhand("offhand"),

        /**
         * The item in the victim's head armor slot.
         */
        Helmet("helmet"),

        /**
         * The item in the victim's chest armor slot.
         */
        Chestplate("chestplate"),

        /**
         * The item in the victim's legs armor slot.
         */
        Leggings("leggings"),

        /**
         * The item in the victim's boots armor slot.
         */
        Boots("boots"),

        /**
         * All the victim's armor.
         */
        Armor("armor"),

        /**
         * All the repairable items in victim's hotbar.
         */
        Hotbar("hotbar"),

        /**
         * All the repairable items in victim's inventory.
         */
        Inventory("inventory"),

        /**
         * All the repairable items in victim's inventory, hotbar, hand, off
         * hand, and armor slots.
         */
        Everything("everything"),

        /**
         * All the repairable items in given container.
         */
        Container("container");

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
        private ItemTarget(String commandValue) {
            this.commandValue = commandValue;
        }

        /**
         * Get the {@link ItemTarget} that corresponds to the given
         * {@link #commandValue}.
         *
         * @param argument The {@link #commandValue} to search for.
         *
         * @return The corresponding {@link ItemTarget}.
         */
        public static ItemTarget getFromCommandArgument(String argument)
            throws NoSuchElementException {
            return Arrays.stream(ItemTarget.values())
                .filter((item) -> item.commandValue.equalsIgnoreCase(argument))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format(
                    "No ItemTarget with command value of '%s'", argument)));
        }

        /**
         * Try to get the {@link ItemTarget} that corresponds to the given
         * {@link #commandValue}.
         *
         * @param argument The {@link #commandValue} to search for.
         *
         * @return The corresponding {@link ItemTarget}, if available;
         *         {@code null} otherwise.
         */
        @Nullable
        public static ItemTarget tryGetFromCommandArgument(String argument) {
            return Arrays.stream(ItemTarget.values())
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
        return "repair";
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
        return "gtnh_customizer.commands.repair.usage";
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
        return index == 0 && !isItemTargetIndex(args, index);
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
    public boolean isItemTargetIndex(String[] args, int index) {
        if (index < 0 || index >= args.length) {
            return false;
        }
        return Arrays.stream(ItemTarget.values())
            .filter((item) -> item.commandValue.equalsIgnoreCase(args[index]))
            .findFirst()
            .isPresent();
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
                        Arrays.stream(ItemTarget.values())
                            // -- Only expose the "offhand" target if the
                            // -- `backhand` mod is available
                            .filter((target) -> target != ItemTarget.Offhand
                                || Loader.isModLoaded("backhand"))
                            .map(ItemTarget::toString)
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
                    Arrays.stream(ItemTarget.values())
                        .map(ItemTarget::toString)
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
        EntityPlayerMP victim = null;
        ItemTarget target = null;
        if (args.length > 1 && isUsernameIndex(args, 0)) {
            victim = getPlayer(sender, args[0]);
            target = ItemTarget.tryGetFromCommandArgument(args[1]);
            if (target == null) {
                throw new WrongUsageException(
                    "gtnh_customizer.commands.repair.error.invalid_item_target",
                    new Object[] {
                        args[1],
                        Arrays.stream(ItemTarget.values())
                            .map(ItemTarget::toString)
                            .map(String::toLowerCase)
                            .collect(Collectors.joining(", "))
                    });
            }
        } else if (args.length > 0) {
            victim = getCommandSenderAsPlayer(sender);
            target = ItemTarget.tryGetFromCommandArgument(args[0]);
            if (target == null) {
                throw new WrongUsageException(
                    "gtnh_customizer.commands.repair.error.invalid_item_target",
                    new Object[] {
                        args[0],
                        Arrays.stream(ItemTarget.values())
                            .map(ItemTarget::toString)
                            .map(String::toLowerCase)
                            .collect(Collectors.joining(", "))
                    });
            }
        } else {
            victim = getCommandSenderAsPlayer(sender);
            target = ItemTarget.Hand;
        }
        if (victim != null && target != null) {
            ArrayList<ItemStack> items = new ArrayList<ItemStack>();
            switch (target) {
                case Hand:
                    if (victim.getHeldItem() != null) {
                        items.add(victim.getHeldItem());
                    }
                    break;
                case Offhand:
                    if (!Loader.isModLoaded("backhand")) {
                        throw new WrongUsageException(
                            "gtnh_customizer.commands.repair.error.invalid_item_target.backhand_not_loaded",
                            new Object[0]);
                    }
                    if (BackhandUtils.getOffhandItem(victim) != null) {
                        items.add(BackhandUtils.getOffhandItem(victim));
                    }
                    break;
                case Helmet:
                    if (victim.inventory.armorInventory[3] != null) {
                        items.add(victim.inventory.armorInventory[3]);
                    }
                    break;
                case Chestplate:
                    if (victim.inventory.armorInventory[2] != null) {
                        items.add(victim.inventory.armorInventory[2]);
                    }
                    break;
                case Leggings:
                    if (victim.inventory.armorInventory[1] != null) {
                        items.add(victim.inventory.armorInventory[1]);
                    }
                    break;
                case Boots:
                    if (victim.inventory.armorInventory[0] != null) {
                        items.add(victim.inventory.armorInventory[0]);
                    }
                    break;
                case Armor:
                    items.addAll(Arrays.stream(victim.inventory.armorInventory)
                        .filter((item) -> item != null)
                        .collect(Collectors.toList()));
                    break;
                case Hotbar:
                    items.addAll(Arrays.asList(
                        Arrays.copyOfRange(victim.inventory.mainInventory, 0,
                            InventoryPlayer
                                .getHotbarSize()))
                        .stream().filter((item) -> item != null)
                        .collect(Collectors.toList()));
                    break;
                case Inventory:
                    if (Loader.isModLoaded("backhand")) {
                        int offhandIndex = BackhandUtils.getOffhandSlot(victim);
                        if (offhandIndex < InventoryPlayer.getHotbarSize()) {
                            items.addAll(Arrays.asList(
                                Arrays.copyOfRange(
                                    victim.inventory.mainInventory,
                                    InventoryPlayer
                                        .getHotbarSize(),
                                    victim.inventory.mainInventory.length))
                                .stream().filter((item) -> item != null)
                                .collect(Collectors.toList()));
                        } else if (offhandIndex >= victim.inventory.mainInventory.length
                            - 1) {
                            items.addAll(Arrays.asList(
                                Arrays.copyOfRange(
                                    victim.inventory.mainInventory,
                                    InventoryPlayer
                                        .getHotbarSize(),
                                    offhandIndex))
                                .stream().filter((item) -> item != null)
                                .collect(Collectors.toList()));
                        } else {
                            items.addAll(Arrays.asList(
                                Arrays.copyOfRange(
                                    victim.inventory.mainInventory,
                                    InventoryPlayer
                                        .getHotbarSize(),
                                    offhandIndex))
                                .stream().filter((item) -> item != null)
                                .collect(Collectors.toList()));
                            items.addAll(Arrays.asList(
                                Arrays.copyOfRange(
                                    victim.inventory.mainInventory,
                                    offhandIndex + 1,
                                    victim.inventory.mainInventory.length))
                                .stream().filter((item) -> item != null)
                                .collect(Collectors.toList()));
                        }

                    } else {
                        items.addAll(Arrays.asList(
                            Arrays.copyOfRange(victim.inventory.mainInventory,
                                InventoryPlayer
                                    .getHotbarSize() + 1,
                                victim.inventory.mainInventory.length))
                            .stream().filter((item) -> item != null)
                            .collect(Collectors.toList()));
                    }
                    break;
                case Everything:
                    items.addAll(Arrays.stream(victim.inventory.mainInventory)
                        .filter((item) -> item != null)
                        .collect(Collectors.toList()));
                    items.addAll(Arrays.stream(victim.inventory.armorInventory)
                        .filter((item) -> item != null)
                        .collect(Collectors.toList()));
                    break;
                case Container: {
                    Integer targetBlockPosX = null;
                    Integer targetBlockPosY = null;
                    Integer targetBlockPosZ = null;
                    TileEntity tileEntity = null;
                    IInventory tileInventory = null;
                    if (args.length >= 5 && isUsernameIndex(args, 0)) {
                        // -- Arguments contain a victim player and position
                        // -- data
                        try {
                            targetBlockPosX = CommandUtils
                                .parseBlockRelativeIntegerArgument(
                                    sender, args[2], (int) victim.posX);
                            targetBlockPosY = CommandUtils
                                .parseBlockRelativeIntegerArgument(
                                    sender, args[3], (int) victim.posY);
                            targetBlockPosZ = CommandUtils
                                .parseBlockRelativeIntegerArgument(
                                    sender, args[4], (int) victim.posZ);
                        } catch (Throwable t) {
                            throw new CommandException(
                                "gtnh_customizer.commands.repair.error.bad_container_position",
                                new Object[] {
                                    String.format("%d", args[2]),
                                    String.format("%d", args[3]),
                                    String.format("%d", args[4])
                                });
                        }
                    } else if (args.length >= 4 && isItemTargetIndex(args, 0)) {
                        // -- Arguments contain no victim player and position
                        // -- data
                        try {
                            targetBlockPosX = CommandUtils
                                .parseBlockRelativeIntegerArgument(
                                    sender, args[1], (int) victim.posX);
                            targetBlockPosY = CommandUtils
                                .parseBlockRelativeIntegerArgument(
                                    sender, args[2], (int) victim.posY);
                            targetBlockPosZ = CommandUtils
                                .parseBlockRelativeIntegerArgument(
                                    sender, args[3], (int) victim.posZ);
                        } catch (Throwable t) {
                            throw new CommandException(
                                "gtnh_customizer.commands.repair.error.bad_container_position",
                                new Object[] {
                                    String.format("%d", args[1]),
                                    String.format("%d", args[2]),
                                    String.format("%d", args[3])
                                });
                        }
                    } else {
                        // -- Arguments contain no victim player and no position
                        // -- data, fall back to container being looked at
                        double reach = victim.theItemInWorldManager
                            .getBlockReachDistance();
                        Vec3 start = Vec3.createVectorHelper(victim.posX,
                            victim.posY + victim.getEyeHeight(), victim.posZ);
                        Vec3 look = victim.getLookVec();
                        Vec3 end = start.addVector(look.xCoord * reach,
                            look.yCoord * reach, look.zCoord * reach);
                        MovingObjectPosition pos = victim.worldObj
                            .func_147447_a(start, end,
                                !Config.getRepairCommandIgnoresLiquids(),
                                Config.getRepairCommandIgnoresLiquids(), false);
                        if (pos == null
                            || pos.typeOfHit != MovingObjectType.BLOCK) {
                            throw new WrongUsageException(
                                "gtnh_customizer.commands.repair.error.no_container",
                                new Object[0]);
                        }
                        targetBlockPosX = pos.blockX;
                        targetBlockPosY = pos.blockY;
                        targetBlockPosZ = pos.blockZ;
                    }
                    tileEntity = victim.worldObj.getTileEntity(targetBlockPosX,
                        targetBlockPosY, targetBlockPosZ);
                    if (tileEntity instanceof IInventory) {
                        tileInventory = (IInventory) tileEntity;
                    }
                    if (!(tileInventory instanceof IInventory)) {
                        throw new WrongUsageException(
                            "gtnh_customizer.commands.repair.error.no_container",
                            new Object[0]);
                    }
                    int slotCount = tileInventory.getSizeInventory();
                    for (int i = 0; i < slotCount; i += 1) {
                        ItemStack itemStack = tileInventory
                            .getStackInSlot(i);
                        if (itemStack != null) {
                            items.add(itemStack);
                        }
                    }
                }
                    break;
            }
            items.parallelStream()
                .forEach((itemStack) -> {
                    // -- Repair the items in this stack only if they can be
                    // -- damaged and are damaged in the first place
                    if (itemStack.getItem().isDamageable()
                        && itemStack.getItemDamage() > 0) {
                        itemStack.setItemDamage(0);
                        // -- Also reset the anvil repair cost at the same time
                        // -- if it is present
                        if (itemStack.hasTagCompound()
                            && itemStack.stackTagCompound
                                .hasKey("RepairCost")) {
                            itemStack.stackTagCompound.removeTag("RepairCost");
                        }
                    }
                });
            sender.addChatMessage(new ChatComponentTranslation(
                "gtnh_customizer.commands.repair.info.success")
                .setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)
                    .setItalic(true)));
            func_152373_a(sender, this,
                "gtnh_customizer.commands.repair.info.admin_notify",
                new Object[] {
                    victim.getDisplayName(),
                    victim.getUniqueID().toString(),
                    String.format("%d",
                        items.stream()
                            .mapToInt((itemStack) -> itemStack.stackSize)
                            .sum())
                });
            return;
        }
        throw new WrongUsageException(
            "gtnh_customizer.commands.repair.error.wrong_usage",
            new Object[0]);
    }
}
