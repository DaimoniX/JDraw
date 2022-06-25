package jdraw.ui.components.controlbar;

import jdraw.ui.IconLoader;
import jdraw.ui.components.IconButton;
import jdraw.ui.components.workingarea.paintarea.PaintArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BrushSettings extends JPanel {
    private final IconButton minus;
    private final IconButton plus;
    private final IconButton dash;
    private final PaintArea paintArea;
    private float brushSize;
    private final JLabel brushSizeLabel;

    public BrushSettings(IconLoader loader, PaintArea paintArea) {
        this.paintArea = paintArea;
        minus = new IconButton(loader.getImage("minus.png"), getBackground());
        plus = new IconButton(loader.getImage("plus.png"), getBackground());
        dash = new IconButton(loader.getImage("dash.png"), getBackground());
        minus.addActionListener(e -> changeSize(-0.5f, (e.getModifiers() & ActionEvent.SHIFT_MASK) == 1));
        plus.addActionListener(e -> changeSize(0.5f, (e.getModifiers() & ActionEvent.SHIFT_MASK) == 1));
        dash.addActionListener(e -> {
            paintArea.setDashed(!paintArea.isDashed());
            if(paintArea.isDashed())
                dash.select();
            else
                dash.deselect();
        });
        brushSize = paintArea.getStrokeWidth();
        brushSizeLabel = new JLabel("" + brushSize);
        brushSizeLabel.setForeground(Color.WHITE);
        add(minus);
        add(brushSizeLabel);
        add(plus);
        add(dash);
    }

    public void changeSize(float delta, boolean isShiftDown) {
        brushSize += delta * (isShiftDown ? 10 : 1);
        brushSize = Math.min(Math.max(brushSize, 0.5f), 80f);
        brushSizeLabel.setText("" + brushSize);
        paintArea.setStrokeWidth(brushSize);
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if(minus != null && plus != null) {
            minus.setBackground(bg);
            plus.setBackground(bg);
            dash.setBackground(bg);
            brushSizeLabel.setBackground(bg);
        }
    }
}
