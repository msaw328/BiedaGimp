// mirrors up-down

public class MirrorVertical extends Effect {

    @Override
    protected byte[] getPixel(int x, int y, int width, int height, ImageState input) {
        return input.getPixel(x, input.getHeight() - y - 1);
    }
}
