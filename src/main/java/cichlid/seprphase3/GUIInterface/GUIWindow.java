package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.Simulator.Simulator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class GUIWindow extends JFrame implements ActionListener {
    MultiPlayerKeyListener kl;
    // This is the current window which is being displayed.
    JPanel currentWindow;
    // This is the simulator which the plant uses.
    Simulator simulator;
    // This is the state which the game is currently in.
    GameState state = GameState.NotStarted;
    // This is the GameOverInterface which is displayed when the game is over.
    GameOverInterface gameover;
    // The end game explosion animation must be preloaded due to its size, so it is stored here.
    Animation explosion;

    /**
     * This creates a GUIWindow with a number of parameters.
     *
     * @param title  The title to give the window.
     * @param width  The width of the window.
     * @param height The height of the window.
     */
    public GUIWindow(String title, int width, int height) {
        super(title);

        // Allow the window to exit when the close button is pressed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the resolution of the window.
        setSize(width, height);

        // If the location is relative to nothing, the window always starts in the middle of the screen.
        setLocationRelativeTo(null);
        // Do not allow the user to resize the window.
        setResizable(false);
        setVisible(true);

        // Preload the Explosion Animation due to its size and store it here to be passed into
        // GameOverInterface.
        explosion = new Animation("animations/explosion", false);
    }

    /**
     * This is called to run the game when it first starts.
     */
    public void run() {
        // Show the menu screen.
        showMenu();

        
        // Setup the timer which is used to update the plant.
        // This makes actionPerformed() be called every 60ms.
        Timer timer = new Timer(60, this);
        timer.start();
    }

    /**
     * Called every 60ms by the Swing Timer.
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // Decide which action to take based on what state the interface is currently in.
        switch (state) {
            // If the game is running, step the simulator and update.
            case Running:
                // Simulator.step returns false if the game is over, in which case the else clause is ran:
                if (simulator.step()) {
                    update();
                } else {
                    // Set the game state to GameOver (so the plant stops updating).
                    // And set the interface to the GameOverInterface.
                    // Some values to display are passed in from the simulator.
                    state = GameState.GameOver;
                    gameover = new GameOverInterface(explosion, simulator.energyGenerated(), simulator.getUsername());
                    setWindow(gameover);
                }
                break;
            // If the game is over, just continue repainting to show the GameOverInterface.
            case GameOver:
                update();
                // gameover.block returns false when the button is pressed to play another game. At this point,
                // the code to return to the menu interface is ran.
                if (!gameover.block) {
                    state = GameState.NotStarted;
                    showMenu();
                }
                break;
            // If the game is in any other screen, just continue as normal.
            default:
                update();
                break;
        }
    }

    /**
     * Sets the active interface on the jFrame (all interfaces are jPanels).
     *
     * @param _window The interface to display.
     */
    public void setWindow(JPanel _window) {
        /**
         * The content pane represents the area of the window where content is shown - all of the window except the
         * title bar. Setting the content panel to a jPanel causes the jPanel to draw to the window.
         */
        currentWindow = _window;
        setContentPane(_window);
    }

    /**
     * Update the window. Revalidate causes the jPanel to revalidate, while redraw redraws the jPanel's content to the
     * screen.
     */
    public void update() {
        currentWindow.revalidate();
        repaint();
    }

    /**
     * Run game starts a new game. It is called by Load, and takes the loaded simulator, creating the new game with it.
     *
     * @param sim
     */
    public void runGame(Simulator sim) {
        simulator = sim;
        this.kl.update(sim,sim);
        setWindow(new PlantInterface(this, simulator, simulator, simulator));
        state = GameState.Running;
    }

    public void showMenu() {
        simulator = new Simulator();
        this.kl = new MultiPlayerKeyListener(this,simulator,simulator);
        this.addKeyListener(kl);
        setWindow(new MenuInterface(this, simulator));
    }
}
