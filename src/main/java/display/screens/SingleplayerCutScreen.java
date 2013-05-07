/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.Asset;
import display.Screen;
import display.ScreenManager;
import display.controls.ButtonControl;
import display.controls.ImageControl;
import display.controls.PageControl;
import display.drawable.DrawableText;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author James
 */
public class SingleplayerCutScreen extends MenuScreen implements ActionListener {
    ButtonControl startButton;
    String player1Name;
    public SingleplayerCutScreen(String player1Name) {
        
        final Font font = new Font("Arial", Font.BOLD, 24);
       
        
        startButton = new ButtonControl(Asset.StartDefault,
                                        Asset.StartOver,
                                        Asset.StartPressed,
                                        1100, 600);
      
        startButton.addActionListener(this);
         
        PageControl page = new PageControl(new ButtonControl(Asset.NextDefault,
                                        Asset.NextOver,
                                        Asset.NextPressed,
                                        1100, 600), startButton);
        
        page.add(new ImageControl(new DrawableText(player1Name + " and your rival Comrad are Physics graduate students!\n"+
                                                   "They are always building their mad scientist inventions, \n"+
                                                   "always with the intent of outdoing the other! \n"+
                                                   "Whenever they aren’t inventing some insane creation, \n"+
                                                   "they are sabotaging the other’s projects! " + player1Name,font,Color.orange), 650, 300));
        
        page.add(new ImageControl(new DrawableText("Looks like Comrad is up to their wacky antics again!\n"+
                                                   "you have decided to build a nuclear reactor for his Physics graduate project, \n"+
                                                   "and Comrad is determined not to be outdone!",font,Color.orange), 650, 300));
        
        page.add(new ImageControl(new DrawableText("Your job is to generate as much power as possible using "+player1Name+"’s\n"+ 
                                                   "homebrew power plant!\n\nWatch out for Comrad though!\n He is determined to sabotage you at any cost!",font,Color.orange), 650, 300));
        
        page.add(new ImageControl(new DrawableText(player1Name + " Generate power by spinning the turbine as fast as possible.\n"
                +"Raise the control rods using the arrow buttons to heat the water faster. Increasing the steam production spins the turbine faster.\nClick the valves and pumps to turn them on and off.\n"+"Keep an eye on the reactor temperature and pressure! Go too high and the reactor will MELTDOWN!",font,Color.orange), 650, 300));
        

        
        add(page,5);
        
        add(new ImageControl(Asset.MenuScientists, -100, 200),3);
        this.player1Name = player1Name;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == startButton) {
            ScreenManager.getInstance().setScreen(new PlantScreen(player1Name));
        }
    }
}
