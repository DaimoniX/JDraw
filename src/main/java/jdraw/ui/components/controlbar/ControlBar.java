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
    private final ColorPickerButton pickerButton;
    private final BrushSettings brushSettings;

    public ControlBar(IconLoader loader, WorkingArea workingArea) {
        super(50);

        pickerButton = new ColorPickerButton(getBackground());

        pickerButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(null, "Pick color", workingArea.getPaintArea().getColor());
            if (selectedColor != null) {
                workingArea.getPaintArea().setColor(selectedColor);
                pickerButton.setColor(selectedColor);
            }
        });

        brushSettings = new BrushSettings(loader, workingArea.getPaintArea());
        add(brushSettings);
        add(pickerButton);
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if (pickerButton != null && brushSettings != null) {
            pickerButton.setBackground(bg);
            brushSettings.setBackground(bg);
        }
    }
}
