package jdraw.ui.components.workingarea.paintarea;

import java.awt.event.*;

class PaintAreaMouseHandler implements MouseListener, MouseMotionListener {
    private final PaintArea paintArea;

    public PaintAreaMouseHandler(PaintArea paintArea) {
        this.paintArea = paintArea;
    }

    private void offsetPoint(MouseEvent e) {
        float sizeDelta = 100f / paintArea.getSizeCoefficient() - 1f;
        e.translatePoint((int) (e.getPoint().x * sizeDelta), (int) (e.getPoint().y * sizeDelta));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        offsetPoint(e);
        paintArea.getBrush().onMousePress(e, paintArea.getImageGraphics(), paintArea.getImage());
        paintArea.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        offsetPoint(e);
        paintArea.getBrush().onMouseRelease(e, paintArea.getImageGraphics(), paintArea.getImage());
        paintArea.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        offsetPoint(e);
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
