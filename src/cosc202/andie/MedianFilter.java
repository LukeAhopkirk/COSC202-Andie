package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.awt.image.*;

public class MedianFilter implements ImageOperation, java.io.Serializable {

    private int kernelSize;

    public MedianFilter(int kernelSize) {
        this.kernelSize = kernelSize;
    }

    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage output = new BufferedImage(width, height, input.getType());
    
        int numBands = input.getRaster().getNumBands();
        byte[] pixels = ((DataBufferByte) input.getRaster().getDataBuffer()).getData();
        byte[] outputPixels = ((DataBufferByte) output.getRaster().getDataBuffer()).getData();
    
        int k = (kernelSize - 1) / 2;
        int windowSize = kernelSize * kernelSize;
        byte[] window = new byte[windowSize];
    
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (int b = 0; b < numBands; b++) {
                    int index = y * width * numBands + x * numBands + b;
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
    
                            int windowPixelIndex = yy * width * numBands + xx * numBands + b;
                            window[windowIndex] = pixels[windowPixelIndex];
                            windowIndex++;
                        }
                    }
    
                    Arrays.sort(window);
                    int medianIndex = windowSize / 2;
    
                    if (window[0] == window[windowSize-1]) { // all pixel values are the same
                        outputPixels[index] = window[0];
                    } else {
                        outputPixels[index] = window[medianIndex];
                    }
                }
            }
        }
    
        return output;
    }
}