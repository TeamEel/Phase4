/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.controls;

import display.Asset;
import display.Control;
import display.DrawableFactory;
import display.drawable.Drawable;
import eel.seprphase4.Simulator.PlantController;
import eel.seprphase4.Simulator.PlantStatus;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author James
 */
public class ControlRodsControl implements Control {
    private PlantController controller;
    private PlantStatus status;

  
    private Drawable sprite;

    
    private int x,y;
    public ControlRodsControl(PlantController controller, PlantStatus status,int x, int y) {
        this.controller = controller;
        this.status = status;
       
        sprite = DrawableFactory.create(Asset.PlantControlRods);
        
        this.x = x; 
        this.y = y;
    }
    
    @Override
    public void paint(Graphics g) {
        sprite.draw(g, x, y-( (int)Math.round(0.8*status.controlRodPosition().points())));
    }

    @Override
    public void advance(int ms) {
        sprite.advance(ms);
        
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
}
