package jdraw.ui.components;

import java.awt.*;

public abstract class HorizontalBar extends PredefinedSizeComponent {
    protected HorizontalBar(int height) {
        super(new Dimension(-1, height));
    }
}
