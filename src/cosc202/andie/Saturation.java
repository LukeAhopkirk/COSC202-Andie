package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to change the Saturation of an image
 * </p>
 */
public class Saturation implements ImageOperation, java.io.Serializable {
    private int multiplier = -1;

    /**
     * <p>
     * Default Constructor
     * </p>
     */
    Saturation() {
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
    Saturation(int multiplier) {
        this.multiplier = multiplier;
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
        float[] hsl = new float[3];

        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                int pixel = input.getRGB(x, y);
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;

                Color.RGBtoHSB(red, green, blue, hsl);
                hsl[1] = Math.max(0, Math.min(1, hsl[1] * (1 + (multiplier / 100f))));

                int rgb = Color.HSBtoRGB(hsl[0], hsl[1], hsl[2]);
                input.setRGB(x, y, (alpha << 24) | (rgb & 0xffffff));
            }
        }
        return input;
    }
}