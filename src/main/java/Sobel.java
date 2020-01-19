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

    private byte[] grayscale(byte[] pix) {
        double aver = ((pix[1] & 0xFF) + (pix[2] & 0xFF) + (pix[3] & 0xFF)) / 3;
        pix = new byte[]{pix[0], (byte) aver, (byte) aver, (byte) aver};
        return pix;
    }

    @Override
    protected byte[] getPixel(int x, int y, int width, int height, ImageState input) {
        double sobelPartH = 0;
        double sobelPartV = 0;
        double val = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((x - 2 > 0 && y - 2 > 0)) {
                    if (x + 2 < width && y + 2 < height) {
                        sobelPartH += sobelMatH[j][i] * (((double) grayscale(input.getPixel(x + i - 1, y + j - 1))[1]) / 256.0);
                        sobelPartV += sobelMatV[j][i] * (((double) grayscale(input.getPixel(x + i - 1, y + j - 1))[1]) / 256.0);
                    }
                }
            }
        }

        val = Math.sqrt((sobelPartH * sobelPartH) + (sobelPartV * sobelPartV));

        if (val * 256.0 > 255.0)
            val = 255.0;
        else
            val = val * 256.0;

        byte[] pixel = new byte[]{input.getPixel(x, y)[0], (byte) val, (byte) val, (byte) val};
        return pixel;
    }
}
