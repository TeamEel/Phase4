/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;


/**
 *
 * @author James
 */
public class MultiplayerPlantScreen extends PlantScreen  {
    
    
    public MultiplayerPlantScreen(String userName) {
        super(userName);
        
        add(new display.MultiplayerSoftwareFailureControl(s,s),0);
        
        s.allowRandomFailures(false);
        
    }
    
    


}
