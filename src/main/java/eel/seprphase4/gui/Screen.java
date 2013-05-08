package eel.seprphase4.gui;

import eel.seprphase4.gui.controls.CompositeControl;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author David
 */
public class Screen implements Control {

    protected CompositeControl controls;

    public Screen() {
        this.controls = new CompositeControl();
    }

    public void add(Control c, int z) {
        controls.add(c, z);
    }

    public void remove(Control c) {
        controls.remove(c);
    }

    @Override
    public void paint(Graphics g) {
        controls.paint(g);
    }

    @Override
    public void advance(int ms) {
        controls.advance(ms);
    }

    @Override
    public void onMouseExited() {
        controls.onMouseExited();
    }

    @Override
    public void onMouseMoved(Point point) {
        controls.onMouseMoved(point);
    }

    @Override
    public boolean onMousePressed(Point point) {
        return controls.onMousePressed(point);
    }

    @Override
    public boolean onMouseReleased(Point point) {
        return controls.onMouseReleased(point);
    }

    @Override
    public boolean onKeyPressed(KeyEvent e) {
        return controls.onKeyPressed(e);
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        return controls.onKeyReleased(e);
    }

    @Override
    public boolean onKeyTyped(KeyEvent e) {
        return controls.onKeyTyped(e);
    }
}
