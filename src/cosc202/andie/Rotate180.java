package cosc202.andie;

import java.awt.image.BufferedImage;

public class Rotate180 implements ImageOperation {

    Rotate180(){
    }

    public BufferedImage apply(BufferedImage input){

        for (int y = 0; y < (input.getHeight()/2); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                int selectedPixel = input.getRGB(x,y);
                int writtenPixel = input.getRGB(input.getWidth() - x -1, input.getHeight() - y - 1);
                
                input.setRGB(input.getWidth() - x - 1, input.getHeight() - y - 1, selectedPixel);
                input.setRGB(x, y, writtenPixel);
            }
        }

        return input;
        
    }
    
}
