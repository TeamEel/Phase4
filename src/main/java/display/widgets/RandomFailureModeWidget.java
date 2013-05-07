/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.widgets;

import display.drawable.DrawableText;
import eel.seprphase4.Simulator.Simulator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author James
 */
public class RandomFailureModeWidget extends Widget {

    private DrawableText text;

    public RandomFailureModeWidget(Simulator simulator, int x, int y) {
        super(simulator, x, y);
        this.text = new DrawableText("Random Failures: " + failureModeText(), new Font("Courier New", Font.BOLD, 14),
                                     Color.BLACK);
    }

    @Override
    public void paint(Graphics g) {
        text.setText("Random Failures: " + failureModeText());
        text.draw(g, x, y);
    }

    @Override
    public void advance(int ms) {
        text.advance(ms);
    }

    @Override
    public void onMouseExited() {
    }

    @Override
    public void onMouseMoved(Point point) {
    }

    @Override
    public boolean onMousePressed(Point point) {
        return false;
    }

    @Override
    public boolean onMouseReleased(Point point) {
        return false;
    }

    @Override
    public boolean onKeyTyped(KeyEvent e) {
        if (e.getKeyChar() == ' ') {
            simulator.allowRandomFailures(!simulator.allowsRandomFailures());
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyPressed(KeyEvent e) {
        return false;
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        return false;
    }

    private String failureModeText() {
        return simulator.allowsRandomFailures() ? "On" : "Off";
    }
}
