/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.Simulator.PlantController;
import cichlid.seprphase3.Simulator.PlantStatus;
import cichlid.seprphase3.Simulator.Simulator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author James
 */
class MultiPlayerKeyListener implements KeyListener{
    PlantController plant;
    PlantStatus status;
    GUIWindow parent;
    public MultiPlayerKeyListener(GUIWindow parentWindow, PlantController plantController, PlantStatus plantStatus) {
       plant = plantController;
       parent = parentWindow;
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
        
        if(parent.state == GameState.Running && !status.allowsRandomFailures())
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
