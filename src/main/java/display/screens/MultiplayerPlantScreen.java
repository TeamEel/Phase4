/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.ScreenManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author James
 */
public class MultiplayerPlantScreen extends PlantScreen implements KeyListener {
    public MultiplayerPlantScreen(String userName) {
        super(userName);
        ScreenManager.getInstance().addKeyListener(this);    
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
