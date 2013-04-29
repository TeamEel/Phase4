package eel.seprphase4.GUIInterface;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This class is the base type for all interfaces in the game. It provides convenience methods for these classes.
 */
public abstract class GameScreen extends JPanel {
    

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
    
    public void actionPerformed(ActionEvent event)
    {
        update();
    }
    
    public void update()
    {
        revalidate();
        repaint();
    }
//    
//     @Override
//    public void mousePressed(MouseEvent me) {
//        
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent me) {
//        
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent me) {
//        
//    }
//
//    @Override
//    public void mouseExited(MouseEvent me) {
//        
//    }

}
