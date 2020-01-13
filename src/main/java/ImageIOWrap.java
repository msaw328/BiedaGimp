import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.*;

/*
                CHEATSHEET:
    grayscale = BufferedImage.TYPE_BYTE_GRAY
    RGB = TYPE_3BYTE_BGR
    ARGB = BufferedImage.TYPE_4BYTE_ABGR
 */

public class ImageIOWrap {

    public static ImageState read(String path) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));                             //read from file
        int width = image.getWidth();
        int height = image.getHeight();
        int imageType = image.getType();                                                //get colorModel of image
        DataBuffer dataBuffer = image.getRaster().getDataBuffer();
        byte[] buffer = ((DataBufferByte) dataBuffer).getData();                        //convert image to byte array of pixels

        if (imageType == BufferedImage.TYPE_BYTE_GRAY)
            buffer = ColorConversion.grayscaleToArgb(buffer);                           //convert from grayscale to RGBA if necessary
        else if (imageType == BufferedImage.TYPE_3BYTE_BGR)
            buffer = ColorConversion.rgbToArgb(buffer);                                 //convert from RGB to RGBA if necessary

        return new ImageState(width, height, buffer);
    }

    public static void write(String path, ImageState imageState, int imageType) throws IOException {
        byte[] buffer = imageState.getBuffer();
        BufferedImage image;

        if (imageType == BufferedImage.TYPE_BYTE_GRAY) {
            buffer = ColorConversion.argbToGrayscale(buffer);                                                                        //convert from RGBA to grayscale if necessary
            image = new BufferedImage(imageState.getWidth(), imageState.getHeight(), BufferedImage.TYPE_BYTE_GRAY);                  //new empty BufferedImage
            image.setData(Raster.createRaster(image.getSampleModel(), new DataBufferByte(buffer, buffer.length), new Point()));      //convert from byte array of pixels to BufferedImage
        } else if (imageType == BufferedImage.TYPE_3BYTE_BGR) {
            buffer = ColorConversion.argbToRgb(buffer);                                                                               //convert from RGBA to RGB if necessary
            image = new BufferedImage(imageState.getWidth(), imageState.getHeight(), BufferedImage.TYPE_3BYTE_BGR);                   //new empty BufferedImage
            image.setData(Raster.createRaster(image.getSampleModel(), new DataBufferByte(buffer, buffer.length), new Point()));       //convert from byte array of pixels to BufferedImage
        } else {
            image = new BufferedImage(imageState.getWidth(), imageState.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);                 //new empty BufferedImage
            image.setData(Raster.createRaster(image.getSampleModel(), new DataBufferByte(buffer, buffer.length), new Point()));     //convert from byte array of pixels to BufferedImage
        }

        ImageIO.write(image, "png", new File(path));                                                                   //write to file

    }
}
