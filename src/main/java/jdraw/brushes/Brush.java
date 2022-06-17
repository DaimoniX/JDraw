package jdraw.brushes;

import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Brush {
    protected Color color;

    protected Brush() {
        this(Color.BLACK);
    }

    protected Brush(Color color) {
        setColor(color);
    }

    public abstract void onMousePress(MouseEvent e, Graphics2D g, BufferedImage img);

    public abstract void onMouseDrag(MouseEvent e, Graphics2D g, BufferedImage img);

    public abstract void onMouseRelease(MouseEvent e, Graphics2D g, BufferedImage img);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}