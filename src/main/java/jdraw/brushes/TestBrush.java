package jdraw.brushes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class TestBrush extends Brush {
    private Point point;

    @Override
    public void onMousePress(MouseEvent e, Graphics2D g, BufferedImage img) {
        point = e.getPoint();
    }

    @Override
    public void onMouseDrag(MouseEvent e, Graphics2D g, BufferedImage img) {
        if (point != null) {
            g.setColor(getColor());
            g.drawLine(point.x, point.y, e.getX(), e.getY());
        }
    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g, BufferedImage img) {
        point = null;
    }
}
