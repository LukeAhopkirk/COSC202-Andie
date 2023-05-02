package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a Sharpen filter.
 * </p>
 * 
 * <p>
 * Type of image filter that enhances edges and fine details in an image.
 * It works by increasing the contrast between neighboring pixels, making edges
 * appear sharper and more defined.
 * </p>
 * 
 */
public class Sharpen implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Default constructor
     * </p>
     */
    Sharpen() {
    }

    /**
     * <p>
     * Apply a Sharpen filter to an image.
     * </p>
     * 
     * <p>
     * Increases the contrast between adjacent pixels in the image.
     * The kernel used places a higher weight on the central
     * pixel and its adjacent pixels, which results in these pixels having a
     * greater influence on the output value.
     * </p>
     * 
     * @param input The image to apply the Sharpen filter to.
     * @return The resulting (sharpened) image.
     */
    public BufferedImage apply(BufferedImage input) {
        float[] array = { 0, -1 / 2.0f, 0,
                -1 / 2.0f, 3, -1 / 2.0f,
                0, -1 / 2.0f, 0 };

        // Make a 3x3 kernel
        Kernel kernel = new Kernel(3, 3, array);

        ConvolveOp convOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);

        // Pad the image with zeros
        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage paddedInput = new BufferedImage(width + 2, height + 2, input.getType());
        Graphics2D g = paddedInput.createGraphics();
        g.drawImage(input, 1, 1, null);
        g.dispose();

        // Apply the ConvolveOp to the padded image
        BufferedImage output = convOp.filter(paddedInput, null);

        // Crop the image to its original size
        output = output.getSubimage(1, 1, width, height);

        return output;
    }
}