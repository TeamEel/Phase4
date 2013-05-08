package eel.seprphase4.gui;

import eel.seprphase4.gui.screens.MainMenuScreen;
import eel.seprphase4.gui.drawable.DrawableImage;
import eel.seprphase4.gui.drawable.DrawableText;
import eel.seprphase4.gui.screens.MultiplayerPlantScreen;
import eel.seprphase4.gui.screens.PlantScreen;
import eel.seprphase4.gui.screens.SingleplayerStartScreen;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;

/**
 *
 * @author David
 */
public class PlantScreenTest extends JFrame {

    ScreenManager sm;

    public PlantScreenTest() {
        super("Plant Test");

        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1366, 788);
        
        // If the location is relative to nothing, the window always starts in the middle of the screen.
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        sm = ScreenManager.getInstance();
        add(sm);
        
        sm.setScreen(new MultiplayerPlantScreen("rob", "bob"));
        sm.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PlantScreenTest displayTest = new PlantScreenTest();
    }
}
