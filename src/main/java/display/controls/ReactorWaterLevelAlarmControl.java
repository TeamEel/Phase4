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
import eel.seprphase4.Utilities.Percentage;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author James
 */
public class ReactorWaterLevelAlarmControl implements Control {

    private PlantController controller;
    private PlantStatus status;

    private Percentage threshold;
    private Drawable dangerSprite;
    
    private int x,y;
    public ReactorWaterLevelAlarmControl(PlantController controller, PlantStatus status,int x, int y) {
        this.controller = controller;
        this.status = status;
        
        this.threshold = new Percentage(15);
        
        dangerSprite = DrawableFactory.create(Asset.PlantDanger);
        this.x = x; 
        this.y = y;
    }
    
    @Override
    public void paint(Graphics g) {
        if(show())
        {
            dangerSprite.draw(g, x, y);
        }
    }

    @Override
    public void advance(int ms) {
        dangerSprite.advance(ms);
    }

    @Override
    public void onMouseExited() {
    }

    @Override
    public void onMouseMoved(Point point) {
        
    }

    @Override
    public boolean onMousePressed(Point point) {
        if(show() && dangerSprite.hitBox(x, y).contains(point))
        {
            controller.quenchReactor();
            return true;
        }
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
    
    private boolean show() {
        return !status.quencherUsed() && status.reactorWaterLevel().points() < status.reactorMinimumWaterLevel().plus(threshold).points();
        
       
    }
}
