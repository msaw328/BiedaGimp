import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class RenderForm {
    private JPanel contentPane;
    private Box aligner;
    private JRenderer renderer;
    private boolean zoomKeyPressed = false;

    private static final int ZOOM_KEY = KeyEvent.VK_SHIFT; // use shift for zooming

    public RenderForm() {
        createUIComponents();

        contentPane.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
                if(!zoomKeyPressed) return;

                if(mouseWheelEvent.getUnitsToScroll() < 0) {
                    renderer.zoomIn();
                } else {
                    renderer.zoomOut();
                }
            }
        });
        contentPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                super.keyPressed(keyEvent);

                if(keyEvent.getKeyCode() == ZOOM_KEY) {
                    zoomKeyPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);

                if(keyEvent.getKeyCode() == ZOOM_KEY) {
                    zoomKeyPressed = false;
                }
            }
        });
        contentPane.addFocusListener(new FocusAdapter() { // bugfix, release zoomKey on lsot focus
            @Override
            public void focusGained(FocusEvent focusEvent) {
                super.focusGained(focusEvent);

                zoomKeyPressed = false;
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                super.focusLost(focusEvent);

                zoomKeyPressed = false;
            }
        });
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

        ImageState img;
        try {
            img = ImageIOWrap.read("/home/user/Pictures/meme/arch_sprudogrey.png");
            m.renderer.updateImageState(img);
            frame.setVisible(true);
            ImageIOWrap.write("/home/user/Pictures/meme/arch_sprudo_generated.png", img, BufferedImage.TYPE_3BYTE_BGR);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        };
    }

    private void createUIComponents() {
        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setLayout(new GridBagLayout());
        contentPane.setFocusable(true);
        contentPane.requestFocusInWindow();

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
