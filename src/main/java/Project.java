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

    public static ImageState applyTransform(Transform transform, ImageState imageState){
        return transform.apply(imageState);
    }

    public static History getHistory() {
        return history;
    }
}
