public class Program {
    public static void main(String[] args) {
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
