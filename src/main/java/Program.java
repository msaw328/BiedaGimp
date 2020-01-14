import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class Program {

    public static void main(String[] args) throws IOException {

        byte[] bytes = new byte[] {100, 100, 100, 100, 101, 101, 101, 101, 102, 102, 102, 102, 103, 103, 103, 103, 104, 104, 104, 104, 105, 105, 105, 105};

        ImageState imageState = new ImageState(2, 3, bytes);   //create new ImageState

        History history = new History(imageState);    //create history object



        byte[] pixel = {80, 81, 82, 83};

        imageState.setPixel(1, 1, pixel);       //set pixel at x = 1 and y = 1 to value of variable pixel

        history.push(1, imageState, "Transform1");



        byte[] pixel2 = {10, 11, 12, 13};

        imageState.setPixel(1,1,pixel2);

        history.push(2, imageState, "Transform2");



        //After 3 changes print history array
        System.out.println("Original");
        System.out.println(Arrays.toString(history.getOriginal().getBuffer()));
        System.out.println(history.getNames().get(0));
        System.out.println(Arrays.toString(history.asList().get(0).getBuffer()));
        System.out.println(history.getNames().get(1));
        System.out.println(Arrays.toString(history.asList().get(1).getBuffer()));


        //checking cleaning history list
       // history.clear();
        //System.out.println(history.asList().isEmpty());

        //checking pushing and removing items
        byte[] pixel3 = {120, 121, 122, 123};
        imageState.setPixel(1,1,pixel3);
        history.push(0, imageState, "Transform3");
        System.out.println(history.getNames().get(0));
        System.out.println(Arrays.toString(history.asList().get(0).getBuffer()));
        System.out.println(history.getNames().get(1));
        System.out.println(Arrays.toString(history.asList().get(1).getBuffer()));
        System.out.println(history.asList().size());
    }
}
