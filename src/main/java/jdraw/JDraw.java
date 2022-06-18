package jdraw;

import jdraw.brushes.*;
import jdraw.brushes.pack.BrushInfo;
import jdraw.brushes.pack.BrushPack;
import jdraw.ui.*;
import jdraw.ui.components.controlbar.ControlBar;
import jdraw.ui.components.toolbar.ToolBar;
import jdraw.ui.components.workingarea.WorkingArea;

import javax.swing.*;
import java.awt.*;

public class JDraw extends JFrame {
    private final static Dimension minimumSize = new Dimension(720, 640);
    private final static Color areaColor = new Color(0xE5E5E5);
    private final static Color barColor = Color.DARK_GRAY;

    public JDraw() {
        super();
        setTitle("JDraw");
        setLayout(new BorderLayout());
        setMinimumSize(minimumSize);
        setSize(minimumSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create and add all components
        IconLoader loader = new IconLoader();

        // Add all brushes and icon to brush pack
        BrushPack brushPack = new BrushPack();
        brushPack.add(new BrushInfo(new BasicBrush(), loader.getImage("pen.png")));
        brushPack.add(new BrushInfo(new EraserBrush(), loader.getImage("eraser.png")));
        brushPack.add(new BrushInfo(new LineBrush(), loader.getImage("line.png")));
        brushPack.add(new BrushInfo(new RectBrush(), loader.getImage("rect.png")));
        brushPack.add(new BrushInfo(new EllipseBrush(), loader.getImage("ellipse.png")));
        brushPack.add(new BrushInfo(new FillBrush(), loader.getImage("fill.png")));
        brushPack.add(new BrushInfo(new TestBrush(), loader.getPlaceholderImage()));

        WorkingArea workingArea = new WorkingArea();

        ControlBar controlBar = new ControlBar();
        ToolBar toolBar = new ToolBar(brushPack, workingArea);

        controlBar.setBackground(barColor);
        toolBar.setBackground(barColor);
        workingArea.setBackground(areaColor);

        add(controlBar, BorderLayout.NORTH);
        add(toolBar, BorderLayout.WEST);
        add(workingArea, BorderLayout.CENTER);

        // Show window
        setVisible(true);
    }
}
