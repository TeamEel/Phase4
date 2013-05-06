package display;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author David
 */
public class ScreenManager extends JPanel implements MouseInputListener, KeyListener, ActionListener {

    private Screen currentScreen;
    private Timer timer;
    private static ScreenManager instance;
    private static final int defaultWidth = 1366, defaultHeight = 768;

    private ScreenManager(int width, int height) {
        this.currentScreen = new Screen();
        this.timer = new Timer(10, this);
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        addMouseMotionListener(this);
        addMouseListener(this);
        addKeyListener(this);
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager(defaultWidth, defaultHeight);
        }

        return instance;
    }

    public static ScreenManager getInstance(int width, int height) {
        if (instance == null) {
            instance = new ScreenManager(width, height);
        }

        return instance;
    }

    public void setScreen(Screen screen) {
        timer.stop();        
        currentScreen = screen;
        timer.restart();
    }

    public void start() {
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        currentScreen.paint(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        requestFocusInWindow();
        // ignore composite event
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentScreen.onMousePressed(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentScreen.onMouseReleased(e.getPoint());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // ignore
    }

    @Override
    public void mouseExited(MouseEvent e) {
        currentScreen.onMouseExited();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currentScreen.onMouseMoved(e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        currentScreen.onMouseMoved(e.getPoint());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        currentScreen.onKeyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        currentScreen.onKeyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentScreen.onKeyReleased(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            currentScreen.advance(10);
            repaint();
        }
    }
}
