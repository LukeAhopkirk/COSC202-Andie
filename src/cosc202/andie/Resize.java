package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to Resize an image
 * </p>
 * 
 * <p>
 * Changes the height and width of an image
 * to a certain degree
 * </p>
 * 
 */
public class Resize implements ImageOperation {

    private double multiplier = 100;

    /**
     * <p>
     * Default Constructor
     * </p>
     */
    Resize() {
    }

    /**
     * <p>
     * Resize image with given multiplier
     * </p>
     * 
     * <p>
     * Multiplier is the percentage Resize applied.
     * </p>
     * 
     * @param multiplier Percentage of current image that will be the new size
     *                   e.g. 10% will produce an image 10% of the size
     */
    Resize(double multiplier) {
        if (multiplier < 0) {
            throw new IllegalArgumentException("Multiplier cannot be negative.");
        }
        this.multiplier = multiplier;
    }

    /**
     * <p>
     * Apply Resize to an image.
     * </p>
     * 
     * <p>
     * Alters the images height and width by finding
     * current dimensions with .getHeight() & getWidth(),
     * to then create a new BufferedImage with resized dimensions
     * </p>
     * 
     * @param input The image to apply the Resize to.
     * @return The resulting (resized) image.
     */
    public BufferedImage apply(BufferedImage input) {

        BufferedImage changeBuff = new BufferedImage(input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);

        int newW = (int) (changeBuff.getWidth() * (multiplier / 100));
        int newH = (int) (changeBuff.getHeight() * (multiplier / 100));

        // Convert Image to BufferedImage
        BufferedImage output = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics g = output.createGraphics();
        g.drawImage(changeBuff, 0, 0, newW, newH, null);
        g.dispose();

        return output;
    }
}