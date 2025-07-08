package com.glektarssza.gtnh_customizer.config.categories;

import java.util.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

import com.glektarssza.gtnh_customizer.config.Category;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.config.Property;

public class Debugging implements Category {
    /**
     * The child categories of this category.
     */
    private final Category[] childCategories;

    /**
     * The child properties of this category.
     */
    private final Property<?>[] childProperties;

    /**
     * Create a new instance.
     */
    public Debugging() {
        Category cat = this;
        this.childCategories = new Category[] {};
        this.childProperties = new Property<?>[] {
            new Property<Boolean>() {
                @Override
                @Nonnull
                public String getID() {
                    return "verbose_logging";
                }

                @Override
                @Nullable
                public String getComment() {
                    return "Whether verbose logging is enabled.";
                }

                @Override
                public boolean getShowInGui() {
                    return true;
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
                    return true;
                }

                @Override
                @Nonnull
                public Boolean getDefaultValue() {
                    return false;
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
                        .setVerboseLoggingEnabled(
                            config.getCategory(cat.getFullPath())
                                .get(this.getID()).getBoolean());
                }
            }
        };
    }

    @Override
    @Nonnull
    public String getID() {
        return "debugging";
    }

    @Override
    @Nullable
    public String getComment() {
        return "Various settings related to debugging the mod.";
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
        return null;
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
