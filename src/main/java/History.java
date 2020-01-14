import java.util.LinkedList;
import java.util.List;

public class History{
    private List<ImageState> historyAsList = new LinkedList<>();  //List of ImageState changes
    private List<String> names = new LinkedList<>();    //List of used transformation
    private final int length = 10; //Constant length of history
    private ImageState original;    //Variable contains original image

    private void deleteUntil(int index){    //clear history and names lists until meet element at selected index
        if (historyAsList.size() >= index) {
            names.subList(index, names.size()).clear();
            historyAsList.subList(index, historyAsList.size()).clear();
        }
    }

    public void clear(){    //clear whole history list
        deleteUntil(0);
    }

    public void pushOriginal(ImageState imageState, String name){
        deleteUntil(0);
        historyAsList.add(new ImageState(imageState));
        names.add(name);
    }

    public List<ImageState> asList(){
        return historyAsList;
    }

    public List<String> getNames() {
        return names;
    }

    public ImageState getOriginal() {
        return original;
    }

    public void push(int index, ImageState imageState, String name){
        if (historyAsList.size() > length){   //if history is larger than 10 delete the oldest ImageState
            historyAsList.remove(0);
            names.remove(0);
        }
        deleteUntil(index + 1); //omit history(index) element
        names.add(name);
        historyAsList.add(new ImageState(imageState));
    }

    public History(ImageState imageState){
        original = new ImageState(imageState);
    }
}