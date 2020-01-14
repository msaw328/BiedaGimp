// mirrors left-right

public class MirrorHorizontal extends Effect {

    @Override
    protected byte[] getPixel(int x, int y, int width, int height, ImageState input) {
        return input.getPixel(input.getWidth() - x - 1, y);
    }
}
