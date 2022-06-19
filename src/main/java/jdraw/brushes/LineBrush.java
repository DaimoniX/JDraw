package jdraw.brushes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class LineBrush extends Brush implements GhostShape {
    private Point startingPoint;
    private Line2D line;

    @Override
    public void onMousePress(MouseEvent e, Graphics2D g, BufferedImage img) {
        startingPoint = e.getPoint();
        line = new Line2D.Float();
    }

    @Override
    public void onMouseDrag(MouseEvent e, Graphics2D g, BufferedImage img) {
        line.setLine(startingPoint, e.getPoint());
    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g, BufferedImage img) {
        if (startingPoint != null) {
            g.setColor(getColor());
            g.drawLine(startingPoint.x, startingPoint.y, e.getX(), e.getY());
        }
        line = null;
    }

    @Override
    public void drawGhost(Graphics2D g, int sizeCoefficient) {
        if (line != null) {
            Line2D ln = (Line2D) line.clone();
            ln.setLine(line.getX1() * sizeCoefficient / 100, line.getY1() * sizeCoefficient / 100,
                    line.getX2() * sizeCoefficient / 100, line.getY2() * sizeCoefficient / 100);
            GhostShape.drawGhost(ln, g);
        }
    }
}
