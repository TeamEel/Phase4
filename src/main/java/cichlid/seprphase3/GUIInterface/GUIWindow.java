package cichlid.seprphase3.GUIInterface;

import javax.swing.*;

public class GUIWindow extends JFrame {
    
    JPanel currentWindow;

    public GUIWindow(String title, int width, int height) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        
        // If the location is relative to nothing, the window always starts in the middle of the screen.
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     *  Sets the active interface on the jFrame (all interfaces are jPanels).
     * 
     *  @param _window  The interface to display.
     */
    public void setWindow(JPanel _window) {
        /**
         * The content pane represents the area of the window where content is shown - all of the
         * window except the title bar. Setting the content panel to a jPanel causes the jPanel to
         * draw to the window.
         */
        currentWindow = _window;
        setContentPane(_window);
    }
    
    public JPanel getWindow() {
        return currentWindow;
    }

    /**
     * Update the window. Revalidate causes the jPanel to revalidate, while redraw redraws the jPanel's content
     * to the screen.
     */
    public void update() {
        revalidate();
        repaint();
    }
}
