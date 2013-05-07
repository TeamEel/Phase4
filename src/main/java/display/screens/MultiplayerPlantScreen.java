/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.widgets.multiplayerwidgets.MultiplayerSoftwareFailureWidget;
import display.widgets.RandomFailureModeWidget;
import display.widgets.multiplayerwidgets.CondenserAttackProgressWidget;
import display.widgets.multiplayerwidgets.Pump1AttackProgressWidget;
import display.widgets.multiplayerwidgets.Pump2AttackProgressWidget;
import display.widgets.multiplayerwidgets.TurbineAttackProgressWidget;
import eel.seprphase4.MultiplayerSimulator;
import eel.seprphase4.Simulator.Simulator;

/**
 *
 * @author James
 */
public class MultiplayerPlantScreen extends PlantScreen {

    public MultiplayerPlantScreen(MultiplayerSimulator simulator) {
        super(simulator);
        add(new MultiplayerSoftwareFailureWidget(simulator), 0);
        add(new RandomFailureModeWidget(simulator, 100, 50), 5);
        add(new CondenserAttackProgressWidget(simulator, 950, 650), 20);
        add(new TurbineAttackProgressWidget(simulator, 920, 30), 20);
        add(new Pump1AttackProgressWidget(simulator, 650, 650), 20);
        add(new Pump2AttackProgressWidget(simulator, 1250, 420), 20);
        simulator.allowRandomFailures(false);
    }

    public MultiplayerPlantScreen(String player1Name, String player2Name) {
        this(new MultiplayerSimulator(player1Name, player2Name));
    }
}
