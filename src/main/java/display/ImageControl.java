package display;

import java.awt.Graphics;
import java.awt.Image;
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
