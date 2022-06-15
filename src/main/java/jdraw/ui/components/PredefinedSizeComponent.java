package jdraw.ui.components;

import javax.swing.*;
import java.awt.*;

public abstract class PredefinedSizeComponent extends JPanel {
    private final Dimension size;

    @Override
    public final Dimension getPreferredSize() {
        return size;
    }

    protected PredefinedSizeComponent(Dimension size) {
        super();
        this.size = size;
    }
}
