/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.widgets.multiplayerwidgets;

import display.Control;
import eel.seprphase4.MultiplayerSimulator;

/**
 *
 * @author drm511
 */
public abstract class MultiplayerWidget implements Control {

    protected final MultiplayerSimulator simulator;
    protected final int x, y;

    public MultiplayerWidget(MultiplayerSimulator simulator, int x, int y) {
        this.simulator = simulator;
        this.x = x;
        this.y = y;
    }
}
