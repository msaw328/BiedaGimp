// Transform is the most abstract type which describes a way that
// one ImageState can be turned into another

import java.awt.*;

public abstract class Transform {
    protected abstract byte[] getPixel(int x, int y, int width, int height, ImageState input);
    protected abstract int getOutputWidth(ImageState input);
    protected abstract int getOutputHeight(ImageState input);

    public final ImageState apply(ImageState input) {
        return this.apply(input, 1);
    }

    public final ImageState apply(ImageState input, int iterations) {
        ImageState partialIn = input;
        ImageState partialOut;

        for(int i = 0; i < iterations; i++) { // in each iteration
            int width = getOutputWidth(partialIn);
            int height = getOutputHeight(partialIn);
            byte[] buffer = new byte[width * height * 4];

            partialOut = new ImageState(width, height, buffer); // create new output imagestate

            for (int x = 0; x < width; x++) { // apply transform from partialIn to partialOut
                for (int y = 0; y < height; y++) {
                    byte[] pixel = getPixel(x, y, width, height, partialIn);
                    partialOut.setPixel(x, y, pixel);
                }
            }

            partialIn = partialOut; // set partialIn for next iteration as output of this iteration
        }

        return partialIn; // after all iterations, result is in partialIn
    }
}
