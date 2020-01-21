import java.awt.*;
import java.io.IOException;

public class Project {
    private static History history;

    public static int SELECTION_ORIGINAL = -1;
    private static int selection = -1;

    public static void setSelection(int val) {
        selection = val;
    }

    public static void open(String filename) throws IOException {  //Initialise history, add original image
        history = new History(ImageIOWrap.read(filename));
    }

    public static void save(String filename, int imageType) throws IOException {
        if(!filename.endsWith(".png"))
            filename += ".png";

        ImageState img;
        if(selection == SELECTION_ORIGINAL)
            img = history.getOriginal();
        else
            img = history.asList().get(selection);
        ImageIOWrap.write(filename, img, imageType);
    }

    public static void applyTransformUnderName(Transform transform, int iterations, int threads, String name){
        ImageState input;
        if(selection == SELECTION_ORIGINAL)
            input = history.getOriginal();
        else
            input = history.asList().get(selection);

        ImageState newImg = transform.apply(input, iterations, threads);

        history.push(selection, newImg, name);
    }

    public static History getHistory() {
        return history;
    }
}
