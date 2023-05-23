package cosc202.andie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw a circle on an image
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
     * Default Constructor
     * </p>
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
