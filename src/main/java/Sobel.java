//wykrywa krawedzie

public class Sobel extends Effect {

    private final static double sobelMatH[][] = {
            {1.0, 2.0, 1.0},
            {0.0, 0.0, 0.0},
            {-1.0, -2.0, -1.0}
    };

    private final static double sobelMatV[][] = {
            {-1.0, 0.0, 1.0},
            {-2.0, 0.0, 2.0},
            {-1.0, 0.0, 1.0}
    };

    // converts a pixel to a value in range <0, 1)
    private double getAverageToFraction(byte[] pix) {
        int average = ((pix[1] & 0xFF) + (pix[2] & 0xFF) + (pix[3] & 0xFF)) / 3;
        return ((double) average) / 256.0;
    }

    // checks if point x, y is within given bounds
    private boolean isWithinBounds(int x, int y, int width, int height) {
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }

    @Override
    protected byte[] getPixel(int x, int y, int width, int height, ImageState input) {
        double sobelPartH = 0;
        double sobelPartV = 0;
        double sobelBoth;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int currentX = x + i - 1;
                int currentY = y + j - 1;

                if(!isWithinBounds(currentX, currentY, width, height)) continue;

                double val = getAverageToFraction(input.getPixel(currentX, currentY));

                sobelPartH += sobelMatH[j][i] * val;
                sobelPartV += sobelMatV[j][i] * val;
            }
        }

        sobelBoth = Math.sqrt((sobelPartH * sobelPartH) + (sobelPartV * sobelPartV));

        // threshholding
        if (sobelBoth * 256.0 > 255.0)
            sobelBoth = 255.0;
        else
            sobelBoth = sobelBoth * 256.0;

        return new byte[]{input.getPixel(x, y)[0], (byte) sobelBoth, (byte) sobelBoth, (byte) sobelBoth};
    }
}
