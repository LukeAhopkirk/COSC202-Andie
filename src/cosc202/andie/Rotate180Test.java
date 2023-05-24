package cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;


public class Rotate180Test {

    @Test
    void initialDummyTest() {
    }

    @Test
    void testApplyWithValidInputImage() {
        BufferedImage input = new BufferedImage(200, 100, BufferedImage.TYPE_INT_ARGB);
        Rotate180 rotate180 = new Rotate180();

        // Act
        BufferedImage output = rotate180.apply(input);

        // Assert
        Assertions.assertTrue(output.getWidth() == 200);
        Assertions.assertTrue(output.getHeight() == 100);
    }
}
