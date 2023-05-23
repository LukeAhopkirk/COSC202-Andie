package cosc202.andie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw a rectangle on an image
 * </p>
 * 
 */
public class Rectangle implements ImageOperation, java.io.Serializable {

    private static Color currColour;
    private static int x;
    private static int y;
    private static int width;
    private static int height;
    private static boolean fill;

    /**
     * <p>
     * Constructor for rectangle class
     * </p>
     * 
     * @param currColour The colour to be used for drawing
     * @param x          The x coordinate of the rectangle
     * @param y          The y coordinate of the rectangle
     * @param width      The width of the rectangle
     * @param height     The height of the rectangle
     *                   </p>
     */
    Rectangle(Color currColour, int x, int y, int width, int height, boolean fill) {
        Rectangle.currColour = currColour;
        Rectangle.x = x;
        Rectangle.y = y;
        Rectangle.width = width;
        Rectangle.height = height;
        Rectangle.fill = fill;
    }

    /**
     * <p>
     * Apply a rectangle drawing to an image.
     * </p>
     * 
     * <p>
     * Draws a rectangle on the image using the current colour and co-oridnates,
     * and returns the resulting image. If the fill flag is set, the rectangle
     * will be filled, otherwise it will be drawn as a border.
     * </p>
     * 
     * @param input The image to be drawn on
     * @return The resulting (drawn on) image
     */
    public BufferedImage apply(BufferedImage input) {
        if (fill) {
            Graphics2D g2d = input.createGraphics();
            g2d.setColor(Rectangle.currColour);
            g2d.setStroke(new BasicStroke(1));

            int offsetX = 2; // Adjust the offset to control the size reduction
            int offsetY = 2;
            int smallerWidth = Rectangle.width + 1 - (2 * offsetX);
            int smallerHeight = Rectangle.height + 1 - (2 * offsetY);
            int smallerX = Rectangle.x + offsetX;
            int smallerY = Rectangle.y + offsetY;

            g2d.fillRect(smallerX, smallerY, smallerWidth, smallerHeight);
            g2d.dispose();
        } else {
            Graphics2D g2d = input.createGraphics();
            g2d.setColor(Rectangle.currColour);
            g2d.setStroke(new BasicStroke(4));
            g2d.drawRect(Rectangle.x, Rectangle.y, Rectangle.width, Rectangle.height);
            g2d.dispose();
        }

        return input;
    }

}