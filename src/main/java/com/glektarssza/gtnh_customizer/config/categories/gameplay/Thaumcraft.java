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
        this.childProperties = new Property[] {
            new Property<Boolean>() {
                @Override
                @Nonnull
                public String getID() {
                    return "can_bone_meal_greatwood_saplings";
                }

                @Override
                @Nullable
                public String getComment() {
                    return "Whether to allow Thaumcraft Greatwood Saplings to have bone meal applied.";
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
                @Nonnull
                public Type getValueType() {
                    return Type.BOOLEAN;
                }

                @Override
                public boolean isList() {
                    return false;
                }

                @Override
                @Nonnull
                public Boolean getDefaultValue() {
                    return true;
                }

                @Override
                @Nonnull
                public Category getParent() {
                    return cat;
                }

                @Override
                public void registerForgeConfigCategory(Configuration config) {
                    net.minecraftforge.common.config.Property prop = config.get(
                        cat.getFullPath(), this.getID(),
                        this.getDefaultValue(), this.getComment());
                    prop.setLanguageKey(this.getLanguageKey());
                    prop.setShowInGui(this.getShowInGui());
                    prop.setRequiresWorldRestart(
                        this.getRequiresWorldRestart());
                    prop.setRequiresMcRestart(this.getRequiresGameRestart());
                }

                @Override
                public void loadValue(Configuration config) {
                    Config
                        .setThaumcraftCanBoneMealGreatwoodSaplings(
                            config.getCategory(cat.getFullPath())
                                .get(this.getID()).getBoolean());
                }
            },
            new Property<Boolean>() {
                @Override
                @Nonnull
                public String getID() {
                    return "can_bone_meal_silverwood_saplings";
                }

                @Override
                @Nullable
                public String getComment() {
                    return "Whether to allow Thaumcraft Silverwood Saplings to have bone meal applied.";
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
                @Nonnull
                public Type getValueType() {
                    return Type.BOOLEAN;
                }

                @Override
                public boolean isList() {
                    return false;
                }

                @Override
                @Nonnull
                public Boolean getDefaultValue() {
                    return true;
                }

                @Override
                @Nonnull
                public Category getParent() {
                    return cat;
                }

                @Override
                public void registerForgeConfigCategory(Configuration config) {
                    net.minecraftforge.common.config.Property prop = config.get(
                        cat.getFullPath(), this.getID(),
                        this.getDefaultValue(), this.getComment());
                    prop.setLanguageKey(this.getLanguageKey());
                    prop.setShowInGui(this.getShowInGui());
                    prop.setRequiresWorldRestart(
                        this.getRequiresWorldRestart());
                    prop.setRequiresMcRestart(this.getRequiresGameRestart());
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
