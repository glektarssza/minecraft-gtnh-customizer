package com.glektarssza.gtnh_customizer.config;

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
