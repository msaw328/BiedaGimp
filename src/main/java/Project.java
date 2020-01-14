import java.io.IOException;

public class Project {
    private History history;

    public void open(String filename) throws IOException {  //Initialise history, add original image
        history = new History(ImageIOWrap.read(filename));
    }

    public void save(String filename, int imageType) throws IOException {
        ImageIOWrap.write(filename, history.asList().get(history.asList().size()-1), imageType);
    }

    public ImageState applyTransform(Transform transform, ImageState imageState){
        return transform.apply(imageState);
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
