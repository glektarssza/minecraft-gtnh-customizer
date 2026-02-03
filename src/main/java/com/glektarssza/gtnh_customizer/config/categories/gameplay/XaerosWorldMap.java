package com.glektarssza.gtnh_customizer.config.categories.gameplay;

import javax.annotation.Nonnull;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

import com.glektarssza.gtnh_customizer.config.Category;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.config.Property;

/**
 * The Thaumcraft gameplay-related configuration category.
 */
public class XaerosWorldMap extends Category {
    /**
     * Create a new instance.
     */
    public XaerosWorldMap(Category parent) {
        super(parent);
        this.childProperties.add(
            new Property<Boolean>(this) {
                @Override
                @Nonnull
                public String getID() {
                    return "show_hovered_biome";
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
                        .setXaerosWorldMapShowHoveredBiome(
                            config.getCategory(this.getParent().getFullPath())
                                .get(this.getID()).getBoolean());
                }
            });
    }

    @Override
    @Nonnull
    public String getID() {
        return "xaeros_world_map";
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
