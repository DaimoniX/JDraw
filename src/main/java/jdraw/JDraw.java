package jdraw;

import jdraw.ui.*;
import jdraw.ui.components.controlbar.ControlBar;
import jdraw.ui.components.toolbar.ToolBar;
import jdraw.ui.components.workingarea.WorkingArea;

import javax.swing.*;
import java.awt.*;

public class JDraw extends JFrame {
    private final static Dimension minimumSize = new Dimension(720, 640);
    private final static Color areaColor = Color.WHITE;
    private final static Color barColor = Color.DARK_GRAY;

    public JDraw() {
        super();
        setTitle("JDraw");
        setLayout(new BorderLayout());
        setMinimumSize(minimumSize);
        setSize(minimumSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        var loader = new IconLoader();

        var cBar = new ControlBar();
        var tBar = new ToolBar(loader);
        var wArea = new WorkingArea();

        cBar.setBackground(barColor);
        tBar.setBackground(barColor);
        wArea.setBackground(areaColor);

        add(cBar, BorderLayout.NORTH);
        add(tBar, BorderLayout.WEST);
        add(wArea, BorderLayout.CENTER);

        setVisible(true);
    }
}
