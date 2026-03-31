package com.glektarssza.gtnh_customizer.utils;

import java.util.Arrays;
import java.util.Optional;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.glektarssza.gtnh_customizer.api.exceptions.args.ArgumentOutOfRangeException;

/**
 * A collection of utilities for working with potions/potion effects.
 */
public class PotionUtils {
    /**
     * A list of all potions in the vanilla game.
     *
     * Any other potions are considered modded.
     */
    public static final Potion[] VANILLA_POTIONS = {
        Potion.moveSpeed,
        Potion.moveSlowdown,
        Potion.digSpeed,
        Potion.digSlowdown,
        Potion.damageBoost,
        Potion.heal,
        Potion.harm,
        Potion.jump,
        Potion.confusion,
        Potion.regeneration,
        Potion.resistance,
        Potion.fireResistance,
        Potion.waterBreathing,
        Potion.invisibility,
        Potion.blindness,
        Potion.nightVision,
        Potion.hunger,
        Potion.weakness,
        Potion.poison,
        Potion.wither
    };

    /**
     * Get a potion by its ID.
     *
     * @param id The ID of the potion to get.
     *
     * @return The potion.
     *
     * @throws ArgumentOutOfRangeException Thrown if the ID is outside the
     *         allowed range.
     */
    public static Potion getByID(int id) throws ArgumentOutOfRangeException {
        if (id < 0 || id >= Potion.potionTypes.length) {
            throw new ArgumentOutOfRangeException("id", id, 0,
                Potion.potionTypes.length,
                "Potion ID was outside the allowed range.");
        }
        return Potion.potionTypes[id];
    }

    /**
     * Try to get a potion by its ID.
     *
     * @param id The ID of the potion to get.
     *
     * @return The potion, if available.
     *
     * @throws ArgumentOutOfRangeException Thrown if the ID is outside the
     *         allowed range.
     */
    public static Optional<Potion> tryGetByID(int id) {
        if (id < 0 || id >= Potion.potionTypes.length) {
            return Optional.empty();
        }
        return Optional.of(Potion.potionTypes[id]);
    }

    /**
     * Check if a potion is a vanilla potion.
     *
     * @param potion The potion to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false}
     *         otherwise.
     */
    public static boolean isVanilla(Potion potion) {
        return Arrays.binarySearch(VANILLA_POTIONS, potion) >= 0;
    }

    /**
     * Check if a potion is a vanilla potion by its ID.
     *
     * @param id The potion ID to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false}
     *         otherwise.
     *
     * @throws ArgumentOutOfRangeException Thrown if the ID is outside the
     *         allowed range.
     */
    public static boolean isVanillaByID(int id)
        throws ArgumentOutOfRangeException {
        return isVanilla(getByID(id));
    }

    /**
     * Check if a potion is a vanilla potion by its potion effect.
     *
     * @param potionEffect The potion effect to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false}
     *         otherwise.
     */
    public static boolean isVanillaByEffect(PotionEffect potionEffect) {
        return isVanillaByID(potionEffect.getPotionID());
    }

    /**
     * Try to check if a potion is a vanilla potion by its ID.
     *
     * @param id The potion ID to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     */
    public static Optional<Boolean> tryIsVanillaByID(int id) {
        return tryGetByID(id).map(PotionUtils::isVanilla);
    }

    /**
     * Try to check if a potion is a vanilla potion by its potion effect.
     *
     * @param potionEffect The potion effect to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     */
    public static Optional<Boolean> tryIsVanillaByEffect(
        PotionEffect potionEffect) {
        return tryGetByID(potionEffect.getPotionID())
            .map(PotionUtils::isVanilla);
    }

    /**
     * Check if a potion is a modded potion.
     *
     * @param potion The potion to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false}
     *         otherwise.
     */
    public static boolean isModded(Potion potion) {
        return Arrays.binarySearch(VANILLA_POTIONS, potion) < 0;
    }

    /**
     * Check if a potion is a vanilla potion by its ID.
     *
     * @param id The potion ID to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false}
     *         otherwise.
     *
     * @throws ArgumentOutOfRangeException Thrown if the ID is outside the
     *         allowed range.
     */
    public static boolean isModdedByID(int id)
        throws ArgumentOutOfRangeException {
        return !isVanillaByID(id);
    }

    /**
     * Try to check if a potion is a vanilla potion by its ID.
     *
     * @param id The potion ID to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     *
     * @throws ArgumentOutOfRangeException Thrown if the ID is outside the
     *         allowed range.
     */
    public static Optional<Boolean> tryIsModdedByID(int id) {
        return tryGetByID(id).map(PotionUtils::isModded);
    }

