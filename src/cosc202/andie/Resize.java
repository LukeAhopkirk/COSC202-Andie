//I don't know how to add it to andie
//Also I think we need to add a new tab to andie cuz it doesn't fit under any existing one

package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Resize {
    Resize(){}
    //Constructor

    //Method to resize an image based on new width and new height
    public BufferedImage resizeImage(BufferedImage input, int newW, int newH){
        try{
            // Scale image to new width/height
        Image changeIm = input.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);

        // Convert Image to BufferedImage
        BufferedImage output = new BufferedImage(changeIm.getWidth(null), changeIm.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        
        return output;

        } catch(Exception ex){
            // Catch errors and prints the problem out
            System.out.println("Error: Resize function \n Problem: " + ex);
            // Return original image if there is an error
            return input;
        }
        
    }

}
