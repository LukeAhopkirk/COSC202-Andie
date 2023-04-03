package cosc202.andie;

import java.awt.image.BufferedImage;

public class Rotate90Left implements ImageOperation{
    
    Rotate90Left(){
        //default contructor
    }


    public BufferedImage apply(BufferedImage input){
        //The following code  makes a new buffered image to the new size of the image
            
       BufferedImage inputImage = new BufferedImage(input.getColorModel(),input.copyData(null),
       input.isAlphaPremultiplied(), null); //Makes a new inputImage with new Dimentions of the rotated image
            
        int height = inputImage.getHeight(); //Makes the newHeight the old width
        int width = inputImage.getWidth();  //Makes the New Width the old height

        int newHeight = width;
        int newWidth = height;
        
        BufferedImage output = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);  

        for(int y = 0; y < (input.getHeight()); y++){
                for(int x = 0; x < input.getWidth(); x++){
                    int xFlip = y; //Because the pixel from 0,0 (Top Left of the image is being selected we need to get the location for its new image at x = 0 and y = width - x - 1 to be the bottom left corner of the image
                    int yFlip = width - x - 1; 
                    int pixel = inputImage.getRGB(x,y);

                    output.setRGB(xFlip,yFlip, pixel);
                } //  co ord at 0,0 needs to be at x = 0 and heigh of -y so y flip = 
            }
        return output;
        
    }
    
}
