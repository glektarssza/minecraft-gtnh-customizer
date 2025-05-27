package com.glektarssza.gtnh_customizer.client.screenshots;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lwjgl.util.Dimension;

import net.minecraft.client.Minecraft;

import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

import com.glektarssza.gtnh_customizer.api.tasks.ITaskData;
import com.glektarssza.gtnh_customizer.api.tasks.TaskRunResult;
import com.glektarssza.gtnh_customizer.api.tasks.events.AbstractRenderTickTask;
import com.glektarssza.gtnh_customizer.api.tasks.events.RenderTickTaskData;

/**
 * A task that captures a screenshot.
 */
public class CaptureTask extends AbstractRenderTickTask {
    /**
     * The target size of the screenshot to capture.
     */
    private final Dimension targetScreenshotSize;

    /**
     * The original size of the display prior to capturing the large screenshot.
     */
    @Nullable
    private Dimension originalDisplaySize;

    /**
     * Create a new instance.
     *
     * @param screenshotSize The size of the screenshot to capture.
     */
    public CaptureTask(Dimension screenshotSize) {
        super("Capture Screenshot Task");
        this.targetScreenshotSize = screenshotSize;
    }

    /**
     * Create a new instance.
     *
     * @param screenshotWidth The width of the screenshot to capture.
     * @param screenshotHeight The height of the screenshot to capture.
     */
    public CaptureTask(int screenshotWidth, int screenshotHeight) {
        super("Capture Screenshot Task");
        this.targetScreenshotSize = new Dimension(screenshotWidth,
            screenshotHeight);
    }

    /**
     * Run the task.
     *
     * @param data The task data to run the task with.
     *
     * @return The result of the task run.
     */
    @Override
    @Nonnull
    public TaskRunResult run(@Nullable ITaskData data) {
        if (data == null) {
            return TaskRunResult.failed(this,
                new IllegalArgumentException("Task data cannot be null"));
        }
        if (!(data instanceof RenderTickTaskData)) {
            return TaskRunResult.failed(this,
                new IllegalArgumentException(
                    "Task data must be an instance of RenderTickTaskData"));
        }
        RenderTickEvent event = ((RenderTickTaskData) data).event;
        if (!event.side.isClient()) {
            return TaskRunResult.failed(this,
                new IllegalStateException(
                    "Task can only run on the client side"));
        }
        if (!isInitialized()) {
            return TaskRunResult.failed(this,
                new IllegalStateException("Task is not initialized"));
        }
        if (isRunning()) {
            return TaskRunResult.failed(this,
                new IllegalStateException("Task is already running"));
        }
        if (isFinished()) {
            return TaskRunResult.failed(this,
                new IllegalStateException("Task is already finished"));
        }
        Minecraft mcInstance = Minecraft.getMinecraft();
        TaskRunResult result;
        if (this.getElapsedTicks() <= 0) {
            // -- Back up original display size
            this.originalDisplaySize = new Dimension(
                mcInstance.displayWidth, mcInstance.displayHeight);
            // -- Resize the display to the target screenshot size
            mcInstance.resize(
                this.targetScreenshotSize.getWidth(),
                this.targetScreenshotSize.getHeight());
            result = TaskRunResult.successRerun(this);
        } else if (this.getElapsedTicks() >= 1 && this.getElapsedTicks() < 3) {
            // -- Wait for the re-renders to finish
            // -- First frame will just be black for some reason
            // -- Second frame will be the actual screenshot render
            result = TaskRunResult.successRerun(this);
        } else {
            // -- Capture the screenshot
            // TODO: Implement the screenshot capture logic here.
            // -- Restore the original framebuffer state
            Dimension originalSize = this.originalDisplaySize;
            if (originalSize != null) {
                mcInstance.resize(originalSize.getWidth(),
                    originalSize.getHeight());
            }
            this.originalDisplaySize = null;
            result = TaskRunResult.successNoRerun(this);
        }
        this.incrementElapsedTicks();
        return result;
    }
}
