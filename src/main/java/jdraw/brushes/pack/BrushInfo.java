package jdraw.brushes.pack;

import jdraw.brushes.Brush;

import java.awt.*;

public class BrushInfo {
    private final Brush brush;
    private final Image icon;

    public BrushInfo(Brush brush, Image icon) {
        this.brush = brush;
        this.icon = icon;
    }

    public Brush getBrush() {
        return brush;
    }

    public Image getIcon() {
        return icon;
    }
}
