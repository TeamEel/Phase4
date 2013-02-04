package cichlid.seprphase3.GUIInterface;

import java.awt.*;
import java.awt.event.*;

public class GUIWindow {

    private Frame frame;

    public GUIWindow(String title, int width, int height) {
        frame = new Frame(title);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.addWindowListener(
        new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
    }
}
