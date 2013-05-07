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
 * @author James
 */
public class MultiplayerSoftwareFailureWidget extends MultiplayerWidget {

    public MultiplayerSoftwareFailureWidget(MultiplayerSimulator simulator) {
        super(simulator, 0, 0);
    }

    @Override
    public void paint(Graphics g) {
        // ignore
    }

    @Override
    public void advance(int ms) {
        // ignore
    }

    @Override
    public void onMouseExited() {
    }

    @Override
    public void onMouseMoved(Point point) {
    }

    @Override
    public boolean onMousePressed(Point point) {
        return false;
    }

    @Override
    public boolean onMouseReleased(Point point) {
        return false;
    }

    @Override
    public boolean onKeyTyped(KeyEvent e) {
        return false;
    }

    @Override
    public boolean onKeyPressed(KeyEvent e) {
        return false;
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case '1':
                simulator.attackPump1();
                return true;
            case '2':
                simulator.attackPump2();
                return true;
            case 'c':
                simulator.attackCondenser();
                return true;
            case 't':
                simulator.attackTurbine();
                return true;
        }
        return false;
    }
}
