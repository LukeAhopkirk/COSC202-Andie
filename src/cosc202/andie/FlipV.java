//COMPLETED Flipping image vertically

package cosc202.andie;

import java.awt.image.BufferedImage;

public class FlipV implements ImageOperation {

    FlipV(){}
    //Constructor

    public BufferedImage apply(BufferedImage input){

        for (int y = 0; y <= (input.getHeight()/2); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int firstPix = input.getRGB(x, y);
                int secondPix = input.getRGB(x, input.getHeight() - y - 1);
                
                input.setRGB(x, input.getHeight() - y - 1, firstPix);
                input.setRGB(x, y, secondPix);
            }
        }

        return input;

    }



}
