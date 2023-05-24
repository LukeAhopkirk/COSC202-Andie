package cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

public class RotateLeftTest {

    @Test
    void initialDummyTest() {
    }

    @Test
    void testApplyWithValidInputImage() {
        BufferedImage input = new BufferedImage(200, 100, BufferedImage.TYPE_INT_ARGB);
        Rotate90Left RotateLeft = new Rotate90Left();

        // Act
        BufferedImage output = RotateLeft.apply(input);

        // Assert
        Assertions.assertTrue(output.getWidth() == 100);
        Assertions.assertTrue(output.getHeight() == 200);
    }
}

