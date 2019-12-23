public class Program {

    public static void main(String[] args) {
        byte[] bytes = new byte[] {100, 100, 100, 100, 101, 101, 101, 101, 102, 102, 102, 102, 103, 103, 103, 103, 104, 104, 104, 104, 105, 105, 105, 105};
        ImageState imageState = new ImageState(2, 3, bytes);   //create new ImageState

        String r = new String(imageState.getBuffer());
        System.out.println("Buffer : " + r + "\n");

        String s = new String(imageState.getPixel(1, 1));
        System.out.println("Pixel at x = 1 and y = 1 : " + s + "\n");

        byte[] pixel = {80, 81, 82, 83};
        imageState.setPixel(1, 1, pixel);       //set pixel at x = 1 and y = 1 to value of variable pixel

        String u = new String(imageState.getBuffer());
        System.out.println("Buffer after change : " + u + "\n");

        String t = new String(imageState.getPixel(1, 1));
        System.out.println("Pixel at x = 1 and y = 1 after change : " + t + "\n");

        System.out.println("Height : " + imageState.getHeight() + "\n");

        System.out.println("Width : " + imageState.getWidth() + "\n");
    }
}
