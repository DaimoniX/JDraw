package jdraw.brushes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Queue;
import java.util.LinkedList;

public class FillBrush extends Brush {
    @Override
    public void onMousePress(MouseEvent e, Graphics2D g, BufferedImage img) {
        int colorToFill = img.getRGB(e.getX(), e.getY());
        if (getColor().getRGB() == colorToFill)
            return;
        Queue<Point> points = new LinkedList<>();
        points.add(e.getPoint());
        while (!points.isEmpty()) {
            Point point = points.poll();
            if (point.x < img.getWidth() && point.x > -1 && point.y < img.getHeight() && point.y > -1 && colorToFill == img.getRGB(point.x, point.y)) {
                img.setRGB(point.x, point.y, color.getRGB());
                points.add(new Point(point.x + 1, point.y));
                points.add(new Point(point.x - 1, point.y));
                points.add(new Point(point.x, point.y + 1));
                points.add(new Point(point.x, point.y - 1));
            }
        }
    }

    @Override
    public void onMouseDrag(MouseEvent e, Graphics2D g, BufferedImage img) {

    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g, BufferedImage img) {

    }
}
