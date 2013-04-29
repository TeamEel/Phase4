package eel.seprphase4.drawing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

/**
 * Provide a painting surface for Sprites
 * 
 * Allows for static Z-ordering of Sprites, and arbitrary movement and animation selection
 * for Sprites. Does not allow for changes in the Z-ordering of sprites which have already been added.
 * 
 * @author drm
 */
public class SpriteCanvas extends JPanel implements ActionListener, MouseInputListener {

    private Screen screen;
    private Timer timer;
    private double scaleFactor;
    
    public SpriteCanvas(Image background) {
        this.screen = new Screen(background);
        this.timer = new Timer(1000, this);
        this.scaleFactor = 1;
        setPreferredSize(new Dimension(background.getWidth(null), background.getHeight(null)));
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public SpriteCanvas() {
        this.timer = new Timer(10, this);
        this.scaleFactor = 1;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setScaleFactor(double scaleFactor) {
        if (scaleFactor > 1 || scaleFactor <= 0) {
            throw new IllegalArgumentException("Cannot set scale factor outside the range (0,1]");
        }
        this.scaleFactor = scaleFactor;
        resizeToScale();
    }
    
    /**
     * Set the interval between animation frames for the canvas
     * 
     * For example, a calling setFrameInterval(100) will cause the frame to
     * advance every 100ms, for a 10-frame-per-second animation.
     * 
     * @param ms the number of milliseconds to delay between frames
     */
    public void setFrameInterval(int ms) {
        timer.setDelay(ms);
    }

    /**
     * Begin animating sprites.
     * 
     * Sprites can be added freely after calling start.
     */
    public void start() {
        timer.start();
    }

    /**
     * Stop animating sprites
     */
    public void stop() {
        timer.stop();
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
        resizeToScale();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        // mildly hacky scaling to fit onto smaller screens
        ((Graphics2D)g).scale(scaleFactor, scaleFactor);
        //g.drawImage(background, 0, 0, this);
        screen.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == timer) {
            advance();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // ignore click events
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // ignore press events
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        screen.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // ignore enter events
    }

    @Override
    public void mouseExited(MouseEvent e) {
        screen.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // ignore drag events
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        screen.mouseMoved(e);
    }

    private void advance() {
        screen.advance();
        repaint();
    }

    private void resizeToScale() {
        setPreferredSize(new Dimension((int)Math.ceil(screen.size().width * scaleFactor),
                                       (int)Math.ceil(screen.size().width * scaleFactor)));
    }
}
