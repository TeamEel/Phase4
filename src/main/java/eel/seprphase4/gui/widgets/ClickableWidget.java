package eel.seprphase4.gui.widgets;

import eel.seprphase4.gui.HitBox;
import eel.seprphase4.Simulator.Simulator;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author David
 */
public abstract class ClickableWidget extends Widget {

    protected final HitBox hitBox;
    private boolean mouseOver;

    public ClickableWidget(Simulator simulator, int x, int y, HitBox hitBox) {
        super(simulator, x, y);
        this.hitBox = hitBox;
        this.mouseOver = false;
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

    @Override
    public void onMouseExited() {
        mouseOver = false;
    }

    @Override
    public void onMouseMoved(Point point) {
        mouseOver = hitBox.contains(point);
    }

    @Override
    public boolean onMousePressed(Point point) {
        // ignore
        return hitBox.contains(point);
    }
    
    public boolean onMouseReleased(Point point) {
        if (hitBox.contains(point)) {
            onClicked();
            return true;
        }
        return false;
    }
    
    protected boolean mouseIsOver() {
        return mouseOver;
    }
    
    protected abstract void onClicked();
}
