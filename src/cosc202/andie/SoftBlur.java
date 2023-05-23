package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a Soft Blur filter.
 * </p>
 * 
 */
public class SoftBlur implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Default constructor
     * </p>
     */
    SoftBlur() {
    }

    /**
     * <p>
     * Apply a Soft Blur filter to an image.
     * </p>
     * 
     * <p>
     * Blurs the image slightly by averaging the values of each pixel with the
     * values of its surrounding pixels.
     * The weights in the kernel are higher for the pixels that are closer to the
     * central pixel,
     * so those pixels have a greater influence on the output value.
     * This results in the image being slightly blurred, but not so much that the
     * details are completely lost.
     * </p>
     * 
     * @param input The image to apply the Sharpen filter to.
     * @return The resulting (sharpened) image.
     */
    public BufferedImage apply(BufferedImage input) {
        float[] array = { 0, 1 / 8.0f, 0,
                1 / 8.0f, 1 / 2.0f, 1 / 8.0f,
                0, 1 / 8.0f, 0 };

        // Make a 3x3 kernel
        Kernel kernel = new Kernel(3, 3, array);

        // Apply this as a convolution - same code as in MeanFilter (DONT UNDERSTAND
        // ATM)
        ConvolveOp convOp = new ConvolveOp(kernel);

        // Applying stuff idk
        BufferedImage output = new BufferedImage(input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
    }
}
