import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class JRenderer extends JPanel {
    private ImageState img;
    private int zoom = 1;

    private static final int MAX_ZOOM = 4;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.img.getWidth() * zoom, this.img.getHeight() * zoom);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(this.img.getWidth() * zoom, this.img.getHeight() * zoom);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(this.img.getWidth() * zoom, this.img.getHeight() * zoom);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        // https://stackoverflow.com/a/34318926 much faster than writing single pixel at once with drawLine
        BufferedImage render = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        IntBuffer intBuffer = ByteBuffer.wrap(img.getBuffer()).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] array = new int[intBuffer.remaining()];
        intBuffer.get(array);
        render.setRGB(0, 0, img.getWidth(), img.getHeight(), array, 0, img.getWidth());

        graphics.drawImage(render, 0, 0, img.getWidth() * zoom, img.getHeight() * zoom, null);
    }

    public JRenderer() {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.GRAY);
        this.setVisible(false);
    }

    public void zoomIn() {
        if(zoom < MAX_ZOOM) {
            zoom++;
            this.setSize(img.getWidth() * zoom, img.getHeight() * zoom);
            revalidate();
        }
    }

    public void zoomOut() {
        if(zoom > 1) {
            zoom--;
            this.setSize(img.getWidth() * zoom, img.getHeight() * zoom);
            revalidate();
        }
    }

    public void updateImageState(ImageState img) {
        if(img == null) {
            setVisible(false);
        } else {
            this.img = img;
            this.zoom = 1;
            this.setSize(img.getWidth(), img.getHeight());
            this.repaint();
            this.setVisible(true);
        }
    }
}
