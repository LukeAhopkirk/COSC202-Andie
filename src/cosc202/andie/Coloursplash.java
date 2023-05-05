package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to change the Saturation of an image
 * </p>
 */
public class Coloursplash implements ImageOperation {
    private int pixel = 1;

    /**
     * <p>
     * Default Constructor
     * </p>
     */
    Coloursplash() {
    }

    /**
     * <p>
     * Construct a Contrast filter with the given multiplier.
     * </p>
     * 
     * <p>
     * Higher multiplier produces an image with more contrast.
     * Lower multiplier produces an image with less contrast.
     * </p>
     * 
     * @param multiplier Strength of the contrast filter.
     */
    Coloursplash(int pixel) {
        this.pixel = pixel;
    }

    /**
     * <p>
     * Apply saturation to an image.
     * </p>
     * 
     * <p>
     * Iterates over each pixes and changes the
     * values to saturate
     * </p>
     * 
     * @param input The image to change saturation
     * @return The resulting image (with altered saturation).
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        int threshold = 100;
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