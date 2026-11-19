package com.glektarssza.gtnh_customizer.utils;

import java.util.Optional;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.glektarssza.gtnh_customizer.api.v2.exception.argument.ArgumentOutOfBoundsException;

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
     * @throws ArgumentOutOfBoundsException Thrown if the ID is outside the
     *         allowed range.
     */
    public static Potion getByID(final int id)
        throws ArgumentOutOfBoundsException {
        if (id < 0 || id >= Potion.potionTypes.length) {
            throw new ArgumentOutOfBoundsException("id", id, 0,
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
     * @throws ArgumentOutOfBoundsException Thrown if the ID is outside the
     *         allowed range.
     */
    public static Optional<Potion> tryGetByID(final int id) {
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
    public static boolean isVanilla(final Potion potion) {
        return ArrayUtils.contains(PotionUtils.VANILLA_POTIONS, potion,
            (o1, o2) -> o2.getId() - o1.getId());
    }

    /**
     * Check if a potion is a vanilla potion by its ID.
     *
     * @param id The potion ID to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false}
     *         otherwise.
     *
     * @throws ArgumentOutOfBoundsException Thrown if the ID is outside the
     *         allowed range.
     */
    public static boolean isVanillaByID(final int id)
        throws ArgumentOutOfBoundsException {
        return PotionUtils.isVanilla(PotionUtils.getByID(id));
    }

    /**
     * Check if a potion is a vanilla potion by its potion effect.
     *
     * @param potionEffect The potion effect to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false}
     *         otherwise.
     */
    public static boolean isVanillaByEffect(final PotionEffect potionEffect) {
        return PotionUtils.isVanillaByID(potionEffect.getPotionID());
    }

    /**
     * Try to check if a potion is a vanilla potion by its ID.
     *
     * @param id The potion ID to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     */
    public static Optional<Boolean> tryIsVanillaByID(final int id) {
        return PotionUtils.tryGetByID(id).map(PotionUtils::isVanilla);
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
        final PotionEffect potionEffect) {
        return PotionUtils.tryGetByID(potionEffect.getPotionID())
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
    public static boolean isModded(final Potion potion) {
        return !ArrayUtils.contains(PotionUtils.VANILLA_POTIONS, potion,
            (o1, o2) -> o2.getId() - o1.getId());
    }

    /**
     * Check if a potion is a vanilla potion by its ID.
     *
     * @param id The potion ID to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false}
     *         otherwise.
     *
     * @throws ArgumentOutOfBoundsException Thrown if the ID is outside the
     *         allowed range.
     */
    public static boolean isModdedByID(final int id)
        throws ArgumentOutOfBoundsException {
        return !PotionUtils.isVanillaByID(id);
    }

    /**
     * Try to check if a potion is a vanilla potion by its ID.
     *
     * @param id The potion ID to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     *
     * @throws ArgumentOutOfBoundsException Thrown if the ID is outside the
     *         allowed range.
     */
    public static Optional<Boolean> tryIsModdedByID(final int id) {
        return PotionUtils.tryGetByID(id).map(PotionUtils::isModded);
    }

    /**
     * Check if a potion is a vanilla potion by its potion effect.
     *
     * @param potionEffect The potion effect to check.
     *
     * @return {@code true} if the potion is a vanilla potion, {@code false}
     *         otherwise.
     */
    public static boolean isModdedByEffect(final PotionEffect potionEffect) {
        return PotionUtils.isModdedByID(potionEffect.getPotionID());
    }

    /**
     * Try to check if a potion is a modded potion by its potion effect.
     *
     * @param potionEffect The potion effect to check.
     *
     * @return {@code true} if the potion is a modded potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     */
    public static Optional<Boolean> tryIsModdedByEffect(
        final PotionEffect potionEffect) {
        return PotionUtils.tryGetByID(potionEffect.getPotionID())
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
    public static boolean isNegative(final Potion potion) {
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
     * @throws ArgumentOutOfBoundsException Thrown if the ID is outside the
     *         allowed range.
     */
    public static boolean isNegativeByID(final int id)
        throws ArgumentOutOfBoundsException {
        return PotionUtils.isNegative(PotionUtils.getByID(id));
    }

    /**
     * Try to check if a potion is a negative potion by its ID.
     *
     * @param id The potion ID to check.
     *
     * @return {@code true} if the potion is a negative potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     *
     * @throws ArgumentOutOfBoundsException Thrown if the ID is outside the
     *         allowed range.
     */
    public static Optional<Boolean> tryIsNegativeByID(final int id) {
        return PotionUtils.tryGetByID(id).map(PotionUtils::isNegative);
    }

    /**
     * Check if a potion is a negative potion by its potion effect.
     *
     * @param potionEffect The potion effect to check.
     *
     * @return {@code true} if the potion is a negative potion, {@code false}
     *         otherwise.
     */
    public static boolean isNegativeByEffect(final PotionEffect potionEffect) {
        return PotionUtils.isNegativeByID(potionEffect.getPotionID());
    }

    /**
     * Try to check if a potion is a negative potion by its potion effect.
     *
     * @param potionEffect The potion effect to check.
     *
     * @return {@code true} if the potion is a negative potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     */
    public static Optional<Boolean> tryIsNegativeByEffect(
        final PotionEffect potionEffect) {
        return PotionUtils.tryGetByID(potionEffect.getPotionID())
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
    public static boolean isPositive(final Potion potion) {
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
     * @throws ArgumentOutOfBoundsException Thrown if the ID is outside the
     *         allowed range.
     */
    public static boolean isPositiveByID(final int id)
        throws ArgumentOutOfBoundsException {
        return PotionUtils.isPositive(PotionUtils.getByID(id));
    }

    /**
     * Try to check if a potion is a positive potion by its ID.
     *
     * @param id The potion ID to check.
     *
     * @return {@code true} if the potion is a positive potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     *
     * @throws ArgumentOutOfBoundsException Thrown if the ID is outside the
     *         allowed range.
     */
    public static Optional<Boolean> tryIsPositiveByID(final int id) {
        return PotionUtils.tryGetByID(id).map(PotionUtils::isPositive);
    }

    /**
     * Check if a potion is a positive potion by its potion effect.
     *
     * @param potionEffect The potion effect to check.
     *
     * @return {@code true} if the potion is a positive potion, {@code false}
     *         otherwise.
     */
    public static boolean isPositiveByEffect(final PotionEffect potionEffect) {
        return PotionUtils.isPositiveByID(potionEffect.getPotionID());
    }

    /**
     * Try to check if a potion is a positive potion by its potion effect.
     *
     * @param potionEffect The potion effect to check.
     *
     * @return {@code true} if the potion is a positive potion, {@code false} if
     *         it is not, an empty {@link Optional} otherwise.
     */
    public static Optional<Boolean> tryIsPositiveByEffect(
        final PotionEffect potionEffect) {
        return PotionUtils.tryGetByID(potionEffect.getPotionID())
            .map(PotionUtils::isPositive);
    }
}
