package com.glektarssza.player_handling_customizer_gtnh.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

import net.minecraftforge.common.util.Constants.NBT;

import com.glektarssza.player_handling_customizer_gtnh.Tags;
import com.glektarssza.player_handling_customizer_gtnh.api.IDamageImmunity;
import com.glektarssza.player_handling_customizer_gtnh.api.IHurtImmunity;
import com.glektarssza.player_handling_customizer_gtnh.api.IImmunity;
import com.glektarssza.player_handling_customizer_gtnh.api.IKnockbackImmunity;
import com.glektarssza.player_handling_customizer_gtnh.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer_gtnh.api.ImmunityType;
import com.glektarssza.player_handling_customizer_gtnh.config.Config;
import com.glektarssza.player_handling_customizer_gtnh.impl.DamageImmunity;
import com.glektarssza.player_handling_customizer_gtnh.impl.HurtImmunity;
import com.glektarssza.player_handling_customizer_gtnh.impl.KnockbackImmunity;
import com.glektarssza.player_handling_customizer_gtnh.impl.TargetingImmunity;

/**
 * A collection of player-related utility methods.
 */
public class PlayerUtils {

    /**
     * Check if a player is globally immune to being targeted.
     *
     * @param player The player to check.
     *
     * @return {@code true} if the player is globally immune; {@code false}
     *         otherwise.
     */
    public static boolean getIsPlayerGloballyImmune(EntityPlayer player) {
        GameProfile playerProfile = player.getGameProfile();
        UUID playerUUID = playerProfile == null ? null
            : EntityPlayer.func_146094_a(playerProfile);
        return Arrays.asList(Config.getImmunePlayers())
            .stream()
            .anyMatch(
                (item) -> playerUUID != null
                    && item.equalsIgnoreCase(playerUUID.toString())
                    || item.equalsIgnoreCase(player.getDisplayName()));
    }

    /**
     * Get the NBT data from a player for the mod.
     *
     * @param player The player to get the NBT data for the mod from.
     *
     * @return The mod data from the player; an empty NBT compound tag if it is
     *         not available.
     */
    public static NBTTagCompound getPlayerModData(EntityPlayer player) {
        NBTTagCompound playerData = player.getEntityData();
        if (!playerData.hasKey(Tags.MOD_ID, NBT.TAG_COMPOUND)) {
            return new NBTTagCompound();
        }
        return (NBTTagCompound) playerData.getTag(Tags.MOD_ID);
    }

    /**
     * Get a list of all immunities attached to a player.
     *
     * @param player The player to get the associated list of immunities from.
     *
     * @return The list of immunities associated with the player.
     */
    public static List<IImmunity<NBTTagCompound>> getPlayerImmunities(
        EntityPlayer player) {
        NBTTagCompound playerData = PlayerUtils.getPlayerModData(player);
        if (!playerData.hasKey("immunities", NBT.TAG_LIST)) {
            return Collections.emptyList();
        }
        NBTTagList nbtImmunityList = playerData.getTagList("immunities",
            NBT.TAG_COMPOUND);
        if (nbtImmunityList.tagCount() <= 0) {
            return Collections.emptyList();
        }
        List<IImmunity<NBTTagCompound>> results = new ArrayList<IImmunity<NBTTagCompound>>();
        for (int i = 0; i < nbtImmunityList.tagCount(); i++) {
            NBTTagCompound nbtBaseItem = nbtImmunityList.getCompoundTagAt(i);
            if (!(nbtBaseItem instanceof NBTTagCompound)) {
                continue;
            }
            NBTTagCompound nbtItem = (NBTTagCompound) nbtBaseItem;
            if (!nbtItem.hasKey("immunityType", NBT.TAG_STRING)) {
                continue;
            }
            ImmunityType type = ImmunityType
                .fromNBTString((NBTTagString) nbtItem.getTag("immunityType"));
            if (type == null) {
                continue;
            }
            IImmunity<NBTTagCompound> item;
            switch (type) {
                case Damage:
                    item = new DamageImmunity();
                    break;
                case Hurt:
                    item = new HurtImmunity();
                    break;
                case Knockback:
                    item = new KnockbackImmunity();
                    break;
                case Targeting:
                    item = new TargetingImmunity();
                    break;
                default:
                    continue;
            }
            item.deserializeNBT(nbtItem);
            results.add(item);
        }
        return results;
    }

    /**
     * Get a list of damage immunities attached to a player.
     *
     * @param player The player to get the associated list of damage immunities
     *        from.
     *
     * @return The list of damage immunities associated with the player.
     */
    public static List<IDamageImmunity> getPlayerDamageImmunities(
        EntityPlayer player) {
        return PlayerUtils.getPlayerImmunities(player)
            .stream()
            .filter((item) -> item.getImmunityType() == ImmunityType.Damage)
            .map((item) -> (IDamageImmunity) item)
            .collect(Collectors.toList());
    }

    /**
     * Get a list of hurt immunities attached to a player.
     *
     * @param player The player to get the associated list of hurt immunities
     *        from.
     *
     * @return The list of hurt immunities associated with the player.
     */
    public static List<IHurtImmunity> getPlayerHurtImmunities(
        EntityPlayer player) {
        return PlayerUtils.getPlayerImmunities(player)
            .stream()
            .filter((item) -> item.getImmunityType() == ImmunityType.Hurt)
            .map((item) -> (IHurtImmunity) item)
            .collect(Collectors.toList());
    }

    /**
     * Get a list of knockback immunities attached to a player.
     *
     * @param player The player to get the associated list of knockback
     *        immunities from.
     *
     * @return The list of knockback immunities associated with the player.
     */
    public static List<IKnockbackImmunity> getPlayerKnockbackImmunities(
        EntityPlayer player) {
        return PlayerUtils.getPlayerImmunities(player)
            .stream()
            .filter((item) -> item.getImmunityType() == ImmunityType.Knockback)
            .map((item) -> (IKnockbackImmunity) item)
            .collect(Collectors.toList());
    }

    /**
     * Get a list of targeting immunities attached to a player.
     *
     * @param player The player to get the associated list of targeting
     *        immunities from.
     *
     * @return The list of targeting immunities associated with the player.
     */
    public static List<ITargetingImmunity> getPlayerTargetingImmunities(
        EntityPlayer player) {
        return PlayerUtils.getPlayerImmunities(player)
            .stream()
            .filter((item) -> item.getImmunityType() == ImmunityType.Targeting)
            .map((item) -> (ITargetingImmunity) item)
            .collect(Collectors.toList());
    }
}
