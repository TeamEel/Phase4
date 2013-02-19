package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.GameOverException;
import cichlid.seprphase3.QuitGameException;
import cichlid.seprphase3.Simulator.Simulator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

enum GameState {

    NotStarted, Running, GameOver
}

public class GUIWindow extends JFrame implements ActionListener {

    JPanel currentWindow;
    Simulator simulator;
    GameOverInterface gameover;
    GameState state;

    public GUIWindow(String title, int width, int height) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);

        // If the location is relative to nothing, the window always starts in the middle of the screen.
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void run() throws InterruptedException {
        startGame();

        Timer timer = new Timer(60, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (state) {
            case Running:
                if (simulator.step()) {
                    update();
                } else {
                    state = GameState.GameOver;
                    gameover = new GameOverInterface();
                    setWindow(gameover);
                }
                break;
            case GameOver:
                revalidate();
                repaint();
                //gameover.paint(this.getGraphics());
                if(!gameover.block) {  
                    startGame();
                }
                break;
            default:
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

    public JPanel getWindow() {
        return currentWindow;
    }

    /**
     * Update the window. Revalidate causes the jPanel to revalidate, while redraw redraws the jPanel's content to the
     * screen.
     */
    public void update() {
        revalidate();
        repaint();
    }

    private void startGame() {
        simulator = new Simulator();
        setWindow(new PlantInterface(simulator, simulator, simulator));
        state = GameState.Running;
    }
}
