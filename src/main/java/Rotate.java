// implements a clockwise rotation of 90, 180 or 270 degrees

public class Rotate extends Transform {
    public final static int ANGLE_90 = 0;
    public final static int ANGLE_180 = 1;
    public final static int ANGLE_270 = 2;

    private int angle;

    @Override
    protected int getOutputWidth(ImageState input) {
        if(this.angle == ANGLE_180)
            return input.getWidth();    // unless we rotate 180...
        else
            return input.getHeight();   // ...dimensions are swapped
    }

    @Override
    protected int getOutputHeight(ImageState input) {
        if(this.angle == ANGLE_180)
            return input.getHeight();   // unless we rotate 180...
        else
            return input.getWidth();    // ...dimensions are swapped
    }

    @Override
    protected byte[] getPixel(int x, int y, int width, int height, ImageState input) {
        byte[] pix;

        switch(this.angle) {
            default:
            case ANGLE_90:
                pix = input.getPixel(y, width - x - 1);
                break;

            case ANGLE_180:
                pix = input.getPixel(width - x - 1, height - y - 1);
                break;

            case ANGLE_270:
                pix = input.getPixel(height - y - 1, x);
                break;
        }

        return pix;
    }

    // constructor takes one of the ANGLE_ constants as parameter
    public Rotate(int angle) {
        this.angle = angle;
    }
}
