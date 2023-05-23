package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to flip an image vertically.
 * </p>
 * 
 */

public class Rotate180 implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Default Constructor
     * </p>
     */
    Rotate180() {
    }

    /**
     * <p>
     * Apply a vertical flip to an image.
     * </p>
     * 
     * <p>
     * Uses a nested loop iterating over the pixels of an image.
     * Horizontally (from left to right) and vertically (from top to bottom).
     * For each pixel, the code swaps its color value with the color value of the
     * pixel on the opposite side of the image along the vertical axis and
     * horizontal axis.
     * </p>
     * 
     * @param input The image to flip vertically.
     * @return The resulting (flipped) image
     */

    public BufferedImage apply(BufferedImage input) {

        for (int y = 0; y < (input.getHeight() / 2); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                int selectedPixel = input.getRGB(x, y);
                int writtenPixel = input.getRGB(input.getWidth() - x - 1, input.getHeight() - y - 1);

                input.setRGB(input.getWidth() - x - 1, input.getHeight() - y - 1, selectedPixel);
                input.setRGB(x, y, writtenPixel);
            }
        }

        return input;

    }

}
