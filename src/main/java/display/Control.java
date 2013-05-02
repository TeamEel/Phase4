package display;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 * Interface for Controls, which can be added to Screens.
 *
 *
 * @author David
 */
public interface Control {

    public void paint(Graphics g);

    public void advance(int ms);

    /**
     * Mouse departure callback.
     *
     * This method will be called when the containing object knows that the mouse has left the control, without the
     * control necessarily being able to infer this information from mouse movement. The archetypal reason for this to
     * occur is that the mouse has left the window.
     */
    public void onMouseExited();

    /**
     * Mouse movement callback
     *
     * This method will be called when the mouse moves; the driving reason for its inclusion is to allow the control to
     * determine when the mouse enters and leaves the boundaries of the control.
     *
     * @param point the point to which the mouse moved
     */
    public void onMouseMoved(Point point);

    /**
     * Mouse press callback
     *
     * This method will be called when the mouse is pressed. Implementations are responsible for determining whether the
     * given point is contained within the control.
     *
     * If a control considers itself to have fully handled a mouse-press (for example, if that control implements a
     * button), it should return true from this method.
     *
     * @param point the point at which the mouse was pressed
     *
     * @return true if the press was intercepted by this control
     */
    public boolean onMousePressed(Point point);

    /**
     * Mouse release callback
     *
     * This method will be called when the mouse is released. Implementations are responsible for determining whether
     * the given point is contained within the control.
     *
     * If a control considers itself to have fully handled a mouse-release (for example, if that control implements a
     * button), it should return true from this method.
     *
     * @param point the point at which the mouse was released
     *
     * @return true if the release was intercepted by this control
     */
    public boolean onMouseReleased(Point point);

    public boolean onKeyTyped(KeyEvent e);

    public boolean onKeyPressed(KeyEvent e);

    public boolean onKeyReleased(KeyEvent e);
}
