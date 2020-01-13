import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.*;


public class ImageIOWrap {

    public static ImageState read(String path) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));                             //read from file
        int width = image.getWidth();
        int height = image.getHeight();
        int imageType = image.getType();                                                //get colorModel of image
        DataBuffer dataBuffer = image.getRaster().getDataBuffer();
        byte[] buffer = ((DataBufferByte) dataBuffer).getData();                        //convert image to byte array of pixels

        if (imageType == BufferedImage.TYPE_BYTE_GRAY)
            buffer = ColorConversion.grayscaleToRgba(buffer);                           //convert from grayscale to RGBA if necessary
        else if (imageType == BufferedImage.TYPE_3BYTE_BGR)
            buffer = ColorConversion.rgbToRgba(buffer);                                 //convert from RGB to RGBA if necessary

        return new ImageState(width, height, buffer);
    }

    public static void write(String path, ImageState imageState, int imageType) throws IOException {
        byte[] buffer = imageState.getBuffer();
        BufferedImage image;

        if (imageType == BufferedImage.TYPE_BYTE_GRAY) {
            buffer = ColorConversion.rgbaToGrayscale(buffer);                                                                        //convert from RGBA to grayscale if necessary
            image = new BufferedImage(imageState.getWidth(), imageState.getHeight(), BufferedImage.TYPE_BYTE_GRAY);                  //new empty BufferedImage
            image.setData(Raster.createRaster(image.getSampleModel(), new DataBufferByte(buffer, buffer.length), new Point()));    //convert from byte array of pixels to BufferedImage

        } else if (imageType == BufferedImage.TYPE_3BYTE_BGR) {
            buffer = ColorConversion.rgbToRgba(buffer);                                                                               //convert from RGBA to RGB if necessary
            image = new BufferedImage(imageState.getWidth(), imageState.getHeight(), BufferedImage.TYPE_3BYTE_BGR);                   //new empty BufferedImage
            image.setData(Raster.createRaster(image.getSampleModel(), new DataBufferByte(buffer, buffer.length), new Point()));     //convert from byte array of pixels to BufferedImage

        } else {
            image = new BufferedImage(imageState.getWidth(), imageState.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);                 //new empty BufferedImage
            image.setData(Raster.createRaster(image.getSampleModel(), new DataBufferByte(buffer, buffer.length), new Point()));     //convert from byte array of pixels to BufferedImage
            ImageIO.write(image, "png", new File(path));                                                                   //write to file
        }

        ImageIO.write(image, "png", new File(path));                                                                   //write to file

    }
        /*
        BufferedImage image = new BufferedImage(imageState.getWidth(), imageState.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);    //new empty BufferedImage
        image.setData(Raster.createRaster(image.getSampleModel(), new DataBufferByte(buffer, buffer.length), new Point() ) );   //convert from byte array of pixels to BufferedImage
        ImageIO.write(image, "png", new File(path));

    public  static void main(String[] args) throws IOException {
        //READ
        String path = "/home/mikolaj/Pulpit/grayscale.jpg";

        BufferedImage image = ImageIO.read(new File(path));                             //read from file
        int width = image.getWidth();
        int height = image.getHeight();
        int imageType = image.getType();                                                //get colorModel of image
        System.out.println(imageType);
        DataBuffer dataBuffer = image.getRaster().getDataBuffer();
        byte[] buffer = ((DataBufferByte) dataBuffer).getData();                        //convert image to byte array of pixels

        if (imageType == 1)
            buffer = ColorConversion.grayscaleToRgba(buffer);                           //convert from grayscale to RGBA if necessary
        else if (imageType == 5)
            buffer = ColorConversion.rgbToRgba(buffer);                                 //convert from RGB to RGBA if necessary

        ImageState imageState = new ImageState(width, height, buffer);


        BufferedImage image = ImageIO.read(new File(path));                             //read from file
        int width = image.getWidth();
        int height = image.getHeight();
        int imageType = image.getType();                                                //get colorModel of image
        DataBuffer dataBuffer = image.getRaster().getDataBuffer();
        byte[] buffer = ((DataBufferByte) dataBuffer).getData();                        //convert image to byte array of pixels

        if (imageType == 1)
            buffer = ColorConversion.grayscaleToRgba(buffer);                           //convert from grayscale to RGBA if necessary
        else if (imageType == 5)
            buffer = ColorConversion.rgbToRgba(buffer);                                 //convert from RGB to RGBA if necessary

        ImageState imageState = new ImageState(width, height, buffer);
        System.out.println("Wczytano");

        String writePath = "/home/mikolaj/Pulpit/newnewnew.png";
        write(writePath, imageState, 5);



        BufferedImage image = ImageIO.read(new File(imagePath));                        //read the image
        int width = image.getWidth();
        int height = image.getHeight();
        DataBuffer dataBuffer = image.getRaster().getDataBuffer();
        byte[] pixels = ((DataBufferByte) dataBuffer).getData();                        //convert image to byte array of pixels
        System.out.println("---------------");
        System.out.println("Type of image: " + image.getType());
        /*public static final int TYPE_4BYTE_ABGR
        Represents an image with 8-bit RGBA color components with the colors Blue, Green, and Red stored in 3 bytes and 1 byte of alpha.
        The image has a ComponentColorModel with alpha. The color data in this image is considered not to be premultiplied with alpha.
        The byte data is interleaved in a single byte array in the order A, B, G, R from lower to higher byte addresses within each pixel.

        System.out.println(width);
        System.out.println(height);
        System.out.println(pixels.length);
        ImageState imageState = new ImageState(width, height, pixels);

        //WRITE
        byte[] buffer = imageState.getBuffer();
        BufferedImage img=new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        img.setData(Raster.createRaster(img.getSampleModel(), new DataBufferByte(buffer, buffer.length), new Point() ) );
        ImageIO.write(img, "png",new File("/home/mikolaj/Pulpit/test_after.png"));

        //NIE DZIALA BO ByteArrayInputStream bis MA W SRODKU {RGBA, RGBA, RGBA} A buffer {R, G, B, A, R, G, B, A}

        ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, "png", new File("/home/mikolaj/Pulpit/example_image_after.png") );

    }


         */
}
