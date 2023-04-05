package cosc202.andie;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BrightnessTest {

    @Test
    void initialDummyTest() {
    }

    @Test
    void testConstructorWithInvalidConstructor() {
        // Arrange & Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Brightness(-150);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Brightness(150);
        });
    }

    @Test
    void testConstructorWithValidMultiplier() {
        // Arrange & Act
        Brightness brightness = new Brightness(50);

        // Assert
        Assertions.assertTrue(brightness != null);
    }

    @Test
    void testApplyWithNullInputImage() {
        BufferedImage input = null;
        Brightness brightness = new Brightness(50);

        // Act & Assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            brightness.apply(input);
        });
    }
}
