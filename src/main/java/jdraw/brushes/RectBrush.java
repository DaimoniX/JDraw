package jdraw.brushes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class RectBrush extends Brush implements GhostShape {
    public RectBrush() {
        ghost = null;
    }

    private Rectangle ghost;
    private Point startPoint;

    public Shape getGhost() {
        return ghost;
    }

    @Override
    public void onMousePress(MouseEvent e, Graphics2D g, BufferedImage img) {
        startPoint = e.getPoint();
        ghost = new Rectangle();
    }

    @Override
    public void onMouseDrag(MouseEvent e, Graphics2D g, BufferedImage img) {
        int startX = Math.min(startPoint.x, e.getX());
        int startY = Math.min(startPoint.y, e.getY());
        int sizeX = Math.abs(startPoint.x - e.getX());
        int sizeY = Math.abs(startPoint.y - e.getY());
        ghost.setLocation(startX, startY);
        ghost.setSize(sizeX, sizeY);
    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g, BufferedImage img) {
        g.setColor(getColor());
        g.drawRect(ghost.x, ghost.y, ghost.width, ghost.height);
        startPoint = null;
        ghost = null;
    }
}
