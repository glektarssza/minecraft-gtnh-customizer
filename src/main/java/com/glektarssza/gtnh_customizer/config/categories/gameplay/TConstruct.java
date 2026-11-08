package com.glektarssza.gtnh_customizer.config.categories.gameplay;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

import com.glektarssza.gtnh_customizer.api.v2.annotation.NotNull;
import com.glektarssza.gtnh_customizer.config.Category;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.config.Property;

/**
 * The Tinker's Construct gameplay-related configuration category.
 */
public class TConstruct extends Category {
    @NotNull
    public final Property<Boolean> canBoneMealSlimeSaplings;

    /**
     * Create a new instance.
     *
     * @param parent The parent of this category.
     */
    public TConstruct(final Category parent) {
        super(parent);
        this.canBoneMealSlimeSaplings = new Property<Boolean>(this) {
            @Override
            @NotNull
            public String getID() {
                return "can_bone_meal_slime_saplings";
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
            public void loadValue(final Configuration config) {
                Config
                    .setTConstructCanBoneMealSlimeSaplings(
                        config.getCategory(this.getParent().getFullPath())
                            .get(this.getID()).getBoolean());
            }

            @Override
            public void saveValue(final Configuration config) {
                config.getCategory(this.getParent().getFullPath())
                    .get(this.getID())
                    .set(Config.getTConstructCanBoneMealSlimeSaplings());
            }
        };
        this.childProperties.add(this.canBoneMealSlimeSaplings);
    }

    @Override
    @NotNull
    public String getID() {
        return "tconstruct";
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
