package com.glektarssza.gtnh_customizer.api.tasks;

import java.util.NoSuchElementException;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * An interface for classes that manage tasks.
 */
public interface ITaskManager {
    /**
     * Register a task to be executed.
     *
     * @param task The task to register.
     *
     * @return The ID assigned to the registered task.
     */
    @Nonnull
    public UUID registerTask(@Nonnull ITask task);

    /**
     * Unregister a task from being executed.
     *
     * @param task The task to unregister.
     *
     * @throws NoSuchElementException If the given task is not registered.
     */
    public void unregisterTask(@Nonnull ITask task)
        throws NoSuchElementException;

    /**
     * Unregister a task from being executed.
     *
     * @apiNote This is an optional method that may not be implemented by all
     *          implementations of this interface. If it is not implemented, it
     *          will return {@code null} when called.
     *
     * @param task The task to unregister.
     *
     * @return The ID of the unregistered task or {@code null} if the given task
     *         was not registered.
     */
    @Nullable
    public default UUID tryUnregisterTask(@Nonnull ITask task) {
        return null;
    }

    /**
     * Get the ID of the given task.
     *
     * @param task The task to get the ID of.
     *
     * @return The ID of the given task.
     *
     * @throws NoSuchElementException If the given task is not registered.
     */
    @Nonnull
    public UUID getTaskId(@Nonnull ITask task) throws NoSuchElementException;

    /**
     * Try to get the ID of the given task.
     *
     * @param task The task to get the ID of.
     *
     * @return The ID of the given task or {@code null} if the given task is not
     *         registered.
     */
    @Nullable
    public UUID tryGetTaskId(@Nonnull ITask task);

    /**
     * Get the task with the given ID.
     *
     * @param id The ID of the task to get.
     *
     * @return The task with the given ID.
     *
     * @throws NoSuchElementException If no task with the given ID exists.
     */
    @Nonnull
    public ITask getTask(@Nonnull UUID id) throws NoSuchElementException;

    /**
     * Try to get the task with the given ID.
     *
     * @param id The ID of the task to get.
     *
     * @return The task with the given ID or {@code null} if no such task
     *         exists.
     */
    @Nullable
    public ITask tryGetTask(@Nonnull UUID id);
}
