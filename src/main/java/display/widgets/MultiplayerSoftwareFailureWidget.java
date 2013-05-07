/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.widgets;

import eel.seprphase4.Simulator.Simulator;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author James
 */
public class MultiplayerSoftwareFailureWidget extends Widget {

    private int msPerPoint = 800;
    private int failCount = 0;
    private int currentPoints = 0;
    private int spareMs = 0;

    public MultiplayerSoftwareFailureWidget(Simulator simulator) {
        super(simulator, 0, 0);
    }

    @Override
    public void paint(Graphics g) {
    }

    @Override
    public void advance(int ms) {
        currentPoints -= (ms + spareMs) / msPerPoint;
        spareMs = (ms + spareMs) % msPerPoint;

        if (currentPoints < 0) {
            currentPoints = 0;
        }
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
        switch (e.getKeyChar()) {
            case '1':
                tryFailPump(1);
                return true;
            case '2':
                tryFailPump(2);
                return true;
            case 'c':
                tryFailCondenser();
                return true;
            case 't':
                tryFailTurbine();
                return true;
            case 's':
                tryFailSoftware();
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

    private void tryFailCondenser() {
        currentPoints++;
        if (currentPoints > pointsRequired()) {
            simulator.failCondenser();
            reset();
        }
    }

    private void reset() {
        failCount++;
        currentPoints = 0;
    }

    private void tryFailPump(int pump) {
        currentPoints++;
        if (currentPoints > pointsRequired()) {
            simulator.failPump(pump);
        }
    }

    private void tryFailSoftware() {
        currentPoints++;
        if (currentPoints > pointsRequired()) {
            simulator.failSoftware();
            reset();
        }
    }

    private void tryFailTurbine() {
        currentPoints++;
        if (currentPoints > pointsRequired()) {
            simulator.failTurbine();
            reset();
        }
    }

    private double completed() {
        return currentPoints / pointsRequired();
    }

    private int pointsRequired() {
        return 2 ^ failCount;
    }
}
