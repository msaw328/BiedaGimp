import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Random;

public class MainForm {
    private JPanel contentPane;

    public MainForm() {
        createUIComponents();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Workspace");
        MainForm m = new MainForm();
        frame.setContentPane(m.contentPane);
        frame.setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 400);
        frame.setMinimumSize(new Dimension(600, 400));

        int width = 200;
        int height = 200;
        byte[] data = new byte[width * height * 4];
        Random r = new Random();
        for(int i = 0; i < data.length; i++) {
            if(i % 4 == 3) {
                data[i] = (byte) ((i / 4) % 256);
            } else if(i % 4 == 0){
                data[i] = (byte) 255;
            } else {
                data[i] = (byte) 0;
            }
        }
        ImageState img = new ImageState(width, height, data);

        Box aligner = new Box(BoxLayout.Y_AXIS);
        aligner.setBackground(Color.GRAY);
        aligner.add(Box.createVerticalGlue());
        aligner.add(new JRenderer(img));
        aligner.add(Box.createVerticalGlue());
        aligner.setVisible(true);

        m.contentPane.add(aligner);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setLayout(new GridBagLayout());
    }
}
