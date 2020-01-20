import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class Program {

    public static void main(String[] args) throws IOException {
        ImageState test = ImageIOWrap.read("/home/user/Pictures/valvepng");
        Transform sobel = new Sobel();
        ImageState after = sobel.apply(test);
        ImageIOWrap.write("/home/user/Pictures/valve_sobel.png", after, BufferedImage.TYPE_4BYTE_ABGR);
    }
}
