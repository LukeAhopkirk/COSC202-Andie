package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * <p>
 * ImageOperation to apply a Median filter.
 * </p>
 * 
 * <p>
 * It is a type of nonlinear filter that replaces the value of each pixel in
 * an image with the median value of the pixels in its surrounding neighborhood.
 * </p>
 */
public class MedianFilter implements ImageOperation, java.io.Serializable {

    private int kernelSize;

    /**
     * <p>
     * Construct a Median filter with the given kernel size.
     * </p>
     * 
     * <p>
     * The kernel size must be an odd number, and greater than or equal to 3.
     * A higher kernel size will result in more noise reduction, but will also
     * result in a more blurred image.
     * </p>
     * 
     * @param kernelSize Size of the square matrix used to perform convolution on
     *                   the image.
     */
    public MedianFilter(int kernelSize) {
        if (kernelSize < 0) {
            throw new IllegalArgumentException("KernelSize cannot be negative.");
        }
        this.kernelSize = kernelSize;
    }

    /**
     * <p>
     * Apply a Median filter to an image.
     * </p>
     * 
     * <p>
     * The code loops over each pixel in the image, and for each pixel
     * it extracts a square window of pixel values from the surrounding
     * neighborhood.
     * </p>
     * 
     * @param input The image to apply the Median filter to.
     * @return The resulting image (with reduced noise).
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage output = new BufferedImage(width, height, input.getType());

        int k = (kernelSize - 1) / 2;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] windowR = new int[kernelSize * kernelSize];
                int[] windowG = new int[kernelSize * kernelSize];
                int[] windowB = new int[kernelSize * kernelSize];
                int[] windowA = new int[kernelSize * kernelSize];
                int windowIndex = 0;

                for (int wy = -k; wy <= k; wy++) {
                    for (int wx = -k; wx <= k; wx++) {
                        int xx = x + wx;
                        int yy = y + wy;

                        if (xx < 0) {
                            xx = -xx;
                        } else if (xx >= width) {
                            xx = width - (xx - width + 2);
                        }

                        if (yy < 0) {
                            yy = -yy;
                        } else if (yy >= height) {
                            yy = height - (yy - height + 2);
                        }

                        int color = input.getRGB(xx, yy);
                        windowR[windowIndex] = (color >> 16) & 0xFF;
                        windowG[windowIndex] = (color >> 8) & 0xFF;
                        windowB[windowIndex] = color & 0xFF;
                        if (input.getAlphaRaster() != null) {
                            windowA[windowIndex] = input.getAlphaRaster().getSample(xx, yy, 0);
                        }
                        windowIndex++;
                    }
                }

                Arrays.sort(windowR, 0, windowIndex);
                Arrays.sort(windowG, 0, windowIndex);
                Arrays.sort(windowB, 0, windowIndex);
                if (input.getAlphaRaster() != null) {
                    Arrays.sort(windowA, 0, windowIndex);
                }

                int medianIndex = windowIndex / 2;
                int r = windowR[medianIndex];
                int g = windowG[medianIndex];
                int b = windowB[medianIndex];
                int a = (input.getAlphaRaster() != null) ? windowA[medianIndex] : 255;
                int color = (a << 24) | (r << 16) | (g << 8) | b;
                output.setRGB(x, y, color);
            }
        }

        return output;
    }
}