package jdraw.brushes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

public class StarBrush extends Brush implements GhostShape {
    private Path2D ghost;
    private Point startingPoint;
    private double dist = 0;
    private double angle = 0;
    private int rayCount = 5;

    public int getRayCount() {
        return rayCount;
    }

    public void setRayCount(int rayCount) {
        if (rayCount < 3)
            rayCount = 3;
        this.rayCount = rayCount;
    }

    @Override
    public void onMousePress(MouseEvent e, Graphics2D g, BufferedImage img) {
        startingPoint = e.getPoint();
    }

    @Override
    public void onMouseDrag(MouseEvent e, Graphics2D g, BufferedImage img) {
        if (startingPoint != null) {
            dist = e.getPoint().distance(startingPoint);
            angle = Math.atan2(e.getPoint().y - startingPoint.y, e.getPoint().x - startingPoint.x);
            ghost = createShape(startingPoint.x, startingPoint.y, dist / 2, dist, rayCount, angle);
        }
    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g, BufferedImage img) {
        if (ghost != null) {
            g.setColor(getColor());
            if (e.isShiftDown())
                g.fill(ghost);
            else
                g.draw(ghost);
        }
        startingPoint = null;
        ghost = null;
    }

    @Override
    public void drawGhost(Graphics2D g, int sizeCoefficient) {
        if (ghost != null) {
            Path2D clone = createShape(startingPoint.x * sizeCoefficient / 100f, startingPoint.y * sizeCoefficient / 100f, dist * sizeCoefficient / 200f, dist * sizeCoefficient / 100f, rayCount, angle);
            GhostShape.drawGhost(clone, g);
        }
    }

    private static Path2D createShape(double centerX, double centerY,
                                      double innerRadius, double outerRadius, int numRays,
                                      double startAngleRad) {
        if (numRays < 3)
            throw new IllegalArgumentException("Minimum ray count is 3");
        Path2D path = new Path2D.Double();
        double deltaAngleRad = Math.PI / numRays;
        for (int i = 0; i < numRays * 2; i++) {
            double angleRad = startAngleRad + i * deltaAngleRad;
            double x = Math.cos(angleRad);
            double y = Math.sin(angleRad);
            double radius = i % 2 == 0 ? outerRadius : innerRadius;
            x *= radius;
            y *= radius;
            if (i == 0)
                path.moveTo(centerX + x, centerY + y);
            else
                path.lineTo(centerX + x, centerY + y);
        }
        path.closePath();
        return path;
    }
}
