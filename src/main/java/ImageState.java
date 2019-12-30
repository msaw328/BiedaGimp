import java.util.Collections;

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
            throw new IllegalArgumentException("X MUST BE LESS THAN OR EQUAL TO WIDTH AND Y MUST BE LESS THAN OR EQUAL TO HEIGHT!");
        }
        int loc = 4 * (x + y * width);      //multiple by 4 because each pixel contains 4 channels
        byte[] pixel = {buffer[loc], buffer[loc + 1], buffer[loc + 2], buffer[loc + 3]};
        return pixel;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public void setPixel(int x, int y, byte[] pixel) {
        if (x >= width || y >= height) {     //throw an error if x or y have wrong values
            throw new IllegalArgumentException("X MUST BE LESS THAN OR EQUAL TO WIDTH AND Y MUST BE LESS THAN OR EQUAL TO HEIGHT!");
        }
        if (pixel.length != 4) {             //throw an error if pixel arrays length is not proper
            throw new IllegalArgumentException("PIXEL ARRAY LENGTH MUST BE EQUAL TO 4!");
        }
        int loc = 4*(x + y * width);         //multiple by 4 because each pixel contains 4 channels
        this.buffer[loc] = pixel[0];         //setting each channel to given value
        this.buffer[loc + 1] = pixel[1];
        this.buffer[loc + 2] = pixel[2];
        this.buffer[loc + 3] = pixel[3];
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public ImageState(int width, int height, byte[] buffer) {
        if (buffer.length != width * height * 4) {     //throw an error if buffer length is not proper
            throw new IllegalArgumentException("BUFFER LENGTH MUST BE EQUAL TO (4 * WIDTH * HEIGHT)!");
        }
        this.width = width;
        this.height = height;
        this.buffer = buffer;
    }

    public ImageState(ImageState imageState){   //copy constructor
        this.width = imageState.getWidth();
        this.height = imageState.getHeight();
        this.buffer = new byte[imageState.getBuffer().length];
        System.arraycopy(imageState.getBuffer(), 0, this.buffer, 0, imageState.getBuffer().length);
    }
}
