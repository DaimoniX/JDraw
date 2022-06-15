package jdraw.ui.components.workingarea.paintarea;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PaintArea extends JPanel {
    private final PaintAreaMouseHandler handler;

    public PaintArea() {
        setBorder(new LineBorder(Color.GRAY, 1));
        handler = new PaintAreaMouseHandler(this);
        addMouseListener(handler);
        addMouseMotionListener(handler);
        // TODO: implement logic
    }
}
