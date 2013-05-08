/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.gui.widgets.multiplayerwidgets;

import eel.seprphase4.MultiplayerSimulator;

public class TurbineAttackProgressWidget extends AttackProgressWidget {

    public TurbineAttackProgressWidget(MultiplayerSimulator simulator, int x, int y) {
        super(simulator, x, y);
    }

    @Override
    protected double progress() {
        return simulator.turbineAttackProgress();
    }
}
