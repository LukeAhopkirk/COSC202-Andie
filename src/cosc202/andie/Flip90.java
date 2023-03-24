package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Flip90 implements ImageOperation{
    
    Flip90(){
        //default contructor
    }


    public BufferedImage apply(BufferedImage input){
        //The following code  makes a new buffered image to the new size of the image
            
       BufferedImage inputImage = new BufferedImage(input.getColorModel(),input.copyData(null),
       input.isAlphaPremultiplied(), null);
            
        int height = inputImage.getHeight(); //Makes the newHeight the old width
        int width = inputImage.getWidth();  //Makes the New Width the old height

        int newHeight = width;
        int newWidth = height;
        
        BufferedImage output = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);  

        for(int y = 0; y < (input.getHeight()); y++){
            for(int x = 0; x < input.getWidth(); x++){
                int xFlip = height - y - 1  ;
                int yFlip = x;
                int pixel = inputImage.getRGB(x,y);

                output.setRGB(xFlip,yFlip, pixel);
            }   
        }
        return output;
        
    }
    
}
