package com.glektarssza.gtnh_customizer.config.categories.gameplay;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

import com.glektarssza.gtnh_customizer.api.annotations.NotNull;
import com.glektarssza.gtnh_customizer.config.Category;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.config.Property;

/**
 * The Thaumcraft gameplay-related configuration category.
 */
public class Thaumcraft extends Category {
    @NotNull
    public final Property<Boolean> canBoneMealGreatwoodSaplings;

    @NotNull
    public final Property<Boolean> canBoneMealSilverwoodSaplings;

    /**
     * Create a new instance.
     */
    public Thaumcraft(Category parent) {
        super(parent);
        this.canBoneMealGreatwoodSaplings = new Property<Boolean>(this) {
            @Override
            @NotNull
            public String getID() {
                return "can_bone_meal_greatwood_saplings";
            }

            @Override
            @NotNull
            public Type getValueType() {
                return Type.BOOLEAN;
            }

            @Override
            @NotNull
            public Boolean getDefaultValue() {
                return true;
            }

            @Override
            public void loadValue(Configuration config) {
                Config
                    .setThaumcraftCanBoneMealGreatwoodSaplings(
                        config.getCategory(this.getParent().getFullPath())
                            .get(this.getID()).getBoolean());
            }

            @Override
            public void saveValue(Configuration config) {
                config.getCategory(this.getParent().getFullPath())
                    .get(this.getID())
                    .set(
                        Config.getThaumcraftCanBoneMealGreatwoodSaplings());
            }
        };
        this.canBoneMealSilverwoodSaplings = new Property<Boolean>(this) {
            @Override
            @NotNull
            public String getID() {
                return "can_bone_meal_silverwood_saplings";
            }

            @Override
            @NotNull
            public Type getValueType() {
                return Type.BOOLEAN;
            }

            @Override
            @NotNull
            public Boolean getDefaultValue() {
                return true;
            }

            @Override
            public void loadValue(Configuration config) {
                Config
                    .setThaumcraftCanBoneMealSilverwoodSaplings(
                        config.getCategory(this.getParent().getFullPath())
                            .get(this.getID()).getBoolean());
            }

            @Override
            public void saveValue(Configuration config) {
                config.getCategory(this.getParent().getFullPath())
                    .get(this.getID())
                    .set(Config.getThaumcraftCanBoneMealSilverwoodSaplings());
            }
        };
        this.childProperties.add(this.canBoneMealGreatwoodSaplings);
        this.childProperties.add(this.canBoneMealSilverwoodSaplings);
    }

    @Override
    @NotNull
    public String getID() {
        return "thaumcraft";
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
