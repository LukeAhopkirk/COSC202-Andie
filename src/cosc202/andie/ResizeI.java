package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ResizeI implements ImageOperation {
    ResizeI(){}
    //Constructor

    //Method to resize an image based on new width and new height
    public BufferedImage apply(BufferedImage input){

        BufferedImage changeBuff = new BufferedImage(input.getColorModel(),
            input.copyData(null),
            input.isAlphaPremultiplied(), null);

        int newW = (int) (changeBuff.getWidth()*1.5);
        int newH = (int) (changeBuff.getHeight()*1.5);

        // Convert Image to BufferedImage
        BufferedImage output = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        
        Graphics g = output.createGraphics();
        g.drawImage(changeBuff, 0, 0, newW, newH, null);
        g.dispose();
        
        

        return output;


    }



}
