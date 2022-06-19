package jdraw.ui.components.workingarea;

import jdraw.ui.components.workingarea.paintarea.PaintArea;

import java.awt.*;
import java.awt.event.*;

class WorkingAreaMouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
    private final PaintArea paintArea;
    private boolean isDragging;
    private Point previousPoint;

    public WorkingAreaMouseHandler(WorkingArea area) {
        paintArea = area.getPaintArea();
        isDragging = false;
        previousPoint = null;
    }

    private void movePaintArea(int x, int y) {
        Point loc = paintArea.getLocation();
        int padding = 30;
        loc.y += y;
        loc.x += x;

        if (!(loc.x - padding + paintArea.getWidth() < 0 || loc.y - padding + paintArea.getHeight() < 0
                || loc.x + padding > paintArea.getParent().getWidth() || loc.y + padding > paintArea.getParent().getHeight())) {
            paintArea.setLocation(loc);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int speed = 4;
        if (e.isControlDown()) {
            paintArea.resize(-e.getUnitsToScroll());
        } else {
            if (e.isShiftDown())
                movePaintArea(e.getUnitsToScroll() * speed, 0);
            else
                movePaintArea(0, -e.getUnitsToScroll() * speed);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1 && e.isShiftDown() && !isDragging) {
            previousPoint = e.getPoint();
            isDragging = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1)
            isDragging = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isDragging) {
            movePaintArea(e.getPoint().x - previousPoint.x, e.getPoint().y - previousPoint.y);
            previousPoint = e.getPoint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isDragging = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
