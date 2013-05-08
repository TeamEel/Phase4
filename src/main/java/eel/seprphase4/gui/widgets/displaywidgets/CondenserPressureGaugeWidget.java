package eel.seprphase4.gui.widgets.displaywidgets;

import eel.seprphase4.Simulator.Simulator;
import eel.seprphase4.Utilities.Pressure;

/**
 *
 * @author David
 */
public class CondenserPressureGaugeWidget extends GaugeWidget {

    public CondenserPressureGaugeWidget(Simulator simulator, int x, int y) {
        super(simulator, x, y);
    }
    
    @Override
    protected Pressure pressure() {
        return simulator.condenserPressure();
    }
    
}
