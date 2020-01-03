import java.util.Arrays;

public class Program {

    public static void main(String[] args) {
        byte[] bytes = new byte[] {100, 100, 100, 100, 101, 101, 101, 101, 102, 102, 102, 102, 103, 103, 103, 103, 104, 104, 104, 104, 105, 105, 105, 105};
        ImageState imageState = new ImageState(2, 3, bytes);   //create new ImageState
        History history = new History();    //create history object
        history.push(imageState);

        byte[] pixel = {80, 81, 82, 83};
        imageState.setPixel(1, 1, pixel);       //set pixel at x = 1 and y = 1 to value of variable pixel
        history.push(imageState);

        byte[] pixel2 = {10, 11, 12, 13};
        imageState.setPixel(1,1,pixel2);
        history.push(imageState);

        //After 3 changes print history array
        System.out.println(Arrays.toString(history.getElement(0).getBuffer()));
        System.out.println(Arrays.toString(history.getElement(1).getBuffer()));
        System.out.println(Arrays.toString(history.getElement(20).getBuffer()));

        // time measurement
        RectangleArea a = new RectangleArea(0, 0, 1920, 1080); // RectangleArea containing all pixels

        long start = System.nanoTime();
        for(int i = 0; i < 1920; i++) { // iterating over 1920x1080 pixels
            for(int j = 0; j < 1080; j++) {
                boolean res = a.contains(i, j);
            }
        }
        long end = System.nanoTime();

        float timeInMs = ((float) (end - start)) / 1000000.0f;

        System.out.println("Time measurement for 1920x1080 rectangular selection - performing contains() on every pixel");
        System.out.println(Float.toString(timeInMs) + " ms");
    }
}
