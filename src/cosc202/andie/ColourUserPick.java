package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * <p>
 * Changing the desired colour to be used by the user.
 * </p>
 * 
 */
public class ColourUserPick implements ImageOperation, java.io.Serializable {

    private int x;
    private int y;

    /**
     * <p>
     * Constructor refering to a specific pixel.
     * </p>
     * 
     * @param x The x coordinate of the selected pixel
     * @param y The y corrdinate of the selected pixel
     */
    ColourUserPick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * <p>
     * Is passed in co-ordinates of a pixel and sets the colour
     * to be used for drawing operations.
     * </p>
     * 
     * @param input The image to be drawn on
     * @return The image.
     */
    public BufferedImage apply(BufferedImage input) {
        int pixel = input.getRGB(x, y);
        Color selectedColor = new Color(pixel);
        DrawActions.setMyColour(selectedColor);
        return input;
    }

}
