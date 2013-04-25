/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.Simulator.Simulator;

/**
 *
 * @author James
 */
public class MultiplayerPlantScreen extends PlantScreen {
    MultiPlayerKeyListener listener;
    public MultiplayerPlantScreen(ScreenContext parent, String username){
        
        
        super(parent,username);
        setFocusable( true );
        super.plantController.allowRandomFailures(false);
        listener = new MultiPlayerKeyListener(super.plantController,super.plantStatus);
        this.addKeyListener(listener);
    } 
}
