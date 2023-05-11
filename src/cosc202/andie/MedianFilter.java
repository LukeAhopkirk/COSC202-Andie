package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.awt.List;
import java.awt.image.*;

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
     * Higher kernelsizes produce more noise reduction
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

        int numBands = input.getRaster().getNumBands();
        byte[] pixels = ((DataBufferByte) input.getRaster().getDataBuffer()).getData();
        byte[] outputPixels = ((DataBufferByte) output.getRaster().getDataBuffer()).getData();

        int k = (kernelSize - 1) / 2;
        int windowSize = kernelSize * kernelSize;
        byte[] windowR = new byte[windowSize];
        byte[] windowG = new byte[windowSize];
        byte[] windowB = new byte[windowSize];
        byte[] windowA = new byte[windowSize];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
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

                        int windowPixelIndex = yy * width * numBands + xx * numBands;
                        windowR[windowIndex] = pixels[windowPixelIndex];
                        windowG[windowIndex] = pixels[windowPixelIndex + 1];
                        windowB[windowIndex] = pixels[windowPixelIndex + 2];
                        if (numBands > 3) {
                            windowA[windowIndex] = pixels[windowPixelIndex + 3];
                        }
                        windowIndex++;
                    }
                }

                Arrays.sort(windowR, 0, windowIndex);
                Arrays.sort(windowG, 0, windowIndex);
                Arrays.sort(windowB, 0, windowIndex);
                if (numBands > 3) {
                    Arrays.sort(windowA, 0, windowIndex);
                }

                int medianIndex = windowIndex / 2;
                int index = y * width * numBands + x * numBands;
                outputPixels[index] = windowR[medianIndex];
                outputPixels[index + 1] = windowG[medianIndex];
                outputPixels[index + 2] = windowB[medianIndex];
                if (numBands > 3) {
                    outputPixels[index + 3] = windowA[medianIndex];
                }
            }
        }

        return output;
    }
}