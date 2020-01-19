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

        switch(imageType) {
            //case BufferedImage.TYPE_INT_ARGB: // no conv needed // BUGFIX#20 no way to test those
            //    break;

            //case BufferedImage.TYPE_INT_RGB:
            //    buffer = ColorConversion.rgbToArgb(buffer);
            //    break;
            case BufferedImage.TYPE_4BYTE_ABGR:
                buffer = ColorConversion.abgrToArgb(buffer);
                break;

            case BufferedImage.TYPE_3BYTE_BGR:
                buffer = ColorConversion.bgrToArgb(buffer);
                break;

            case BufferedImage.TYPE_BYTE_GRAY:
                buffer = ColorConversion.grayscaleToArgb(buffer);
                break;

            default:
                throw new IOException("invalid input picture color encoding");

        }

        return new ImageState(width, height, buffer);
    }

    public static void write(String path, ImageState imageState, int imageType) throws IOException {
        byte[] buffer = imageState.getBuffer();
        BufferedImage image;

        switch(imageType) {
            //case BufferedImage.TYPE_INT_RGB: // BUGFIX#20 no way to test those
            //case BufferedImage.TYPE_INT_ARGB: // no conv needed
            //    break;

            case BufferedImage.TYPE_4BYTE_ABGR:
                buffer = ColorConversion.argbToAbgr(buffer);
                break;

            case BufferedImage.TYPE_3BYTE_BGR:
                buffer = ColorConversion.argbToBgr(buffer);
                break;

            case BufferedImage.TYPE_BYTE_GRAY:
                buffer = ColorConversion.argbToGrayscale(buffer);
                break;

            default:
                throw new IOException("invalid output picture color encoding");

        }

        image = new BufferedImage(imageState.getWidth(), imageState.getHeight(), imageType);
        image.setData(Raster.createRaster(image.getSampleModel(), new DataBufferByte(buffer, buffer.length), new Point()));

        ImageIO.write(image, "png", new File(path)); //write to file

    }
}
