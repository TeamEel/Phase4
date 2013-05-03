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
public class ValveControl implements Control, ActionListener {

 

    private int valveNumber;
    
    
    private PlantStatus status;
    private PlantController control;

    private AnimationControl valveAnimation;
    
    public ValveControl(PlantStatus status, PlantController control, int valveNumber,
                         int x, int y) {
        
        
        this.status = status;
        this.control = control; 
        
     
        this.valveNumber = valveNumber;
        this.valveAnimation = new AnimationControl(Asset.PlantValveAnimation,x,y);
  
        this.valveAnimation.addActionListener(this);
        
   
    }
    
   

    @Override
    public void paint(Graphics g) {
        valveAnimation.paint(g);
    }

    @Override
    public void onMouseExited() {
        valveAnimation.onMouseExited();
    }

    @Override
    public void onMouseMoved(Point point) {
        valveAnimation.onMouseMoved(point);
    }

    @Override
    public boolean onMousePressed(Point point) {
        return valveAnimation.onMousePressed(point);
        
    }

    @Override
    public boolean onMouseReleased(Point point) {
        return valveAnimation.onMouseReleased(point);
      
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
        
        if(!status.valveState(valveNumber))
        {
            valveAnimation.reverse(ms);
        }
        else {
            valveAnimation.advance(ms);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae.getSource());
        
        try
        {
            control.changeValveState(valveNumber, !status.valveState(valveNumber));
        }
        catch(Exception e){}
        
        
       
    }
}