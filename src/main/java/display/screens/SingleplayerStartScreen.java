/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.controls.ButtonControl;
import display.ScreenManager;
import display.controls.TextFieldControl;
import display.drawable.DrawableText;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author drm511
 */
public class SingleplayerStartScreen extends MenuScreen implements ActionListener {

    private TextFieldControl playerName;
    private ButtonControl backButton;
    private ButtonControl startButton;

    public SingleplayerStartScreen() {
        playerName = new TextFieldControl("Player 1", new Font("Courier New", Font.BOLD, 50), Color.white, 800, 300);
        final Font font = new Font("Arial", Font.BOLD, 24);
        backButton = new ButtonControl(new DrawableText("BACK", font, Color.orange),
                                       new DrawableText("BACK", font, Color.white),
                                       new DrawableText("BACK", font, Color.red),
                                       800, 600);
        startButton = new ButtonControl(new DrawableText("START", font, Color.orange),
                                        new DrawableText("START", font, Color.white),
                                        new DrawableText("START", font, Color.red),
                                        1100, 600);
        
        startButton.addActionListener(this);
        backButton.addActionListener(this);
        
        add(playerName, 2);
        add(backButton, 3);
        add(startButton, 3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == backButton) {
            ScreenManager.getInstance().setScreen(new MainMenuScreen());
        } else if (source == startButton) {
            ScreenManager.getInstance().setScreen(new PlantScreen(playerName.getText()));
        }
    }
}
