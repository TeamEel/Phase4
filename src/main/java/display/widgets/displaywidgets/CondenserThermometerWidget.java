package display.widgets.displaywidgets;

import eel.seprphase4.Utilities.Temperature;
import eel.seprphase4.Simulator.Simulator;

public class CondenserThermometerWidget extends ThermometerWidget {

    public CondenserThermometerWidget(Simulator simulator, int x, int y) {
        super(simulator, x, y);
    }

    @Override
    public Temperature temperature() {
        return simulator.condenserTemperature();
    }
}
