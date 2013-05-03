/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.Simulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.Timer;

/**
 *
 * @author James
 */
public class ButtonBash extends Observable implements ActionListener {
    private PlantController controller; 
    private int failCount = 0;
    private int health = 0;
    private Timer timer;
    
    public ButtonBash(PlantController controller)
    {
        this.controller = controller;
        this.timer = new Timer(1000, this);
        this.timer.start();
    }
    
    public void failCondenser()
    {
        health++;
        if(health>hitsRequired())
        {
            controller.failCondenser();
            failCount++;
            health = 0;
        }
        
        updateAndNotify();
        
        
    }
    
    public void failPump(int pump)
    {
        health++;
        if(health>hitsRequired())
        {
        controller.failPump(pump);
        failCount++;
        health = 0;
        }
        updateAndNotify();
    }
    
    public void failSoftware()
    {
        health++;
        if(health>hitsRequired())
        {
        controller.failSoftware();
        failCount++;
        health = 0;
        }
        updateAndNotify();
    }
    
    public void failTurbine()
    {
        health++;
        if(health>hitsRequired())
        {
            controller.failTurbine();
            failCount++;
            health = 0;
        }
        updateAndNotify();
    }

    
    public double completed()
    {
        return health/hitsRequired();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(this.timer)){
            health--;
            if(health<0)
            {
                health = 0;
            }
            updateAndNotify();
        }
    }
    
    private int hitsRequired()
    {
        return 2^failCount;
    }
    
    private void updateAndNotify() {
        setChanged();
        notifyObservers();
    }
 
}
