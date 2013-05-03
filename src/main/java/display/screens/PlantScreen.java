/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.Asset;
import display.ImageControl;
import display.Screen;
import display.ScreenManager;
import eel.seprphase4.Simulator.Simulator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author James
 */
public class PlantScreen extends Screen implements KeyListener{
    Simulator s;
    public PlantScreen(String playerName) {
        super();
        s = new Simulator(playerName);
        add(new ImageControl(Asset.PlantDefaultWater, 0, 0), 0);
        add(new ImageControl(Asset.PlantBackground, 0, 0), 1);
        
        add(new display.PumpControl(s,s,1,680,550),2);
        add(new display.PumpControl(s,s,2, 1168, 444),2);
        
        
        add(new display.ValveControl(s,s,1,1010, 300),2);
        add(new display.ValveControl(s,s,2,598, 115),2);
        
        ScreenManager.getInstance().addKeyListener(this);
    }
    
    @Deprecated
    public PlantScreen() {
        this("bill");
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        char c = ke.getKeyChar();
        
        if(c=='1')
        {
            s.failPump(1);
        }
        else if(c=='2')
        {
            s.failPump(2);
        }
        else if(c=='c')
        {
            s.failCondenser();
        }
        else if(c=='t')
        {
            s.failTurbine();
        }
        else if(c=='s')
        {
            s.failSoftware();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
}
