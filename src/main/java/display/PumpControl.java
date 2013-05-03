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
   
    private AnimationControl pumpAnimation;
    private ImageControl failedPump;

    public PumpControl(PlantStatus status, PlantController control, int pumpNumber,
                         int x, int y) {
        
        
        this.status = status;
        this.control = control; 
        
        this.pumpAnimation = new AnimationControl(DrawableFactory.create(Asset.PlantPump),x,y);
        
        this.failedPump = new ImageControl(DrawableFactory.create(Asset.PlantFailedPump),x,y);
        
        this.x = x;
        this.y = y;
   
        this.pumpNumber= pumpNumber;
        
        this.pumpAnimation.addActionListener(this);
        this.failedPump.addActionListener(this);
   
    }
    
   

    @Override
    public void paint(Graphics g) {
        
        if(!status.pumpFailed(pumpNumber)){
            pumpAnimation.paint(g);
        }
        else
        {
            failedPump.paint(g);
        }
    }

    @Override
    public void onMouseExited() {
        pumpAnimation.onMouseExited();
    }

    @Override
    public void onMouseMoved(Point point) {
        pumpAnimation.onMouseMoved(point);
    }

    @Override
    public boolean onMousePressed(Point point) {
        return pumpAnimation.onMousePressed(point);
    }

    @Override
    public boolean onMouseReleased(Point point) {
        return pumpAnimation.onMouseReleased(point);
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
        if(!status.pumpFailed(pumpNumber))
        {
            if(status.pumpStatus(pumpNumber))
            {
                pumpAnimation.advance(ms);
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
            control.changePumpState(pumpNumber, !status.pumpStatus(pumpNumber));
        }
        catch(Exception e){}
    }
}