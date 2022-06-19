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

    @Override
    public void drawGhost(Graphics2D g, int sizeCoefficient) {
        if (ghost != null) {
            Rectangle rect = (Rectangle) ghost.clone();
            rect.setLocation((int) (ghost.getX() * sizeCoefficient / 100), (int) (ghost.getY() * sizeCoefficient / 100));
            rect.setSize((int) (ghost.getWidth() * sizeCoefficient / 100), (int) (ghost.getHeight() * sizeCoefficient / 100));
            GhostShape.drawGhost(rect, g);
        }
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
        ghost.setRect(startX, startY, sizeX, sizeY);
    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g, BufferedImage img) {
        g.setColor(getColor());
        if (e.isShiftDown())
            g.fillRect(ghost.x, ghost.y, ghost.width, ghost.height);
        else
            g.drawRect(ghost.x, ghost.y, ghost.width, ghost.height);
        startPoint = null;
        ghost = null;
    }
}
