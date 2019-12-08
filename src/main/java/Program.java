public class Program {
    public static void main(String[] args) {

        System.out.println((byte) 255 - Byte.MIN_VALUE);
        byte[] rgb = new byte[] {100, 100, 100, 50, 50, 50, 10, 10, 10};
        byte[] grayscale = new byte[] {5,100,127,0,-10,-50};

        ColorConversion conversion = new ColorConversion();
        int x = conversion.rgbToRgba(rgb).length;

        //Converting rgb to rgba
        System.out.println(" ");
        for (int i = 0; i < conversion.rgbToRgba(rgb).length; i++) {
            System.out.print(Byte.toString( conversion.rgbToRgba(rgb)[i]) + ",");
        }

        //Converting rgba to rgb
        System.out.println(" ");
        for (int i = 0; i < conversion.rgbaToRgb(conversion.rgbToRgba(rgb)).length; i++) {
            System.out.print(Byte.toString(conversion.rgbaToRgb(conversion.rgbToRgba(rgb))[i]) + ",");
        }

        //Converting grayscale to rgba
        System.out.println(" ");
        for (int i = 0; i < conversion.grayscaleToRgba(grayscale).length; i++) {
            System.out.print(Byte.toString( conversion.grayscaleToRgba(grayscale)[i]) + ",");
        }

        //Converting rgba to grayscale
        System.out.println(" ");
        for (int i = 0; i < conversion.rgbaToGrayscale(conversion.grayscaleToRgba(grayscale)).length; i++) {
            System.out.print(Byte.toString( conversion.rgbaToGrayscale(conversion.grayscaleToRgba(grayscale))[i]) + ",");
        }
    }
}
