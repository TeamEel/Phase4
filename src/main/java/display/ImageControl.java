package display;

import display.drawable.Drawable;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author David
 */
public class ImageControl implements Control {

    private Drawable image;
    int x, y;

    public ImageControl(Drawable resource, int x, int y) {
        this.image = resource;
        this.x = x;
        this.y = y;
    }
    
    public ImageControl(Asset asset, int x, int y) {
        this(DrawableFactory.create(asset), x, y);
    }

    @Override
    public void paint(Graphics g) {
        image.draw(g, x, y);
    }

    @Override
    public void onMouseExited() {
        // do nothing
    }

    @Override
    public void onMouseMoved(Point point) {
        // do nothing
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
        image.advance(ms);
    }
}
