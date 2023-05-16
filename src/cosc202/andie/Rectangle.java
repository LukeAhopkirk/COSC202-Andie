package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw a rectangle on an image
 * </p>
 * 
 */
public class Rectangle implements ImageOperation {

    private static Color currColour;
    private static int x;
    private static int y;
    private static int width;
    private static int height;

    /**
     * <p>
     * Default Constructor
     * </p>
     */
    Rectangle(Color currColour, int x, int y, int width, int height) {
        Rectangle.currColour = currColour;
        Rectangle.x = x;
        Rectangle.y = y;
        Rectangle.width = width;
        Rectangle.height = height;
    }

    /**
     * <p>
     * Apply a rectangle drawing to an image.
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
        g2d.setColor(Rectangle.currColour);
        g2d.drawRect(Rectangle.x, Rectangle.y, Rectangle.width, Rectangle.height); // (x, y, width, height)
        g2d.dispose();

        return input;
    }

}