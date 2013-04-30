package eel.seprphase4.GUIInterface;

import eel.seprphase4.Simulator.Simulator;
import eel.seprphase4.drawing.SpriteCanvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

/**
 * The GUIWindow class extends JFrame and provides methods for dealing with the current interface. It implements
 * ActionListener so it can work with Swing Timers for scheduling updating at a specified interval.
 */
public class GUIWindow extends JFrame {

    GameScreen currentScreen;
    private SpriteCanvas canvas;
    // This is the simulator which the plant uses.

    /**
     * This creates a GUIWindow with a number of parameters.
     *
     * @param title  The title to give the window.
     * @param width  The width of the window.
     * @param height The height of the window.
     */
    public GUIWindow(String title, int width, int height) throws IOException {

        super(title);

        setFocusable(true);
        canvas = new SpriteCanvas();
        canvas.setScreen(new MenuScreen());
        add(canvas);
        canvas.start();
        //transitionTo(new MenuScreen(this));



        // Allow the window to exit when the close button is pressed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the resolution of the window.
        setSize(width, height);

        // If the location is relative to nothing, the window always starts in the middle of the screen.
        setLocationRelativeTo(null);
        // Do not allow the user to resize the window.
        setResizable(false);
        setVisible(true);
    }

    /**
     * Update the window. Revalidate causes the jPanel to revalidate, while redraw redraws the jPanel's content to the
     * screen.
     */
    public void update() {
        currentScreen.revalidate();
        repaint();
    }
}
