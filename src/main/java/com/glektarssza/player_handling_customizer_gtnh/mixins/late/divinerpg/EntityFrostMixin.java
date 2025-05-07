package com.glektarssza.player_handling_customizer_gtnh.mixins.late.divinerpg;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.glektarssza.player_handling_customizer_gtnh.PlayerHandlingCustomizer;
import com.glektarssza.player_handling_customizer_gtnh.api.ITargetingImmunity;
import com.glektarssza.player_handling_customizer_gtnh.utils.ImmunityUtils;
import com.glektarssza.player_handling_customizer_gtnh.utils.PlayerUtils;

import divinerpg.objects.entities.entity.vanilla.EntityFrost;

/**
 * Mixin for the {@code EntityFrost} class.
 */
@Mixin(EntityFrost.class)
public class EntityFrostMixin {
    /**
     * A thin wrapper around the {@link EntityFrost.AIFrostShotAttack
     * AIFrostShotAttack} private inner class.
     */
    private static class AIFrostShotAttackShim extends EntityAIBase {
        /**
         * The {@link EntityFrost.AIFrostShotAttack AIFrostShotAttack} being
         * wrapped.
         *
         * Typed as an {@link EntityAIBase} because we don't have access to the
         * actual type.
         */
        private EntityAIBase wrapped;

        /**
         * Create a new instance.
         *
         * @param instance The instance to wrap in the new instance.
         */
        public AIFrostShotAttackShim(EntityAIBase instance) {
            this.wrapped = instance;
        }

        @Override
        public boolean shouldExecute() {
            try {
                EntityFrost self = getEntityFrost();
                EntityLiving attacker = (EntityLiving) self;
                EntityLivingBase target = (EntityLivingBase) self
                    .getAttackTarget();
                if (target instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) target;
                    List<ITargetingImmunity> immunities = PlayerUtils
                        .getPlayerTargetingImmunities(player);
                    if (ImmunityUtils.entityMatchesAnyTargetingImmunity(
                        attacker,
                        immunities)
                        || PlayerUtils.getIsPlayerGloballyImmune(player)) {
                        return false;
                    }
                }

            } catch (Exception ex) {
                if (PlayerHandlingCustomizer
                    .shouldEmitWarning(
                        "entityFrostAIFrostShotAttackShouldExecute")) {
                    PlayerHandlingCustomizer.LOGGER.warn(
                        String.format(
                            "Failed to execute shim \"%s\" for class \"%s\"!",
                            "com.glektarssza.player_handling_customizer_gtnh.mixins.divinerpg.EntityFrostMixin$AIFrostShotAttackShim#shouldExecute",
                            "divinerpg.objects.entities.entity.vanilla.EntityFrost$AIFrostShotAttack"),
                        ex);
                    PlayerHandlingCustomizer
                        .trackEmitWarning(
                            "entityFrostAIFrostShotAttackShouldExecute");
                }
            }
            return wrapped.shouldExecute();
        }

