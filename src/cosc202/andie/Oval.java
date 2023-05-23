package cosc202.andie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw an oval on an image
 * </p>
 * 
 */
public class Oval implements ImageOperation, java.io.Serializable {

    private static Color currColour;
    private static int x;
    private static int y;
    private static int width;
    private static int height;
    private static boolean fill;

    /**
     * <p>
     * Constructor for oval class
     * </p>
     * 
     * @param currColour The colour to be used for drawing
     * @param x          The x coordinate of the oval
     * @param y          The y coordinate of the oval
     * @param width      The width of the oval
     * @param height     The height of the oval
     * @param fill       Whether the oval should be filled or not
     *                   </p>
     */
    Oval(Color currColour, int x, int y, int width, int height, boolean fill) {
        Oval.currColour = currColour;
        Oval.x = x;
        Oval.y = y;
        Oval.width = width;
        Oval.height = height;
        Oval.fill = fill;
    }

    /**
     * <p>
     * Apply a oval drawing to an image.
     * </p>
     * 
     * <p>
     * Draws a oval on the image using the current colour and co-oridnates,
     * and returns the resulting image. If the fill flag is set, the oval
     * will be filled, otherwise it will be drawn as a border.
     * </p>
     * 
     * @param input The image to be drawn on
     * @return The resulting (drawn on) image
     */
    public BufferedImage apply(BufferedImage input) {
        if (fill) {
            Graphics2D g2d = input.createGraphics();
            g2d.setColor(Oval.currColour);
            g2d.setStroke(new BasicStroke(1));

            int offsetX = 2; // Adjust the offset to control the size reduction
            int offsetY = 2;
            int smallerWidth = Oval.width - (2 * offsetX);
            int smallerHeight = Oval.height - (2 * offsetY);
            int smallerX = Oval.x + offsetX;
            int smallerY = Oval.y + offsetY;

            g2d.fillOval(smallerX, smallerY, smallerWidth, smallerHeight);
            g2d.dispose();
        } else {
            Graphics2D g2d = input.createGraphics();
            g2d.setColor(Oval.currColour);
            g2d.setStroke(new BasicStroke(4));
            g2d.drawOval(Oval.x, Oval.y, Oval.width, Oval.height);
            g2d.dispose();
        }

        return input;
    }

}
