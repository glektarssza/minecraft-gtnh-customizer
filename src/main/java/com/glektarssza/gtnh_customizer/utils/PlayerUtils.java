package com.glektarssza.gtnh_customizer.utils;

import java.util.Arrays;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraftforge.common.util.Constants.NBT;

import com.glektarssza.gtnh_customizer.Tags;
import com.glektarssza.gtnh_customizer.config.Config;

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
        return Arrays.asList(Config.getGloballyImmunePlayers())
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
}
