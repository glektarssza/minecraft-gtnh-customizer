package com.glektarssza.gtnh_customizer.client.big_screenshots;

import net.minecraft.client.Minecraft;

import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

import com.glektarssza.gtnh_customizer.api.big_screenshots.AbstractRenderTickTask;

/**
 * A render tick task for capturing screenshots.
 */
public class CaptureTask extends AbstractRenderTickTask {
    /**
     * The number of elapsed frames since the task started running.
     */
    protected int frameCount = 0;

    /**
     * The initial display width of the Minecraft client.
     */
    protected int initialDisplayWidth = -1;

    /**
     * The initial display height of the Minecraft client.
     */
    protected int initialDisplayHeight = -1;

    /**
     * The width of the screenshot to be captured.
     *
     * If this is less than or equal to {@code 0}, the screenshot will be
     * captured at the current Minecraft display width.
     */
    public final int screenshotWidth;

    /**
     * The height of the screenshot to be captured.
     *
     * If this is less than or equal to {@code 0}, the screenshot will be
     * captured at the current Minecraft display height.
     */
    public final int screenshotHeight;

    /**
     * Create a new instance.
     */
    public CaptureTask(int screenshotWidth, int screenshotHeight) {
        super("CaptureTask");
        this.screenshotWidth = screenshotWidth;
        this.screenshotHeight = screenshotHeight;
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
    public void onRenderTick(RenderTickEvent event) {
        if (!this.isSetup()) {
            throw new IllegalStateException(
                "Cannot run a task that is not set up");
        }
        if (this.isDone()) {
            throw new IllegalStateException(
                "Cannot run a task that has already run without tearing it down");
        }
        if (this.isRunning()) {
            throw new IllegalStateException(
                "Cannot run a task that is already running");
        }
        this.isRunning = true;
        switch (this.frameCount) {
            case 0:
                // -- Backup the Minecraft settings
                this.initialDisplayWidth = Minecraft
                    .getMinecraft().displayWidth;
                this.initialDisplayHeight = Minecraft
                    .getMinecraft().displayHeight;

                // -- Resize the viewport to the target size
                int targetWidth = this.screenshotWidth > 0
                    ? this.screenshotWidth
                    : this.initialDisplayWidth;
                int targetHeight = this.screenshotHeight > 0
                    ? this.screenshotHeight
                    : this.initialDisplayHeight;
                Minecraft.getMinecraft().resize(targetWidth, targetHeight);
                break;
            case 1:
                // -- Game renders as a black frame because we resized the
                // -- viewport
                break;
            case 2:
                // -- Game actually renders on this frame
                break;
            case 3:
                try {
                    // TODO: Capture the screenshot
                } finally {
                    // -- Restore the viewport to the original size
                    Minecraft.getMinecraft().resize(this.initialDisplayWidth,
                        this.initialDisplayHeight);
                }
                break;
            default:
                this.isDone = true;
                break;
        }
        this.frameCount += 1;
    }
}
