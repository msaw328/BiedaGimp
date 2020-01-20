public class GaussianBlur extends Effect{
    public static final int MAT3 = 0;
    public static final int MAT5 = 1;

    private int mode;

    private final static double[][] gaussianMat3 = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}
    };
    private final static double[][] gaussianMat5 = {
            {1.0/273.0, 4.0/273.0, 7.0/273.0, 4.0/273.0, 1.0/273.0},
            {4.0/273.0, 16.0/273.0, 26.0/273.0, 16.0/273.0, 4.0/273.0},
            {7.0/273.0, 26.0/273.0, 41.0/273.0, 26.0/273.0, 7.0/273.0},
            {4.0/273.0, 16.0/273.0, 26.0/273.0, 16.0/273.0, 4.0/273.0},
            {1.0/273.0, 4.0/273.0, 7.0/273.0, 4.0/273.0, 1.0/273.0}
    };

    public GaussianBlur(int mode) {
        this.mode = mode;
    }

    // converts a pixel to a value in range <0, 1)
    private double toFraction(byte pix) {
        return ((double) (pix & 0xFF)) / 256.0;
    }

    // checks if point x, y is within given bounds
    private boolean isWithinBounds(int x, int y, int width, int height) {
        return (x > 0 && x < width) && (y > 0 && y < height);
    }
    @Override
    protected byte[] getPixel(int x, int y, int width, int height, ImageState input) {
        double r = 0;
        double g = 0;
        double b = 0;

        int matSize;
        double[][] mat;
        switch(this.mode) {
            default:
            case MAT3:
                matSize = 3;
                mat = gaussianMat3;
                break;

            case MAT5:
                matSize = 5;
                mat = gaussianMat5;
                break;
        }

        for (int i = 0; i < matSize; i++) {
            for (int j = 0; j < matSize; j++) {
                int currentX = x + i - (matSize / 2);
                int currentY = y + j - (matSize / 2);

                if(!isWithinBounds(currentX, currentY, width, height)) continue;

                double valR = toFraction(input.getPixel(currentX,currentY)[1]);
                double valG = toFraction(input.getPixel(currentX,currentY)[2]);
                double valB = toFraction(input.getPixel(currentX,currentY)[3]);
                r += mat[j][i] * valR;
                g += mat[j][i] * valG;
                b += mat[j][i] * valB;
            }
        }

        byte pixR = (byte)(r * 256.0);
        byte pixG = (byte)(g * 256.0);
        byte pixB = (byte)(b * 256.0);

        return new byte[]{input.getPixel(x,y)[0], pixR, pixG, pixB};
    }

}
