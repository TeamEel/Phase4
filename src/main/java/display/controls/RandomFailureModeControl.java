/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.controls;

import display.Control;
import display.drawable.DrawableText;
import eel.seprphase4.Simulator.PlantController;
import eel.seprphase4.Simulator.PlantStatus;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author James
 */
public class RandomFailureModeControl implements Control, Observer {
 
    private PlantController controller;
    private PlantStatus status;
    private int x,y;
    private Observable plant;
    private DrawableText text;
    
    public RandomFailureModeControl(PlantController controller, PlantStatus status, Observable plant,  int x, int y) {
        this.controller = controller;
        this.status = status;
        this.x= x;
        this.y = y;
        this.plant = plant;
        this.text = new DrawableText("Random Failures: " + failureModeText(),new Font("Arial", Font.BOLD, 14),Color.orange);
        this.plant.addObserver(this);
    }
    
    @Override
    public void paint(Graphics g) {
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
        return false;
    }

    @Override
    public boolean onKeyPressed(KeyEvent e) {
        if(e.getKeyChar() == ' ') {
            controller.allowRandomFailures(!status.allowsRandomFailures());
            return true;
        }
        return false;
     
        
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        return false;
    }

    @Override
    public void update(Observable o, Object o1) {
         text.setText("Random Failures: " + failureModeText());
    }
    
    private String failureModeText()
    {
        return status.allowsRandomFailures() ? "On" : "Off";
    }
    
}
