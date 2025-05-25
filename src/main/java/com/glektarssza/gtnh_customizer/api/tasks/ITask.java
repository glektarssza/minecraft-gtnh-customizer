package com.glektarssza.gtnh_customizer.api.tasks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A task which needs to be setup and run by the mod upon some trigger occuring.
 *
 * A task may need to be run multiple times in order to completely finish.
 */
public interface ITask {
    /**
     * The name of the task.
     *
     * Mainly used for debugging purposes.
     *
     * @return The name of the task, if any, or {@code null} if no name is
     *         available.
     */
    @Nullable
    public String getName();

    /**
     * Check whether the task is initialized.
     *
     * @return {@code true} if the task is initialized, {@code false} otherwise.
     */
    public boolean isInitialized();

    /**
     * Check whether the task is running.
     *
     * @return {@code true} if the task is running, {@code false} otherwise.
     */
    public boolean isRunning();

    /**
     * Check whether the task is finished.
     *
     * @return {@code true} if the task is finished, {@code false} otherwise.
     */
    public boolean isFinished();

    /**
     * Check whether the task has succeeded.
     *
     * @return {@code true} if the task has succeeded, {@code false} otherwise.
     */
    public boolean isSucceeded();

    /**
     * Check whether the task is failed.
     *
     * @return {@code true} if the task is failed, {@code false} otherwise.
     */
    public boolean isFailed();

    /**
     * Initialize the task.
     *
     * @return {@code true} if the task was initialized successfully,
     *         {@code false} otherwise.
     */
    public boolean initialize();

    /**
     * Tear down the task.
     *
     * @implNote This method should, if posible, never throw any exceptions.
     */
    public void tearDown();

    /**
     * Run the task.
     *
     * @return The result of the task run.
     */
    @Nonnull
    public TaskRunResult run();
}
