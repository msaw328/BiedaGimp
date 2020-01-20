import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class Program {

    public static void main(String[] args) throws IOException {
        ImageState test = ImageIOWrap.read("/home/mikolaj/Pulpit/Stworcy.jpg");
        Transform sobel = new WeirdSobel();
        ImageState after = sobel.apply(test);
        ImageIOWrap.write("/home/mikolaj/Pulpit/Stworcy_WeirdSobel.png", after, BufferedImage.TYPE_4BYTE_ABGR);
    }
}
