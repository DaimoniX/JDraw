package jdraw.brushes;

import java.awt.*;

public interface GhostShape {
    Color GHOST_COLOR = Color.BLUE;
    Color XOR_COLOR = Color.WHITE;
    void drawGhost(Graphics2D g, int sizeCoefficient);

    static void drawGhost(Shape s, Graphics2D g) {
        g.setColor(GHOST_COLOR);
        g.setXORMode(XOR_COLOR);
        g.draw(s);
        g.setPaintMode();
    }
}
