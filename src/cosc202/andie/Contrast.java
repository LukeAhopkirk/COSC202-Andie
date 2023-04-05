package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Contrast implements ImageOperation {
    private int multiplier = 1;

    // Default constructor
    Contrast() {
    }

    Contrast(int multiplier) {
        if (multiplier < 0) {
            throw new IllegalArgumentException("Multiplier cannot be negative.");
        }
        this.multiplier = multiplier;
    }

    // Method to decrease image brightness by 25%
    public BufferedImage apply(BufferedImage input) {

        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                Color col = new Color(input.getRGB(x, y));

                int oldRed = col.getRed();
                int oldGreen = col.getGreen();
                int oldBlue = col.getBlue();

                int newRed = pixelConverter(oldRed, multiplier);
                int newGreen = pixelConverter(oldGreen, multiplier);
                int newBlue = pixelConverter(oldBlue, multiplier);

                int newPix = new Color(newRed, newGreen, newBlue).getRGB();

                input.setRGB(x, y, newPix);
            }
        }

        return input;

    }

    // Method to apply equation to pixel colour
    private int pixelConverter(int oldColour, double contrast) {
        int v_dash = (int) ((1 + (contrast / 100)) * (oldColour - 127.5) + 127.5 * (1 + (0 / 100)));
        if (v_dash > 255) {
            v_dash = 255;
        } else if (v_dash < 0) {
            v_dash = 0;
        }
        return v_dash;
    }

}
