/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.Asset;
import display.ImageControl;
import display.Screen;

import eel.seprphase4.Simulator.Simulator;

/**
 *
 * @author James
 */
public class PlantScreen extends Screen {
    public PlantScreen() {
        super();
        Simulator s = new Simulator("bill");
        add(new ImageControl(Asset.PlantDefaultWater, 0, 0), 0);
        add(new ImageControl(Asset.PlantBackground, 0, 0), 1);
        
        add(new display.PumpControl(s,s,1,680,550),2);
        add(new display.PumpControl(s,s,2, 1168, 444),2);
        
        
        add(new display.ValveControl(s,s,1,1010, 300),2);
        
    }
}
