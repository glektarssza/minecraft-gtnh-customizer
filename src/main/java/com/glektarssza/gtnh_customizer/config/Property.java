package com.glektarssza.gtnh_customizer.config;

import javax.annotation.Nonnull;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property.Type;

/**
 * A configuration property.
 *
 * @param <T> The type of the value held in the property.
 */
public abstract class Property<T> {
    /**
     * The category that this property belongs to.
     */
    protected Category parent;

    /**
     * Create a new instance.
     *
     * @param parent The category that this property will belong to.
     */
    public Property(Category parent) {
        this.parent = parent;
    }

    /**
     * Get the ID of the property.
     *
     * @return The ID of the property.
     */
    @Nonnull
    public abstract String getID();

    /**
     * Get the full path of the property.
     *
     * @return The full path of the property.
     */
    @Nonnull
    public String getFullPath() {
        return String.join(Configuration.CATEGORY_SPLITTER,
            this.getParent().getFullPath(), this.getID());
    }

    /**
     * Get the language key of the property.
     *
     * @return The language key of the property.
     */
    @Nonnull
    public String getLanguageKey() {
        return String.join(Configuration.CATEGORY_SPLITTER,
            this.getParent().getLanguageKey(), this.getID());
    }

    /**
     * Get the comment of the property.
     *
     * @return The comment of the property.
     */
    @Nonnull
    public String getComment() {
        return "";
    }

    /**
     * Get whether to show this property in the GUI.
     *
     * @return Whether to show this property in the GUI.
     */
    public boolean getShowInGui() {
        return true;
    }

    /**
     * Get whether changes to this property require a world restart.
     *
     * @return Whether changes to this property require a world restart.
     */
    public boolean getRequiresWorldRestart() {
        return false;
    }

    /**
     * Get whether changes to this property require a game restart.
     *
     * @return Whether changes to this property require a game restart.
     */
    public boolean getRequiresGameRestart() {
        return false;
    }

    /**
     * Get the type of value held in this property.
     *
     * @return The type of value held in this property.
     */
    @Nonnull
    public abstract Type getValueType();

    /**
     * Get whether the type of value held in this property is a list.
     *
     * @return Whether the type of value held in this property is a list.
     */
    public boolean isList() {
        return false;
    }

    /**
     * Get whether the type of value held in this property is of a fixed length
     * if it is list.
     *
     * @return Whether the type of value held in this property is of a fixed
     *         length if it is list.
     *
     * @see #isList()
     */
    public boolean isFixedLengthList() {
        return false;
    }

    /**
     * Get the max length of the property if it is a list.
     *
     * @return The max length of the property if it is a list.
     *
     * @see #isList()
     */
    public int getMaxListLength() {
        return -1;
    }

    /**
     * Get the minimum allowed integer value of the property if it holds an
     * integer.
     *
     * @return The minimum allowed integer value of the property if it holds an
     *         integer.
     *
     * @see #getValueType()
     */
    public int getMinIntValue() {
        return Integer.MIN_VALUE;
    }

    /**
     * Get the maximum allowed integer value of the property if it holds an
     * integer.
     *
     * @return The maximum allowed integer value of the property if it holds an
     *         integer.
     *
     * @see #getValueType()
     */
    public int getMaxIntValue() {
        return Integer.MAX_VALUE;
    }

    /**
     * Get the minimum allowed double value of the property if it holds an
     * double.
     *
     * @return The minimum allowed double value of the property if it holds an
     *         double.
     *
     * @see #getValueType()
     */
    public double getMinDoubleValue() {
        return Double.MIN_VALUE;
    }

    /**
     * Get the maximum allowed double value of the property if it holds an
     * double.
     *
     * @return The maximum allowed double value of the property if it holds an
     *         double.
     *
     * @see #getValueType()
     */
    public double getMaxDoubleValue() {
        return Double.MAX_VALUE;
    }

    /**
     * Get the default value of the property.
     *
     * @return The default value of the property.
     */
    @Nonnull
    public abstract T getDefaultValue();

    /**
     * Get the parent category of this property.
     *
     * @return The parent category of this property.
     */
    @Nonnull
    public Category getParent() {
        return this.parent;
    }

    /**
     * Register this property onto the given Forge configuration.
     *
     * @param config The Forge configuration to register this property on to.
     */
    public void registerForgeConfigCategory(Configuration config) {
        net.minecraftforge.common.config.Property prop;
        switch (this.getValueType()) {
            case BOOLEAN:
                if (this.isList()) {
                    prop = config.get(this.getParent().getFullPath(),
                        this.getID(), (boolean[]) this.getDefaultValue(),
                        this.getComment());
                } else {
                    prop = config.get(this.getParent().getFullPath(),
                        this.getID(), (boolean) this.getDefaultValue(),
                        this.getComment());
                }
                break;
            case COLOR:
            case MOD_ID:
            case STRING:
                if (this.isList()) {
                    prop = config.get(this.getParent().getFullPath(),
                        this.getID(), (String[]) this.getDefaultValue(),
                        this.getComment(), this.getValueType());
                } else {
                    prop = config.get(this.getParent().getFullPath(),
                        this.getID(), (String) this.getDefaultValue(),
                        this.getComment(), this.getValueType());
                }
                break;
            case DOUBLE:
                if (this.isList()) {
                    prop = config.get(this.getParent().getFullPath(),
                        this.getID(), (double[]) this.getDefaultValue(),
                        this.getComment());
                } else {
                    prop = config.get(this.getParent().getFullPath(),
                        this.getID(), (double) this.getDefaultValue(),
                        this.getComment());
                }
                prop.setMinValue(this.getMinDoubleValue());
                prop.setMaxValue(this.getMaxDoubleValue());
                break;
            case INTEGER:
                if (this.isList()) {
                    prop = config.get(this.getParent().getFullPath(),
                        this.getID(), (int[]) this.getDefaultValue(),
                        this.getComment());
                } else {
                    prop = config.get(this.getParent().getFullPath(),
                        this.getID(), (int) this.getDefaultValue(),
                        this.getComment());
                }
                prop.setMinValue(this.getMinIntValue());
                prop.setMaxValue(this.getMaxIntValue());
                break;
            default:
                return;
        }
        prop.setLanguageKey(this.getLanguageKey());
        prop.setShowInGui(this.getShowInGui());
        prop.setRequiresWorldRestart(
            this.getRequiresWorldRestart());
        prop.setRequiresMcRestart(this.getRequiresGameRestart());
        prop.setIsListLengthFixed(this.isFixedLengthList());
        if (this.isList() && this.getMaxListLength() > 0) {
            prop.setMaxListLength(this.getMaxListLength());
        }
    }

    /**
     * Load this property's values.
     *
     * @param config The Forge configuration to load this property's values
     *        from.
     */
    public abstract void loadValue(Configuration config);
}
