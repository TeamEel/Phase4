package cichlid.seprphase3.GUIInterface;

import javax.swing.*;

public class GUIWindow extends JFrame {

    public GUIWindow(String title, int width, int height) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void setWindow(JPanel _window) {
        setContentPane(_window);
    }

    public void update() {
        repaint();
    }
}
