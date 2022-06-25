package jdraw.brushes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Queue;
import java.util.LinkedList;

public class FillBrush extends Brush {
    private static final int THRESHOLD = 20;

    private static int colorDifference(int colorA, int colorB) {
        int diff = 0;
        for(int i = 0; i < 3; i++) {
            diff += Math.abs(colorA % 256 - colorB % 256);
            colorA /= 256;
            colorB /= 256;
        }
        return diff;
    }

    @Override
    public void onMousePress(MouseEvent e, Graphics2D g, BufferedImage img) {
        int colorToFill = img.getRGB(e.getX(), e.getY());
        if (getColor().getRGB() == colorToFill)
            return; // return if clicked color is same as brush color
        Queue<Point> points = new LinkedList<>();
        points.add(e.getPoint());
        while (!points.isEmpty()) {
            Point point = points.poll();
            if (point.x < img.getWidth() && point.x > -1 && point.y < img.getHeight() && point.y > -1 && // bounds check
                    getColor().getRGB() != img.getRGB(point.x, point.y) && colorDifference(colorToFill, img.getRGB(point.x, point.y)) < THRESHOLD) { // color threshold check
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
