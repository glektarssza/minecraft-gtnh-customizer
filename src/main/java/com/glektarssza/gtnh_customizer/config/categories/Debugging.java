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
    @Nonnull
    public final Property<Boolean> verboseLogging;

    /**
     * Create a new instance.
     */
    public Debugging() {
        this.verboseLogging = new Property<Boolean>(this) {
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

            @Override
            public void saveValue(Configuration config) {
                config.getCategory(this.getParent().getFullPath())
                    .get(this.getID())
                    .set(Config.getVerboseLoggingEnabled());
            }
        };
        this.childProperties.add(this.verboseLogging);
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
