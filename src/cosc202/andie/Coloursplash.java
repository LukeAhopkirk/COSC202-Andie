package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to change all the pixels grey in an image,
 * besides a selected colour chosen by the user.
 * </p>
 */
public class Coloursplash implements ImageOperation, java.io.Serializable {
    private int x, y;;

    /**
     * <p>
     * Default Constructor
     * </p>
     */
    Coloursplash() {
    }

    /**
     * <p>
     * Constructor refering to a specific pixel.
     * </p>
     * 
     * @param x The x coordinate of the selected pixel
     * @param y The y corrdinate of the selected pixel
     */
    Coloursplash(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * <p>
     * Apply coloursplash to an image.
     * </p>
     * 
     * <p>
     * Iterates over each pixes and checks if the colour is
     * within a certian threshold of the selected colour, if not
     * the pixel is grescaled.
     * </p>
     * 
     * @param input The image to coloursplashed
     * @return The resulting image (with all other colours greyscaled).
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        int threshold = 40;

        int pixel = input.getRGB(x, y);
        Color selectedColor = new Color(pixel);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int currpixel = input.getRGB(x, y);
                Color currColor = new Color(currpixel);
                int red = currColor.getRed();
                int green = currColor.getGreen();
                int blue = currColor.getBlue();
                int avg = (red + green + blue) / 3;
                int diffRed = Math.abs(selectedColor.getRed() - red);
                int diffGreen = Math.abs(selectedColor.getGreen() - green);
                int diffBlue = Math.abs(selectedColor.getBlue() - blue);

                if (diffRed > threshold || diffGreen > threshold || diffBlue > threshold) {
                    input.setRGB(x, y, new Color(avg, avg, avg).getRGB());
                }
            }
        }

        return input;
    }
}