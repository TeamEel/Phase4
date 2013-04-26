/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.GUIInterface;

import eel.seprphase4.Simulator.PlantController;
import eel.seprphase4.Simulator.PlantStatus;
import eel.seprphase4.Simulator.Simulator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author James
 */
class MultiPlayerKeyListener implements KeyListener{
    PlantController plant;
    PlantStatus status;
    
    public MultiPlayerKeyListener(PlantController plantController, PlantStatus plantStatus) {
       plant = plantController;
       status = plantStatus;
       System.out.println("kl init");
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println(ke);
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println(ke);
        /*
            if(ke.getKeyChar() == 'r' )
            {
                plant.failReactor();
            }
            else 
        */
 
        if(ke.getKeyChar() == ' ')
        {
            /*
             * Toggle allow random failures on space
             */
            plant.allowRandomFailures(!status.allowsRandomFailures());
        }
        
        if(!status.allowsRandomFailures())
        {
            if(ke.getKeyChar() == 'c' )
            {
                plant.failCondenser();
            }
            else if(ke.getKeyChar() == 't' )
            {
                /*
                 * To do: Fail turbine
                 */
                plant.failTurbine();
            }
            else if(ke.getKeyChar() == 's' )
            {
                plant.failSoftware();
            }
            else if(ke.getKeyChar() == '1' )
            {
                plant.failPump(1);
            }
            else if(ke.getKeyChar() == '2' )
            {
                plant.failPump(2);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }

  

    void update(PlantController plantController, PlantStatus plantStatus) {
        plant = plantController;
        status = plantStatus;
    }
    
    
    
}
