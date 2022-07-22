package jdraw;

import jdraw.brushes.pack.BrushPack;
import jdraw.ui.*;
import jdraw.ui.components.controlbar.ControlBar;
import jdraw.ui.components.toolbar.ToolBar;
import jdraw.ui.components.workingarea.WorkingArea;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class JDraw extends JFrame {
    private final static Dimension MINIMUM_SIZE = new Dimension(720, 640);
    private final static Color AREA_COLOR = new Color(0xE5E5E5);
    private final static Color BAR_COLOR = Color.DARK_GRAY;
    private final WorkingArea workingArea;
    private File currentFile;

    public JDraw() {
        // Basic setup
        super();
        setLayout(new BorderLayout());
        setMinimumSize(MINIMUM_SIZE);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(MINIMUM_SIZE);

        // Set Nimbus as look and feel
        for (var lookAndFeelInfo : UIManager.getInstalledLookAndFeels()) {
            if (lookAndFeelInfo.getName().equals("Nimbus")) {
                try {
                    UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                } catch (Exception e) {
                    System.err.println("Nimbus look and feel not available");
                }
            }
        }

        // Initialize all components
        IconLoader loader = new IconLoader();
        BrushPack brushPack = new BrushPack(loader);

        workingArea = new WorkingArea();

        ControlBar controlBar = new ControlBar(loader, workingArea);
        ToolBar toolBar = new ToolBar(loader, brushPack, workingArea);

        controlBar.setBackground(BAR_COLOR);
        toolBar.setBackground(BAR_COLOR);
        workingArea.setBackground(AREA_COLOR);

        // Set layout
        add(controlBar, BorderLayout.NORTH);
        add(toolBar, BorderLayout.WEST);
        add(workingArea, BorderLayout.CENTER);

        // Set menu
        setJMenuBar(new MenuBar(this));

        // Add dialogue on exit
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                exit();
            }
        });

        // Set temporary active file
        currentFile = new File("unnamed.jpeg");
        int altIndex = 0;
        while (currentFile.exists()) {
            altIndex++;
            currentFile = new File("unnamed (" + altIndex + ").jpeg");
        }
        createNew(currentFile.getAbsolutePath(), 512, 512);

        workingArea.getPaintArea().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                updateTitle();
            }
        });

        // Show window
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showCreationDialog() {
        // File name input
        JTextField name = new JTextField("image");
        // Spinner models
        SpinnerNumberModel widthModel = new SpinnerNumberModel(512, 1, Integer.MAX_VALUE, 1);
        SpinnerNumberModel heightModel = new SpinnerNumberModel(512, 1, Integer.MAX_VALUE, 1);
        // Spinner fields
        JSpinner width = new JSpinner(widthModel);
        JSpinner height = new JSpinner(heightModel);
        // Content of dialog
        Object[] message = {
                "Name:", name,
                "Width:", width,
                "Height:", height
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION)
            createNew(name.getText(), widthModel.getNumber().intValue(), heightModel.getNumber().intValue());
    }

    public void createNew(String path, int width, int height) {
        if (width < 1 || height < 1)
            throw new IllegalArgumentException("Dimension can't be less than 1");
        if (!path.matches(".*\\.jpeg"))
            path += ".jpeg";
        currentFile = new File(path);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.getGraphics().setColor(Color.WHITE);
        image.getGraphics().fillRect(0, 0, image.getWidth(), image.getHeight());
        workingArea.getPaintArea().loadImage(image);
        updateTitle();
    }

    public void save() {
        try {
            save(currentFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String path) throws IOException {
        if (!path.matches(".*\\.jpeg"))
            path += ".jpeg";
        currentFile = new File(path);
        FileOutputStream out = new FileOutputStream(currentFile);
        ImageIO.write(workingArea.getPaintArea().getImage(), "jpeg", out);
        out.close();
        workingArea.getPaintArea().setHasChanges(false);
        updateTitle();
    }

    // Load file and update title
    public void load(String path) throws IOException {
        currentFile = new File(path);
        var in = new FileInputStream(currentFile);
        var img = ImageIO.read(in);
        if (img != null)
            workingArea.getPaintArea().loadImage(img);
        in.close();
        updateTitle();
    }

    // Update window title
    public void updateTitle() {
        String title = (workingArea.getPaintArea().hasChanges() ? "*" : "") + currentFile.getName() + " - JDraw";
        setTitle(title);
    }

    public void exit() {
        // Check if app has unsaved changes
        if (workingArea.getPaintArea().hasChanges()) {
            final Object[] options = {"Save",
                    "Don't save",
                    "Cancel"};
            // Show dialog and get user input
            final int ans = JOptionPane.showOptionDialog(null,
                    "There are unsaved changes in file",
                    "Unsaved changes",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[2]);
            if (ans == 0)
                save();
            if (ans == 0 || ans == 1)
                System.exit(0);
        } else
            System.exit(0);
    }
}
