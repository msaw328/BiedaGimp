public class ImageState {
    private int width;      //width of image
    private int height;     //height of image
    private byte[] buffer;  //bytes buffer of image

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public byte[] getPixel(int x, int y) {
        if (x >= width || y >= height) {    //throw an error if x or y have wrong values
            throw new IllegalArgumentException("x must be less than or equal to width and y must be less than or equal to height");
        }
        int loc = 4 * (x + y * width);      //multiple by 4 because each pixel contains 4 channels
        byte[] pixel = {buffer[loc], buffer[loc + 1], buffer[loc + 2], buffer[loc + 3]};
        return pixel;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public void setPixel(int x, int y, byte red, byte green, byte blue, byte alpha) {
        if (x >= width || y >= height) {     //throw an error if x or y have wrong values
            throw new IllegalArgumentException("x must be less than or equal to width and y must be less than or equal to height");
        }
        int loc = 4*(x + y * width);         //multiple by 4 because each pixel contains 4 channels
        this.buffer[loc] = red;              //setting each channel to given value
        this.buffer[loc + 1] = green;
        this.buffer[loc + 2] = blue;
        this.buffer[loc + 3] = alpha;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public ImageState(int width, int height, byte[] buffer) {
        this.width = width;
        this.height = height;
        this.buffer = buffer;
    }
}
