package com.glektarssza.gtnh_customizer.impl.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.glektarssza.gtnh_customizer.api.tasks.ITask;
import com.glektarssza.gtnh_customizer.api.tasks.ITaskManager;

/**
 * A class which manages {@link ITask} instances as well as subscribing to Forge
 * events in order to execute tasks that are triggered by them.
 */
public class TaskManager implements ITaskManager {
    /**
     * The list of tasks that this instance is managing.
     */
    private final Map<UUID, ITask> tasks = new HashMap<UUID, ITask>();

    /**
     * Create a new instance.
     */
    public TaskManager() {
        // -- Purposefully empty
    }

    /**
     * Register a task to be executed.
     *
     * @param task The task to register.
     */
    @Override
    @Nonnull
    public UUID registerTask(@Nonnull ITask task) {
        UUID id = getAvailableId();
        tasks.put(id, task);
        task.setTaskManager(this);
        return id;
    }

    /**
     * Unregister a task from being executed.
     *
     * @param task The task to unregister.
     */
    @Override
    public void unregisterTask(@Nonnull ITask task)
        throws NoSuchElementException {
        UUID id = tryGetTaskId(task);
        if (id == null) {
            throw new NoSuchElementException(
                String.format("No task with ID '%s' is registered",
                    task.getId()));
        }
        tasks.remove(id);
        task.setTaskManager(null);
    }

    /**
     * Unregister a task from being executed.
     *
     * @param task The task to unregister.
     *
     * @return The ID of the unregistered task or {@code null} if the given task
     *         was not registered.
     */
    @Override
    @Nullable
    public UUID tryUnregisterTask(@Nonnull ITask task) {
        UUID id = tryGetTaskId(task);
        if (id == null) {
            return null;
        }
        tasks.remove(id);
        task.setTaskManager(null);
        return id;
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
    @Override
    @Nonnull
    public UUID getTaskId(@Nonnull ITask task) throws NoSuchElementException {
        return tasks.entrySet().stream()
            .filter(entry -> entry.getValue().equals(task))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException(
                String.format("No task with ID '%s' is registered",
                    task.getId())))
            .getKey();
    }

    /**
     * Try to get the ID of the given task.
     *
     * @param task The task to get the ID of.
     *
     * @return The ID of the given task or {@code null} if the given task is not
     *         registered.
     */
    @Override
    @Nullable
    public UUID tryGetTaskId(@Nonnull ITask task) {
        return tasks.entrySet().stream()
            .filter(entry -> entry.getValue().equals(task))
            .findFirst()
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    /**
     * Get the task with the given ID.
     *
     * @param id The ID of the task to get.
     *
     * @return The task with the given ID.
     *
     * @throws NoSuchElementException If no task with the given ID exists.
     */
    @Override
    @Nonnull
    public ITask getTask(@Nonnull UUID id) throws NoSuchElementException {
        ITask result = this.tryGetTask(id);
        if (result == null) {
            throw new NoSuchElementException(
                String.format("No task with ID '%s' is registered", id));
        }
        return result;
    }

    /**
     * Try to get the task with the given ID.
     *
     * @param id The ID of the task to get.
     *
     * @return The task with the given ID or {@code null} if no such task
     *         exists.
     */
    @Override
    @Nullable
    public ITask tryGetTask(@Nonnull UUID id) {
        return tasks.getOrDefault(id, null);
    }

    /**
     * Get the next available ID for a task.
     *
     * @return The next available ID for a task.
     *
     * @throws IllegalStateException If the method fails to generate a unique ID
     *         after multiple attempts.
     */
    private UUID getAvailableId() throws IllegalStateException {
        int tries = 0;
        UUID id;
        do {
            id = UUID.randomUUID();
            tries += 1;
        } while (tries < 100 && tasks.keySet().contains(id));
        if (tries >= 100) {
            throw new IllegalStateException(
                "Failed to generate a unique ID for the task after 100 attempts");
        }
        return id;
    }
}
