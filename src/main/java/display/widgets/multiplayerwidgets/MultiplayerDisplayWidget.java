/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.widgets.multiplayerwidgets;

import eel.seprphase4.MultiplayerSimulator;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author drm511
 */
public abstract class MultiplayerDisplayWidget extends MultiplayerWidget {

    public MultiplayerDisplayWidget(MultiplayerSimulator simulator, int x, int y) {
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
