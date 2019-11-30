public class ColorConversion {

    private static int byteToUnsigned(byte b)
    {
        return b & 0xFF;
    }

    public int[] rgbToRgba(byte[] rgb) {
        int[] rgba = new int[3];
        rgba[0] = byteToUnsigned(rgb[0]);
        rgba[1] = byteToUnsigned(rgb[1]);
        rgba[2] = byteToUnsigned(rgb[2]);
        rgba[3] = 1;
        return rgba;
    }

    public byte[] rgbaToRgb(int[]rgba) {
        byte[] rgb = new byte[2];
        rgb[0] = (byte) (Byte.MIN_VALUE + rgba[0]);
        rgb[1] = (byte) (Byte.MIN_VALUE + rgba[1]);
        rgb[2] = (byte) (Byte.MIN_VALUE + rgba[2]);
        return rgb;
    }

    public int[] grayscaleToRgba(byte[] grayscale)
    {
        int[] rgba = new int[3];
        rgba[0] = (byteToUnsigned(grayscale[0]) > 127) ? 255 : (byteToUnsigned(grayscale[0]) / 2);
        rgba[1] = (byteToUnsigned(grayscale[0]) > 127) ? (byteToUnsigned(grayscale[0]) / 2 ) : 0;
        rgba[2] = (byteToUnsigned(grayscale[0]) > 127) ? (byteToUnsigned(grayscale[0]) / 2 ) : 0;
        rgba[3] = 1;
        return rgba;
    }

    public byte[] rgbaToGrayscale(int[] rgba)
    {
        byte[] grayscale = new byte[3];
        int average = (rgba[0] + rgba[1] + rgba[2]) / 3;
        grayscale[0] = (byte) (Byte.MIN_VALUE + average);
        grayscale[1] = (byte) (Byte.MIN_VALUE + average);
        grayscale[2] = (byte) (Byte.MIN_VALUE + average);
        grayscale[3] = (byte) ((Byte.MIN_VALUE + average) / 255);
        return grayscale;
    }
}


