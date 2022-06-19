package jdraw.ui.components.toolbar;

import jdraw.brushes.pack.BrushInfo;
import jdraw.brushes.pack.BrushPack;
import jdraw.ui.IconLoader;
import jdraw.ui.components.IconButton;
import jdraw.ui.components.VerticalBar;
import jdraw.ui.components.workingarea.WorkingArea;

import java.awt.*;

public class ToolBar extends VerticalBar {
    private final IconButton[] buttons;
    private final IconButton clearButton;
    private int selectedButton;

    public ToolBar(IconLoader loader, BrushPack brushPack, WorkingArea wArea) {
        super(60);
        buttons = new IconButton[brushPack.getBrushInfos().size()];

        int index = 0;
        for (BrushInfo brush : brushPack.getBrushInfos()) {
            buttons[index] = new IconButton(brush.getIcon(), getBackground());
            int finalIndex = index;
            buttons[index].addActionListener(e -> selectButton(finalIndex));
            buttons[index].addActionListener(e -> wArea.getPaintArea().setBrush(brush.getBrush()));
            add(buttons[index++]);
        }

        selectedButton = 0;
        buttons[0].doClick();

        clearButton = new IconButton(loader.getImage("cross.png"), getBackground());
        clearButton.addActionListener(e -> wArea.getPaintArea().clear());
        add(clearButton);
    }

    private void selectButton(int index) {
        buttons[selectedButton].deselect();
        selectedButton = index;
        buttons[selectedButton].select();
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if (buttons != null && clearButton != null) {
            for (var button : buttons)
                button.setBackground(bg);
            clearButton.setBackground(bg);
        }
    }
}