        /**
         * Get the wrapped {@link EntityFrost} via reflection.
         *
         * @return The wrapped {@link EntityFrost}.
         *
         * @throws IllegalAccessException Thrown if the wrapped
         *         {@link EntityFrost} cannot be accessed.
         */
        private EntityFrost getEntityFrost()
            throws NoSuchFieldException, IllegalAccessException {
            Class<?> clazz = this.wrapped.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                if (!field.getType().isAssignableFrom(EntityFrost.class)) {
                    continue;
                }
                field.setAccessible(true);
                return (EntityFrost) field.get(this.wrapped);
            }
            throw new NoSuchFieldException();
        }
    }

    /**
     * Mixin for the {@code initEntityAI} method.
     */
    @Inject(method = "initEntityAI", at = @At("TAIL"))
    public void initEntityAI(CallbackInfo ci) {
        EntityFrost self = (EntityFrost) (Object) this;
        try {
            if (shouldReplaceAIFrostShotAttack(self)) {
                replaceAIFrostShotAttack(self);
            }
        } catch (Exception ex) {
            if (PlayerHandlingCustomizer
                .shouldEmitWarning(
                    "shimEntityFrostAIFrostShotAttackInitEntityAI")) {
                PlayerHandlingCustomizer.LOGGER.warn(
                    String.format(
                        "Failed to shim \"%s\" for class \"%s\" from within \"%s\"!",
                        "divinerpg.objects.entities.entity.vanilla.EntityFrost.AIFrostShotAttack",
                        "divinerpg.objects.entities.entity.vanilla.EntityFrost",
                        "com.glektarssza.player_handling_customizer_gtnh.mixins.divinerpg.EntityFrostMixin#initEntityAI"),
                    ex);
                PlayerHandlingCustomizer
                    .trackEmitWarning(
                        "shimEntityFrostAIFrostShotAttackInitEntityAI");
            }
        }
    }

    /**
     * Mixin for the {@code onLivingUpdate} method.
     */
    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    public void onLivingUpdate(CallbackInfo ci) {
        EntityFrost self = (EntityFrost) (Object) this;
        try {
            if (shouldReplaceAIFrostShotAttack(self)) {
                replaceAIFrostShotAttack(self);
            }
        } catch (Exception ex) {
            if (PlayerHandlingCustomizer
                .shouldEmitWarning(
                    "shimEntityFrostAIFrostShotAttackOnLivingUpdate")) {
                PlayerHandlingCustomizer.LOGGER.warn(
                    String.format(
                        "Failed to shim \"%s\" for class \"%s\" from within \"%s\"!",
                        "divinerpg.objects.entities.entity.vanilla.EntityFrost.AIFrostShotAttack",
                        "divinerpg.objects.entities.entity.vanilla.EntityFrost",
                        "com.glektarssza.player_handling_customizer_gtnh.mixins.divinerpg.EntityFrostMixin#onLivingUpdate"),
                    ex);
                PlayerHandlingCustomizer
                    .trackEmitWarning(
                        "shimEntityFrostAIFrostShotAttackOnLivingUpdate");
            }
        }
    }

    /**
     * Check if an {@link EntityFrost} needs to have its behaviors shimmed.
     *
     * @param entityFrost The {@link EntityFrost} to check.
     *
     * @return {@code true} if the {@link EntityFrost} needs its behaviours
     *         shimmed; {@code false} otherwise.
     */
    private boolean shouldReplaceAIFrostShotAttack(EntityFrost entityFrost) {
        try {
            Class<?> clazz = Class.forName(
                "divinerpg.objects.entities.entity.vanilla.EntityFrost$AIFrostShotAttack");
            return entityFrost.tasks.taskEntries.stream()
                .anyMatch((task) -> clazz.isInstance(task.action));
        } catch (ClassNotFoundException ex) {
            if (PlayerHandlingCustomizer
                .shouldEmitWarning(
                    "shouldReplaceAIFrostShotAttackClassNotFound")) {
                PlayerHandlingCustomizer.LOGGER.warn(
                    String.format(
                        "Failed to find class \"%s\"!",
                        "divinerpg.objects.entities.entity.vanilla.EntityFrost$AIFrostShotAttack"),
                    ex);
                PlayerHandlingCustomizer
                    .trackEmitWarning(
                        "shouldReplaceAIFrostShotAttackClassNotFound");
            }
        }
        return false;
    }

    /**
     * Shim the behaviors of an {@link EntityFrost}.
     *
     * @param entityFrost The {@link EntityFrost} to shim.
     */
    private void replaceAIFrostShotAttack(EntityFrost entityFrost) {
        try {
            Class<?> clazz = Class.forName(
                "divinerpg.objects.entities.entity.vanilla.EntityFrost$AIFrostShotAttack");
            for (EntityAITaskEntry task : entityFrost.tasks.taskEntries) {
                if (!clazz.isInstance(task.action)) {
                    continue;
                }
                AIFrostShotAttackShim shimmed = new AIFrostShotAttackShim(
                    task.action);
                entityFrost.tasks.removeTask(task.action);
                entityFrost.tasks.addTask(task.priority, shimmed);
                return;
            }
        } catch (ClassNotFoundException ex) {
            if (PlayerHandlingCustomizer
                .shouldEmitWarning(
                    "replaceReplaceAIFrostShotAttackClassNotFound")) {
                PlayerHandlingCustomizer.LOGGER.warn(
                    String.format(
                        "Failed to find class \"%s\"!",
                        "divinerpg.objects.entities.entity.vanilla.EntityFrost$AIFrostShotAttack"),
                    ex);
                PlayerHandlingCustomizer
                    .trackEmitWarning(
                        "replaceReplaceAIFrostShotAttackClassNotFound");
            }
        }
    }
}
