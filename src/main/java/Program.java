import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class Program {

    public static void main(String[] args) throws IOException {
        ImageState test = ImageIOWrap.read("C:/Users/HP/Desktop/RetinexExample.jpg");
        Transform sobel = new GaussianBlur(GaussianBlur.MAT5);
        ImageState after = sobel.apply(test);
        ImageIOWrap.write("C:/Users/HP/Desktop/GaussianBlurExample3.png", after, BufferedImage.TYPE_4BYTE_ABGR);
    }
}
