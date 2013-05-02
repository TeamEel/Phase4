package display;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author David
 */
public interface Screen {

    public void paint(Graphics g);
    
    public void advance(int ms);

    public void onMouseExited();

    public void onMouseMoved(Point point);

    public void onMousePressed(Point point);

    public void onMouseReleased(Point point);

    public void onKeyPressed(KeyEvent e);

    public void onKeyReleased(KeyEvent e);
}
