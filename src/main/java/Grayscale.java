//converts image from colors to grayscale

public class Grayscale extends Effect {

    @Override
    protected byte[] getPixel(int x, int y, int width, int height, ImageState input) {
        byte[] pix = input.getPixel(x, y);
        int aver = ((pix[1] & 0xFF) + (pix[2] & 0xFF) + (pix[3] & 0xFF)) / 3;
        pix = new byte[]{pix[0], (byte) aver, (byte) aver, (byte) aver};
        return pix;
    }
}
