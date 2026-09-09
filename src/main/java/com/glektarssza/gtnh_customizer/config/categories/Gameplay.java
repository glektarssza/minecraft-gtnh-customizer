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
    @Nonnull
    public final TConstruct tconstruct;

    @Nonnull
    public final Thaumcraft thaumcraft;

    @Nonnull
    public final XaerosWorldMap xaerosWorldMap;

    @Nonnull
    public final Property<String[]> globallyImmunePlayers;

    @Nonnull
    public final Property<Boolean> preventEnderModTeleportation;

    /**
     * Create a new instance.
     */
    public Gameplay() {
        this.tconstruct = new TConstruct(this);
        this.thaumcraft = new Thaumcraft(this);
        this.xaerosWorldMap = new XaerosWorldMap(this);
        this.globallyImmunePlayers = new Property<String[]>(this) {
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

            @Override
            public void saveValue(Configuration config) {
                config.getCategory(this.getParent().getFullPath())
                    .get(this.getID())
                    .set(Config.getGloballyImmunePlayers());
            }
        };
        this.preventEnderModTeleportation = new Property<Boolean>(this) {
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

            @Override
            public void saveValue(Configuration config) {
                config.getCategory(this.getParent().getFullPath())
                    .get(this.getID())
                    .set(Config.getPreventEnderMobTeleportation());
            }
        };
        this.childCategories.add(this.tconstruct);
        this.childCategories.add(this.thaumcraft);
        this.childCategories.add(this.xaerosWorldMap);
        this.childProperties.add(this.globallyImmunePlayers);
        this.childProperties.add(this.preventEnderModTeleportation);
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
