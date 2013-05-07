package display.widgets.displaywidgets;

import display.widgets.Widget;
import eel.seprphase4.Simulator.Simulator;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author David
 */
public abstract class DisplayWidget extends Widget {

    public DisplayWidget(Simulator simulator, int x, int y) {
        super(simulator, x, y);
    }
    
    @Override
    public void onMouseExited() {
        // ignore
    }

    @Override
    public void onMouseMoved(Point point) {
        // ignore
    }

    @Override
    public boolean onMousePressed(Point point) {
        // ignore
        return false;
    }

    @Override
    public boolean onMouseReleased(Point point) {
        // ignore
        return false;
    }

    @Override
    public boolean onKeyTyped(KeyEvent e) {
        // ignore
        return false;
    }

    @Override
    public boolean onKeyPressed(KeyEvent e) {
        // ignore
        return false;
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        // ignore
        return false;
    }
}
