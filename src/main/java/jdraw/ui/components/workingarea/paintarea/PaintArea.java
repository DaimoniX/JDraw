package jdraw.ui.components.workingarea.paintarea;

import jdraw.brushes.Brush;
import jdraw.brushes.GhostShape;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PaintArea extends JPanel {
    private BufferedImage image;
    private Graphics2D imageGraphics;
    private final Dimension size;
    private Brush brush;
    private BasicStroke stroke;
    private Color color;
    private GhostShape ghostShape;

    public PaintArea() {
        super();
        setBorder(new LineBorder(Color.GRAY, 1));
        PaintAreaMouseHandler handler = new PaintAreaMouseHandler(this);
        addMouseListener(handler);
        addMouseMotionListener(handler);
        size = new Dimension(100, 100);
        setColor(Color.BLACK);
        stroke = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        loadImage(new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB));
    }

    public void loadImage(BufferedImage image) {
        this.image = image;
        imageGraphics = image.createGraphics();
        imageGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        getImageGraphics().setStroke(stroke);
        size.setSize(image.getWidth(), image.getHeight());
        setSize(size);
        clear();
    }

    public void clear() {
        imageGraphics.setColor(Color.WHITE);
        imageGraphics.fillRect(0, 0, image.getWidth(), image.getHeight());
    }

    public BufferedImage getImage() {
        return image;
    }

    public Graphics2D getImageGraphics() {
        return imageGraphics;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Brush getBrush() {
        return brush;
    }

    public float getStrokeWidth() {
        return stroke.getLineWidth();
    }

    public void setStrokeWidth(float width) {
        if (width <= 0)
            return;
        stroke = new BasicStroke(width, stroke.getEndCap(), stroke.getLineJoin());
    }

    public void setBrush(Brush brush) {
        this.brush = brush;
        this.brush.setColor(color);
        ghostShape = null;
        if(this.brush instanceof GhostShape)
            ghostShape = (GhostShape) this.brush;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        if(ghostShape != null) {
            Shape ghost = ghostShape.getGhost();
            if(ghost != null) {
                g2d.setColor(Color.BLUE);
                g2d.draw(ghost);
            }
        }
    }
}
