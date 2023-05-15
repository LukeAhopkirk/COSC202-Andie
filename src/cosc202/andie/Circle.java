package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw a circle on an image
 * </p>
 * 
 */
public class Circle implements ImageOperation {

    private static Color currColour;

    /**
     * <p>
     * Default Constructor
     * </p>
     */
    Circle(Color currColour) {
        Circle.currColour = currColour;
    }

    /**
     * <p>
     * Apply a circle drawing to an image.
     * </p>
     * 
     * <p>
     * Write how it works here
     * </p>
     * 
     * @param input The image to be drawn on
     * @return The resulting (drawn on) image
     */
    public BufferedImage apply(BufferedImage input) {

        // image is a BufferedImage
        Graphics2D g2d = input.createGraphics();
        g2d.setColor(Circle.currColour);
        g2d.drawOval(100, 100, 100, 100); // (x, y, width, height)
        g2d.dispose();

        return input;
    }

}
