package cosc202.andie;

import java.awt.image.*;

public class Sharpen implements ImageOperation, java.io.Serializable{
    
    Sharpen(){
        // Constructor
    }

     // Actual application of the blur
    public BufferedImage apply (BufferedImage input){
       float [] array = { 0 , -1/2.0f, 0,
        -1/2.0f, 3, -1/2.0f,
        0 , -1/2.0f, 0 };
        
        //Make a 3x3 filter from the Array
        Kernel kernel = new Kernel(3,3, array);

        // Apply this as a convolution - same code as in MeanFilter (DONT UNDERSTAND ATM)
        ConvolveOp convOp = new ConvolveOp(kernel); 
    
        // Applying stuff idk
        BufferedImage output = new BufferedImage(input.getColorModel(),
        input.copyData(null),
        input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
    }
}