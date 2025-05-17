package com.glektarssza.gtnh_customizer.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import com.glektarssza.gtnh_customizer.utils.KeyAlreadyExistsException;

/**
 * A collection of utilities for migration configuration stuff.
 */
public final class MigrationUtils {
    /**
     * Clone a property.
     *
     * @param property The property to clone.
     *
     * @return A clone of the property.
     */
    public static Property cloneProperty(Property property) {
        if (property.isList()) {
            return new Property(property.getName(), property.getStringList(),
                property.getType(), property.getLanguageKey())
                .setRequiresMcRestart(property.requiresMcRestart())
                .setRequiresWorldRestart(property.requiresWorldRestart());
        } else {
            return new Property(property.getName(), property.getString(),
                property.getType(), property.getValidValues(),
                property.getLanguageKey())
                .setRequiresMcRestart(property.requiresMcRestart())
                .setRequiresWorldRestart(property.requiresWorldRestart());
        }
    }

    /**
     * Clone a property.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to clone.
     *
     * @return A clone of the property.
     */
    public static Property cloneProperty(Configuration instance, String path) {
        return cloneProperty(instance, path, Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Clone a property.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to clone.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return A clone of the property.
     */
    public static Property cloneProperty(Configuration instance, String path,
        CharSequence pathSeparators) {
        return cloneProperty(getPropertyByPath(instance, path, pathSeparators));
    }

    /**
     * Clone a category.
     *
     * @param category The category to clone.
     *
     * @return A clone of the category.
     */
    public static ConfigCategory cloneCategory(ConfigCategory category) {
        return cloneCategory(category, null);
    }

    /**
     * Clone a category.
     *
     * @param category The category to clone.
     * @param parent The category make the parent of the cloned category.
     *
     * @return A clone of the category.
     */
    public static ConfigCategory cloneCategory(ConfigCategory category,
        ConfigCategory parent) {
        ConfigCategory cloned = new ConfigCategory(category.getName(), null);
        cloned.getChildren()
            .forEach((childCategory) -> cloneCategory(childCategory, cloned));
        category.forEach((key, prop) -> cloned.put(key, cloneProperty(prop)));
        return cloned;
    }

    /**
     * Clone a category.
     *
     * @param category The category to clone.
     *
     * @return A clone of the category.
     */
    public static ConfigCategory cloneCategory(Configuration instance,
        String path) {
        return cloneCategory(instance, path, Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Clone a category.
     *
     * @param category The category to clone.
     *
     * @return A clone of the category.
     */
    public static ConfigCategory cloneCategory(Configuration instance,
        String path, ConfigCategory parent) {
        return cloneCategory(instance, path, parent,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Clone a category.
     *
     * @param category The category to clone.
     *
     * @return A clone of the category.
     */
    public static ConfigCategory cloneCategory(Configuration instance,
        String path, String parentPath) {
        return cloneCategory(instance, path, parentPath,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Clone a category.
     *
     * @param category The category to clone.
     * @param parent The category make the parent of the cloned category.
     *
     * @return A clone of the category.
     */
    public static ConfigCategory cloneCategory(Configuration instance,
        String path, ConfigCategory parent, CharSequence pathSeparators) {
        return cloneCategory(getCategoryByPath(instance, path, pathSeparators),
            parent);
    }

    /**
     * Clone a category.
     *
     * @param category The category to clone.
     * @param parent The category make the parent of the cloned category.
     *
     * @return A clone of the category.
     */
    public static ConfigCategory cloneCategory(Configuration instance,
        String path, String parentPath, CharSequence pathSeparators) {
        return cloneCategory(getCategoryByPath(instance, path, pathSeparators),
            getCategoryByPath(instance, path, pathSeparators));
    }

    /**
     * Copy a property from one configuration to another.
     *
     * @param instance The configuration to copy the property from.
     * @param newInstance The configuration to copy the property to.
     * @param path The path to the property to copy.
     *
     * @throws NoSuchElementException Thrown if no such element exists to copy.
     * @throws NoSuchElementException Thrown if no such category exists to copy
     *         onto.
     * @throws KeyAlreadyExistsException Thrown if the new instance already has
     *         a property at the given path.
     */
    public static void copyProperty(Configuration instance,
        Configuration newInstance, String path)
        throws NoSuchElementException, KeyAlreadyExistsException {
        copyProperty(instance, newInstance, path,
            Configuration.CATEGORY_SPLITTER, false);
    }

    /**
     * Copy a property from one configuration to another.
     *
     * @param instance The configuration to copy the property from.
     * @param newInstance The configuration to copy the property to.
     * @param path The path to the property to copy.
     * @param overwrite Whether to overwrite an existing property.
     *
     * @throws NoSuchElementException Thrown if no such element exists to copy.
     * @throws NoSuchElementException Thrown if no such category exists to copy
     *         onto.
     * @throws KeyAlreadyExistsException Thrown if the new instance already has
     *         a property at the given path.
     */
    public static void copyProperty(Configuration instance,
        Configuration newInstance, String path, boolean overwrite)
        throws NoSuchElementException, KeyAlreadyExistsException {
        copyProperty(instance, newInstance, path,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Copy a property from one configuration to another.
     *
     * @param instance The configuration to copy the property from.
     * @param newInstance The configuration to copy the property to.
     * @param path The path to the property to copy.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @throws NoSuchElementException Thrown if no such element exists to copy.
     * @throws NoSuchElementException Thrown if no such category exists to copy
     *         onto.
     * @throws KeyAlreadyExistsException Thrown if the new instance already has
     *         a property at the given path.
     */
    public static void copyProperty(Configuration instance,
        Configuration newInstance, String path, CharSequence pathSeparators)
        throws NoSuchElementException, KeyAlreadyExistsException {
        copyProperty(instance, newInstance, path, pathSeparators, false);
    }

    /**
     * Copy a property from one configuration to another.
     *
     * @param instance The configuration to copy the property from.
     * @param newInstance The configuration to copy the property to.
     * @param path The path to the property to copy.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     * @param overwrite Whether to overwrite an existing property.
     *
     * @throws NoSuchElementException Thrown if no such element exists to copy.
     * @throws NoSuchElementException Thrown if no such category exists to copy
     *         onto.
     * @throws KeyAlreadyExistsException Thrown if the new instance already has
     *         a property at the given path.
     */
    public static void copyProperty(Configuration instance,
        Configuration newInstance, String path, CharSequence pathSeparators,
        boolean overwrite)
        throws NoSuchElementException, KeyAlreadyExistsException {
        if (!hasPropertyByPath(instance, path, pathSeparators)) {
            throw new NoSuchElementException(String.format(
                "Cannot copy configuration property '%s' to the given configuration instance (no property exists to copy)",
                path));
        }
        boolean hasPropertyAlready = hasPropertyByPath(newInstance, path,
            pathSeparators);
        if (hasPropertyAlready && !overwrite) {
            throw new KeyAlreadyExistsException(String.format(
                "Cannot copy configuration property '%s' to the given configuration instance (a property already exists there)",
                path));
        }
        ConfigCategory oldCategory = getPropertyParentCategoryByPath(instance,
            path, pathSeparators);
        Property oldProperty = getPropertyByPath(instance, path,
            pathSeparators);
        if (hasPropertyAlready) {
            getPropertyParentCategoryByPath(newInstance, path, pathSeparators)
                .remove(oldProperty.getName());
        }
        if (!hasCategoryByPath(newInstance, oldCategory.getQualifiedName(),
            pathSeparators)) {
            throw new NoSuchElementException(String.format(
                "Cannot copy configuration property '%s' to the given configuration instance (no category exists to copy it onto)",
                path));
        }
        newInstance.getCategory(oldCategory.getQualifiedName())
            .put(oldProperty.getName(), cloneProperty(oldProperty));
    }

    /**
     * Copy a property from one configuration to another.
     *
     * @param instance The configuration to copy the property from.
     * @param newInstance The configuration to copy the property to.
     * @param path The path to the property to copy.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryCopyProperty(Configuration instance,
        Configuration newInstance, String path) {
        return tryCopyProperty(instance, newInstance, path,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Copy a property from one configuration to another.
     *
     * @param instance The configuration to copy the property from.
     * @param newInstance The configuration to copy the property to.
     * @param path The path to the property to copy.
     * @param overwrite Whether to overwrite an existing property.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryCopyProperty(Configuration instance,
        Configuration newInstance, String path, boolean overwrite) {
        return tryCopyProperty(instance, newInstance, path,
            Configuration.CATEGORY_SPLITTER, overwrite);
    }

    /**
     * Copy a property from one configuration to another.
     *
     * @param instance The configuration to copy the property from.
     * @param newInstance The configuration to copy the property to.
     * @param path The path to the property to copy.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryCopyProperty(Configuration instance,
        Configuration newInstance, String path, CharSequence pathSeparators) {
        try {
            copyProperty(instance, newInstance, path, pathSeparators, false);
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    /**
     * Copy a property from one configuration to another.
     *
     * @param instance The configuration to copy the property from.
     * @param newInstance The configuration to copy the property to.
     * @param path The path to the property to copy.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     * @param overwrite Whether to overwrite an existing property.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryCopyProperty(Configuration instance,
        Configuration newInstance, String path, CharSequence pathSeparators,
        boolean overwrite) {
        return tryCopyProperty(instance, newInstance, path, pathSeparators,
            overwrite);
    }

    /**
     * Copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     *
     * @throws NoSuchElementException Thrown if no such element exists to copy.
     * @throws NoSuchElementException Thrown if no such category exists to copy
     *         onto.
     * @throws KeyAlreadyExistsException Thrown if the new instance already has
     *         a category at the given path.
     */
    public static void copyCategory(Configuration instance,
        Configuration newInstance, String path)
        throws NoSuchElementException, KeyAlreadyExistsException {
        copyCategory(instance, newInstance, path,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     * @param overwrite Whether to overwrite an existing category. This will
     *        erase all properties that were previously on the old category!
     *
     * @throws NoSuchElementException Thrown if no such element exists to copy.
     * @throws NoSuchElementException Thrown if no such category exists to copy
     *         onto.
     * @throws KeyAlreadyExistsException Thrown if the new instance already has
     *         a category at the given path.
     */
    public static void copyCategory(Configuration instance,
        Configuration newInstance, String path, boolean overwrite)
        throws NoSuchElementException, KeyAlreadyExistsException {
        copyCategory(instance, newInstance, path,
            Configuration.CATEGORY_SPLITTER, overwrite);
    }

    /**
     * Copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @throws NoSuchElementException Thrown if no such element exists to copy.
     * @throws NoSuchElementException Thrown if no such category exists to copy
     *         onto.
     * @throws KeyAlreadyExistsException Thrown if the new instance already has
     *         a category at the given path.
     */
    public static void copyCategory(Configuration instance,
        Configuration newInstance, String path, CharSequence pathSeparators)
        throws NoSuchElementException, KeyAlreadyExistsException {
        copyCategory(instance, newInstance, path, pathSeparators, false);
    }

    /**
     * Copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     * @param overwrite Whether to overwrite an existing category. This will
     *        erase all properties that were previously on the old category!
     *
     * @throws NoSuchElementException Thrown if no such element exists to copy.
     * @throws NoSuchElementException Thrown if no such category exists to copy
     *         onto.
     * @throws KeyAlreadyExistsException Thrown if the new instance already has
     *         a category at the given path.
     */
    public static void copyCategory(Configuration instance,
        Configuration newInstance, String path, CharSequence pathSeparators,
        boolean overwrite)
        throws NoSuchElementException, KeyAlreadyExistsException {
        ConfigCategory oldCategory = getCategoryByPath(instance, path,
            pathSeparators);
        if (newInstance.hasCategory(oldCategory.getQualifiedName())
            && !overwrite) {
            throw new KeyAlreadyExistsException(String.format(
                "Cannot copy configuration category '%s' to the given configuration instance (a category already exists there)",
                path));
        } else if (newInstance.hasCategory(oldCategory.getQualifiedName())) {
            newInstance.removeCategory(
                newInstance.getCategory(oldCategory.getQualifiedName()));
        }
        ConfigCategory newCategory = newInstance
            .getCategory(oldCategory.getQualifiedName());
        newCategory.setComment(oldCategory.getComment());
        newCategory.setLanguageKey(oldCategory.getLanguagekey());
        newCategory.setRequiresMcRestart(oldCategory.requiresMcRestart());
        newCategory.setRequiresWorldRestart(oldCategory.requiresWorldRestart());
        newCategory.setConfigEntryClass(oldCategory.getConfigEntryClass());
        newCategory.setShowInGui(oldCategory.showInGui());
    }

    /**
     * Copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryCopyCategory(Configuration instance,
        Configuration newInstance, String path) {
        return tryCopyCategory(instance, newInstance, path,
            Configuration.CATEGORY_SPLITTER, false);
    }

    /**
     * Copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     * @param overwrite Whether to overwrite an existing category. This will
     *        erase all properties that were previously on the old category!
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryCopyCategory(Configuration instance,
        Configuration newInstance, String path, boolean overwrite) {
        return tryCopyCategory(instance, newInstance, path,
            Configuration.CATEGORY_SPLITTER, overwrite);
    }

    /**
     * Copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryCopyCategory(Configuration instance,
        Configuration newInstance, String path, CharSequence pathSeparators) {
        try {
            copyCategory(instance, newInstance, path,
                Configuration.CATEGORY_SPLITTER, false);
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    /**
     * Copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     * @param overwrite Whether to overwrite an existing category. This will
     *        erase all properties that were previously on the old category!
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryCopyCategory(Configuration instance,
        Configuration newInstance, String path, CharSequence pathSeparators,
        boolean overwrite) {
        try {
            copyCategory(instance, newInstance, path,
                Configuration.CATEGORY_SPLITTER, overwrite);
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    /**
     * Deeply copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     *
     * @throws NoSuchElementException Thrown if no such element exists to copy.
     * @throws NoSuchElementException Thrown if no such category exists to copy
     *         onto.
     * @throws KeyAlreadyExistsException Thrown if the new instance already has
     *         a category at the given path.
     */
    public static void deepCopyCategory(Configuration instance,
        Configuration newInstance, String path)
        throws NoSuchElementException, KeyAlreadyExistsException {
        deepCopyCategory(instance, newInstance, path,
            Configuration.CATEGORY_SPLITTER, false);
    }

    /**
     * Deeply copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     * @param overwrite Whether to overwrite an existing category.
     *
     * @throws NoSuchElementException Thrown if no such element exists to copy.
     * @throws NoSuchElementException Thrown if no such category exists to copy
     *         onto.
     * @throws KeyAlreadyExistsException Thrown if the new instance already has
     *         a category at the given path.
     */
    public static void deepCopyCategory(Configuration instance,
        Configuration newInstance, String path, boolean overwrite)
        throws NoSuchElementException, KeyAlreadyExistsException {
        deepCopyCategory(instance, newInstance, path,
            Configuration.CATEGORY_SPLITTER, overwrite);
    }

    /**
     * Deeply copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @throws NoSuchElementException Thrown if no such element exists to copy.
     * @throws NoSuchElementException Thrown if no such category exists to copy
     *         onto.
     * @throws KeyAlreadyExistsException Thrown if the new instance already has
     *         a category at the given path.
     */
    public static void deepCopyCategory(Configuration instance,
        Configuration newInstance, String path, CharSequence pathSeparators)
        throws NoSuchElementException, KeyAlreadyExistsException {
        deepCopyCategory(instance, newInstance, path, pathSeparators, false);
    }

    /**
     * Deeply copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     * @param overwrite Whether to overwrite an existing category.
     *
     * @throws NoSuchElementException Thrown if no such element exists to copy.
     * @throws NoSuchElementException Thrown if no such category exists to copy
     *         onto.
     * @throws KeyAlreadyExistsException Thrown if the new instance already has
     *         a category at the given path.
     */
    public static void deepCopyCategory(Configuration instance,
        Configuration newInstance, String path, CharSequence pathSeparators,
        boolean overwrite)
        throws NoSuchElementException, KeyAlreadyExistsException {
        copyCategory(instance, newInstance, path, pathSeparators, overwrite);
        getCategoryByPath(instance, path, pathSeparators)
            .forEach((propertyName, property) -> copyProperty(instance,
                newInstance,
                String.format("%s%s%s", path, Configuration.CATEGORY_SPLITTER,
                    propertyName),
                overwrite));
        getCategoryByPath(instance, path, pathSeparators).getChildren()
            .forEach((category) -> copyCategory(instance, newInstance,
                category.getQualifiedName(), pathSeparators, overwrite));
        getCategoryByPath(newInstance, path, pathSeparators).setPropertyOrder(
            new ArrayList<String>(
                getCategoryByPath(instance, path, pathSeparators)
                    .getPropertyOrder()));
    }

    /**
     * Try to deeply copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryDeepCopyCategory(Configuration instance,
        Configuration newInstance, String path) {
        return tryDeepCopyCategory(instance, newInstance, path,
            Configuration.CATEGORY_SPLITTER, false);
    }

    /**
     * Try to deeply copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     * @param overwrite Whether to overwrite an existing category.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryDeepCopyCategory(Configuration instance,
        Configuration newInstance, String path, boolean overwrite) {
        return tryDeepCopyCategory(instance, newInstance, path,
            Configuration.CATEGORY_SPLITTER, overwrite);
    }

    /**
     * Try to deeply copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryDeepCopyCategory(Configuration instance,
        Configuration newInstance, String path, CharSequence pathSeparators) {
        try {
            deepCopyCategory(instance, newInstance, path,
                Configuration.CATEGORY_SPLITTER, false);
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    /**
     * Try to deeply copy a category from one configuration to another.
     *
     * @param instance The configuration to copy the category from.
     * @param newInstance The configuration to copy the category to.
     * @param path The path to the category to copy.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     * @param overwrite Whether to overwrite an existing category.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryDeepCopyCategory(Configuration instance,
        Configuration newInstance, String path, CharSequence pathSeparators,
        boolean overwrite) {
        try {
            deepCopyCategory(instance, newInstance, path,
                Configuration.CATEGORY_SPLITTER, overwrite);
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    /**
     * Clone a configuration.
     *
     * @param instance The instance to clone.
     *
     * @return A clone of the configuration.
     */
    public static Configuration cloneConfiguration(Configuration instance) {
        // TODO: Figure out how to access case sensitivity field...
        Configuration cloned = new Configuration(instance.getConfigFile(),
            instance.getDefinedConfigVersion());
        instance.getCategoryNames()
            .forEach((category) -> deepCopyCategory(instance, cloned, category,
                true));
        return cloned;
    }

    /**
     * Try to clone a configuration.
     *
     * @param instance The instance to clone.
     *
     * @return A clone of the configuration on success; {@code null} otherwise.
     */
    @Nullable
    public static Configuration tryCloneConfiguration(Configuration instance) {
        try {
            return cloneConfiguration(instance);
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * Copy the contents of a configuration to a different instance.
     *
     * @param instance The instance to copy the contents of.
     * @param newInstance The instance to copy the contents to.
     *
     * @throws NoSuchElementException Thrown if no such element exists to copy.
     * @throws NoSuchElementException Thrown if no such category exists to copy
     *         onto.
     * @throws KeyAlreadyExistsException Thrown if the new instance already has
     *         a category at the given path.
     * @throws KeyAlreadyExistsException Thrown if the new instance already has
     *         a property at the given path.
     */
    public static void copyConfiguration(Configuration instance,
        Configuration newInstance)
        throws NoSuchElementException, KeyAlreadyExistsException {
        instance.getCategoryNames().parallelStream()
            .forEach((categoryName) -> copyCategory(instance, newInstance,
                categoryName));
    }

    /**
     * Try to copy the contents of a configuration to a different instance.
     *
     * @param instance The instance to copy the contents of.
     * @param newInstance The instance to copy the contents to.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryCopyConfiguration(Configuration instance,
        Configuration newInstance) {
        try {
            copyConfiguration(instance, newInstance);
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    /**
     * Rename a property within its current category.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to rename.
     * @param newName The new name to assign to the property.
     *
     * @throws NoSuchElementException Thrown if there is no property at the
     *         given path.
     * @throws KeyAlreadyExistsException Thrown if a property already exists in
     *         the location where the property is going to be renamed to.
     */
    public static void renameProperty(Configuration instance, String path,
        String newName)
        throws NoSuchElementException, KeyAlreadyExistsException {
        renameProperty(instance, path, newName,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Rename a property within its current category.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to rename.
     * @param newName The new name to assign to the property.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @throws NoSuchElementException Thrown if there is no property at the
     *         given path.
     * @throws KeyAlreadyExistsException Thrown if a property already exists in
     *         the location where the property is going to be renamed to.
     */
    public static void renameProperty(Configuration instance, String path,
        String newName, CharSequence pathSeparators)
        throws NoSuchElementException, KeyAlreadyExistsException {
        ConfigCategory category = getPropertyParentCategoryByPath(instance,
            path, pathSeparators);
        Property property = getPropertyByPath(instance, path, pathSeparators);
        if (category.containsKey(newName)) {
            throw new KeyAlreadyExistsException(String.format(
                "Cannot rename configuration property '%s' from '%s' to '%s' (a property already exists there)",
                path, property.getName(), newName));
        }
        category.remove(property.getName());
        property.setName(newName);
        category.put(newName, property);
    }

    /**
     * Try to rename a property within its current category.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to rename.
     * @param newName The new name to assign to the property.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryRenameProperty(Configuration instance,
        String path, String newName) {
        return tryRenameProperty(instance, path, newName,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Try to rename a property within its current category.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to rename.
     * @param newName The new name to assign to the property.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryRenameProperty(Configuration instance,
        String path, String newName, CharSequence pathSeparators) {
        try {
            renameProperty(instance, path, newName);
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    /**
     * Move a property from its current category to a new category.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to rename.
     * @param newCategoryPath The new path to the category to assign the
     *        property to.
     *
     * @throws NoSuchElementException Thrown if there is no property at the
     *         given path.
     * @throws KeyAlreadyExistsException Thrown if a property already exists in
     *         the location where the property is going to be moved to.
     */
    public static void moveProperty(Configuration instance, String path,
        String newCategoryPath)
        throws NoSuchElementException {
        moveProperty(instance, path, newCategoryPath,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Move a property from its current category to a new category.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to rename.
     * @param newCategoryPath The new path to the category to assign the
     *        property to.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @throws NoSuchElementException Thrown if there is no property at the
     *         given path.
     * @throws KeyAlreadyExistsException Thrown if a property already exists in
     *         the location where the property is going to be moved to.
     */
    public static void moveProperty(Configuration instance, String path,
        String newCategoryPath, CharSequence pathSeparators)
        throws NoSuchElementException {
        Property property = getPropertyByPath(instance, path, pathSeparators);
        ConfigCategory oldCategory = getPropertyParentCategoryByPath(instance,
            path, pathSeparators);
        ConfigCategory newCategory = getPropertyParentCategoryByPath(instance,
            newCategoryPath, pathSeparators);
        if (newCategory.containsKey(property.getName())) {
            throw new KeyAlreadyExistsException(String.format(
                "Cannot move configuration property '%s' from category '%s' to '%s' (a property already exists there of the same name)",
                property.getName(), oldCategory.getQualifiedName(),
                newCategory.getQualifiedName()));
        }
        oldCategory.remove(property.getName());
        newCategory.put(property.getName(), property);
    }

    /**
     * Move a property from its current category to a new category.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to rename.
     * @param newCategoryPath The new path to the category to assign the
     *        property to.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryMoveProperty(Configuration instance,
        String path, String newCategoryPath) {
        return tryMoveProperty(instance, path, newCategoryPath,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Move a property from its current category to a new category.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to rename.
     * @param newCategoryPath The new path to the category to assign the
     *        property to.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryMoveProperty(Configuration instance,
        String path, String newCategoryPath, CharSequence pathSeparators) {
        try {
            moveProperty(instance, path, newCategoryPath, pathSeparators);
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    /**
     * Move and rename a property from its current name on its category to a new
     * name on a category.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to rename.
     * @param newPath The new path to assign the property to.
     *
     * @throws NoSuchElementException Thrown if there is no property at the
     *         given path.
     * @throws KeyAlreadyExistsException Thrown if a property already exists in
     *         the location where the property is going to be moved to.
     */
    public static void moveAndRenameProperty(Configuration instance,
        String path, String newPath)
        throws NoSuchElementException {
        moveAndRenameProperty(instance, path, newPath,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Move and rename a property from its current name on its category to a new
     * name on a category.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to rename.
     * @param newPath The new path to assign the property to.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @throws NoSuchElementException Thrown if there is no property at the
     *         given path.
     * @throws KeyAlreadyExistsException Thrown if a property already exists in
     *         the location where the property is going to be moved to.
     */
    public static void moveAndRenameProperty(Configuration instance,
        String path, String newPath, CharSequence pathSeparators)
        throws NoSuchElementException {
        Property property = getPropertyByPath(instance, path, pathSeparators);
        ConfigCategory oldCategory = getPropertyParentCategoryByPath(instance,
            path, pathSeparators);
        ConfigCategory newCategory = getPropertyParentCategoryByPath(instance,
            newPath, pathSeparators);
        String[] newNameComps = splitPath(newPath, pathSeparators);
        String newName = newNameComps[newNameComps.length - 1];
        if (newCategory.containsKey(property.getName())) {
            throw new KeyAlreadyExistsException(String.format(
                "Cannot move and rename configuration property '%s' to '%s' (a property already exists there)",
                path, newPath));
        }
        oldCategory.remove(property.getName());
        property.setName(newName);
        newCategory.put(newName, property);
    }

    /**
     * Move and rename a property from its current name on its category to a new
     * name on a category.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to rename.
     * @param newPath The new path to assign the property to.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryMoveAndRenameProperty(Configuration instance,
        String path, String newPath) {
        return tryMoveAndRenameProperty(instance, path, newPath,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Move and rename a property from its current name on its category to a new
     * name on a category.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to rename.
     * @param newPath The new path to assign the property to.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return {@code true} if the operation succeeded; {@code} false otherwise.
     */
    public static boolean tryMoveAndRenameProperty(Configuration instance,
        String path, String newPath, CharSequence pathSeparators) {
        try {
            moveAndRenameProperty(instance, path, newPath, pathSeparators);
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    public static void renameCategory(Configuration instance, String path,
        String newName)
        throws NoSuchElementException {

    }

    public static boolean tryRenameCategory(Configuration instance,
        String path, String newName) {
        try {
            renameCategory(instance, path, newName);
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    public static void moveCategory(Configuration instance, String path,
        String newPath)
        throws NoSuchElementException {

    }

    public static boolean tryMoveCategory(Configuration instance,
        String path, String newPath) {
        try {
            moveCategory(instance, path, newPath);
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    /**
     * Check if a configuration instance has a category by its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the category to check for.
     *
     * @return {@code true} if the configuration instance has a category at the
     *         given path; {@code false} otherwise.
     */
    public static boolean hasCategoryByPath(Configuration instance,
        String path) {
        return hasCategoryByPath(instance, path,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Check if a configuration instance has a category by its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the category to check for.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return {@code true} if the configuration instance has a category at the
     *         given path; {@code false} otherwise.
     */
    public static boolean hasCategoryByPath(Configuration instance,
        String path, CharSequence pathSeparators) {
        return tryGetCategoryByPath(instance, path,
            Configuration.CATEGORY_SPLITTER) != null;
    }

    /**
     * Check if a configuration instance has a property by its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to check for.
     *
     * @return {@code true} if the configuration instance has a property at the
     *         given path; {@code false} otherwise.
     */
    public static boolean hasPropertyByPath(Configuration instance,
        String path) {
        return hasPropertyByPath(instance, path,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Check if a configuration instance has a property by its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to check for.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return {@code true} if the configuration instance has a property at the
     *         given path; {@code false} otherwise.
     */
    public static boolean hasPropertyByPath(Configuration instance,
        String path, CharSequence pathSeparators) {
        return tryGetPropertyByPath(instance, path,
            Configuration.CATEGORY_SPLITTER) != null;
    }

    /**
     * Get a category from a configuration instance by its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the category to get.
     *
     * @return The configuration category.
     *
     * @throws NoSuchElementException Thrown if there is no category at the
     *         given path.
     */
    public static ConfigCategory getCategoryByPath(Configuration instance,
        String path) throws NoSuchElementException {
        return getCategoryByPath(instance, path,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Get a category from a configuration instance by its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the category to get.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return The configuration category.
     *
     * @throws NoSuchElementException Thrown if there is no category at the
     *         given path.
     */
    public static ConfigCategory getCategoryByPath(Configuration instance,
        String path, CharSequence pathSeparators)
        throws NoSuchElementException {
        String[] pathComps = splitPath(path, pathSeparators);
        if (pathComps.length > 0) {
            if (!instance.hasCategory(pathComps[0])) {
                throw new NoSuchElementException(String
                    .format("No such configuration category with path '%s'",
                        path));
            }
            ConfigCategory category = instance.getCategory(pathComps[0]);
            for (int i = 1; i < pathComps.length; i++) {
                String categoryComp = pathComps[i];
                Optional<ConfigCategory> newCategory = category.getChildren()
                    .parallelStream()
                    .filter((item) -> item.getName().equals(categoryComp))
                    .findFirst();
                if (!newCategory.isPresent()) {
                    return null;
                }
                category = newCategory.get();
            }
            return category;
        }
        throw new NoSuchElementException(String
            .format("No such configuration category with path '%s'", path));
    }

    /**
     * Get a category from a configuration instance by its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the category to get.
     *
     * @return The configuration category on success; {@code null} otherwise.
     */
    @Nullable
    public static ConfigCategory tryGetCategoryByPath(Configuration instance,
        String path) {
        return tryGetCategoryByPath(instance, path,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Get a category from a configuration instance by its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the category to get.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return The configuration category on success; {@code null} otherwise.
     */
    @Nullable
    public static ConfigCategory tryGetCategoryByPath(Configuration instance,
        String path, CharSequence pathSeparators) {
        try {
            return getCategoryByPath(instance, path, pathSeparators);
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * Get a property from a configuration instance by its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to get.
     *
     * @return The configuration property.
     *
     * @throws NoSuchElementException Thrown if there is no property at the
     *         given path.
     */
    public static Property getPropertyByPath(Configuration instance,
        String path) throws NoSuchElementException {
        return getPropertyByPath(instance, path,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Get a property from a configuration instance by its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to get.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return The configuration property.
     *
     * @throws NoSuchElementException Thrown if there is no property at the
     *         given path.
     */
    public static Property getPropertyByPath(Configuration instance,
        String path, CharSequence pathSeparators)
        throws NoSuchElementException {
        String[] pathComps = splitPath(path, pathSeparators);
        if (pathComps.length > 1) {
            ConfigCategory category = getCategoryByPath(instance, String.join(
                Configuration.CATEGORY_SPLITTER,
                Arrays.copyOfRange(pathComps, 0,
                    pathComps.length - 1)));
            if (!category.containsKey(pathComps[pathComps.length - 1])) {
                throw new NoSuchElementException(String
                    .format("No such configuration property with path '%s'",
                        path));
            }
            return category.get(pathComps[pathComps.length - 1]);
        }
        throw new NoSuchElementException(String
            .format("No such configuration property with path '%s'", path));
    }

    /**
     * Get a property from a configuration instance by its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to get.
     *
     * @return The configuration property on success; {@code null} otherwise.
     */
    @Nullable
    public static Property tryGetPropertyByPath(Configuration instance,
        String path) {
        return tryGetPropertyByPath(instance, path,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Get a property from a configuration instance by its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to get.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return The configuration property on success; {@code null} otherwise.
     */
    @Nullable
    public static Property tryGetPropertyByPath(Configuration instance,
        String path, CharSequence pathSeparators) {
        try {
            return getPropertyByPath(instance, path, pathSeparators);
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * Get the parent category of a property from a configuration instance by
     * its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to get the category of.
     *
     * @return The configuration category of the property.
     *
     * @throws NoSuchElementException Thrown if there is no property at the
     *         given path.
     */
    public static ConfigCategory getPropertyParentCategoryByPath(
        Configuration instance,
        String path) throws NoSuchElementException {
        return getPropertyParentCategoryByPath(instance, path,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Get the parent category of a property from a configuration instance by
     * its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to get the category of.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return The configuration category of the property.
     *
     * @throws NoSuchElementException Thrown if there is no property at the
     *         given path.
     */
    public static ConfigCategory getPropertyParentCategoryByPath(
        Configuration instance,
        String path, CharSequence pathSeparators)
        throws NoSuchElementException {
        if (!hasPropertyByPath(instance, path, pathSeparators)) {
            throw new NoSuchElementException(String
                .format("No such configuration property with path '%s'", path));
        }
        String[] pathComps = splitPath(path, pathSeparators);
        return getCategoryByPath(instance,
            String.join(Configuration.CATEGORY_SPLITTER,
                Arrays.copyOfRange(pathComps, 0, pathComps.length - 1)));
    }

    /**
     * Get the parent category of a property from a configuration instance by
     * its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to get the category of.
     *
     * @return The configuration category of the property on success;
     *         {@code null} otherwise.
     */
    @Nullable
    public static ConfigCategory tryGetPropertyParentCategoryByPath(
        Configuration instance,
        String path) {
        return tryGetPropertyParentCategoryByPath(instance, path,
            Configuration.CATEGORY_SPLITTER);
    }

    /**
     * Get the parent category of a property from a configuration instance by
     * its path.
     *
     * @param instance The configuration instance.
     * @param path The path to the property to get the category of.
     * @param pathSeparators The character(s) to treat as separators of path
     *        components.
     *
     * @return The configuration category of the property on success;
     *         {@code null} otherwise.
     */
    @Nullable
    public static ConfigCategory tryGetPropertyParentCategoryByPath(
        Configuration instance,
        String path, CharSequence pathSeparators) {
        try {
            return getPropertyParentCategoryByPath(instance, path,
                pathSeparators);
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * Split a path into its components.
     *
     * @param path The path to split.
     * @param pathSeparators A collection of characters to treat as separators
     *        of path components.
     *
     * @return An array of path components.
     */
    public static String[] splitPath(String path, CharSequence pathSeparators) {
        StringBuilder sb = new StringBuilder();
        List<String> seps = pathSeparators.chars()
            .mapToObj((i) -> String.valueOf(Character.toChars(i)))
            .collect(Collectors.toList());
        sb.append('[');
        while (!seps.isEmpty()) {
            String sep = seps.remove(0);
            if (sep.equals("-") && !seps.isEmpty()) {
                seps.add(sep);
                sep = seps.get(0);
            }
            sb.append(sep);
            if (sep.equals("\\")) {
                sb.append(sep);
            }
        }
        sb.append(']');
        return path.split(sb.toString());
    }
}
