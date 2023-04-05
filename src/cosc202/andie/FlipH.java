package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to flip an image horizontally.
 * </p>
 * 
 */
public class FlipH implements ImageOperation {

    /**
     * <p>
     * Default Constructor
     * </p>
     */
    FlipH() {
    }

    /**
     * <p>
     * Apply a horizontal flip to an image.
     * </p>
     * 
     * <p>
     * Uses a nested loop iterating over the pixels of an image.
     * Horizontally (from left to right) and vertically (from top to bottom).
     * For each pixel, the code swaps its color value with the color value of the
     * pixel on the opposite side of the image along the horizontal axis.
     * </p>
     * 
     * @param input The image to flip horizontally.
     * @return The resulting (flipped) image
     */
    public BufferedImage apply(BufferedImage input) {

        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x <= (input.getWidth() / 2); ++x) {
                int firstPix = input.getRGB(x, y);
                int secondPix = input.getRGB(input.getWidth() - x - 1, y);

                input.setRGB(input.getWidth() - x - 1, y, firstPix);
                input.setRGB(x, y, secondPix);
            }
        }
        return input;
    }

}
