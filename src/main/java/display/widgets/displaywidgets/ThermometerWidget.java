package display.widgets.displaywidgets;

import display.Asset;
import display.DrawableFactory;
import display.drawable.Drawable;
import display.drawable.DrawableText;
import display.drawable.RangeDrawable;
import eel.seprphase4.Simulator.Simulator;
import eel.seprphase4.Utilities.Temperature;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public abstract class ThermometerWidget extends DisplayWidget {

    private final RangeDrawable thermometer;
    private final DrawableText text;

    public ThermometerWidget(Simulator simulator, int x, int y) {
        super(simulator, x, y);
        this.thermometer = DrawableFactory.createRange(Asset.Thermometer, 80, 140);
        this.text = new DrawableText("", new Font("Courier New", Font.BOLD, 12), Color.BLACK);
    }

    @Override
    public void paint(Graphics g) {
        thermometer.setLevel(temperature().inCelsius());
        thermometer.draw(g, x, y);
        // todo: format with degrees sign
        text.setText(temperature().toString());
        text.draw(g, x+5, y+175);
    }

    @Override
    public void advance(int ms) {
        // ignore
    }
    
    public abstract Temperature temperature();
}
