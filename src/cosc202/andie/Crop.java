package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to flip an image horizontally.
 * </p>
 * 
 */
public class Crop implements ImageOperation {
    static int x, y, width, height;

<<<<<<< HEAD

=======
>>>>>>> 9d2f9338d37208414d1f901e872d5147b3094878
    /**
     * <p>
     * Default Constructor
     * </p>
     */
    Crop(int x, int y, int width, int height) {
        Crop.x = x;
        Crop.y = y;
        Crop.width = width;
        Crop.height = height;

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
        BufferedImage output = input.getSubimage(x, y, width, height);
        return output;
    }

}
