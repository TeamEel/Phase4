package eel.seprphase4.GUIInterface;

import eel.seprphase4.Simulator.Simulator;
import eel.seprphase4.drawing.SpriteCanvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

/**
 * This GameState defines the current state of the game.
 */
enum GameState {

    NotStarted, Running, GameOver
}

/**
 * The GUIWindow class extends JFrame and provides methods for dealing with the current interface. It implements
 * ActionListener so it can work with Swing Timers for scheduling updating at a specified interval.
 */
public class GUIWindow extends JFrame implements ActionListener, ScreenContext {

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

        setFocusable( true );
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

    
    @Override
    public void transitionTo(GameScreen newScreen)
    {
        this.currentScreen = newScreen;
        setContentPane(newScreen);


    }
    
    
    /**
     * This is called to run the game when it first starts.
     */
    public void run() {
        
       //SET MENUPANE?
        

        
        // Setup the timer which is used to update the plant.
        // This makes actionPerformed() be called every 60ms.
        Timer timer = new Timer(120, this);
       // timer.start();
    }

    /**
     * Called every 60ms by the Swing Timer.
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
 
        currentScreen.actionPerformed(event);
    }

    /**
     * Sets the active interface on the jFrame (all interfaces are jPanels).
     *
     * @param _window The interface to display.
     */
    @Deprecated
    public void setWindow(JPanel _window) {
        /**
         * The content pane represents the area of the window where content is shown - all of the window except the
         * title bar. Setting the content panel to a jPanel causes the jPanel to draw to the window.
         */
        //currentWindow = _window;
        setContentPane(_window);
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
