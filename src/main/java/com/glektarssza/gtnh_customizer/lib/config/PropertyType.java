package com.glektarssza.gtnh_customizer.lib.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.glektarssza.gtnh_customizer.lib.exceptions.collections.KeyNotFoundException;

/**
 * An enumeration of known types of {@link Property} objects.
 */
public enum PropertyType {
    BOOLEAN("boolean", false),
    NUMBER("number", false),
    STRING("string", false),
    BOOLEAN_LIST("boolean_list", true),
    NUMBER_LIST("number_list", true),
    STRING_LIST("string_list", true);

    /**
     * A mapping of known enumeration values to their IDs.
     */
    private static final Map<String, PropertyType> KNOWN_VALUES = new HashMap<>();

    static {
        for (PropertyType type : PropertyType.values()) {
            KNOWN_VALUES.putIfAbsent(type.id, type);
        }
    }

    /**
     * Parse an enumeration ID into its value.
     *
     * @param id The enumeration ID to parse.
     *
     * @return The enumeration value.
     *
     * @throws KeyNotFoundException Thrown if no enumeration value can be parsed
     *         from the given ID.
     */
    @Nonnull
    public static PropertyType parse(@Nonnull String id)
        throws KeyNotFoundException {
        PropertyType value = KNOWN_VALUES.get(id);
        if (value == null) {
            throw new KeyNotFoundException((id));
        }
        return value;
    }

    /**
     * Try to parse an enumeration ID into its value.
     *
     * @param id The enumeration ID to parse.
     *
     * @return The enumeration value or {@code null} if no enumeration value can
     *         be parsed.
     */
    @Nullable
    public static PropertyType tryParse(@Nonnull String id) {
        return KNOWN_VALUES.get(id);
    }

    /**
     * The unique ID for this type.
     */
    public final String id;

    /**
     * Whether this instance represents a list type.
     */
    public final boolean isList;

    /**
     * Create a new instance.
     *
     * @param id The unique ID of the new instance.
     * @param isList Whether the new instance represents a list type.
     */
    private PropertyType(@Nonnull String id, boolean isList) {
        this.id = id;
        this.isList = isList;
    }
}
