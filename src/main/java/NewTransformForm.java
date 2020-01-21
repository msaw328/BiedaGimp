import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewTransformForm {
    private static final Class[] transformClassArray = new Class[] { // a constant array of available classes
            Rotate.class,
            MirrorHorizontal.class,
            MirrorVertical.class,
            GaussianBlur.class,
            MedianFilter.class,
            Grayscale.class,
            Sobel.class,
            WeirdSobel.class
    };

    private JPanel contentPane;
    private JPanel listPane;
    private JPanel optionPane;

    private JPanel iterationPane;
    private JPanel threadPane;
    private JPanel namePane;

    private JTextField iterationInput;
    private JTextField threadInput;
    private JTextField nameInput;

    private JButton applyBt;

    private JList transformList;

    private NewTransformForm() {
        createUIComponents();

        transformList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                nameInput.setText(transformClassArray[transformList.getSelectedIndex()].getName());
            }
        });

        transformList.setSelectedIndex(0);
        nameInput.setText(transformClassArray[transformList.getSelectedIndex()].getName());

        applyBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int threadCount;
                int iterations;

                try {
                    threadCount = Integer.parseInt(threadInput.getText());
                    iterations = Integer.parseInt(iterationInput.getText());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(contentPane, "Iterations and Threads need to be a number");
                    return;
                }

                String threadCountWarning = "Seems like you want to use more threads than there is cores available. Using more threads than cores available does not grant any performance boost. Continue anyways?";
                if(threadCount > Runtime.getRuntime().availableProcessors()) {
                    int reply = JOptionPane.showConfirmDialog(contentPane, threadCountWarning, "Warning", JOptionPane.YES_NO_OPTION);
                    if(reply == JOptionPane.NO_OPTION) return;
                }

                if(nameInput.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Name cannot be empty");
                    return;
                }
                String name = nameInput.getText().trim();

                if(transformList.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(contentPane, "Select a transform");
                    return;
                }

                applyBt.setEnabled(false); // here validation ends, disable button until transform is complete
                Class transformClass = transformClassArray[transformList.getSelectedIndex()];

                Transform t;
                if(transformClass == GaussianBlur.class) { // only GaussianBlur takes an additional argument
                    String[] options = {"3x3", "5x5"};
                    int reply = JOptionPane.showOptionDialog(contentPane, "Select kernel size for Gaussian blur:", "Additional parameters", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);

                    if(reply == 0) // 3x3
                        t = new GaussianBlur(GaussianBlur.MAT3);
                    else
                        t = new GaussianBlur(GaussianBlur.MAT5);
                } else {
                    try {
                        t = (Transform) transformClass.newInstance();
                    } catch(Exception e) {
                        JOptionPane.showMessageDialog(contentPane, "Cannot instantiate transform, critical");
                        return;
                    }
                }

                Project.applyTransformUnderName(t, iterations, threadCount, name);

                r.updateHistoryList();
                frame.dispose();
            }
        });
    }

    private RenderForm r;
    private static JFrame frame;

    public static void main(RenderForm r) { // main needs reference to RenderForm so he can tell it to update history view
        frame = new JFrame("New Transform");
        NewTransformForm n = new NewTransformForm();
        n.r = r; // keep reference to RenderForm
        frame.setContentPane(n.contentPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setLayout(new BorderLayout());

        listPane = new JPanel();
        listPane.setLayout(new BorderLayout());
        listPane.setBackground(Color.LIGHT_GRAY);
        listPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Available Transforms"));

        transformList = new JList();
        transformList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        transformList.setBackground(Color.WHITE);

        DefaultListModel<String> model = new DefaultListModel<>();
        for(Class c : transformClassArray) {
            model.addElement(c.getName());
        }
        transformList.setModel(model);
        transformList.setSelectedIndex(0);

        optionPane = new JPanel();
        optionPane.setBackground(Color.DARK_GRAY);
        optionPane.setLayout(new BorderLayout());
        optionPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        optionPane.setPreferredSize(new Dimension(0, 80));

        iterationPane = new JPanel();
        iterationPane.setBackground(Color.LIGHT_GRAY);
        iterationPane.setLayout(new BorderLayout());
        iterationPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Iterations"));
        iterationPane.setPreferredSize(new Dimension(200, 0));

        iterationInput = new JTextField();
        iterationInput.setText("1");
        iterationPane.add(iterationInput, BorderLayout.CENTER);

        threadPane = new JPanel();
        threadPane.setBackground(Color.LIGHT_GRAY);
        threadPane.setLayout(new BorderLayout());
        String threadPaneText = "Threads (Recommended: " + Runtime.getRuntime().availableProcessors() + ")";
        threadPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), threadPaneText));
        threadPane.setPreferredSize(new Dimension(200, 0));

        threadInput = new JTextField();
        threadInput.setText(Integer.toString(Runtime.getRuntime().availableProcessors()));
        threadPane.add(threadInput, BorderLayout.CENTER);

        namePane = new JPanel();
        namePane.setBackground(Color.LIGHT_GRAY);
        namePane.setLayout(new BorderLayout());
        namePane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Name (in history)"));
        namePane.setPreferredSize(new Dimension(200, 0));

        nameInput = new JTextField();
        namePane.add(nameInput, BorderLayout.CENTER);

        applyBt = new JButton();
        applyBt.setText("Apply");

        optionPane.add(iterationPane, BorderLayout.LINE_START);
        optionPane.add(threadPane, BorderLayout.CENTER);
        optionPane.add(namePane, BorderLayout.LINE_END);
        optionPane.add(applyBt, BorderLayout.PAGE_END);

        contentPane.add(listPane, BorderLayout.CENTER);
        contentPane.add(optionPane, BorderLayout.PAGE_END);
        listPane.add(transformList, BorderLayout.CENTER);
    }
}
