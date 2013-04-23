package cichlid.seprphase3.GUIInterface;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This class is the base type for all interfaces in the game. It provides convenience methods for these classes.
 */
public class BaseScreen extends JPanel {

    // Convenience method to check if a particular mouse event is inside a Rectangle.
    public boolean clicked(Rectangle rectangle, MouseEvent click) {
        return rectangle.contains(click.getPoint());
    }

    // Convenience method to wrap SwingUtilities to determine if a mouseEvent was a left click.
    public boolean leftClick(MouseEvent click) {
        return SwingUtilities.isLeftMouseButton(click);

    }
    // Convenience method to wrap SwingUtilities to determine if a mouseEvent was a right click.

    public boolean rightClick(MouseEvent click) {
        return SwingUtilities.isRightMouseButton(click);
    }
}
