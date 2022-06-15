package jdraw.ui.components.toolbar;

import jdraw.ui.IconLoader;
import jdraw.ui.components.VerticalBar;

import java.awt.*;

public class ToolBar extends VerticalBar {
    private final ToolbarButton[] buttons;
    private int selectedButton;

    public ToolBar(IconLoader loader) {
        super(50);
        buttons = new ToolbarButton[]{
                new ToolbarButton(loader.getPlaceholderImage(), getBackground()),
                new ToolbarButton(loader.getPlaceholderImage(), getBackground()),
                new ToolbarButton(loader.getPlaceholderImage(), getBackground()),
                new ToolbarButton(loader.getPlaceholderImage(), getBackground()),
        };

        for (int i = 0; i < buttons.length; i++) {
            add(buttons[i]);
            int finalI = i;
            buttons[i].addActionListener(e -> selectButton(finalI));
            // TODO: select tool/brush
        }

        selectedButton = 0;
        selectButton(selectedButton);
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
