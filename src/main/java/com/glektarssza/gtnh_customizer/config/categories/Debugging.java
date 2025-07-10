package com.glektarssza.gtnh_customizer.config.categories;

import javax.annotation.Nonnull;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

import com.glektarssza.gtnh_customizer.config.Category;
import com.glektarssza.gtnh_customizer.config.Config;
import com.glektarssza.gtnh_customizer.config.Property;

/**
 * The debugging-related configuration category.
 */
public class Debugging extends Category {
    /**
     * Create a new instance.
     */
    public Debugging() {
        this.childProperties.add(new Property<Boolean>(this) {
            @Override
            @Nonnull
            public String getID() {
                return "verbose_logging";
            }

            @Override
            @Nonnull
            public Type getValueType() {
                return Type.BOOLEAN;
            }

            @Override
            @Nonnull
            public Boolean getDefaultValue() {
                return false;
            }

            @Override
            public void loadValue(Configuration config) {
                Config
                    .setVerboseLoggingEnabled(
                        config.getCategory(this.getParent().getFullPath())
                            .get(this.getID()).getBoolean());
            }
        });
    }

    @Override
    @Nonnull
    public String getID() {
        return "debugging";
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
