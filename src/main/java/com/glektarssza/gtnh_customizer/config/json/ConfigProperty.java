package com.glektarssza.gtnh_customizer.config.json;

import javax.annotation.Nonnull;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;

import com.glektarssza.gtnh_customizer.utils.TypeHelpers;
import com.glektarssza.gtnh_customizer.utils.exceptions.InvalidStateException;

/**
 * The base type for configuration properties.
 */
public class ConfigProperty {
    /**
     * The raw JSON data stored in the configuration property.
     */
    @Nonnull
    private JsonElement jsonData;

    /**
     * Whether this configuration property has been modified but not yet saved
     * to the disk.
     */
    private boolean dirty;

    /**
     * The configuration property name.
     */
    @Nonnull
    public final String name;

    /**
     * Create a new instance.
     *
     * @param name The configuration property name.
     */
    protected ConfigProperty(@Nonnull String name) {
        this.name = name;
        this.dirty = false;
        this.jsonData = TypeHelpers.asNonnull(JsonNull.INSTANCE);
    }

    /**
     * Whether this configuration property has been changed but not saved to the
     * disk yet.
     *
     * @return {@code true} if this configuration property has been changed but
     *         not saved to the disk yet, {@code false} otherwise.
     */
    public boolean isDirty() {
        return this.dirty;
    }

    /**
     * Check if this configuration property is a null value.
     *
     * @return {@code true} if this configuration property is a null value,
     *         {@code false} otherwise.
     */
    public boolean isNull() {
        return this.jsonData.isJsonNull();
    }

    /**
     * Check if this configuration property is a boolean value.
     *
     * @return {@code true} if this configuration property is a boolean value,
     *         {@code false} otherwise.
     */
    public boolean isBoolean() {
        return this.jsonData.isJsonPrimitive()
            && ((JsonPrimitive) this.jsonData).isBoolean();
    }

    /**
     * Check if this configuration property is a numerical value.
     *
     * @return {@code true} if this configuration property is a numerical value,
     *         {@code false} otherwise.
     */
    public boolean isNumber() {
        return this.jsonData.isJsonPrimitive()
            && ((JsonPrimitive) this.jsonData).isNumber();
    }

    /**
     * Check if this configuration property is a string value.
     *
     * @return {@code true} if this configuration property is a string value,
     *         {@code false} otherwise.
     */
    public boolean isString() {
        return this.jsonData.isJsonPrimitive()
            && ((JsonPrimitive) this.jsonData).isString();
    }

    /**
     * Check if this configuration property is a JSON array value.
     *
     * @return {@code true} if this configuration property is a JSON array
     *         value, {@code false} otherwise.
     */
    public boolean isArray() {
        return this.jsonData.isJsonArray();
    }

    /**
     * Get the configuration property as a boolean value.
     *
     * @return The configuration property as a boolean value.
     */
    public boolean getBoolean() {
        if (!this.isBoolean()) {
            throw new InvalidStateException(
                "Cannot get configuration property as boolean (does not contain boolean data)");
        }
        return this.jsonData.getAsBoolean();
    }

    /**
     * Get the configuration property as a character value.
     *
     * @return The configuration property as a character value.
     */
    public char getCharacter() {
        if (!this.isString()) {
            throw new InvalidStateException(
                "Cannot get configuration property as character (does not contain string data)");
        }
        return this.jsonData.getAsString().charAt(0);
    }

    /**
     * Set the value held in this configuration property.
     *
     * @param value The new value to hold in this configuration property.
     */
    protected void setValue(@Nonnull JsonElement value) {
        this.jsonData = value;
        this.dirty = true;
    }

    /**
     * Set the configuration property to the given boolean value.
     *
     * @param value The boolean value to set the configuration property to.
     */
    public void setValueBoolean(boolean value) {
        this.setValue(new JsonPrimitive(value));
    }
}
