package jdraw.brushes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.geom.*;

public class EllipseBrush extends Brush implements GhostShape {
    public EllipseBrush() {
        ghost = null;
    }

    private Ellipse2D ghost;
    private Point startPoint;

    public Shape getGhost() {
        return ghost;
    }

    @Override
    public void onMousePress(MouseEvent e, Graphics2D g, BufferedImage img) {
        startPoint = e.getPoint();
        ghost = new Ellipse2D.Float();
    }

    @Override
    public void onMouseDrag(MouseEvent e, Graphics2D g, BufferedImage img) {
        int startX = Math.min(startPoint.x, e.getX());
        int startY = Math.min(startPoint.y, e.getY());
        int sizeX = Math.abs(startPoint.x - e.getX());
        int sizeY = Math.abs(startPoint.y - e.getY());
        ghost.setFrame(startX, startY, sizeX, sizeY);
    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g, BufferedImage img) {
        g.setColor(getColor());
        g.drawOval((int) ghost.getX(), (int) ghost.getY(), (int) ghost.getWidth(), (int) ghost.getHeight());
        startPoint = null;
        ghost = null;
    }
}
