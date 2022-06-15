package jdraw.ui.components;

import java.awt.*;

public abstract class VerticalBar extends PredefinedSizeComponent {
    protected VerticalBar(int width) {
        super(new Dimension(width, -1));
    }
}
