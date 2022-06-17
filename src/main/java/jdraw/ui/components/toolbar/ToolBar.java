package jdraw.ui.components.toolbar;

import jdraw.brushes.pack.BrushInfo;
import jdraw.brushes.pack.BrushPack;
import jdraw.ui.components.VerticalBar;
import jdraw.ui.components.workingarea.WorkingArea;

import java.awt.*;

public class ToolBar extends VerticalBar {
    private final ToolbarButton[] buttons;
    private int selectedButton;

    public ToolBar(BrushPack brushPack, WorkingArea wArea) {
        super(60);
        buttons = new ToolbarButton[brushPack.getBrushInfos().size()];

        int index = 0;
        for (BrushInfo brush: brushPack.getBrushInfos()) {
            buttons[index] = new ToolbarButton(brush.getIcon(), getBackground());
            int finalIndex = index;
            buttons[index].addActionListener(e -> selectButton(finalIndex));
            buttons[index].addActionListener(e -> wArea.getPaintArea().setBrush(brush.getBrush()));
            add(buttons[index++]);
        }

        selectedButton = 0;
        buttons[0].doClick();
    }

    private void selectButton(int index) {
        buttons[selectedButton].deselect();
        selectedButton = index;
        buttons[selectedButton].select();
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if (buttons != null)
            for (var button : buttons)
                button.setBackground(bg);
    }
}
