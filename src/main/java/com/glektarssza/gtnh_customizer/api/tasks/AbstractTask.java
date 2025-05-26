package com.glektarssza.gtnh_customizer.api.tasks;

import java.util.UUID;

import javax.annotation.Nullable;

import com.glektarssza.gtnh_customizer.impl.tasks.TaskManager;

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
     * The unique ID of this instance.
     */
    private UUID id = null;

    /**
     * The task manager that this instance is registered to.
     */
    private ITaskManager taskManager = null;

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
     * Get the unique identifier of this instance.
     *
     * @return The unique identifier of this instance or {@code null} if this
     *         instance has not been assigned to a {@link TaskManager} yet.
     */
    @Nullable
    public UUID getId() {
        return this.id;
    }

    /**
     * Get the task manager that this instance is registered to.
     *
     * @return The task manager that this instance is registered to or
     *         {@code null} if this instance has not been assigned to a
     *         {@link TaskManager} yet.
     */
    @Nullable
    public ITaskManager getTaskManager() {
        return this.taskManager;
    }

    /**
     * Set the task manager that this instance is registered to.
     *
     * @param taskManager The new task manager to set this instance as
     *        registered to.
     */
    public void setTaskManager(@Nullable ITaskManager taskManager) {
        if (taskManager == null) {
            this.id = null;
        } else {
            this.id = taskManager.getTaskId(this);
        }
        this.taskManager = taskManager;
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

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!ITask.class.isAssignableFrom(other.getClass())) {
            return false;
        }
        ITask otherTask = (ITask) other;
        ITaskManager taskManager = this.getTaskManager();
        ITaskManager otherTaskManager = otherTask.getTaskManager();
        if (taskManager == null || otherTaskManager == null) {
            return false;
        }
        if (!taskManager.equals(otherTaskManager)) {
            return false;
        }
        UUID id = this.getId();
        UUID otherId = otherTask.getId();
        if (id == null || otherId == null) {
            return false;
        }
        return id.equals(otherId);
    }

    @Override
    public int hashCode() {
        ITaskManager taskManager = getTaskManager();
        UUID id = getId();
        if (taskManager == null || id == null) {
            return super.hashCode();
        }
        return taskManager.hashCode() ^ id.hashCode();
    }
}
