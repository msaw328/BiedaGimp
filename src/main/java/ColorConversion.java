public class ColorConversion {
    //functions converts data between byte and integer type:
    //https://stackoverflow.com/questions/7401550/how-to-convert-int-to-unsigned-byte-and-back
    private static int byteToUnsigned(byte b) {
        return b & 0xFF;
    }

    private static byte unsignedToByte(int b) {
        return (byte) (b);
    }

    public static byte[] abgrToArgb(byte[] abgr) {
        byte[] argb = new byte[abgr.length];
        for(int i = 0; i < argb.length; i += 4) {
            argb[i + 0] = abgr[i + 0];
            argb[i + 1] = abgr[i + 3];
            argb[i + 2] = abgr[i + 2];
            argb[i + 3] = abgr[i + 1];
        }

        return argb;
    }

    public static byte[] bgrToArgb(byte[] bgr) {
        byte[] argb = new byte[bgr.length + bgr.length / 3];
        for(int i = 0; i < argb.length; i += 4) {
            int j = i / 4 * 3; // index in bgr buffer
            argb[i + 0] = unsignedToByte(255);
            argb[i + 1] = bgr[j + 2];
            argb[i + 2] = bgr[j + 1];
            argb[i + 3] = bgr[j + 0];
        }

        return argb;
    }

    public static byte[] rgbToArgb(byte[] abgr) { // INT RGB with ignored alpha, make sure its 255 everywhere
        byte[] argb = new byte[abgr.length];
        for(int i = 0; i < argb.length; i += 4) {
            argb[i + 0] = unsignedToByte(255);
            argb[i + 1] = abgr[i + 1];
            argb[i + 2] = abgr[i + 2];
            argb[i + 3] = abgr[i + 3];
        }

        return argb;
    }

    public static byte[] argbToAbgr(byte[] rgb) { // its the same thing as the other way around actually
        return abgrToArgb(rgb);
    }

    public static byte[] argbToRgb(byte[] argb) {
        byte[] rgb = new byte[argb.length - argb.length / 4];   // in each group of 4 bytes is 1 alpha byte
        for (int i = 0; i < rgb.length; i += 3) {
            int j = i / 3 * 4;
            rgb[i + 0] = argb[j + 1];
            rgb[i + 1] = argb[j + 2];
            rgb[i + 2] = argb[j + 3];
        }
        return rgb;
    }

    public static byte[] argbToBgr(byte[] argb) {
        byte[] bgr = new byte[argb.length - argb.length / 4];   // in each group of 4 bytes is 1 alpha byte
        for (int i = 0; i < bgr.length; i += 3) {
            int j = i / 3 * 4;
            bgr[i + 0] = argb[j + 3];
            bgr[i + 1] = argb[j + 2];
            bgr[i + 2] = argb[j + 1];
        }
        return bgr;
    }

    public static byte[] grayscaleToArgb(byte[] grayscale) {
        byte[] rgba = new byte[4 * grayscale.length];   //grayscale has only 1 canal. Rgba has 4.
        int j = 0;  //needed to increment grayscale's canals
        for (int i = 0; i < rgba.length; i = i + 4) {   //per each grayscale's byte fall 3 rgba's colors bytes and 1 alpha byte
            rgba[i] = unsignedToByte(255);
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