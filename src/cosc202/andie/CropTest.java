package cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;


public class CropTest {
    @Test
    void initialDummyTest() {
    }

    @Test 
    void testApplyWithValidInputImage(){
         BufferedImage input = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Crop crop = new Crop(50, 50, 50, 50);

        BufferedImage output = crop.apply(input);
        Assertions.assertTrue(output.getWidth() == 50);
        Assertions.assertTrue(output.getHeight() == 50);

    }

    @Test
    public void ShowErrorMessageWhenInvalidCropArea() {
        Crop crop = new Crop(50, 50, 100, 100);
        BufferedImage input = new BufferedImage(100 , 100, BufferedImage.TYPE_INT_ARGB);

        BufferedImage outputImage = crop.apply(input);
        
        assertNotNull(outputImage);
    }
    
}
