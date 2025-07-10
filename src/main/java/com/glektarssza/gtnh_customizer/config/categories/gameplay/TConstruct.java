package com.glektarssza.gtnh_customizer.config.categories.gameplay;

import javax.annotation.Nonnull;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

import com.glektarssza.gtnh_customizer.config.Category;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.config.Property;

/**
 * The Tinker's Construct gameplay-related configuration category.
 */
public class TConstruct extends Category {
    /**
     * Create a new instance.
     *
     * @param parent The parent of this category.
     */
    public TConstruct(Category parent) {
        super(parent);
        this.childProperties.add(
            new Property<Boolean>(this) {
                @Override
                @Nonnull
                public String getID() {
                    return "can_bone_meal_slime_saplings";
                }

                @Override
                @Nonnull
                public Type getValueType() {
                    return Type.BOOLEAN;
                }

                @Override
                @Nonnull
                public Boolean getDefaultValue() {
                    return true;
                }

                @Override
                public void loadValue(Configuration config) {
                    Config
                        .setTConstructCanBoneMealSlimeSaplings(
                            config.getCategory(this.getParent().getFullPath())
                                .get(this.getID()).getBoolean());
                }
            });
    }

    @Override
    @Nonnull
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
