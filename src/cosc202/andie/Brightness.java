package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to change the Brighness of an image
 * </p>
 * 
 */
public class Brightness implements ImageOperation, java.io.Serializable {

    private int multiplier = 1;

    /**
     * <p>
     * Default Constructor
     * </p>
     */
    Brightness() {
    }

    /**
     * <p>
     * Construct a Brightness filter with the given multiplier
     * </p>
     * 
     * <p>
     * Higher multiplier produces an image with more Brightness
     * Lower multiplier produces an image with less Brightness
     * </p>
     * 
     * @param multiplier Strength of the Brightness filter.
     */
    Brightness(int multiplier) {
        if (multiplier < -100 || multiplier > 100) {
            throw new IllegalArgumentException("Multiplier out of bounds (-100,100).");
        }
        this.multiplier = multiplier;
    }

    /**
     * <p>
     * Apply Brightness change to an image.
     * </p>
     * 
     * <p>
     * Iterates over each pixes and uses pixelConverter method
     * to increase the contrast via RGB values
     * </p>
     * 
     * @param input The image to change the Contrast.
     * @return The resulting image (with changed Contrast).
     */
    public BufferedImage apply(BufferedImage input) {

        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                Color col = new Color(input.getRGB(x, y));

                int oldRed = col.getRed();
                int oldGreen = col.getGreen();
                int oldBlue = col.getBlue();

                int newRed = pixelConverter(oldRed, multiplier);
                int newGreen = pixelConverter(oldGreen, multiplier);
                int newBlue = pixelConverter(oldBlue, multiplier);

                int newPix = new Color(newRed, newGreen, newBlue).getRGB();

                input.setRGB(x, y, newPix);
            }
        }
        return input;
    }

    /**
     * <p>
     * Method to apply Brightness change
     * </p>
     * 
     * @param oldColour  Original colour of a pixel
     * @param brightness Multiplier to be used
     * @return The resulting pixel (with changed Contrast).
     */
    private int pixelConverter(int oldColour, double brightness) {
        int v_dash = (int) ((1 + (0 / 100)) * (oldColour - 127.5) + 127.5 * (1 + (brightness / 100)));
        if (v_dash > 255) {
            v_dash = 255;
        } else if (v_dash < 0) {
            v_dash = 0;
        }
        return v_dash;
    }

}
