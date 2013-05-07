package display.widgets.displaywidgets;

import display.Asset;
import display.DrawableFactory;
import display.drawable.DrawableText;
import display.drawable.RangeDrawable;
import eel.seprphase4.Simulator.Simulator;
import eel.seprphase4.Utilities.Pressure;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public abstract class GaugeWidget extends DisplayWidget {

    private final RangeDrawable gauge;
    private final DrawableText text;
    
    public GaugeWidget(Simulator simulator, int x, int y) {
        super(simulator, x, y);
        this.gauge = DrawableFactory.createRange(Asset.Gauge, 0, 150);
        this.text = new DrawableText("", new Font("Courier New", Font.BOLD, 12), Color.BLACK);
    }

    @Override
    public void paint(Graphics g) {
        gauge.setLevel(pressure().inAtmospheres());
        gauge.draw(g, x, y);
        text.setText(pressure().toString());
        text.draw(g, x, y + 70);
    }

    @Override
    public void advance(int ms) {
        // do nothing
    }

    protected abstract Pressure pressure();
}
