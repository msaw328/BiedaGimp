import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RenderForm {
    private JPanel contentPane;
    private Box aligner;
    private JRenderer renderer;

    public RenderForm() {
        createUIComponents();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Render");
        RenderForm m = new RenderForm();
        frame.setContentPane(m.contentPane);
        frame.setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 400);
        frame.setMinimumSize(new Dimension(600, 400));

        int width = 400;
        int height = 400;
        byte[] data = new byte[width * height * 4];
        Random r = new Random();
        for(int i = 0; i < data.length; i++) {
            if(i % 4 == 3) {
                data[i] = (byte) 255;
            } else {
                data[i] = (byte) (r.nextInt() % 256);
            }
        }
        ImageState img = new ImageState(width, height, data);

        byte[] data2 = new byte[width * height * 4 * 2];
        for(int i = 0; i < data2.length; i++) {
            if(i % 4 == 3) {
                data2[i] = (byte) 255;
            } else {
                data2[i] = (byte) (r.nextInt() % 256);
            }
        }
        ImageState img2 = new ImageState(width * 2, height, data2);
        m.renderer.updateImageState(img);
        frame.setVisible(true);
        try {
            Thread.sleep(3000);
            m.renderer.updateImageState(null);
            Thread.sleep(3000);
            m.renderer.updateImageState(img2);
        } catch(Exception e) {

        }

    }

    private void createUIComponents() {
        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setLayout(new GridBagLayout());

        aligner = new Box(BoxLayout.Y_AXIS);
        aligner.setBackground(Color.GRAY);
        aligner.add(Box.createVerticalGlue());

        renderer = new JRenderer();
        aligner.add(renderer);

        aligner.add(Box.createVerticalGlue());
        aligner.setVisible(true);
        contentPane.add(aligner);
    }

    public void updateImageState(ImageState img) {
        renderer.updateImageState(img);
    }
}
