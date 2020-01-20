public class Colorization extends Effect {
    private int r;
    private int g;
    private int b;

    @Override
    protected byte[] getPixel(int x, int y, int width, int height, ImageState input) {
        return implementation(input.getPixel(x,y));
    }

    public Colorization(int r, int g, int b){
            this.r = r;
            this.g = g;
            this.b = b;
    }

    private byte[] implementation(byte[] pixel){
        int r,g,b;
        //if pixel color value is greater than max value assign 255
        if((this.r + pixel[1] & 0xFF) > 255)
            r = 255;
        else r = (this.r+pixel[1] & 0xFF);
        if((this.g + pixel[2] & 0xFF) > 255)
            g = 255;
        else g = (this.g+pixel[1] & 0xFF);
        if((this.b + pixel[3] & 0xFF) > 255)
            b = 255;
        else b = (this.b + pixel[1] & 0xFF);

        return new byte[]{pixel[0],(byte)(r), (byte)(g), (byte)(b)};
    }
}
