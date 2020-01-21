import javax.swing.*;
import java.awt.image.BufferedImage;

public class FileSaveForm {
    private JPanel contentPane;
    ImageState img;

    private FileSaveForm() {
        createUIComponents();
    }

    public static void main() {
        JFrame frame = new JFrame("Save file");
        FileSaveForm form = new FileSaveForm();
        frame.setContentPane(form.contentPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        JFileChooser fileChooser = new JFileChooser();
        int status = fileChooser.showSaveDialog(form.contentPane);

        if(status == JFileChooser.APPROVE_OPTION) {
            String pathname = fileChooser.getSelectedFile().getPath();
            try {
                Project.save(pathname, BufferedImage.TYPE_4BYTE_ABGR);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        frame.dispose();
        return;
    }

    private void createUIComponents() {
        contentPane = new JPanel();
    }
}
