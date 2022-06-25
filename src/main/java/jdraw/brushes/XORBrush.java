package jdraw.brushes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class XORBrush extends RectBrush {
    private static Color invert(Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g, BufferedImage img) {
        g.setXORMode(invert(getColor()));
        super.onMouseRelease(e, g, img);
        g.setPaintMode();
    }
}
