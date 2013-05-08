package eel.seprphase4.gui.widgets.displaywidgets;

import eel.seprphase4.gui.Asset;
import eel.seprphase4.gui.DrawableFactory;
import eel.seprphase4.gui.drawable.RangeDrawable;
import eel.seprphase4.Simulator.Simulator;
import eel.seprphase4.Utilities.Percentage;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public abstract class WaterLevelWidget extends DisplayWidget {
    
    private final RangeDrawable water;
    
    public WaterLevelWidget(Simulator simulator, int x, int y, Asset a) {
        super(simulator, x, y);
        this.water = DrawableFactory.createRange(a, 0, 100);
    }
    
    @Override
    public void paint(Graphics g) {
        water.setLevel(waterLevel().points());
        water.draw(g, x, y);
    }

    @Override
    public void advance(int ms) {
        // ignore
    }

    protected abstract Percentage waterLevel();  
}
