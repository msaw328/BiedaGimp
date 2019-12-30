import java.util.LinkedList;
import java.util.List;

public class History{
    private List<ImageState> historyAsList = new LinkedList<>();  //List of ImageState changes
    private final int length = 10; //Constant length of history

    public List<ImageState> getHistoryAsList(){
        return historyAsList;
    }

    public ImageState getElement(int index){    //in case of wrong index return last ImageState record
        if(index < historyAsList.size()) return historyAsList.get(index);
        else return historyAsList.get(historyAsList.size() - 1);
    }

    public void push(ImageState imageState){
        if (historyAsList.size() > length){   //if history is larger than 10 delete the oldest ImageState
            historyAsList.remove(0);
        }
        historyAsList.add(new ImageState(imageState));
    }
}