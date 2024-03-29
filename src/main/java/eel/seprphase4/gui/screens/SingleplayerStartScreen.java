
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.gui.screens;

import eel.seprphase4.gui.Asset;
import eel.seprphase4.gui.Control;
import eel.seprphase4.gui.controls.ButtonControl;
import eel.seprphase4.gui.ScreenManager;
import eel.seprphase4.gui.controls.ImageControl;
import eel.seprphase4.gui.controls.TextFieldControl;
import eel.seprphase4.gui.drawable.DrawableText;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author drm511
 */
public class SingleplayerStartScreen extends MenuScreen implements ActionListener {

    private final TextFieldControl playerName;
    private final ButtonControl backButton;
    private final ButtonControl startButton;

    public SingleplayerStartScreen() {
        Control enterName = new ImageControl(Asset.EnterName, LEFT_MARGIN, TOP_MARGIN);
        playerName = new TextFieldControl("Player 1", LEFT_MARGIN, TOP_MARGIN + 50);
        backButton = new ButtonControl(Asset.BackDefault,
                                       Asset.BackOver,
                                       LEFT_MARGIN, TOP_MARGIN + 350);
        startButton = new ButtonControl(Asset.StartDefault,
                                        Asset.StartOver,
                                        LEFT_MARGIN + 400, TOP_MARGIN + 350);
        add(enterName, 2);
        add(playerName, 2);
        add(backButton, 2);
        add(startButton, 2);
        backButton.addActionListener(this);
        startButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == backButton) {
            ScreenManager.getInstance().setScreen(new MainMenuScreen());
        } else if (source == startButton) {
            ScreenManager.getInstance().setScreen(new SingleplayerCutScreen(playerName.getText()));
        }
    }
}


