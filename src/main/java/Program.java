import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class Program {

    public static void main(String[] args) throws IOException {

        String path = "/home/mikolaj/Pulpit/test.png";
        String savePath = "/home/mikolaj/Pulpit/NEW_7.png";


        ImageState image = ImageIOWrap.read(path);                          //read image

        ImageIOWrap.write(savePath, image, BufferedImage.TYPE_3BYTE_BGR);   //write image
    }
}
