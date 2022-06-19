package jdraw.ui.components.workingarea;

import jdraw.ui.components.workingarea.paintarea.PaintArea;

import javax.swing.*;
import java.awt.*;

public class WorkingArea extends JPanel {
    private final PaintArea paintArea;

    public WorkingArea() {
        super();
        paintArea = new PaintArea();
        paintArea.setBackground(Color.WHITE);
        setLayout(null);
        paintArea.setLocation(20, 20);
        add(paintArea);
        WorkingAreaMouseHandler mouseHandler = new WorkingAreaMouseHandler(this);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        addMouseWheelListener(mouseHandler);
    }

    public PaintArea getPaintArea() {
        return paintArea;
    }
}

