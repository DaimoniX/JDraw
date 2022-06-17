package jdraw.ui.components.workingarea.paintarea;

import java.awt.*;
import java.awt.event.*;

class PaintAreaMouseHandler implements MouseListener, MouseMotionListener {
    private final PaintArea paintArea;

    public PaintAreaMouseHandler(PaintArea paintArea) {
        this.paintArea = paintArea;
    }

    // TODO: Implement mouse events

    @Override
    public void mousePressed(MouseEvent e) {
        paintArea.getBrush().onMousePress(e, paintArea.getImageGraphics(), paintArea.getImage());
        paintArea.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        paintArea.getBrush().onMouseRelease(e, paintArea.getImageGraphics(), paintArea.getImage());
        paintArea.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        paintArea.getBrush().onMouseDrag(e, paintArea.getImageGraphics(), paintArea.getImage());
        paintArea.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
