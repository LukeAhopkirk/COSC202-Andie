package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to flip an image vertically.
 * </p>
 * 
 */
public class Rotate90Left implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Default Constructor
     * </p>
     */
    Rotate90Left() {
    }

    /**
     * <p>
     * Applys a rotation of 90 degrees to the image.
     * </p>
     * 
     * <p>
     * It makes a new image with the height of the old width and the width of
     * old height. It then Uses a nested for loop iterating over the pixels of an
     * image.
     * Horizontally (from left to right) and vertically (from top to bottom).
     * From the top left, the code swaps its color value with the color value of the
     * pixel starting from the bottom right of the new image.
     * </p>
     * 
     * @param input The image to flip vertically.
     * @return The resulting (flipped) image
     */

    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(height, width, input.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int xFlip = y;
                int yFlip = width - x - 1;
                int pixel = input.getRGB(x, y);

                output.setRGB(xFlip, yFlip, pixel);
            }
        }

        return output;
    }
}
