package com.glektarssza.gtnh_customizer.utils;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class ScreenshotUtils {
    /**
     * An enumeration of the screenshot formats.
     *
     * Not all formats are supported by all systems.
     */
    public enum ScreenshotFormat {
        /**
         * PNG (Portable Network Graphics) format.
         *
         * Probably decent.
         */
        PNG("image/png"),

        /**
         * JPEG (Joint Photographic Experts Group) format.
         *
         * Mid.
         */
        JPEG("image/jpeg"),

        /**
         * BMP (Bitmap) format.
         *
         * Thicc.
         */
        BMP("image/bmp"),

        /**
         * TIFF (Tagged Image File) format.
         *
         * Eh... It's okay.
         */
        TIFF("image/tiff"),

        /**
         * WEBP (Web Picture format) format.
         *
         * Uhhhh... Probably not.
         */
        WEBP("image/webp"),

        /**
         * GIF (Graphics Interchange Format) format.
         *
         * Why would you ever...
         */
        GIF("image/gif");

        /**
         * A list of MIME types associated with this format.
         */
        private final String[] mimeTypes;

        private ScreenshotFormat(String... mimeTypes) {
            this.mimeTypes = mimeTypes;
        }

        /**
         * Check if this screenshot format is supported by the current system.
         *
         * @return {@code true} if the format is supported, {@code false}
         *         otherwise.
         */
        public boolean isSupported() {
            return Arrays.stream(ImageIO.getWriterMIMETypes())
                .anyMatch((mimeType) -> Arrays.stream(this.mimeTypes)
                    .anyMatch(mimeType::equalsIgnoreCase));
        }
    }

    /**
     * Flip pixel data vertically.
     *
     * @param pixels The pixel data to flip vertically.
     * @param width The width of the image in pixels.
     * @param height The height of the image in pixels.
     * @param bytesPerPixel The number of bytes per pixel.
     *
     * @return A new {@link ByteBuffer} containing the flipped pixel data.
     */
    public static ByteBuffer flipPixelDataVertically(ByteBuffer pixels,
        int width, int height, int bytesPerPixel) {
        ByteBuffer flippedPixels = ByteBuffer.allocate(pixels.capacity());
        byte[] row = new byte[width * bytesPerPixel];
        for (int y = 0; y < height; y++) {
            pixels.position(y * width * bytesPerPixel);
            pixels.get(row);
            int flippedY = height - y - 1;
            flippedPixels.position(flippedY * width * bytesPerPixel);
            flippedPixels.put(row);
        }
        flippedPixels.flip();
        return flippedPixels;
    }

    public static void flipPixelDataInPlaceVertically(ByteBuffer pixels,
        int width) {
        // TODO
    }

    public static void saveScreenshot(ByteBuffer pixels, int width, int height,
        int bytesPerPixel, File directory) {

    }
}
