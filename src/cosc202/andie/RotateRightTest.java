package cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

public class RotateRightTest {

    @Test
    void initialDummyTest() {
    }

    @Test
    void testApplyWithValidInputImage() {
        BufferedImage input = new BufferedImage(200, 100, BufferedImage.TYPE_INT_ARGB);
        Rotate90Right rotateRight = new Rotate90Right();

        // Act
        BufferedImage output = rotateRight.apply(input);

        // Assert
        Assertions.assertTrue(output.getWidth() == 100);
        Assertions.assertTrue(output.getHeight() == 200);
    }
}
