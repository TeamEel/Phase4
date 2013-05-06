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
public class FailedTurbineControl implements Control {
    private PlantController controller;
    private PlantStatus status;

  
    private Drawable failedSprite;
    private Drawable workingSprite;
    private int x,y;
    public FailedTurbineControl(PlantController controller, PlantStatus status,int x, int y) {
        this.controller = controller;
        this.status = status;
       
        failedSprite = DrawableFactory.create(Asset.PlantFailedTurbine);
        workingSprite = DrawableFactory.create(Asset.PlantTurbine);
        this.x = x; 
        this.y = y;
    }
    
    @Override
    public void paint(Graphics g) {
        if(status.turbineHasFailed())
        {
            failedSprite.draw(g, x, y);
        }
        else
        {
            workingSprite.draw(g, x, y);
        }
    }

    @Override
    public void advance(int ms) {
        if(status.turbineHasFailed())
        {
            failedSprite.advance(ms);
        }
        else
        {
            workingSprite.advance(ms);
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
        if(status.turbineHasFailed() && failedSprite.hitBox(x, y).contains(point))
        {
            controller.repairTurbine();
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
}
