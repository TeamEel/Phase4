package display;

import display.drawable.Drawable;
import eel.seprphase4.Simulator.PlantController;
import eel.seprphase4.Simulator.PlantStatus;
import eel.seprphase4.Simulator.Pump;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Observer;

/**
 *
 * @author James
 */
public class PumpControl implements Control {

  
    private int x, y;
    private ArrayList<ActionListener> actionListeners;

    private int pumpNumber;
    
    private PlantStatus status;
    private PlantController control;
   
    private ButtonControl buttonControl;

    public PumpControl(PlantStatus status, PlantController control, int pumpNumber,
                         int x, int y) {
        
        
        this.status = status;
        this.control = control; 
        
        this.buttonControl = new ButtonControl(
            DrawableFactory.create(Asset.PlantPump),
            DrawableFactory.create(Asset.PlantPump),
            DrawableFactory.create(Asset.PlantPump),
            x,y);
        
        
        
        this.x = x;
        this.y = y;
   
       
        
        this.actionListeners = new ArrayList<ActionListener>();
   
    }
    
    public void addActionListener(ActionListener al) {
        actionListeners.add(al);
    }

    @Override
    public void paint(Graphics g) {
        buttonControl.paint(g);
    }

    @Override
    public void onMouseExited() {
        buttonControl.onMouseExited();
    }

    @Override
    public void onMouseMoved(Point point) {
        buttonControl.onMouseMoved(point);
    }

    @Override
    public boolean onMousePressed(Point point) {
        return buttonControl.onMousePressed(point);
    }

    @Override
    public boolean onMouseReleased(Point point) {
        return buttonControl.onMouseReleased(point);
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

    @Override
    public void advance(int ms) {
        if(!status.componentList().get("pump1").hasFailed() && ((Pump)status.componentList().get("pump1")).getStatus())
        {
            buttonControl.advance(ms);
        }
    }
}
