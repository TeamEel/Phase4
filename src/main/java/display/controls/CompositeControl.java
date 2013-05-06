package display.controls;

import display.Control;
import display.zlist.ZList;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Iterator;

/**
 *
 * @author David
 */
public class CompositeControl implements Control {

    private ZList<Control> controls;

    public CompositeControl() {
        this.controls = new ZList<Control>();
    }

    public void add(Control c, int z) {
        controls.add(c, z);
    }

    public void remove(Control c) {
        Iterator<Control> i = controls.iterator();
        while (i.hasNext()) {
            if (i.next() == c) {
                i.remove();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        for (Control c : controls) {
            c.paint(g);
        }
    }

    @Override
    public void advance(int ms) {
        for (Control c : controls) {
            c.advance(ms);
        }
    }

    @Override
    public void onMouseExited() {
        for (Control c : controls.backwards()) {
            c.onMouseExited();
        }
    }

    @Override
    public void onMouseMoved(Point point) {
        for (Control c : controls.backwards()) {
            c.onMouseMoved(point);
        }
    }

    @Override
    public boolean onMousePressed(Point point) {
        for (Control c : controls.backwards()) {
            if (c.onMousePressed(point)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onMouseReleased(Point point) {
        for (Control c : controls.backwards()) {
            if (c.onMouseReleased(point)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onKeyPressed(KeyEvent e) {
        for (Control c : controls.backwards()) {
            if (c.onKeyPressed(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        for (Control c : controls.backwards()) {
            if (c.onKeyReleased(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onKeyTyped(KeyEvent e) {
        for (Control c : controls.backwards()) {
            if (c.onKeyTyped(e)) {
                return true;
            }
        }
        return false;
    }
}
