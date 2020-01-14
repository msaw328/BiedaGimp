public class Rotate90 extends Transform {
    @Override
    protected int getOutputWidth(ImageState input) {
        return input.getHeight(); // dimensions are swapped
    }

    @Override
    protected int getOutputHeight(ImageState input) {
        return input.getWidth(); // dimensions are swapped
    }

    @Override
    protected byte[] getPixel(int x, int y, int width, int height, ImageState input) {
        return input.getPixel(y, width - x - 1);
    }
}
