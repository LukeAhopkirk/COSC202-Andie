package cosc202.andie;

import java.awt.image.*;


public class GaussianB implements ImageOperation, java.io.Serializable {

    private int kernelSize;
    private float sigma;

    // Constructor
    public GaussianB(int kernelSize, float sigma) {
        this.kernelSize = kernelSize;
        this.sigma = sigma;
    }

    public BufferedImage apply(BufferedImage input) {
        // Create a Gaussian kernel
        int k = (kernelSize - 1) / 2;
        float[] kernelData = new float[kernelSize * kernelSize];
        float sum = 0;
        for (int y = -k; y <= k; y++) {
            for (int x = -k; x <= k; x++) {
                float value = (float) (1.0 / (2.0 * Math.PI * sigma * sigma)
                        * Math.exp(-(x * x + y * y) / (2.0 * sigma * sigma)));
                kernelData[(y + k) * kernelSize + (x + k)] = value;
                sum += value;
            }
        }
        for (int i = 0; i < kernelData.length; i++) {
            kernelData[i] /= sum;
        }
        Kernel kernel = new Kernel(kernelSize, kernelSize, kernelData);

        // Create a ConvolveOp with the Gaussian kernel
        ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);

        // Apply the ConvolveOp to the image
        BufferedImage output = convolve.filter(input, null);

        return output;
    }
}
