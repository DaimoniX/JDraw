package jdraw.ui.components.controlbar;

import jdraw.ui.IconLoader;
import jdraw.ui.components.HorizontalBar;
import jdraw.ui.components.IconButton;
import jdraw.ui.components.workingarea.WorkingArea;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class ControlBar extends HorizontalBar {
    private final LinkedList<JButton> buttons;
    private final WorkingArea workingArea;

    public ControlBar(IconLoader loader, WorkingArea workingArea) {
        super(50);
        this.workingArea = workingArea;

        JFileChooser fileChooser = new JFileChooser("."); // new File(".")
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg");
        fileChooser.setFileFilter(filter);

        buttons = new LinkedList<>();

        var pickerButton = new ColorPickerButton(getBackground());
        var saveButton = new IconButton(loader.getImage("save.png"), getBackground());
        var loadButton = new IconButton(loader.getImage("load.png"), getBackground());

        pickerButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(null, "Pick color", workingArea.getPaintArea().getColor());
            if (selectedColor != null) {
                workingArea.getPaintArea().setColor(selectedColor);
                pickerButton.setColor(selectedColor);
            }
        });

        saveButton.addActionListener(e -> {
            try {
                if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                    save(fileChooser.getSelectedFile().getName());
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });

        loadButton.addActionListener(e -> {
            try {
                if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
                    load(fileChooser.getSelectedFile().getPath());
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });

        buttons.add(pickerButton);
        buttons.add(loadButton);
        buttons.add(saveButton);

        for (var button : buttons) {
            add(button);
        }
        // TODO: add brush settings
    }

    public void load(String path) throws IOException {
        var in = new FileInputStream(path);
        var img = ImageIO.read(in);
        if (img != null)
            workingArea.getPaintArea().loadImage(img);
        in.close();
    }

    public void save(String path) throws IOException {
        if (!path.matches(".*\\.jpg"))
            path += ".jpg";
        var out = new FileOutputStream(path);
        ImageIO.write(workingArea.getPaintArea().getImage(), "jpeg", out);
        out.close();
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if (buttons != null)
            for (var button : buttons)
                button.setBackground(bg);
    }
}
