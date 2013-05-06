
package display.controls;

import display.Asset;
import display.Control;
import display.DrawableFactory;
import eel.seprphase4.Simulator.PlantController;
import eel.seprphase4.Simulator.PlantStatus;
import display.drawable.Animation;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author James
 */
public class CondenserWaterLevelControl implements Control {

    private PlantController controller;
    private PlantStatus status;
    private int x,y;
    private Animation animation;
    
    public CondenserWaterLevelControl(PlantController controller, PlantStatus status, int x, int y ) {
        this.controller = controller;
        this.status = status;
        this.x = x;
        this.y = y;
        
        animation = (Animation) DrawableFactory.create(Asset.PlantCondenserWaterLevelAnimation);
    }
    
    @Override
    public void paint(Graphics g) {
        animation.jumpToFrame(
                (int)Math.round((status.condenserWaterLevel().points()-status.condenserWaterLevel().points()%10)/10)
                );
        animation.draw(g, x,y );
    }

    @Override
    public void advance(int ms) {
        
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
