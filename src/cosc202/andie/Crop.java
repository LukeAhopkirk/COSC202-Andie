package cosc202.andie;

import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

/**
 * <p>
 * ImageOperation to crop an image.
 * </p>
 * 
 */
public class Crop implements ImageOperation, java.io.Serializable {
    static int x, y, width, height;

    /**
     * <p>
     * Constructor for crop class
     * </p>
     * 
     * @param x      The x coordinate of the top left corner of the crop
     * @param y      The y coordinate of the top left corner of the crop
     * @param width  The width of the crop
     * @param height The height of the crop
     */
    Crop(int x, int y, int width, int height) {
        Crop.x = x;
        Crop.y = y;
        Crop.width = width;
        Crop.height = height;

    }

    /**
     * <p>
     * Apply a crop to an image.
     * </p>
     * 
     * <p>
     * Crops the image to the specified dimensions, and returns the resulting
     * image. If the crop is outside the bounds of the image, the crop is
     * adjusted to fit within the image.
     * </p>
     * 
     * @param input The image to be cropped
     * @return The resulting (cropped) image
     */
    public BufferedImage apply(BufferedImage input) {
        int initialWidth = input.getWidth();
        int initialHeight = input.getHeight();
        if (x + width > initialWidth) {
            width = initialWidth - x;
        }
        if (y + height > initialHeight) {
            height = initialHeight - y;
        }
        if (y > initialHeight || x > initialWidth) {
            JOptionPane.showMessageDialog(null, "Please Select and area inside the image!");
            return input;
        }
        BufferedImage output = input.getSubimage(x, y, width, height);
        return output;
    }

}
