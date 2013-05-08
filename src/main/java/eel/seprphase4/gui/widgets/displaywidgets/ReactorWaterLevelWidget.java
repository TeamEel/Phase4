package eel.seprphase4.gui.widgets.displaywidgets;

import eel.seprphase4.gui.Asset;
import eel.seprphase4.Utilities.Percentage;
import eel.seprphase4.Simulator.Simulator;

public class ReactorWaterLevelWidget extends WaterLevelWidget {

    public ReactorWaterLevelWidget(Simulator simulator, int x, int y) {
        super(simulator, x, y, Asset.PlantReactorWaterLevelAnimation);
    }

    @Override
    protected Percentage waterLevel() {
        return simulator.reactorWaterLevel();
    }
}
