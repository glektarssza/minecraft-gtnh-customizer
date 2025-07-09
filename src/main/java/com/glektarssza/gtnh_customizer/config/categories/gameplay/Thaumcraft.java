package com.glektarssza.gtnh_customizer.config.categories.gameplay;

import java.util.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

import com.glektarssza.gtnh_customizer.config.Category;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.config.Property;

public class Thaumcraft implements Category {
    /**
     * The child categories of this category.
     */
    private final Category[] childCategories;

    /**
     * The child properties of this category.
     */
    private final Property<?>[] childProperties;

    /**
     * The parent category.
     */
    private final Category parent;

    /**
     * Create a new instance.
     */
    public Thaumcraft(Category parent) {
        this.parent = parent;
        Category cat = this;
        this.childCategories = new Category[] {};
        this.childProperties = new Property<?>[] {
            new Property<Boolean>(cat) {
                @Override
                @Nonnull
                public String getID() {
                    return "can_bone_meal_greatwood_saplings";
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
                        .setThaumcraftCanBoneMealGreatwoodSaplings(
                            config.getCategory(cat.getFullPath())
                                .get(this.getID()).getBoolean());
                }
            },
            new Property<Boolean>(cat) {
                @Override
                @Nonnull
                public String getID() {
                    return "can_bone_meal_silverwood_saplings";
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
                        .setThaumcraftCanBoneMealSilverwoodSaplings(
                            config.getCategory(cat.getFullPath())
                                .get(this.getID()).getBoolean());
                }
            }
        };
    }

    @Override
    @Nonnull
    public String getID() {
        return "thaumcraft";
    }

    @Override
    @Nullable
    public String getComment() {
        return "Various tweaks related to Thaumcraft gameplay.";
    }

    @Override
    public boolean getRequiresWorldRestart() {
        return false;
    }

    @Override
    public boolean getRequiresGameRestart() {
        return false;
    }

    @Override
    @Nullable
    public Category getParent() {
        return this.parent;
    }

    @Override
    public boolean hasChildCategories() {
        return childCategories.length <= 0;
    }

    @Override
    public boolean hasChildProperties() {
        return childProperties.length <= 0;
    }

    @Override
    @Nonnull
    public Category[] getChildrenCategories() {
        return Arrays.copyOf(childCategories, childCategories.length);
    }

    @Override
    @Nonnull
    public Property<?>[] getChildrenProperties() {
        return Arrays.copyOf(childProperties, childProperties.length);
    }
}
