package jdraw.ui.components.controlbar;

import jdraw.ui.components.IconButton;

import java.awt.*;

public class ColorPickerButton extends IconButton {
    private Color color;

    public ColorPickerButton(Color backgroundColor) {
        super(null, backgroundColor);
        color = Color.BLACK;
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    public Color getColor() {
        return color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int size = getPreferredSize().width;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, size, size);

        int padding = 3;

        if (getModel().isRollover()) {
            g2d.setColor(getBackground().brighter());
            g2d.fillRoundRect(0, 0, size - 1, size - 1, 20, 20);
        }

        g2d.setColor(color);
        g2d.fillRoundRect(padding, padding, size - padding * 2 - 1, size - padding * 2 - 1, 20, 20);
    }
}
