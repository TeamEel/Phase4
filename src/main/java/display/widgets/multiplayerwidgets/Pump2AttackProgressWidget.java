/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.widgets.multiplayerwidgets;

import eel.seprphase4.MultiplayerSimulator;

public class Pump2AttackProgressWidget extends AttackProgressWidget {

    public Pump2AttackProgressWidget(MultiplayerSimulator simulator, int x, int y) {
        super(simulator, x, y);
    }

    @Override
    protected double progress() {
        return simulator.pump2AttackProgress();
    }
}
