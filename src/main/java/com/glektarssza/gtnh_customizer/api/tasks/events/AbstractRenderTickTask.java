package com.glektarssza.gtnh_customizer.api.tasks.events;

import javax.annotation.Nullable;

import com.glektarssza.gtnh_customizer.api.tasks.AbstractTask;
import com.glektarssza.gtnh_customizer.api.tasks.ITaskData;

/**
 * An abstract class for tasks that are executed on render ticks.
 */
public abstract class AbstractRenderTickTask extends AbstractTask {
    /**
     * The number of ticks that have elapsed since the task started.
     */
    private int elapsedTicks = 0;

    /**
     * Create a new instance.
     *
     * @param name The name of the task.
     */
    public AbstractRenderTickTask(String name) {
        super(name);
    }

    /**
     * Check whether the instance accepts the given task data.
     *
     * @param data The task data to check.
     *
     * @return {@code true} if the instance accepts the given task data,
     *         {@code false} otherwise.
     */
    @Override
    public boolean acceptsTaskData(@Nullable ITaskData data) {
        if (data == null) {
            return false;
        }
        return data instanceof RenderTickTaskData;
    }

    /**
     * Get the number of ticks that have elapsed since the task started.
     *
     * @return The number of ticks that have elapsed since the task started.
     */
    public int getElapsedTicks() {
        return this.elapsedTicks;
    }

    /**
     * Increment the number of ticks that have elapsed since the task started.
     *
     * @see #getElapsedTicks()
     */
    protected void incrementElapsedTicks() {
        this.elapsedTicks += 1;
    }
}
