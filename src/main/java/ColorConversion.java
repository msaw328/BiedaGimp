public class ColorConversion {

    //functions converts data between byte and integer type:
    //https://stackoverflow.com/questions/7401550/how-to-convert-int-to-unsigned-byte-and-back
    private static int byteToUnsigned(byte b)
    {
        return b & 0xFF;
    }
    private static byte unsignedToByte(int b)
    {
        return (byte) (b);
    }


    public byte[] rgbToRgba(byte[] rgb) {
        byte[] rgba = new byte[rgb.length + rgb.length/3];
        int j = 0;
        for (int i = 0; i < rgba.length; i++)
        {
           if((i + 1) % 4 != 0)
               rgba[i] = rgb[i - j];
            else {
                rgba[i] = Byte.MAX_VALUE;
                j++;
            }

        }
        return rgba;
    }

    public byte[] rgbaToRgb(byte[] rgba) {
        byte[] rgb = new byte[rgba.length - rgba.length / 4];

        int j = 0;
        for (int i = 0; i < rgba.length; i++)
        {
            if((i + 1) % 4 == 0) j++;
            else rgb[i-j] = rgba[i];
        }
        return rgb;
    }

    public byte[] grayscaleToRgba(byte[] grayscale)
    {
        byte[] rgba = new byte[4 * grayscale.length];
        int j = 0;
        for (int i = 0; i < rgba.length; i=i+4)
        {
            rgba[i] = grayscale[j];
            rgba[i+1] = grayscale[j];
            rgba[i+2] = grayscale[j];
            rgba[i+3] = Byte.MAX_VALUE;
            j++;
        }
        return rgba;
    }

    public byte[] rgbaToGrayscale(byte[] rgba)
    {
        byte[] grayscale = new byte[rgba.length / 4];
        int j = 0;
        for (int i = 0; i < grayscale.length; i++)
        {
                int average = (byteToUnsigned(rgba[j]) + byteToUnsigned(rgba[j+1]) + byteToUnsigned(rgba[j+2])) / 3;
                grayscale[i] = unsignedToByte(average);
                j=j+4;
        }
        return grayscale;
    }
}


