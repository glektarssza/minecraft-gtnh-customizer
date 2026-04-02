package com.glektarssza.gtnh_customizer.mixins.early.vanilla;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.potion.Potion;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.glektarssza.gtnh_customizer.utils.extensions.IPotionExtensions;

@Mixin(Potion.class)
public class PotionMixin implements IPotionExtensions {
    /**
     * A local shadow for the {@code isBadEffect} field.
     */
    @Final
    @Shadow
    private boolean isBadEffect;

    /**
     * Check whether the potion gives a negative/bad effect for the living
     * entity.
     *
     * @param entity The living entity which would be targeted by the potion's
     *        effect.
     *
     * @return {@code true} if the potion's effect would be negative for the
     *         living entity, {@code false} otherwise.
     */
    @Override
    public boolean isNegativeEffectFor(Entity entity) {
        Potion self = (Potion) (Object) this;
        if (!(entity instanceof EntityMob)) {
            return this.isBadEffect;
        }
        EntityMob mob = (EntityMob) entity;
        // -- Undead mobs heal from damaging effects and are harmed by healing
        // -- effects, except Wither which is just painful period!
        if (!mob.isEntityUndead() &&
            (self.getId() == Potion.heal.getId()
                || self.getId() == Potion.harm.getId()
                || self.getId() == Potion.regeneration.getId()
                || self.getId() == Potion.poison.getId())) {
            return !this.isBadEffect;
        }
        return this.isBadEffect;
    }
}
