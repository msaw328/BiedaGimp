// Transform is the most abstract type which describes a way that
// one ImageState can be turned into another

import java.awt.*;

public abstract class Transform {
    protected abstract byte[] getPixel(int x, int y, int width, int height, ImageState input);
    protected abstract int getOutputWidth(ImageState input);
    protected abstract int getOutputHeight(ImageState input);

    public final ImageState apply(ImageState input) {
        return this.apply(input, 1, 1);
    }

    public final ImageState apply(ImageState input, int iterations, int threadCount) {
        ImageState partialIn = input;

        Thread[] workers = new Thread[threadCount];

        for(int i = 0; i < iterations; i++) { // in each iteration
            final ImageState sharedPartialIn = partialIn;

            int width = getOutputWidth(sharedPartialIn);
            int height = getOutputHeight(sharedPartialIn);
            byte[] buffer = new byte[width * height * 4];
            final ImageState sharedPartialOut = new ImageState(width, height, buffer); // create new output imagestate

            int threadHeight = height / threadCount;

            for(int t = 0; t < threadCount; t++) {
                int startHeight = t * threadHeight;

                workers[t] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int x = 0; x < width; x++) { // apply transform from partialIn to partialOut
                            for (int y = startHeight; y < startHeight + threadHeight; y++) {
                                byte[] pixel = getPixel(x, y, width, height, sharedPartialIn);
                                sharedPartialOut.setPixel(x, y, pixel);
                            }
                        }
                    }
                });

                workers[t].start();
            }

            for(Thread t : workers) { // finish workers
                try {
                    t.join();
                } catch(InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }

            partialIn = sharedPartialOut; // set partialIn for next iteration as output of this iteration
        }

        return partialIn; // after all iterations, result is in partialIn
    }
}
