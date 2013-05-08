package eel.seprphase4.gui.widgets;

import eel.seprphase4.gui.Control;
import eel.seprphase4.Simulator.Simulator;

/**
 *
 * @author David
 */
public abstract class Widget implements Control {
    protected final Simulator simulator;
    protected final int x, y;
    public Widget(Simulator simulator, int x, int y) {
        this.simulator = simulator;
        this.x = x;
        this.y = y;
    }
}
