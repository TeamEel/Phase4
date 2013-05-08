/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.gui.screens;

import eel.seprphase4.gui.Asset;
import eel.seprphase4.gui.ScreenManager;
import eel.seprphase4.gui.controls.ButtonControl;
import eel.seprphase4.gui.controls.ImageControl;
import eel.seprphase4.gui.drawable.DrawableText;
import eel.seprphase4.MultiplayerSimulator;
import eel.seprphase4.Simulator.Simulator;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

/**
 *
 * @author drm511
 */
public class MultiplayerGameOverScreen extends MenuScreen {

    private final ButtonControl replayButton;
    private final ButtonControl exitButton;

    public MultiplayerGameOverScreen(MultiplayerSimulator simulator) {
        Font font = new Font("Courier New", Font.BOLD, 24);
        Color color = new Color(255, 155, 0);
        this.replayButton = new ButtonControl(Asset.SwapDefault,
                                              Asset.SwapOver,
                                              LEFT_MARGIN, TOP_MARGIN + 350);
        this.exitButton = new ButtonControl(Asset.ExitDefault,
                                            Asset.ExitOver,
                                            LEFT_MARGIN + 400, TOP_MARGIN + 350);
        add(new ImageControl(
                new DrawableText("Congratulations, " + simulator.getPlayer1Name() + "\n" +
                                 "You have generated\n" +
                                 simulator.energyGenerated(),
                                 font, color),
                LEFT_MARGIN, TOP_MARGIN),
            2);
        add(replayButton, 2);
        add(exitButton, 2);
        replayButton.addActionListener(this);
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
