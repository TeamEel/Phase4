/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.gui.widgets.multiplayerwidgets;

import eel.seprphase4.MultiplayerSimulator;

public class CondenserAttackProgressWidget extends AttackProgressWidget {

    public CondenserAttackProgressWidget(MultiplayerSimulator simulator, int x, int y) {
        super(simulator, x, y);
    }

    @Override
    protected double progress() {
        return simulator.condenserAttackProgress();
    }
}
