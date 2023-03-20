

package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Contrast implements ImageOperation {

    private double brightness = 0;

    private int multiplier = 25;

    //Default constructor
    Contrast(){}
    

    Contrast(int multiplier){
        this.multiplier = multiplier;
    }
    //Constructor

    //Method to decrease image brightness by 25%
    public BufferedImage apply(BufferedImage input){

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
    // Mutator for contrast
    public void setBrightness(double brightness){
        this.brightness = brightness;
    }

    // Method to apply equation to pixel colour
    private int pixelConverter(int oldColour, double contrast){
        int v_dash = (int) ((1+(contrast/100))*(oldColour - 127.5) + 127.5 * (1 + (brightness/100)));
        if(v_dash > 255){
            v_dash = 255;
        }else if(v_dash < 0){
            v_dash = 0;
        }
        return v_dash;
    }

}
