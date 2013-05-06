/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.controls;

import display.Asset;
import display.Control;
import display.DrawableFactory;
import display.drawable.Animation;
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
public class CondenserStatusControl implements Control,Observer {
    

private PlantController controller;
    private PlantStatus status;
    private int x,y;
    private Observable plant;

    private DrawableText text;
    
    public CondenserStatusControl(PlantController controller, PlantStatus status, Observable plant,   int x, int y) {
        this.controller = controller;
        this.status = status;
        this.plant = plant;
        this.x= x;
        this.y = y;
        this.text = new DrawableText("Condenser Pressure: " + status.condenserPressure().inAtmospheres()+ " atm",new Font("Arial", Font.BOLD, 14),Color.orange);
        
        plant.addObserver(this);
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
        return false;
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        return false;
    }

    @Override
    public void update(Observable o, Object o1) {
        this.text.setText("Condenser Pressure: " + status.condenserPressure().inAtmospheres()+ " atm");
    }
    
}