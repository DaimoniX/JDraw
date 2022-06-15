package jdraw.ui.components.toolbar;

import javax.swing.*;
import java.awt.*;

class ToolbarButton extends JButton {
    private final Image icon;
    private boolean selected;
    private final Dimension preferredSize;

    public void select() {
        selected = true;
        repaint();
    }

    public void deselect() {
        selected = false;
        repaint();
    }

    public ToolbarButton(Image icon, Color backgroundColor) {
        super("  ");
        setBackground(backgroundColor);
        this.icon = icon;
        setBorderPainted(false);
        setOpaque(true);
        selected = false;
        preferredSize = new Dimension(-1, -1);
    }

    @Override
    public Dimension getPreferredSize() {
        int size = super.getPreferredSize().width;
        preferredSize.setSize(size, size);
        return preferredSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int imagePadding = 2;
        int size = getPreferredSize().width;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, size, size);

        g2d.setColor(getBackground().darker().darker());
        if(selected) {
            g2d.fillRoundRect(0, 0, size - 1, size - 1, 20, 20);
        } else if (getModel().isRollover()) {
            g2d.drawRoundRect(0, 0, size - 1, size - 1, 20, 20);
        }

        g2d.drawImage(icon, imagePadding, imagePadding, size - imagePadding * 2, size - imagePadding * 2, null);
    }
}
