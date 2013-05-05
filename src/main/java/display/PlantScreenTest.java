package display;

import display.screens.MainMenuScreen;
import display.drawable.DrawableImage;
import display.drawable.DrawableText;
import display.screens.MultiplayerPlantScreen;
import display.screens.PlantScreen;
import display.screens.SingleplayerStartScreen;
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
        
        sm.setScreen(new PlantScreen("rob"));
        sm.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PlantScreenTest displayTest = new PlantScreenTest();
    }
}
