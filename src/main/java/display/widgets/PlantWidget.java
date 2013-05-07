/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.widgets;

import eel.seprphase4.GameOverException;
import eel.seprphase4.Simulator.Simulator;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public class PlantWidget extends Widget {

    private static final int msPerStep = 50;
    private int spareMs = 0;
    private int currentStep;
    private ArrayList<ActionListener> actionListeners;

    public PlantWidget(Simulator simulator) {
        super(simulator, 0, 0);
        actionListeners = new ArrayList<ActionListener>();
    }

    public void addActionListener(ActionListener al) {
        actionListeners.add(al);
    }
    
    @Override
    public void advance(int ms) {
        int oldStep = currentStep;
        currentStep += (ms + spareMs) / msPerStep;

        spareMs = (ms + spareMs) % msPerStep;

        if (oldStep != currentStep) {
            try {
                simulator.step(1);
            } catch (GameOverException e) {
                ActionEvent ae = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null);
                for (ActionListener al : actionListeners) {
                    al.actionPerformed(ae);
                }
                //ScreenManager.getInstance().setScreen(new MainMenuScreen());
            }
        }
    }

    @Override
    public void paint(Graphics g) {
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
    public boolean onKeyPressed(KeyEvent e) {
        return false;
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        return false;
    }

    @Override
    public boolean onKeyTyped(KeyEvent e) {
        return false;
    }
}