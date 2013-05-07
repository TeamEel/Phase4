package display.controls;

import display.Control;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class HotKeyControl implements Control {

    private final int keyCode;
    private ArrayList<ActionListener> actionListeners;

    public HotKeyControl(int keyCode) {
        this.keyCode = keyCode;
        this.actionListeners = new ArrayList<ActionListener>();
    }

    @Override
    public void paint(Graphics g) {
        // does not paint
    }

    @Override
    public void advance(int ms) {
        // does not animate
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
        return e.getKeyCode() == keyCode;
    }

    @Override
    public boolean onKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == keyCode) {
            ActionEvent ae = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null);
            for (ActionListener al : actionListeners) {
                al.actionPerformed(ae);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        return e.getKeyCode() == keyCode;
    }
    
    public void addActionListener(ActionListener al) {
        actionListeners.add(al);
    }
}
