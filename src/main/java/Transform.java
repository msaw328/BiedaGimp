// ImageStateTransform is the most abstract type which describes a way that
// one ImageState can be turned into another

public abstract class Transform {
    public abstract ImageState apply(ImageState input);
}
