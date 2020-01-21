import java.lang.reflect.Array;
import java.util.*;

public class MedianFilter extends Effect {

    private boolean isWithinBounds(int x, int y, int width, int height) {
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }

    @Override
    protected byte[] getPixel(int x, int y, int width, int height, ImageState input) {
        ArrayList<Integer> listR = new ArrayList<>(8);
        ArrayList<Integer> listG = new ArrayList<>(8);
        ArrayList<Integer> listB = new ArrayList<>(8);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int currentX = x + i - 1;
                int currentY = y + j - 1;

                if(currentX == x && currentY == y) continue; // dont count current pixel in

                if (!isWithinBounds(currentX, currentY, width, height)) continue;

                byte[] pix = input.getPixel(currentX, currentY);

                listR.add(pix[1] & 0xFF);
                listG.add(pix[2] & 0xFF);
                listB.add(pix[3] & 0xFF);
            }
        }

        Collections.sort(listR);
        Collections.sort(listG);
        Collections.sort(listB);

        int len = listR.size();

        int medianR;
        int medianG;
        int medianB;
        if(len % 2 == 0) {
            medianR = (listR.get(len / 2) + listR.get((len - 1) / 2)) / 2;
            medianG = (listG.get(len / 2) + listG.get((len - 1) / 2)) / 2;
            medianB = (listB.get(len / 2) + listB.get((len - 1) / 2)) / 2;
        } else {
            medianR = listR.get(len / 2);
            medianG = listG.get(len / 2);
            medianB = listB.get(len / 2);
        }

        return new byte[] { input.getPixel(x, y)[0], (byte) medianR, (byte) medianG, (byte) medianB };
    }
}
