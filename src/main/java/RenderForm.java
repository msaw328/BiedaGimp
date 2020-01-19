import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RenderForm {
    private JPanel contentPane;
    private JPanel renderPane;
    private JPanel sidePane;
    private JScrollPane scrollPane;
    private JPanel zoomPane;
    private JRenderer renderer;

    private History hist;
    private JPanel historyPane;
    private JButton historyOriginalBt;
    private JList historyList;

    private JPanel newTransformPane;
    private JButton newTransformBt;

    private JButton zoomInBt;
    private JButton zoomOutBt;

    public RenderForm(History hist) {
        this.hist = hist;
        createUIComponents();
        updateHistoryList();
        renderer.updateImageState(hist.getOriginal());

        zoomInBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean canZoomMore = renderer.zoomIn();

                zoomOutBt.setEnabled(true);
                zoomInBt.setEnabled(canZoomMore);
                contentPane.revalidate();
            }
        });

        zoomOutBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean canZoomMore = renderer.zoomOut();

                zoomInBt.setEnabled(true);
                zoomOutBt.setEnabled(canZoomMore);
                contentPane.revalidate();
            }
        });

        historyOriginalBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                renderer.updateImageState(hist.getOriginal());
                historyList.clearSelection();
                contentPane.revalidate();
            }
        });

        historyList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                int index = historyList.getSelectedIndex();
                if(index > -1) {
                    ImageState img = hist.asList().get(index);

                    renderer.updateImageState(img);
                    contentPane.revalidate();
                }
            }
        });

        newTransformBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // TODO: apply new transforms and such
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BiedaGimp");

        try {
            ImageState img = ImageIOWrap.read("/home/user/Pictures/meme/arch_sprudo.png");
            History h = new History(img);
            Transform t = new MirrorVertical();
            h.push(h.asList().size(), t.apply(img), "mirrorv");

            t = new Rotate(Rotate.ANGLE_90);
            h.push(h.asList().size(), t.apply(img), "rot90");

            t = new MirrorHorizontal();
            h.push(h.asList().size(), t.apply(img), "sdpogk");
            RenderForm m = new RenderForm(h);
            frame.setContentPane(m.contentPane);
            frame.setBackground(Color.GRAY);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setSize(1500, 800);
            frame.setVisible(true);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        };
    }

    private void updateHistoryList() {
        DefaultListModel model = new DefaultListModel();
        List<String> l = hist.getNames();
        for(String name : l)
            model.addElement(name);
        historyList.setModel(model);
    }

    private void createUIComponents() {
        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setLayout(new BorderLayout());

        renderPane = new JPanel();
        renderPane.setLayout(new GridBagLayout());
        renderPane.setBackground(Color.GRAY);

        scrollPane = new JScrollPane(renderPane);
        scrollPane.setBackground(Color.GRAY);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        renderer = new JRenderer();
        renderPane.add(renderer);

        sidePane = new JPanel();
        sidePane.setLayout(new BorderLayout());
        sidePane.setPreferredSize(new Dimension(250, 0));
        sidePane.setBackground(Color.DARK_GRAY);
        sidePane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        zoomPane = new JPanel();
        zoomPane.setLayout(new GridBagLayout());
        zoomPane.setPreferredSize(new Dimension(0, 80));
        zoomPane.setBackground(Color.DARK_GRAY);

        zoomInBt = new JButton();
        zoomInBt.setText("Zoom in");

        zoomOutBt = new JButton();
        zoomOutBt.setEnabled(false);
        zoomOutBt.setText("Zoom out");

        zoomPane.add(zoomInBt);
        zoomPane.add(zoomOutBt);

        historyPane = new JPanel();
        historyPane.setLayout(new BorderLayout());
        historyPane.setBackground(Color.LIGHT_GRAY);
        historyPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "History"));

        historyOriginalBt = new JButton();
        historyOriginalBt.setText("Original");

        historyList = new JList();
        historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        historyList.setBackground(Color.WHITE);

        newTransformBt = new JButton();
        newTransformBt.setText("Apply new Transform");

        newTransformPane = new JPanel();
        newTransformPane.setLayout(new GridBagLayout());
        newTransformPane.setPreferredSize(new Dimension(0, 80));
        newTransformPane.setBackground(Color.DARK_GRAY);
        newTransformPane.add(newTransformBt);

        historyPane.add(historyOriginalBt, BorderLayout.PAGE_START);
        historyPane.add(historyList, BorderLayout.CENTER);
        sidePane.add(zoomPane, BorderLayout.PAGE_END);
        sidePane.add(historyPane, BorderLayout.CENTER);
        sidePane.add(newTransformPane, BorderLayout.PAGE_START);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(sidePane, BorderLayout.LINE_END);
    }
}
