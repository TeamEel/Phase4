package eel.seprphase4.gui.widgets.displaywidgets;

import eel.seprphase4.Utilities.Temperature;
import eel.seprphase4.Simulator.Simulator;

public class ReactorThermometerWidget extends ThermometerWidget {

    public ReactorThermometerWidget(Simulator simulator, int x, int y) {
        super(simulator, x, y);
    }

    @Override
    public Temperature temperature() {
        return simulator.reactorTemperature();
    }
}
