package com.glektarssza.gtnh_customizer.utils.extensions;

import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;

/**
 * Extension methods for the {@link Potion} class.
 */
public interface IPotionExtensions {
    /**
     * Check whether the potion gives a negative/bad effect for the living
     * entity.
     *
     * @param entityLiving The living entity which would be targeted by the
     *        potion's effect.
     *
     * @return {@code true} if the potion's effect would be negative for the
     *         living entity, {@code false} otherwise.
     */
    public boolean isNegativeEffectFor(EntityLiving entityLiving);
}
