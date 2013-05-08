package eel.seprphase4;

import eel.seprphase4.gui.ScreenManager;
import eel.seprphase4.gui.screens.MainMenuScreen;
import eel.seprphase4.gui.drawable.DrawableImage;
import eel.seprphase4.gui.drawable.DrawableText;
import eel.seprphase4.gui.screens.SingleplayerStartScreen;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;

/**
 *
 * @author David
 */
public class App extends JFrame {

    ScreenManager sm;

    public App() {
        super("Atom Lab");

        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1366, 768);
        // If the location is relative to nothing, the window always starts in the middle of the screen.
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        sm = ScreenManager.getInstance();
        add(sm);

        sm.setScreen(new MainMenuScreen());
        sm.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        App window = new App();
    }
}
