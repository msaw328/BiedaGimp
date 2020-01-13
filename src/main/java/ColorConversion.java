public class ColorConversion {
    //functions converts data between byte and integer type:
    //https://stackoverflow.com/questions/7401550/how-to-convert-int-to-unsigned-byte-and-back
    private static int byteToUnsigned(byte b) {
        return b & 0xFF;
    }

    private static byte unsignedToByte(int b) {
        return (byte) (b);
    }

    public static byte[] rgbToArgb(byte[] rgb) {
        byte[] rgba = new byte[rgb.length + rgb.length / 3];
        int j = 0;  //needed to implement alpha byte
        for (int i = 0; i < rgba.length; i++) { //after each group of 3 bytes of colors add 1 alpha byte
            if ((i + 1) % 4 == 1) {
                rgba[i] = Byte.MAX_VALUE;
                j++;
            } else {
                rgba[i] = rgb[i - j];
            }
        }
        return rgba;
    }

    public static byte[] argbToRgb(byte[] rgba) {
        byte[] rgb = new byte[rgba.length - rgba.length / 4];   // in each group of 4 bytes is 1 alpha byte
        int j = 0;  //needed to avoid alpha byte
        for (int i = 0; i < rgba.length; i++) { //per each group of 4 bytes avoid alpha byte
            if ((i + 1) % 4 == 1) j++;
            else rgb[i - j] = rgba[i];
        }
        return rgb;
    }

    public static byte[] grayscaleToArgb(byte[] grayscale) {
        byte[] rgba = new byte[4 * grayscale.length];   //grayscale has only 1 canal. Rgba has 4.
        int j = 0;  //needed to increment grayscale's canals
        for (int i = 0; i < rgba.length; i = i + 4) {   //per each grayscale's byte fall 3 rgba's colors bytes and 1 alpha byte
            rgba[i] = Byte.MAX_VALUE;
            rgba[i + 1] = grayscale[j];
            rgba[i + 2] = grayscale[j];
            rgba[i + 3] = grayscale[j];
            j++;
        }
        return rgba;
    }

    public static byte[] argbToGrayscale(byte[] rgba) {
        byte[] grayscale = new byte[rgba.length / 4];   //grayscale has 1 canal in contrast to rgba
        int j = 1;  //needed to increment rgba's canals
        for (int i = 0; i < grayscale.length; i++) {    //per each group of 4 rgba's bytes avoid alpha byte (j) and
            int average = (byteToUnsigned(rgba[j]) + byteToUnsigned(rgba[j + 1]) + byteToUnsigned(rgba[j + 2])) / 3;
            grayscale[i] = unsignedToByte(average);
            j = j + 4;
        }
        return grayscale;
    }
}