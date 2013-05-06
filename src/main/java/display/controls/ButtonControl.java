package display.controls;

import display.Asset;
import display.Control;
import display.DrawableFactory;
import display.HitBox;
import display.drawable.Drawable;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author David
 */
public class ButtonControl implements Control {

    private enum State {

        Default,
        MouseOver,
        Pressed
    }
    private final Drawable defaultImage;
    private final Drawable mouseOverImage;
    private final Drawable pressedImage;
    private final HitBox hitBox;
    private State state;
    private int x, y;
    private ArrayList<ActionListener> actionListeners;
    private boolean pressed;

    public ButtonControl(Drawable defaultImage,
                         Drawable mouseOverImage,
                         Drawable pressedImage,
                         int x, int y) {

        this.defaultImage = defaultImage;
        this.mouseOverImage = mouseOverImage;
        this.pressedImage = pressedImage;
        this.hitBox = this.defaultImage.hitBox(x, y);
        this.state = State.Default;
        this.x = x;
        this.y = y;
        this.actionListeners = new ArrayList<ActionListener>();
        this.pressed = false;
    }

    public ButtonControl(Asset defaultAsset,
                         Asset mouseOverAsset,
                         Asset pressedAsset,
                         int x, int y) {
        this(DrawableFactory.create(defaultAsset),
             DrawableFactory.create(mouseOverAsset),
             DrawableFactory.create(pressedAsset),
             x, y);
    }

    public void addActionListener(ActionListener al) {
        actionListeners.add(al);
    }

    @Override
    public void paint(Graphics g) {
        Drawable imageToDraw;
        switch (state) {
            case MouseOver:
                imageToDraw = mouseOverImage;
                break;
            case Pressed:
                imageToDraw = pressedImage;
                break;
            case Default:
            default:
                imageToDraw = defaultImage;
                break;
        }
        imageToDraw.draw(g, x, y);
    }

    @Override
    public void onMouseExited() {
        state = State.Default;
    }

    @Override
    public void onMouseMoved(Point point) {
        if (hitBox.contains(point)) {
            state = pressed ? State.Pressed : State.MouseOver;
        } else {
            state = State.Default;
        }
    }

    @Override
    public boolean onMousePressed(Point point) {
        pressed = true;
        if (hitBox.contains(point)) {
            state = State.Pressed;
            return true;
        }
        state = State.Default;
        return false;
    }

    @Override
    public boolean onMouseReleased(Point point) {
        pressed = false;
        if (hitBox.contains(point)) {
            ActionEvent ae = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "");
            for (ActionListener al : actionListeners) {
                al.actionPerformed(ae);
            }
            state = State.MouseOver;
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyPressed(KeyEvent e) {
        return false;
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        return false;
    }

    @Override
    public boolean onKeyTyped(KeyEvent e) {
        return false;
    }

    @Override
    public void advance(int ms) {
        defaultImage.advance(ms);
        mouseOverImage.advance(ms);
        pressedImage.advance(ms);
    }
}
