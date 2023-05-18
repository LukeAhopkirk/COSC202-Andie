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
        int newW = (int) (input.getWidth() * (multiplier / 100));
        int newH = (int) (input.getHeight() * (multiplier / 100));

        // Create a new BufferedImage with the desired dimensions and ARGB type
        BufferedImage output = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        // Create a Graphics2D object for drawing on the output image
        Graphics2D g2d = output.createGraphics();

        // Set rendering hints for better quality and smooth resizing
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Draw the resized image onto the output image
        g2d.drawImage(input, 0, 0, newW, newH, null);

        // Dispose the Graphics2D object
        g2d.dispose();

        return output;
    }
}