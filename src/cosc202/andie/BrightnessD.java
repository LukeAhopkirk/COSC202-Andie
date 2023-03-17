package cosc202.andie;


import java.awt.image.BufferedImage;

public class BrightnessD implements ImageOperation {

    double contrastD = 0;

    BrightnessD(){}
    //Constructor

    //Method to resize an image based on new width and new height
    public BufferedImage apply(BufferedImage input){

        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int oldPix = input.getRGB(x, y);
                int newPix = pixelConverter(oldPix, 0.25);

                input.setRGB(x, y, newPix);
            }
        }

        return input;

    }

    public void setContast(double contrast){
        this.contrastD = contrast;
    }

    private int pixelConverter(int oldPix, double brightness){
        int v_dash = (int) ((1+(contrastD/100))*(oldPix - 127.5) + 127.5 * (1 + (brightness/100)));
        return v_dash;
    }

}