    /**
     * Check if a potion is a vanilla potion by its potion effect.
     *
     * @param potionEffect The potion effect to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false}
     *         otherwise.
     */
    public static boolean isModdedByEffect(PotionEffect potionEffect) {
        return isModdedByID(potionEffect.getPotionID());
    }

    /**
     * Try to check if a potion is a modded potion by its potion effect.
     *
     * @param id The potion effect to check.
     *
     * @return {@code true} if the potion is a modded potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     */
    public static Optional<Boolean> tryIsModdedByEffect(
        PotionEffect potionEffect) {
        return tryGetByID(potionEffect.getPotionID())
            .map(PotionUtils::isModded);
    }

    /**
     * Check if a potion is a negative potion.
     *
     * @param potion The potion to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false}
     *         otherwise.
     */
    public static boolean isNegative(Potion potion) {
        return potion.isBadEffect();
    }

    /**
     * Check if a potion is a negative potion by its ID.
     *
     * @param id The potion ID to check.
     *
     * @return {@code true} if the potion is a negative potion, {@code false}
     *         otherwise.
     *
     * @throws ArgumentOutOfRangeException Thrown if the ID is outside the
     *         allowed range.
     */
    public static boolean isNegativeByID(int id)
        throws ArgumentOutOfRangeException {
        return isNegative(getByID(id));
    }

    /**
     * Try to check if a potion is a negative potion by its ID.
     *
     * @param id The potion ID to check.
     *
     * @return {@code true} if the potion is a negative potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     *
     * @throws ArgumentOutOfRangeException Thrown if the ID is outside the
     *         allowed range.
     */
    public static Optional<Boolean> tryIsNegativeByID(int id) {
        return tryGetByID(id).map(PotionUtils::isNegative);
    }

    /**
     * Check if a potion is a negative potion by its potion effect.
     *
     * @param potionEffect The potion effect to check.
     *
     * @return {@code true} if the potion is a negative potion, {@code false}
     *         otherwise.
     */
    public static boolean isNegativeByEffect(PotionEffect potionEffect) {
        return isNegativeByID(potionEffect.getPotionID());
    }

    /**
     * Try to check if a potion is a negative potion by its potion effect.
     *
     * @param id The potion effect to check.
     *
     * @return {@code true} if the potion is a negative potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     */
    public static Optional<Boolean> tryIsNegativeByEffect(
        PotionEffect potionEffect) {
        return tryGetByID(potionEffect.getPotionID())
            .map(PotionUtils::isNegative);
    }

    /**
     * Check if a potion is a positive potion.
     *
     * @param potion The potion to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false}
     *         otherwise.
     */
    public static boolean isPositive(Potion potion) {
        return !potion.isBadEffect();
    }

    /**
     * Check if a potion is a positive potion by its ID.
     *
     * @param id The potion ID to check.
     *
     * @return {@code true} if the potion is a positive potion, {@code false}
     *         otherwise.
     *
     * @throws ArgumentOutOfRangeException Thrown if the ID is outside the
     *         allowed range.
     */
    public static boolean isPositiveByID(int id)
        throws ArgumentOutOfRangeException {
        return isPositive(getByID(id));
    }

    /**
     * Try to check if a potion is a positive potion by its ID.
     *
     * @param id The potion ID to check.
     *
     * @return {@code true} if the potion is a positive potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     *
     * @throws ArgumentOutOfRangeException Thrown if the ID is outside the
     *         allowed range.
     */
    public static Optional<Boolean> tryIsPositiveByID(int id) {
        return tryGetByID(id).map(PotionUtils::isPositive);
    }

    /**
     * Check if a potion is a positive potion by its potion effect.
     *
     * @param potionEffect The potion effect to check.
     *
     * @return {@code true} if the potion is a positive potion, {@code false}
     *         otherwise.
     */
    public static boolean isPositiveByEffect(PotionEffect potionEffect) {
        return isPositiveByID(potionEffect.getPotionID());
    }

    /**
     * Try to check if a potion is a positive potion by its potion effect.
     *
     * @param id The potion effect to check.
     *
     * @return {@code true} if the potion is a positive potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     */
    public static Optional<Boolean> tryIsPositiveByEffect(
        PotionEffect potionEffect) {
        return tryGetByID(potionEffect.getPotionID())
            .map(PotionUtils::isPositive);
    }
}
