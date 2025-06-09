package com.glektarssza.gtnh_customizer.client.screenshots;

import java.nio.ByteBuffer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Dimension;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;

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
     * The number of bytes per pixel for a RGB format.
     */
    public static final int BYTES_PER_PIXEL_RGB = 3;

    /**
     * The number of bytes per pixel for a RGBA format.
     */
    public static final int BYTES_PER_PIXEL_RGBA = 4;

    /**
     * The number of bytes per pixel in the screenshot.
     *
     * By default this is set to the {@link #BYTES_PER_PIXEL_RGB} value which is
     * consistent with how we want the data; in raw, uncompressed RGB values.
     */
    public static final int BYTES_PER_PIXEL = BYTES_PER_PIXEL_RGB;

    /**
     * The OpenGL format for RGB pixel data.
     */
    public static final int GL_FORMAT_RGB = GL11.GL_RGB;

    /**
     * The OpenGL format for RGBA pixel data.
     */
    public static final int GL_FORMAT_RGBA = GL11.GL_RGBA;

    /**
     * The OpenGL format for the pixel data in the screenshot.
     *
     * By default this is set to the {@link #GL_FORMAT_RGB} value which is
     * consistent with how we want the data; in raw, uncompressed RGB values.
     */
    public static final int GL_FORMAT = GL_FORMAT_RGB;

    /**
     * The OpenGL data type for unsigned byte pixel data.
     */
    public static final int GL_DATA_TYPE_UNSIGNED_BYTE = GL11.GL_UNSIGNED_BYTE;

    /**
     * The OpenGL data type for the pixel data in the screenshot.
     *
     * By default this is set to the {@link #GL_DATA_TYPE_UNSIGNED_BYTE} value
     * which is consistent with how we want the data; in raw, uncompressed RGB
     * values.
     */
    public static final int GL_DATA_TYPE = GL_DATA_TYPE_UNSIGNED_BYTE;

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
            ByteBuffer framebufferData = ByteBuffer.allocateDirect(
                this.targetScreenshotSize.getWidth() *
                    this.targetScreenshotSize.getHeight() *
                    BYTES_PER_PIXEL);
            // -- Capture the screenshot
            GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, 1);
            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
            if (OpenGlHelper.isFramebufferEnabled()) {
                mcInstance.getFramebuffer().bindFramebufferTexture();
                GL11.glGetTexImage(GL11.GL_TEXTURE_2D, 0, GL_FORMAT,
                    GL_DATA_TYPE, framebufferData);
            } else {
                GL11.glReadPixels(0, 0, this.targetScreenshotSize.getWidth(),
                    this.targetScreenshotSize.getHeight(), GL_FORMAT,
                    GL_DATA_TYPE, framebufferData);
            }
            // QUESTION: Do we need to flip the data vertically?
            // -- Save the screenshot data

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
