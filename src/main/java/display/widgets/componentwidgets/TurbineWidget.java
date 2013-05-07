package display.widgets.componentwidgets;

import display.Asset;
import display.DrawableFactory;
import display.drawable.Drawable;
import display.widgets.ClickableWidget;
import eel.seprphase4.Simulator.Simulator;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class TurbineWidget extends ClickableWidget {

    private final Drawable runningTurbine;
    private final Drawable failedTurbine;
    
    public TurbineWidget(Simulator simulator, int x, int y) {
        super(simulator, x, y, DrawableFactory.create(Asset.Turbine).hitBox(x, y));
        this.runningTurbine = DrawableFactory.create(Asset.Turbine);
        this.failedTurbine = DrawableFactory.create(Asset.TurbineFailed);
    }
    
    @Override
    public void paint(Graphics g) {
        if (simulator.turbineHasFailed()) {
            failedTurbine.draw(g, x, y);
        } else {
            runningTurbine.draw(g, x, y);
        }
    }

    @Override
    public void advance(int ms) {
        if (simulator.valveState(1) && simulator.valveState(2)) {
            runningTurbine.advance(ms);
        }
    }

    @Override
    protected void onClicked() {
        if (simulator.turbineHasFailed()) {
            simulator.repairTurbine();
        }
    }
}
