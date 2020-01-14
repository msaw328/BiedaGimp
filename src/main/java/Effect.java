// Effect is a type of Transform that does not modify
// the dimensions of ImageState, and so it has final implementations
// of getOutputWidth and getOutputHeight


public abstract class Effect extends Transform {
    @Override
    protected final int getOutputWidth(ImageState input) {
        return input.getWidth();
    }

    @Override
    protected final int getOutputHeight(ImageState input) {
        return input.getHeight();
    }
}
