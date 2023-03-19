//COMPLETED Flipping image horizontally

package cosc202.andie;

import java.awt.image.BufferedImage;

public class FlipH implements ImageOperation {

    FlipH(){}
    //Constructor

    public BufferedImage apply(BufferedImage input){

        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x  <= (input.getWidth()/2); ++x) {
                int firstPix = input.getRGB(x, y);
                int secondPix = input.getRGB(input.getWidth() - x - 1, y);
                
                input.setRGB(input.getWidth() - x - 1, y, firstPix);
                input.setRGB(x, y, secondPix);
            }
        }

        return input;

    }



}
