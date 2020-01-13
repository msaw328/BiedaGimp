import javax.swing.*;
import java.awt.*;

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

        for(int x = 0; x < this.img.getWidth(); x++) {
            for(int y = 0; y < this.img.getHeight(); y++) {
                byte[] p = this.img.getPixel(x, y);

                graphics.setColor(new Color(p[0] & 0xff, p[1] & 0xff, p[2] & 0xff, p[3] & 0xff));
                graphics.drawLine(x, y, x, y);
            }
        }
    }

    public JRenderer(ImageState img) {
        super();
        this.img = img;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setSize(img.getWidth(), img.getHeight());
        this.setBackground(Color.GRAY);
        this.setVisible(true);
    }


}
