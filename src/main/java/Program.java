import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class Program {

    public static void main(String[] args) throws IOException {
        ImageState test = ImageIOWrap.read("/home/mikolaj/Pulpit/frog.png");
        Transform sobel = new Sobel();
        ImageState after = sobel.apply(test);
        ImageIOWrap.write("/home/mikolaj/Pulpit/frog_sobel.png", after, BufferedImage.TYPE_4BYTE_ABGR);
    }
}
