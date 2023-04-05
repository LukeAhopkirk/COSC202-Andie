package cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;

public class ResizeTest {

    @Test
    void initialDummyTest() {
    }

    @Test
    void testApplyWithValidInputImage() {
        BufferedImage input = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Resize resize = new Resize(50);

        // Act
        BufferedImage output = resize.apply(input);

        // Assert
        Assertions.assertTrue(output.getWidth() == 50);
        Assertions.assertTrue(output.getHeight() == 50);
    }

    @Test
    void testApplyWithNullInputImage() {
        BufferedImage input = null;
        Resize resize = new Resize(50);

        // Act & Assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            resize.apply(input);
        });
    }

    @Test
    void testConstructorWithValidMultiplier() {
        // Arrange & Act
        Resize resize = new Resize(50);

        // Assert
        Assertions.assertTrue(resize != null);
    }

    @Test
    void testConstructorWithNegativeMultiplier() {
        // Arrange & Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Resize(-50);
        });
    }

}