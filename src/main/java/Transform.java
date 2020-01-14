// Transform is the most abstract type which describes a way that
// one ImageState can be turned into another

public abstract class Transform {
    protected abstract byte[] getPixel(int x, int y, int width, int height, ImageState input);
    protected abstract int getOutputWidth(ImageState input);
    protected abstract int getOutputHeight(ImageState input);

    public final ImageState apply(ImageState input) {
        int width = getOutputWidth(input);
        int height = getOutputHeight(input);
        byte[] data = new byte[width * height * 4];

        ImageState output = new ImageState(width, height, data);

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                byte[] pixel = getPixel(x, y, width, height, input);
                output.setPixel(x, y, pixel);
            }
        }

        return output;
    }
}
