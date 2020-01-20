import java.lang.reflect.Array;
import java.util.Arrays;

public class MedianFilter extends Effect {
    private double medianR;
    private double medianG;
    private double medianB;
    private byte[] pixel;
    private int[][] numArray;
    private double[] medians = {0, 0, 0};   //contains medians of each RGB channel

    @Override
    protected byte[] getPixel(int x, int y, int width, int height, ImageState input) {
        int position = 0;
        int leftPixels = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isWithinBounds(x + i - 1, y + j - 1, width, height)) leftPixels++; //counting avoided pixels
            }
        }
        numArray = new int[3][9-leftPixels];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isWithinBounds(x + i - 1, y + j - 1, width, height)) continue;
                pixel = input.getPixel(x + i - 1, y + j - 1);
                numArray[0][position] = pixel[1] & 0x0FF;  //r
                numArray[1][position] = pixel[2] & 0x0FF;  //g
                numArray[2][position] = pixel[3] & 0x0FF;  //b
                position++;
            }
        }

        for(int i =0; i< medians.length; i++) {
            Arrays.sort(numArray[i]);
            if (numArray[i].length % 2 == 0)
                medians[i] = ((double) numArray[i][numArray[i].length / 2] + (double) numArray[i][numArray[i].length / 2 - 1]) / 2;
            else
                medians[i] = (double) numArray[i][numArray[i].length / 2];
        }
        return new byte[]{input.getPixel(x,y)[0], (byte)medians[0], (byte)medians[1], (byte)medians[2]};
    }

    private boolean isWithinBounds(int x, int y, int width, int height) {
        return (x > 0 && x < width) && (y > 0 && y < height);
    }
}
