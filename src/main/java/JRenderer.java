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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.img.getWidth(), this.img.getHeight());
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(this.img.getWidth(), this.img.getHeight());
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(this.img.getWidth(), this.img.getHeight());
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

        graphics.drawImage(render, 0, 0, null);
    }

    public JRenderer() {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.GRAY);
        this.setVisible(false);
    }

    public void updateImageState(ImageState img) {
        if(img == null) {
            setVisible(false);
        } else {
            this.img = img;
            this.setSize(img.getWidth(), img.getHeight());
            this.repaint();
            this.setVisible(true);
        }
    }
}
