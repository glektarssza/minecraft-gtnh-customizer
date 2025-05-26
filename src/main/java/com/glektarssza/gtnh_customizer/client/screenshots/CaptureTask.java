package com.glektarssza.gtnh_customizer.client.screenshots;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lwjgl.util.Dimension;

import net.minecraft.client.Minecraft;

import com.glektarssza.gtnh_customizer.api.tasks.AbstractTask;
import com.glektarssza.gtnh_customizer.api.tasks.ITaskData;
import com.glektarssza.gtnh_customizer.api.tasks.TaskRunResult;

/**
 * A task that captures a screenshot.
 */
public class CaptureTask extends AbstractTask {
    /**
     * The number of frames that have elapsed since the task started.
     */
    private int frameCount = 0;

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
        if (this.frameCount <= 0) {
            // -- Back up original display size
            this.originalDisplaySize = new Dimension(
                mcInstance.displayWidth, mcInstance.displayHeight);
            // -- Resize the display to the target screenshot size
            mcInstance.resize(
                this.targetScreenshotSize.getWidth(),
                this.targetScreenshotSize.getHeight());
            result = TaskRunResult.successRerun(this);
        } else if (this.frameCount > 0 && this.frameCount < 3) {
            // -- Wait for the re-render to finish
            result = TaskRunResult.successRerun(this);
        } else {
            // -- Capture the screenshot
            // TODO: Implement the screenshot capture logic here.
            // -- Restore the original framebuffer state
            if (this.originalDisplaySize != null) {
                mcInstance.resize(
                    this.originalDisplaySize.getWidth(),
                    this.originalDisplaySize.getHeight());
            }
            this.originalDisplaySize = null;
            result = TaskRunResult.successNoRerun(this);
        }
        this.frameCount += 1;
        return result;
    }
}
