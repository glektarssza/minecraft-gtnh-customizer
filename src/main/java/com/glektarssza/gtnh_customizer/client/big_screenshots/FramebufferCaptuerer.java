package com.glektarssza.gtnh_customizer.client.big_screenshots;

import java.nio.ByteBuffer;

// NOTE: This is bad practice but it's really the best way to this...
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;

/**
 * A utility class for capturing a framebuffer.
 */
public class FramebufferCaptuerer {
    /**
     * The number of bytes per pixel for RGB format.
     */
    public static final int BYTES_PER_PIXEL_RGB = 3;

    /**
     * The number of bytes per pixel for RGBA format.
     */
    public static final int BYTES_PER_PIXEL_RGBA = 4;

    /**
     * The number of bytes per pixel.
     *
     * This is set to {@link #BYTES_PER_PIXEL_RGB} for RGB format as that's what
     * we want in 99% of the cases. Raw, uncompressed pixel data.
     */
    public static final int BYTES_PER_PIXEL = BYTES_PER_PIXEL_RGB;

    /**
     * The pixel format we want to retrieve data in.
     *
     * This is set to {@link GL_UNSIGNED_BYTE} as that's what we want in 99% of
     * the cases. Raw, uncompressed pixel data.
     */
    public static final int DATA_FORMAT = GL_UNSIGNED_BYTE;

    /**
     * Flips the given {@link ByteBuffer} vertically.
     *
     * This method assumes that the buffer is in RGB format and that the
     * contents are of the given width.
     *
     * @param buffer The {@link ByteBuffer} to flip.
     * @param width The width of contents of the buffer in pixels.
     */
    public static void flipBufferVertically(ByteBuffer buffer, int width) {
        int height = buffer.capacity() / (width * BYTES_PER_PIXEL);
        byte[] pixelDataA = new byte[width * BYTES_PER_PIXEL];
        byte[] pixelDataB = new byte[width * BYTES_PER_PIXEL];
        for (int row = height - 1; row >= 0; row -= 1) {
            // -- Read the rows of pixels to be swapped
            buffer.position(row * width * BYTES_PER_PIXEL);
            buffer.get(pixelDataA);
            buffer.position((height - row - 1) * width * BYTES_PER_PIXEL);
            buffer.get(pixelDataB);

            // -- Write the rows of pixels back to the buffer
            buffer.position((height - row - 1) * width
                * BYTES_PER_PIXEL);
            buffer.put(pixelDataA);
            buffer.position(row * width * BYTES_PER_PIXEL);
            buffer.put(pixelDataB);
        }
        // -- Reset the buffer back to the start
        buffer.rewind();
    }

    /**
     * Capture the contents of the given framebuffer.
     *
     * This method will bind the framebuffer but not resize the viewport before
     * capturing it.
     *
     * @param framebuffer The framebuffer to capture.
     *
     * @return A {@link ByteBuffer} containing the pixel data of the
     *         framebuffer.
     */
    public static ByteBuffer captureFramebuffer(Framebuffer framebuffer) {
        return captureFramebuffer(framebuffer, true, false);
    }

    /**
     * Capture the contents of the given framebuffer.
     *
     * This method will bind the framebuffer before capturing it.
     *
     * @param framebuffer The framebuffer to capture.
     * @param resizeViewport Whether to resize the viewport before capturing.
     *
     * @return A {@link ByteBuffer} containing the pixel data of the
     *         framebuffer.
     */
    public static ByteBuffer captureFramebuffer(Framebuffer framebuffer,
        boolean resizeViewport) {
        return captureFramebuffer(framebuffer, true, resizeViewport);
    }

    /**
     * Capture the contents of the given framebuffer.
     *
     * @param framebuffer The framebuffer to capture.
     * @param bindFramebuffer Whether to bind the framebuffer before capturing.
     * @param resizeViewport Whether to resize the viewport before capturing.
     *
     * @return A {@link ByteBuffer} containing the pixel data of the
     *         framebuffer.
     */
    public static ByteBuffer captureFramebuffer(Framebuffer framebuffer,
        boolean bindFramebuffer, boolean resizeViewport) {
        int dataSize = framebuffer.framebufferWidth
            * framebuffer.framebufferHeight * BYTES_PER_PIXEL;
        ByteBuffer pixelData = ByteBuffer.allocateDirect(dataSize);
        // -- Configure the pixel store modes
        glPixelStorei(GL_PACK_ALIGNMENT, 1);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        if (bindFramebuffer) {
            framebuffer.bindFramebuffer(resizeViewport);
        }
        if (OpenGlHelper.isFramebufferEnabled()) {
            glBindTexture(GL_TEXTURE_2D, framebuffer.framebufferTexture);
            glGetTexImage(GL_TEXTURE_2D, 0, GL_BGR, DATA_FORMAT, pixelData);
        } else {
            // NOTE: This is MUCH slower than the framebuffer method
            glReadPixels(0, 0, framebuffer.framebufferWidth,
                framebuffer.framebufferHeight, GL_BGR, DATA_FORMAT, pixelData);
        }
        return pixelData;
    }

    /**
     * Capture the contents of the current Minecraft client framebuffer.
     *
     * This method will bind the framebuffer but not resize the viewport before
     * capturing it.
     *
     * @return A {@link ByteBuffer} containing the pixel data of the current
     *         Minecraft client framebuffer.
     */
    public static ByteBuffer captureCurrentFramebuffer() {
        return captureCurrentFramebuffer(false);
    }

    /**
     * Capture the contents of the current Minecraft client framebuffer.
     *
     * This method will bind the framebuffer before capturing it.
     *
     * @param resizeViewport Whether to resize the viewport before capturing.
     *
     * @return A {@link ByteBuffer} containing the pixel data of the current
     *         Minecraft client framebuffer.
     */
    public static ByteBuffer captureCurrentFramebuffer(boolean resizeViewport) {
        return captureFramebuffer(Minecraft.getMinecraft().getFramebuffer(),
            true, resizeViewport);
    }

    /**
     * Capture the contents of the current Minecraft client framebuffer.
     *
     * @param bindFramebuffer Whether to bind the framebuffer before capturing.
     * @param resizeViewport Whether to resize the viewport before capturing.
     *
     * @return A {@link ByteBuffer} containing the pixel data of the current
     *         Minecraft client framebuffer.
     */
    public static ByteBuffer captureCurrentFramebuffer(boolean bindFramebuffer,
        boolean resizeViewport) {
        return captureFramebuffer(Minecraft.getMinecraft().getFramebuffer(),
            bindFramebuffer, resizeViewport);
    }
}
