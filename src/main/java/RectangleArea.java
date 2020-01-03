public class RectangleArea extends Area {
    private int x; // x coordinate of top left corner
    private int y; // y coordinate of top left corner
    private int w; // Rectangle's width
    private int h; // Rectangle's height

    public RectangleArea(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public boolean contains(int x, int y) {
        // point has to be between x and x + width on X axis and
        // between y and y + height on Y axis
        return x >= this.x && x <= (this.x + this.w) && y >= this.y && y <= (this.y +this.h);
    }
}
