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
        add(new ImageControl(new DrawableText("Hello " + player1Name,font,Color.orange), 800, 350),4);
        startButton = new ButtonControl(Asset.StartDefault,
                                        Asset.StartOver,
                                        Asset.StartPressed,
                                        1100, 600);
        
        startButton.addActionListener(this);
        
        add(startButton,5);
        
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
