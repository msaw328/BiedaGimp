import java.util.LinkedList;
import java.util.List;

public class History{
    private List<ImageState> history = new LinkedList<>();  //List of ImageState changes
    private final int length = 10; //Constant length of history
    private ImageState imageState;
    byte[] newBuffer;   //Buffer necessary to rewrite older one to avoid adverse overwriting

    public List<ImageState> getHistory(){
        return history;
    }

    public ImageState getElement(int index){    //in case of wrong index return last ImageState record
        if(index < history.size()) return history.get(index);
        else return history.get(history.size() - 1);
    }

    public void newImageState(ImageState imageState){
        newBuffer = new byte[imageState.getBuffer().length];
        for(int i = 0; i< imageState.getBuffer().length; i++){  //rewriting imageState buffer
            newBuffer[i] = imageState.getBuffer()[i];
        }
        this.imageState = new ImageState(imageState.getWidth(), imageState.getHeight(), newBuffer); //create new instance of private imageState
        if (history.size() > length){   //if history is larger than 10- delete the oldest ImageState
            history.remove(0);
        }
        history.add(this.imageState);
    }
}
