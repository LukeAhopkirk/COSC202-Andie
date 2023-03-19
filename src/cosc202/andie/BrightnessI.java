// COMPLETED - Brightness Increase

package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class BrightnessI implements ImageOperation {

    private double contrastD = 0;

    BrightnessI(){}
    //Constructor

    //Method to increase image brightness by 25%
    public BufferedImage apply(BufferedImage input){

        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                Color col = new Color(input.getRGB(x, y));

                int oldRed = col.getRed();
                int oldGreen = col.getGreen();
                int oldBlue = col.getBlue();

                int newRed = pixelConverter(oldRed, 25);
                int newGreen = pixelConverter(oldGreen, 25);
                int newBlue = pixelConverter(oldBlue, 25);

                int newPix = new Color(newRed, newGreen, newBlue).getRGB();

                input.setRGB(x, y, newPix);
            }
        }

        return input;

    }
    // Mutator for contrast
    public void setContast(double contrast){
        this.contrastD = contrast;
    }

    // Method to apply equation to pixel colour
    private int pixelConverter(int oldColour, double brightness){
        int v_dash = (int) ((1+(contrastD/100))*(oldColour - 127.5) + 127.5 * (1 + (brightness/100)));
        if(v_dash > 255){
            v_dash = 255;
        }else if(v_dash < 0){
            v_dash = 0;
        }
        return v_dash;
    }

}
