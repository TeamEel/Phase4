package display;

import java.awt.Font;
import javax.swing.JFrame;

/**
 *
 * @author David
 */
public class DisplayTest extends JFrame {

    ScreenManager sm;

    public DisplayTest() {
        super("Display Test");

        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        // If the location is relative to nothing, the window always starts in the middle of the screen.
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        sm = new ScreenManager(1000, 600);
        add(sm);

        ControlContainer cc = new ControlContainer();

        sm.setScreen(cc);

        cc.add(new ImageControl(new DrawableImage("/menu/background.png"), 0, WIDTH), 0);
        
        cc.add(new ButtonControl(new DrawableImage("/menu/button_multiplayer.png"),
                                 new DrawableImage("/menu/button_multiplayer_sel.png"),
                                 new DrawableImage("/menu/button_multiplayer_press.png"), 100, 100), 1);

        //cc.add(new ImageControl(new DrawableImage("/menu/button_multiplayer_sel.png"), 100, 100), 1);
        cc.add(new TextFieldControl("test", new Font("Arial", Font.PLAIN, 16), 100, 200), 1);
        sm.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DisplayTest displayTest = new DisplayTest();
    }
}
