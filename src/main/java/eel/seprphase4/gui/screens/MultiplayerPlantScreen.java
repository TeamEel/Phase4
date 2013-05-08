/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.gui.screens;

import eel.seprphase4.gui.ScreenManager;
import eel.seprphase4.gui.widgets.RandomFailureModeWidget;
import eel.seprphase4.gui.widgets.multiplayerwidgets.CondenserAttackProgressWidget;
import eel.seprphase4.gui.widgets.multiplayerwidgets.MultiplayerSoftwareFailureWidget;
import eel.seprphase4.gui.widgets.multiplayerwidgets.Pump1AttackProgressWidget;
import eel.seprphase4.gui.widgets.multiplayerwidgets.Pump2AttackProgressWidget;
import eel.seprphase4.gui.widgets.multiplayerwidgets.TurbineAttackProgressWidget;
import eel.seprphase4.MultiplayerSimulator;

/**
 *
 * @author James
 */
public class MultiplayerPlantScreen extends PlantScreen {

    private MultiplayerSimulator multiplayerSimulator;

    public MultiplayerPlantScreen(MultiplayerSimulator simulator) {
        super(simulator);
        this.multiplayerSimulator = simulator;
        add(new MultiplayerSoftwareFailureWidget(simulator), 0);
        add(new RandomFailureModeWidget(simulator, 95, 130), 5);
        add(new CondenserAttackProgressWidget(simulator, 950, 650), 20);
        add(new TurbineAttackProgressWidget(simulator, 920, 30), 20);
        add(new Pump1AttackProgressWidget(simulator, 650, 650), 20);
        add(new Pump2AttackProgressWidget(simulator, 1250, 420), 20);
        simulator.allowRandomFailures(false);
    }

    public MultiplayerPlantScreen(String player1Name, String player2Name) {
        this(new MultiplayerSimulator(player1Name, player2Name));
    }

    @Override
    protected void gameOver() {
        ScreenManager.getInstance().setScreen(new GameOverScreen(multiplayerSimulator));
    }
    
    @Override
    protected void pause() {
        ScreenManager.getInstance().setScreen(new MultiplayerPauseScreen(this, multiplayerSimulator));
    }
}
