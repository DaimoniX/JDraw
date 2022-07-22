package jdraw.ui.components;

import javax.swing.*;
import java.awt.*;

public class IconButton extends JButton {
    private final Image icon;
    private boolean selected;
    private final Dimension preferredSize;
    private int padding;

    public void select() {
        selected = true;
        repaint();
    }

    public void deselect() {
        selected = false;
        repaint();
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
        repaint();
    }

    public IconButton(Image icon, Color backgroundColor) {
        this(icon, backgroundColor, 4);
    }

    public IconButton(Image icon, Color backgroundColor, int padding) {
        super("  ");
        setBackground(backgroundColor);
        this.icon = icon;
        setBorderPainted(false);
        setOpaque(true);
        selected = false;
        preferredSize = new Dimension(-1, -1);
        this.padding = padding;
    }

    @Override
    public Dimension getPreferredSize() {
        int size = super.getPreferredSize().width;
        preferredSize.setSize(size, size);
        return preferredSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int size = getPreferredSize().width;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, size, size);

        if (selected) { // paint background darker if button is selected
            g2d.setColor(getBackground().darker().darker());
            g2d.fillRoundRect(0, 0, size - 1, size - 1, 20, 20);
        } else if (getModel().isRollover()) { // or brighter if is hovered
            g2d.setColor(getBackground().brighter());
            g2d.fillRoundRect(0, 0, size - 1, size - 1, 20, 20);
        }

        g.drawImage(icon, padding, padding, size - padding * 2, size - padding * 2, null);
    }
}
