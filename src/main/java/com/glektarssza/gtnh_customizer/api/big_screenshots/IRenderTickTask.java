package com.glektarssza.gtnh_customizer.api.big_screenshots;

import javax.annotation.Nullable;

import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

/**
 * An interface for tasks that can be run on each render tick.
 */
public interface IRenderTickTask {
    /**
     * Get the name of the task.
     *
     * Mainly used for debugging.
     *
     * @return The name of the task if available; {@code null} otherwise.
     */
    @Nullable
    public String getName();

    /**
     * Check whether the task is setup.
     *
     * @return {@code true} if the task is setup; {@code false} otherwise.
     */
    public boolean isSetup();

    /**
     * Check whether the task is running.
     *
     * @return {@code true} if the task is running; {@code false} otherwise.
     */
    public boolean isRunning();

    /**
     * Check whether the task is done running.
     *
     * @return {@code true} if the task is done running; {@code false}
     *         otherwise.
     */
    public boolean isDone();

    /**
     * Set up the task.
     *
     * @return {@code true} if the task was set up successfully; {@code false}
     *         otherwise.
     */
    public boolean setup();

    /**
     * Tear down the task.
     */
    public void tearDown();

    /**
     * Called on each render tick to run the task.
     *
     * @param event The render tick event.
     *
     * @throws IllegalStateException Thrown if the task is in an invalid state
     *         for the current render tick.
     */
    public void onRenderTick(RenderTickEvent event);
}
