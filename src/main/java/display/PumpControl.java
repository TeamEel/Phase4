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
public class PumpControl implements Control, ActionListener {

  
    private int x, y;
    

    private int pumpNumber;
    
    private PlantStatus status;
    private PlantController control;
   
    private ButtonControl buttonControl;
    private ButtonControl failedPump;

    public PumpControl(PlantStatus status, PlantController control, int pumpNumber,
                         int x, int y) {
        
        
        this.status = status;
        this.control = control; 
        
        this.buttonControl = new ButtonControl(
            DrawableFactory.create(Asset.PlantPump),
            DrawableFactory.create(Asset.PlantPump),
            DrawableFactory.create(Asset.PlantPump),
        
            x,y);
        
        this.failedPump = new ButtonControl(
            DrawableFactory.create(Asset.PlantFailedPump),
            DrawableFactory.create(Asset.PlantFailedPump),
            DrawableFactory.create(Asset.PlantFailedPump),
            x,y);
        
        this.x = x;
        this.y = y;
   
       
        this.buttonControl.addActionListener(this);
        this.failedPump.addActionListener(this);
   
        control.failPump(1);
    }
    
   

    @Override
    public void paint(Graphics g) {
        
        if(!status.componentList().get("pump1").hasFailed()){
        buttonControl.paint(g);
        }
        else
        {
            failedPump.paint(g);
        }
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
        if(!status.componentList().get("pump1").hasFailed())
        {
            if(!((Pump)status.componentList().get("pump1")).getStatus())
            {
                buttonControl.advance(ms);
            }
        }
        else
        {
            this.failedPump.advance(ms);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae.getSource());
        
        try
        {
            control.changePumpState(1, !((Pump)status.componentList().get("pump1")).getStatus());
        }
        catch(Exception e){}
    }
}