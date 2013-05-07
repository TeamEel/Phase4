package display.widgets.displaywidgets;

import display.Asset;
import eel.seprphase4.Utilities.Percentage;

import eel.seprphase4.Simulator.Simulator;

public class CondenserWaterLevelWidget extends WaterLevelWidget {

    public CondenserWaterLevelWidget(Simulator simulator, int x, int y) {
        super(simulator, x, y, Asset.PlantCondenserWaterLevelAnimation);
    }

    @Override
    protected Percentage waterLevel() {
        return simulator.condenserWaterLevel();
    }
}
