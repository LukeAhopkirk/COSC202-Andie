package cosc202.andie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw a line on an image
 * </p>
 * 
 */
public class Line implements ImageOperation, java.io.Serializable {

    private static Color currColour;
    private static int x1;
    private static int y1;
    private static int x2;
    private static int y2;

    /**
     * <p>
     * Constructor
     * 
     * @param currColour The colour to be used for drawing
     * @param x1         The x coordinate of the start of the line
     * @param y1         The y coordinate of the start of the line
     * @param x2         The x coordinate of the end of the line
     * @param y2         The y coordinate of the end of the line
     *                   </p>
     */
    Line(Color currColour, int x1, int y1, int x2, int y2) {
        Line.currColour = currColour;
        Line.x1 = x1;
        Line.y1 = y1;
        Line.x2 = x2;
        Line.y2 = y2;
    }

    /**
     * <p>
     * Apply a line drawing to an image.
     * </p>
     * 
     * <p>
     * Draws a line on the image using the current colour and co-oridnates,
     * and returns the resulting image.
     * </p>
     * 
     * @param input The image to be drawn on
     * @return The resulting (drawn on) image
     */
    public BufferedImage apply(BufferedImage input) {

        // image is a BufferedImage
        Graphics2D g2d = input.createGraphics();
        g2d.setColor(Line.currColour);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(Line.x1, Line.y1, Line.x2, Line.y2); // (x1, y1, x2, y2)
        g2d.dispose();

        return input;
    }
}
