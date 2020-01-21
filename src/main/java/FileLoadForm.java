import javax.swing.*;
import java.io.File;

public class FileLoadForm {
    private JPanel contentPane;

    private FileLoadForm() {
        createUIComponents();
    }

    public static void main() {
        JFrame frame = new JFrame("Open file");
        FileLoadForm form = new FileLoadForm();
        frame.setContentPane(form.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        JFileChooser fileChooser = new JFileChooser();
        int status = fileChooser.showOpenDialog(form.contentPane);

        if(status == JFileChooser.APPROVE_OPTION) {
            String filepath = fileChooser.getSelectedFile().getPath();
            try {
                Project.open(filepath);
                frame.dispose();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    private void createUIComponents() {
        contentPane = new JPanel();
    }
}
