/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import eel.seprphase4.Simulator.Simulator;

/**
 *
 * @author James
 */
public class MultiplayerPlantScreen extends PlantScreen {

    public MultiplayerPlantScreen(Simulator simulator) {
        super(simulator);

        add(new display.controls.MultiplayerSoftwareFailureControl(simulator, simulator), 0);
        add(new display.controls.RandomFailureModeControl(simulator, simulator, simulator, 30, 50), 5);

        simulator.allowRandomFailures(false);
    }

    public MultiplayerPlantScreen(String userName) {
        super(new Simulator(userName));
    }
}
