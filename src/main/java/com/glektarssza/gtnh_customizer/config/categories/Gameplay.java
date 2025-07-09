package com.glektarssza.gtnh_customizer.config.categories;

import java.util.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

import com.glektarssza.gtnh_customizer.config.Category;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.config.Property;
import com.glektarssza.gtnh_customizer.config.categories.gameplay.TConstruct;

/**
 * The general configuration category.
 */
public class Gameplay implements Category {
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
    public Gameplay() {
        Category cat = this;
        this.childCategories = new Category[] {
            new TConstruct(cat)
        };
        this.childProperties = new Property<?>[] {
            new Property<String[]>(cat) {
                @Override
                @Nonnull
                public String getID() {
                    return "globally_immune_players";
                }

                @Override
                public boolean isList() {
                    return true;
                }

                @Override
                @Nonnull
                public Type getValueType() {
                    return Type.STRING;
                }

                @Override
                @Nonnull
                public String[] getDefaultValue() {
                    return new String[0];
                }

                @Override
                public void loadValue(Configuration config) {
                    Config
                        .setImmunePlayers(config.getCategory(cat.getFullPath())
                            .get(this.getID()).getStringList());
                }
            },
            new Property<Boolean>(cat) {
                @Override
                @Nonnull
                public String getID() {
                    return "prevent_ender_mob_teleportation";
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
                    Config.setPreventEnderMobTeleportation(
                        config.getCategory(cat.getFullPath())
                            .get(this.getID()).getBoolean());
                }
            }
        };
    }

    @Override
    @Nonnull
    public String getID() {
        return "gameplay";
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
