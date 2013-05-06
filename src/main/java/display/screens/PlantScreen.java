/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.Asset;
import display.controls.ImageControl;
import display.Screen;
import display.ScreenManager;
import eel.seprphase4.Simulator.Simulator;
import java.awt.event.KeyEvent;

/**
 *
 * @author James
 */
public class PlantScreen extends Screen {
    protected Simulator s;
    public PlantScreen(String playerName) {
        super();
        s = new Simulator(playerName);
        
        
        
        add(new ImageControl(Asset.PlantDefaultWater, 0, 0), 0);
        
        add(new display.controls.ReactorWaterLevelControl(s, s, 278, 440),1);
        add(new display.controls.CondenserWaterLevelControl(s, s, 956, 465),1);
        add(new display.controls.QuencherControl(s, s, 136, 207),1);
        
        
        add(new ImageControl(Asset.PlantBackground, 0, 0), 2);
        
        add(new display.controls.PumpControl(s,s,1,680,550),3);
        add(new display.controls.PumpControl(s,s,2, 1168, 444),3);
        
        
        add(new display.controls.ValveControl(s,s,1,1010, 300),3);
        add(new display.controls.ValveControl(s,s,2,598, 115),3);
        
        
        add(new display.controls.FailedCondenserControl(s,s,955, 511),3);
        add(new display.controls.FailedTurbineControl(s,s,953, 30),3);
        
        add(new display.controls.ControlRodPositionControl(s, s,150,450),5);
        add(new display.controls.ReactorWaterLevelAlarmControl(s, s, 50, 50),5);
        
        add(new display.controls.ReactorStatusControl(s, s, s, 443, 354),5);
        add(new display.controls.CondenserStatusControl(s, s, s, 1083, 408),5);
        
        
        
        s.failTurbine();
    }
    
    @Deprecated
    public PlantScreen() {
        this("bill");
    }

}
