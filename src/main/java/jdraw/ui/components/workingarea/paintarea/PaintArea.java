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
    private final Dimension modifiedSize;
    private int sizeCoefficient;
    private boolean isDashed;

    public PaintArea() {
        super();
        setBorder(new LineBorder(Color.GRAY, 1));
        isDashed = false;
        PaintAreaMouseHandler handler = new PaintAreaMouseHandler(this);
        addMouseListener(handler);
        addMouseMotionListener(handler);
        size = new Dimension(100, 100);
        modifiedSize = new Dimension(-1, -1);
        setColor(Color.BLACK);
        stroke = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 30);
        loadImage(new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB));
        clear();
    }

    public void loadImage(BufferedImage image) {
        this.image = image;
        imageGraphics = image.createGraphics();
        imageGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        getImageGraphics().setStroke(stroke);
        size.setSize(image.getWidth(), image.getHeight());
        sizeCoefficient = 100;
        modifiedSize.setSize(size.width * sizeCoefficient / 100, size.height * sizeCoefficient / 100 );
        setSize(modifiedSize);
        repaint();
    }

    public void clear() {
        imageGraphics.setPaintMode();
        imageGraphics.setColor(Color.WHITE);
        imageGraphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        repaint();
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
        if (brush != null)
            brush.setColor(color);
    }

    public Brush getBrush() {
        return brush;
    }

    public boolean isDashed() {
        return isDashed;
    }

    public void setDashed(boolean value) {
        isDashed = value;
        setStrokeWidth(getStrokeWidth());
    }

    public float getStrokeWidth() {
        return stroke.getLineWidth();
    }

    public void setStrokeWidth(float width) {
        if (width <= 0)
            return;
        if(isDashed)
            stroke = new BasicStroke(width, stroke.getEndCap(), stroke.getLineJoin(), stroke.getMiterLimit(), new float[] { Math.max(2, width * 2) }, 0);
        else
            stroke = new BasicStroke(width, stroke.getEndCap(), stroke.getLineJoin(), stroke.getMiterLimit());
        imageGraphics.setStroke(stroke);
    }

    public void setBrush(Brush brush) {
        this.brush = brush;
        this.brush.setColor(color);
        ghostShape = null;
        if (this.brush instanceof GhostShape)
            ghostShape = (GhostShape) this.brush;
    }

    public void resize(int units) {
        sizeCoefficient += units;
        if (sizeCoefficient < 5)
            sizeCoefficient = 5;
        else if (sizeCoefficient > 200)
            sizeCoefficient = 200;
        modifiedSize.setSize(size.width * sizeCoefficient / 100, size.height * sizeCoefficient / 100);
        setSize(modifiedSize);
    }

    public int getSizeCoefficient() {
        return sizeCoefficient;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        if (ghostShape != null)
            ghostShape.drawGhost((Graphics2D) g, sizeCoefficient);
    }
}
