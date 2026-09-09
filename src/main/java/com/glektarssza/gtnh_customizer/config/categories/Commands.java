package com.glektarssza.gtnh_customizer.config.categories;

import javax.annotation.Nonnull;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

import com.glektarssza.gtnh_customizer.config.Category;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.config.Property;

/**
 * The command-related configuration category.
 */
public class Commands extends Category {
    @Nonnull
    public final Property<Boolean> repairRaycastIgnoresLiquids;

    @Nonnull
    public final Property<Integer> extinguishMaxVolume;

    /**
     * Create a new instance.
     */
    public Commands() {
        this.repairRaycastIgnoresLiquids = new Property<Boolean>(this) {
            @Override
            @Nonnull
            public String getID() {
                return "repair_raycast_ignores_liquids";
            }

            @Override
            @Nonnull
            public Type getValueType() {
                return Type.BOOLEAN;
            }

            @Override
            @Nonnull
            public Boolean getDefaultValue() {
                return false;
            }

            @Override
            public void loadValue(Configuration config) {
                Config
                    .setRepairCommandRaycastIgnoresLiquids(
                        config.getCategory(this.getParent().getFullPath())
                            .get(this.getID()).getBoolean());
            }

            @Override
            public void saveValue(Configuration config) {
                config.getCategory(this.getParent().getFullPath())
                    .get(this.getID())
                    .set(Config.getRepairCommandRaycastIgnoresLiquids());
            }
        };
        this.extinguishMaxVolume = new Property<Integer>(this) {
            @Override
            @Nonnull
            public String getID() {
                return "extinguish_max_volume";
            }

            @Override
            @Nonnull
            public Type getValueType() {
                return Type.INTEGER;
            }

            @Override
            @Nonnull
            public Integer getDefaultValue() {
                return Integer.MAX_VALUE;
            }

            @Override
            public void loadValue(Configuration config) {
                Config
                    .setExtinguishCommandMaxVolume(
                        config.getCategory(this.getParent().getFullPath())
                            .get(this.getID()).getInt());
            }

            @Override
            public void saveValue(Configuration config) {
                config.getCategory(this.getParent().getFullPath())
                    .get(this.getID())
                    .set(Config.getExtinguishCommandMaxVolume());
            }
        };
        this.childProperties.add(this.repairRaycastIgnoresLiquids);
        this.childProperties.add(this.extinguishMaxVolume);
    }

    @Override
    @Nonnull
    public String getID() {
        return "commands";
    }

    @Override
    public boolean getRequiresWorldRestart() {
        return false;
    }

    @Override
    public boolean getRequiresGameRestart() {
        return false;
    }
}
