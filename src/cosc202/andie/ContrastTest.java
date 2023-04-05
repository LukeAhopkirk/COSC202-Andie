package cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;

public class ContrastTest {

    @Test
    void initialDummyTest() {
    }

    @Test
    void testApplyWithNullInputImage() {
        BufferedImage input = null;
        Contrast contrast = new Contrast(50);

        // Act & Assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            contrast.apply(input);
        });
    }

    @Test
    void testConstructorWithValidMultiplier() {
        // Arrange & Act
        Contrast contrast = new Contrast(50);

        // Assert
        Assertions.assertTrue(contrast != null);
    }

    @Test
    void testConstructorWithNegativeMultiplier() {
        // Arrange & Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contrast(-50);
        });
    }

}