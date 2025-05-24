package com.glektarssza.gtnh_customizer.api.big_screenshots;

import javax.annotation.Nullable;

import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

/**
 * An abstract super class for tasks that can be run on each render tick.
 */
public abstract class AbstractRenderTickTask implements IRenderTickTask {
    /**
     * Whether this instance is set up.
     */
    protected boolean isSetup = false;

    /**
     * Whether this instance is running.
     */
    protected boolean isRunning = false;

    /**
     * Whether this instance is done running.
     */
    protected boolean isDone = false;

    /**
     * The name of this instance.
     */
    @Nullable
    private String name = null;

    /**
     * Create a new instance with the given name.
     *
     * @param name The name to give the new instance, if any.
     */
    protected AbstractRenderTickTask(@Nullable String name) {
        this.name = name;
    }

    /**
     * Get the name of the task.
     *
     * Mainly used for debugging.
     *
     * @return The name of the task if available; {@code null} otherwise.
     */
    @Override
    @Nullable
    public String getName() {
        return this.name;
    }

    /**
     * Check whether the task is setup.
     *
     * @return {@code true} if the task is setup; {@code false} otherwise.
     */
    @Override
    public boolean isSetup() {
        return this.isSetup;
    }

    /**
     * Check whether the task is running.
     *
     * @return {@code true} if the task is running; {@code false} otherwise.
     */
    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Check whether the task is done running.
     *
     * @return {@code true} if the task is done running; {@code false}
     *         otherwise.
     */
    @Override
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Set up the task.
     *
     * @return {@code true} if the task was set up successfully; {@code false}
     *         otherwise.
     */
    @Override
    public boolean setup() {
        this.isSetup = true;
        return true;
    }

    /**
     * Tear down the task.
     */
    @Override
    public void tearDown() {
        if (!this.isDone() && this.isRunning()) {
            throw new IllegalStateException(
                "Cannot tear down a task that is still running");
        }
        // -- Reset the state of this instance
        this.isSetup = false;
        this.isRunning = false;
        this.isDone = false;
    }

    /**
     * Called on each render tick to run the task.
     *
     * @param event The render tick event.
     *
     * @throws IllegalStateException Thrown if the task is in an invalid state
     *         for the current render tick.
     */
    @Override
    public abstract void onRenderTick(RenderTickEvent event);
}
