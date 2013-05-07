/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.widgets.MultiplayerSoftwareFailureWidget;
import display.widgets.RandomFailureModeWidget;
import eel.seprphase4.Simulator.Simulator;

/**
 *
 * @author James
 */
public class MultiplayerPlantScreen extends PlantScreen {

    public MultiplayerPlantScreen(Simulator simulator) {
        super(simulator);
        add(new MultiplayerSoftwareFailureWidget(simulator), 0);
        add(new RandomFailureModeWidget(simulator, 30, 50), 5);

        simulator.allowRandomFailures(false);
    }

    public MultiplayerPlantScreen(String userName) {
        this(new Simulator(userName));
    }
}
