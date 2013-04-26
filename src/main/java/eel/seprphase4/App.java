package eel.seprphase4;

import eel.seprphase4.GUIInterface.GUIWindow;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {

    /**
     * The main entry point for the application
     *
     * @param args
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // Create a new GUIWindow - this is a JFrame which is the window.
        GUIWindow gameWindow = new GUIWindow("Nuke Dukem", 1377, 768);

        // Run the game. This shows the menu screen and sets up a timer to update everything.
        gameWindow.run();
    }
}
