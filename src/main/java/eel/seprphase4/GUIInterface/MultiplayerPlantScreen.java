/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.GUIInterface;

import eel.seprphase4.Simulator.Simulator;

/**
 *
 * @author James
 */
public class MultiplayerPlantScreen extends PlantScreen {
    MultiPlayerKeyListener listener;
    public MultiplayerPlantScreen(String username){
        
        
        super(username);
        setFocusable( true );
        super.plantController.allowRandomFailures(false);
        listener = new MultiPlayerKeyListener(super.plantController,super.plantStatus);
        this.addKeyListener(listener);
    } 
}
