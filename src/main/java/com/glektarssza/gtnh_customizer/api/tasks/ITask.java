package com.glektarssza.gtnh_customizer.api.tasks;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.glektarssza.gtnh_customizer.impl.tasks.TaskManager;

/**
 * A task which needs to be setup and run by the mod upon some trigger
 * occurring.
 *
 * A task may need to be run multiple times in order to completely finish.
 */
public interface ITask {
    /**
     * Get the unique identifier of this instance.
     *
     * @implNote This method should return a cached value, if possible, to avoid
     *           possible infinite recursion when the ID is requested by a
     *           {@link ITaskManager}. The value can be retrieved, initially,
     *           when the instance is being assigned to a {@link ITaskManager}
     *           as part of the {@link #setTaskManager} call.
     *
     * @implNote This method should return {@code null} after the instance has
     *           been unregistered from the {@link ITaskManager} it was assigned
     *           to. This occurs during the {@link #setTaskManager} call with
     *           the {@link ITaskManager} parameter set to {@code null}.
     *
     *
     * @apiNote The ID is not guaranteed to be unique across different
     *          {@link ITaskManager} instances.
     *
     *
     * @return The unique identifier of this instance or {@code null} if this
     *         instance has not been assigned to a {@link TaskManager} yet.
     */
    @Nullable
    public UUID getId();

    /**
     * Get the task manager that this instance is registered to.
     *
     * @return The task manager that this instance is registered to or
     *         {@code null} if this instance has not been assigned to a
     *         {@link TaskManager} yet.
     */
    @Nullable
    public ITaskManager getTaskManager();

    /**
     * Set the task manager that this instance is registered to.
     *
     * @param taskManager The new task manager to set this instance as
     *        registered to.
     */
    public void setTaskManager(@Nullable ITaskManager taskManager);

    /**
     * The name of the instance.
     *
     * Mainly used for debugging purposes.
     *
     * @return The name of the instance, if any, or {@code null} if no name is
     *         available.
     */
    @Nullable
    public String getName();

    /**
     * Check whether the instance is initialized.
     *
     * @return {@code true} if the instance is initialized, {@code false}
     *         otherwise.
     */
    public boolean isInitialized();

    /**
     * Check whether the instance is running.
     *
     * @return {@code true} if the instance is running, {@code false} otherwise.
     */
    public boolean isRunning();

    /**
     * Check whether the instance is finished.
     *
     * @return {@code true} if the instance is finished, {@code false}
     *         otherwise.
     */
    public boolean isFinished();

    /**
     * Check whether the instance has succeeded.
     *
     * @return {@code true} if the instance has succeeded, {@code false}
     *         otherwise.
     */
    public boolean isSucceeded();

    /**
     * Check whether the instance is failed.
     *
     * @return {@code true} if the instance is failed, {@code false} otherwise.
     */
    public boolean isFailed();

    /**
     * Initialize the instance.
     *
     * @return {@code true} if the instance was initialized successfully,
     *         {@code false} otherwise.
     */
    public boolean initialize();

    /**
     * Tear down the instance.
     *
     * @implNote This method should, if possible, never throw any exceptions.
     */
    public void tearDown();

    /**
     * Check whether the instance accepts the given task data.
     *
     * @param data The task data to check.
     *
     * @return {@code true} if the instance accepts the given task data,
     *         {@code false} otherwise.
     */
    public default boolean acceptsTaskData(@Nullable ITaskData data) {
        return false;
    }

    /**
     * Run the instance.
     *
     * @param data The task data to run the instance with.
     *
     * @return The result of running the instance.
     */
    @Nonnull
    public TaskRunResult run(@Nullable ITaskData data);
}
