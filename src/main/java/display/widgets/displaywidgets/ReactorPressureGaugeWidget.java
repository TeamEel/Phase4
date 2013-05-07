package display.widgets.displaywidgets;

import eel.seprphase4.Simulator.Simulator;
import eel.seprphase4.Utilities.Pressure;

/**
 *
 * @author David
 */
public class ReactorPressureGaugeWidget extends GaugeWidget {

    public ReactorPressureGaugeWidget(Simulator simulator, int x, int y) {
        super(simulator, x, y);
    }
    
    @Override
    protected Pressure pressure() {
        return simulator.reactorPressure();
    }
    
}
