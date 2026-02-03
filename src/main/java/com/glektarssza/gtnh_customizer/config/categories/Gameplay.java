package com.glektarssza.gtnh_customizer.config.categories;

import javax.annotation.Nonnull;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

import com.glektarssza.gtnh_customizer.config.Category;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.config.Property;
import com.glektarssza.gtnh_customizer.config.categories.gameplay.TConstruct;
import com.glektarssza.gtnh_customizer.config.categories.gameplay.Thaumcraft;
import com.glektarssza.gtnh_customizer.config.categories.gameplay.XaerosWorldMap;

/**
 * The gameplay-related configuration category.
 */
public class Gameplay extends Category {
    /**
     * Create a new instance.
     */
    public Gameplay() {
        this.childCategories.add(new TConstruct(this));
        this.childCategories.add(new Thaumcraft(this));
        this.childCategories.add(new XaerosWorldMap(this));
        this.childProperties.add(
            new Property<String[]>(this) {
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
                        .setImmunePlayers(
                            config.getCategory(this.getParent().getFullPath())
                                .get(this.getID()).getStringList());
                }
            });
        this.childProperties.add(new Property<Boolean>(this) {
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
                    config.getCategory(this.getParent().getFullPath())
                        .get(this.getID()).getBoolean());
            }
        });
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
}
