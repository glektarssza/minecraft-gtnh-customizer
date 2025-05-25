package com.glektarssza.gtnh_customizer.api.tasks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * An abstract base class for tasks.
 */
public abstract class AbstractTask implements ITask {
    /**
     * The name of the task.
     */
    @Nullable
    private final String name;

    /**
     * Whether the task is initialized.
     */
    protected boolean initialized = false;

    /**
     * Whether the task is running.
     */
    protected boolean running = false;

    /**
     * Whether the task is finished.
     */
    protected boolean finished = false;

    /**
     * Whether the task has succeeded.
     */
    protected boolean succeeded = false;

    /**
     * Whether the task has failed.
     */
    protected boolean failed = false;

    /**
     * Create a new instance of the task.
     *
     * @param name The name of the task.
     */
    public AbstractTask(@Nullable String name) {
        this.name = name;
    }

    /**
     * The name of the task.
     *
     * Mainly used for debugging purposes.
     *
     * @return The name of the task, if any, or {@code null} if no name is
     *         available.
     */
    @Override
    @Nullable
    public String getName() {
        return this.name;
    }

    /**
     * Check whether the task is initialized.
     *
     * @return {@code true} if the task is initialized, {@code false} otherwise.
     */
    @Override
    public boolean isInitialized() {
        return this.initialized;
    }

    /**
     * Check whether the task is running.
     *
     * @return {@code true} if the task is running, {@code false} otherwise.
     */
    @Override
    public boolean isRunning() {
        return this.running;
    }

    /**
     * Check whether the task is finished.
     *
     * @return {@code true} if the task is finished, {@code false} otherwise.
     */
    @Override
    public boolean isFinished() {
        return this.finished;
    }

    /**
     * Check whether the task has succeeded.
     *
     * @return {@code true} if the task has succeeded, {@code false} otherwise.
     */
    @Override
    public boolean isSucceeded() {
        return this.succeeded;
    }

    /**
     * Check whether the task is failed.
     *
     * @return {@code true} if the task is failed, {@code false} otherwise.
     */
    @Override
    public boolean isFailed() {
        return this.failed;
    }

    /**
     * Initialize the task.
     *
     * @return {@code true} if the task was initialized successfully,
     *         {@code false} otherwise.
     */
    @Override
    public boolean initialize() {
        if (this.initialized) {
            return true;
        }
        this.initialized = true;
        return true;
    }

    /**
     * Tear down the task.
     *
     * @implNote This method should, if possible, never throw any exceptions.
     */
    @Override
    public void tearDown() {
        this.finished = false;
        this.running = false;
        this.initialized = false;
    }

    /**
     * Run the task.
     *
     * @return The result of the task run.
     */
    @Override
    @Nonnull
    public TaskRunResult run() {
        if (!this.isInitialized()) {
            return TaskRunResult.failed(this,
                new IllegalStateException("Task is not initialized"));
        }
        if (this.isFinished()) {
            return TaskRunResult.failed(this,
                new IllegalStateException("Task is already finished"));
        }
        if (this.isRunning()) {
            return TaskRunResult.failed(this,
                new IllegalStateException("Task is already running"));
        }
        this.running = true;
        return TaskRunResult.successNoRerun(this);
    }

}
