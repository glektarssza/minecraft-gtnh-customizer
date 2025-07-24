package com.glektarssza.gtnh_customizer.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;

/**
 * A collection of utilities for dealing with NBT data.
 */
public class NBTUtils {
    /**
     * Check if a {@link NBTTagList} is empty.
     *
     * @param list The {@link NBTTagList} to check.
     *
     * @return {@code true} if the {@link NBTTagList} is empty, {@code false}
     *         otherwise.
     */
    public static boolean isNBTListEmpty(final NBTTagList list) {
        return list.tagCount() <= 0;
    }

    /**
     * Check whether a {@link NBTTagList} is a list of the given type of NBT
     * tag.
     *
     * @param list The {@link NBTTagList} to check.
     * @param nbtType The type of NBT tag to check for.
     *
     * @return {@code true} if the {@link NBTTagList} contains more than one
     *         element and that element is of the given NBT tag type,
     *         {@code false} otherwise.
     */
    public static boolean isListOfNBTType(final NBTTagList list, int nbtType) {
        return !isNBTListEmpty(list)
            && ((NBTTagList) list.copy()).removeTag(0).getId() == nbtType;
    }

    /**
     * Convert a {@link NBTTagList} into a list of {@link NBTBase} objects.
     *
     * @param list The {@link NBTTagList} to convert.
     *
     * @return A list of {@link NBTBase} objects.
     */
    public static List<NBTBase> nbtListToJavaList(final NBTTagList list) {
        NBTTagList myList = (NBTTagList) list.copy();
        List<NBTBase> result = new ArrayList<NBTBase>();
        while (!isNBTListEmpty(list)) {
            result.add(myList.removeTag(0));
        }
        return result;
    }

    /**
     * Convert a {@link NBTTagList} into a list of {@link NBTBase} objects.
     *
     * @param list The {@link NBTTagList} to convert.
     *
     * @return A list of {@link NBTBase} objects.
     */
    public static NBTTagList javaListToNBTList(final List<NBTBase> list) {
        NBTTagList result = new NBTTagList();
        list.forEach((tag) -> result.appendTag(tag.copy()));
        return result;
    }

    /**
     * Call the supplied consumer on each element of the given
     * {@link NBTTagList}.
     *
     * @param list The {@link NBTTagList} to call the consumer on each element
     *        of.
     * @param consumer The consumer to call for each element of the
     *        {@link NBTTagList}.
     */
    public static void forEachInNBTList(final NBTTagList list,
        Consumer<NBTBase> consumer) {
        nbtListToJavaList(list).forEach(consumer);
    }
}
