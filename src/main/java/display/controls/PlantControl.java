/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.controls;

import display.Asset;
import display.Control;
import display.DrawableFactory;
import display.ScreenManager;
import display.screens.MainMenuScreen;
import display.screens.MenuScreen;
import eel.seprphase4.GameOverException;
import eel.seprphase4.Simulator.PlantController;
import eel.seprphase4.Simulator.PlantStatus;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author James
 */
public class PlantControl implements Control {
    

  
    private int x, y;
    

    private int pumpNumber;
    
    private PlantStatus status;
    private PlantController control;
   
    private AnimationControl pumpAnimation;
    private ImageControl failedPump;

    private int msPerStep = 50;
    private int spareMs = 0;
    private int currentStep;
    
    public PlantControl(PlantStatus status, PlantController control) {

        this.status = status;
        this.control = control; 

    }
    
    @Override
    public void advance(int ms) {
        int oldStep = currentStep;
        currentStep += (ms + spareMs) / msPerStep;
        
        spareMs = (ms + spareMs) % msPerStep;
        
        if(oldStep != currentStep) {
            try
            {
                control.step(1);
            }
            catch(GameOverException e)
            {
                ScreenManager.getInstance().setScreen(new MainMenuScreen());
            }
        }
    }
   

    @Override
    public void paint(Graphics g) {
      
    }

    @Override
    public void onMouseExited() {
        
    }

    @Override
    public void onMouseMoved(Point point) {
        
    }

    @Override
    public boolean onMousePressed(Point point) {
        return false;
    }

    @Override
    public boolean onMouseReleased(Point point) {
        return false;
    }

    @Override
    public boolean onKeyPressed(KeyEvent e) {
        return false;
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        return false;
    }

    @Override
    public boolean onKeyTyped(KeyEvent e) {
        return false;
    }

    

   
}