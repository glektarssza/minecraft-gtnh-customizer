package com.glektarssza.gtnh_customizer.config.categories;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

import com.glektarssza.gtnh_customizer.api.annotations.NotNull;
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
    @NotNull
    public final TConstruct tconstruct;

    @NotNull
    public final Thaumcraft thaumcraft;

    @NotNull
    public final XaerosWorldMap xaerosWorldMap;

    @NotNull
    public final Property<String[]> globallyImmunePlayers;

    @NotNull
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
            @NotNull
            public String getID() {
                return "globally_immune_players";
            }

            @Override
            public boolean isList() {
                return true;
            }

            @Override
            @NotNull
            public Type getValueType() {
                return Type.STRING;
            }

            @Override
            @NotNull
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
            @NotNull
            public String getID() {
                return "prevent_ender_mob_teleportation";
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
    @NotNull
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
