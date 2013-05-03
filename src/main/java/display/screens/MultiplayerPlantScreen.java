/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.ScreenManager;
import eel.seprphase4.Simulator.ButtonBash;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author James
 */
public class MultiplayerPlantScreen extends PlantScreen implements KeyListener {
    ButtonBash b;
    public MultiplayerPlantScreen(String userName) {
        super(userName);
        ScreenManager.getInstance().addKeyListener(this);
        s.allowRandomFailures(false);
        b = new ButtonBash(s);
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        char c = ke.getKeyChar();
        
        if(c=='1')
        {
            b.failPump(1);
        }
        else if(c=='2')
        {
            b.failPump(2);
        }
        else if(c=='c')
        {
            b.failCondenser();
        }
        else if(c=='t')
        {
            b.failTurbine();
        }
        else if(c=='s')
        {
            b.failSoftware();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
}
