package jdraw.brushes.pack;

import jdraw.brushes.*;
import jdraw.ui.IconLoader;

import java.util.ArrayList;
import java.util.List;

public class BrushPack {
    private final List<BrushInfo> brushInfos;

    public BrushPack(IconLoader loader) {
        brushInfos = new ArrayList<>();
        brushInfos.add(new BrushInfo(new BasicBrush(), loader.getImage("pen.png")));
        brushInfos.add(new BrushInfo(new EraserBrush(), loader.getImage("eraser.png")));
        brushInfos.add(new BrushInfo(new LineBrush(), loader.getImage("line.png")));
        brushInfos.add(new BrushInfo(new RectBrush(), loader.getImage("rect.png")));
        brushInfos.add(new BrushInfo(new EllipseBrush(), loader.getImage("ellipse.png")));
        brushInfos.add(new BrushInfo(new StarBrush(), loader.getImage("star.png")));
        brushInfos.add(new BrushInfo(new FillBrush(), loader.getImage("fill.png")));
        brushInfos.add(new BrushInfo(new RadialBrush(), loader.getImage("rad.png")));
        brushInfos.add(new BrushInfo(new XORBrush(), loader.getImage("rect.png")));
    }

    public List<BrushInfo> getBrushInfos() {
        return brushInfos;
    }
}
