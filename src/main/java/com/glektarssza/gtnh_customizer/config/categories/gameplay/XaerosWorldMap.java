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
public class XaerosWorldMap extends Category {
    @NotNull
    public final Property<Boolean> showHoveredBiome;

    /**
     * Create a new instance.
     */
    public XaerosWorldMap(Category parent) {
        super(parent);
        this.showHoveredBiome = new Property<Boolean>(this) {
            @Override
            @NotNull
            public String getID() {
                return "show_hovered_biome";
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
                    .setXaerosWorldMapShowHoveredBiome(
                        config.getCategory(this.getParent().getFullPath())
                            .get(this.getID()).getBoolean());
            }

            @Override
            public void saveValue(Configuration config) {
                config.getCategory(this.getParent().getFullPath())
                    .get(this.getID())
                    .set(Config.getXaerosWorldMapShowHoveredBiome());
            }
        };
        this.childProperties.add(this.showHoveredBiome);
    }

    @Override
    @NotNull
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
