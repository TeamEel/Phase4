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
public class MultiplayerCutScreen extends MenuScreen implements ActionListener {
    
    ButtonControl startButton;
    String player1Name;
    String player2Name;
    public MultiplayerCutScreen(String player1Name, String player2Name) {
    
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
        
        page.add(new ImageControl(new DrawableText("Hello " + player1Name,font,Color.orange), 800, 350));
        page.add(new ImageControl(new DrawableText("Page 2",font,Color.orange), 700, 350));
        page.add(new ImageControl(new DrawableText("Page 3",font,Color.orange), 700, 350));
        
        
        add(page,5);
        
        add(new ImageControl(Asset.MenuScientists, -100, 200),3);
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == startButton) {
            ScreenManager.getInstance().setScreen(new MultiplayerPlantScreen(player1Name, player2Name));
        }
    }
    
}
