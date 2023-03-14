//NOT FINISHED - RICHARD

package cosc202.andie;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resize {
    Resize(){}
    //Constructor

    //Method to resize an image based on new width and new height
    public BufferedImage resizeImage(BufferedImage input, int newW, int newH){

        int currW = input.getWidth();
        int currH = input.getHeight();

        // Scale image to new width/height
        Image changeIm = input.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);

        


        BufferedImage output = BufferedImage(changeIm.getWidth(null), changeIm.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        return output;
    }

}
