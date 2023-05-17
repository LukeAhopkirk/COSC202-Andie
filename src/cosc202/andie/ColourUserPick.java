package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw a circle on an image
 * </p>
 * 
 */
public class ColourUserPick implements ImageOperation {

    private int x;
    private int y;

    /**
     * <p>
     * Default Constructor
     * </p>
     */
    ColourUserPick(int x, int y) {
        this.x = x;
        this.y = y;
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
        int pixel = input.getRGB(x, y);
        Color selectedColor = new Color(pixel);
        DrawActions.setMyColour(selectedColor);
        return input;
    }

}
