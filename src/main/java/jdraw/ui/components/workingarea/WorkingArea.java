package jdraw.ui.components.workingarea;

import jdraw.ui.components.workingarea.paintarea.PaintArea;

import javax.swing.*;
import java.awt.*;

public class WorkingArea extends JPanel {
    private final PaintArea paintArea;

    public WorkingArea() {
        paintArea = new PaintArea();
        paintArea.setBackground(Color.WHITE);
        setLayout(null);
        paintArea.setSize(new Dimension(200, 200));
        paintArea.setLocation(40, 40);
        add(paintArea);
        WorkingAreaMouseHandler mouseHandler = new WorkingAreaMouseHandler(this);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        addMouseWheelListener(mouseHandler);
    }

    public PaintArea getPaintArea() {
        return paintArea;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // TODO: add scrollbars
    }
}
