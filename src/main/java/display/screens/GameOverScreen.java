/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.Asset;
import display.ScreenManager;
import display.controls.ButtonControl;
import display.controls.ImageControl;
import display.drawable.DrawableText;
import eel.seprphase4.Simulator.Simulator;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

/**
 *
 * @author drm511
 */
public class GameOverScreen extends MenuScreen {

    private final ButtonControl exitButton;

    public GameOverScreen(Simulator simulator) {
        Font font = new Font("Courier New", Font.BOLD, 24);
        Color color = new Color(255, 155, 0);
        this.exitButton = new ButtonControl(Asset.ExitDefault,
                                            Asset.ExitOver,
                                            LEFT_MARGIN + 400, TOP_MARGIN + 350);
        add(new ImageControl(
                new DrawableText("Congratulations, " + simulator.getUsername() + "\n" +
                                 "You have generated\n" +
                                 simulator.energyGenerated(),
                                 font, color),
                LEFT_MARGIN, TOP_MARGIN),
            2);
        add(exitButton, 2);
        exitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == exitButton) {
            ScreenManager.getInstance().setScreen(new MainMenuScreen());
        }
    }
}
