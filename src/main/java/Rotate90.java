public class Rotate90 extends Transform {
    @Override
    public ImageState apply(ImageState input) {
        int height = input.getWidth(); // dimensions get swapped
        int width = input.getHeight();

        byte[] pixels = new byte[width * height * 4];

        ImageState output = new ImageState(width, height, pixels);

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                byte[] p = input.getPixel(y, width - x - 1);
                output.setPixel(x, y, p);
            }
        }

        return output;
    }
}
