package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to increase the Contrast of an image
 * </p>
 */
public class Contrast implements ImageOperation, java.io.Serializable {
    private int multiplier = 1;

    /**
     * <p>
     * Default Constructor
     * </p>
     */
    Contrast() {
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
    Contrast(int multiplier) {
        if (multiplier < 0) {
            throw new IllegalArgumentException("Multiplier cannot be negative.");
        }
        this.multiplier = multiplier;
    }

    /**
     * <p>
     * Apply increased Contrast to an image.
     * </p>
     * 
     * <p>
     * Iterates over each pixes and uses pixelConverter method
     * to increase the contrast via RGB values
     * </p>
     * 
     * @param input The image to increase the Contrast.
     * @return The resulting image (with increased Contrast).
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
     * Method to apply Contrast change
     * </p>
     * 
     * @param oldColour Original colour of a pixel
     * @param contrast  Multiplier to be used
     * @return The resulting pixel (with changed Brightness).
     */
    private int pixelConverter(int oldColour, double contrast) {
        int v_dash = (int) ((1 + (contrast / 100)) * (oldColour - 127.5) + 127.5 * (1 + (0 / 100)));
        if (v_dash > 255) {
            v_dash = 255;
        } else if (v_dash < 0) {
            v_dash = 0;
        }
        return v_dash;
    }

}
