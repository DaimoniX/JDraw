package jdraw.brushes.pack;

import java.util.ArrayList;
import java.util.List;

public class BrushPack {
    private final List<BrushInfo> brushInfos;

    public BrushPack() {
        brushInfos = new ArrayList<>();
    }

    public void add(BrushInfo brushInfo) {
        brushInfos.add(brushInfo);
    }

    public List<BrushInfo> getBrushInfos() {
        return brushInfos;
    }
}
