/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.gui.screens;

import eel.seprphase4.gui.Asset;
import eel.seprphase4.gui.Screen;
import eel.seprphase4.gui.ScreenManager;
import eel.seprphase4.gui.controls.ButtonControl;
import eel.seprphase4.MultiplayerSimulator;
import eel.seprphase4.Simulator.Simulator;
import java.awt.Font;
import java.awt.event.ActionEvent;

/**
 *
 * @author drm511
 */
public class MultiplayerPauseScreen extends MenuScreen {

    private final Screen previousScreen;
    private final MultiplayerSimulator simulator;
    private final ButtonControl resumeButton;
    private final ButtonControl exitButton;

    public MultiplayerPauseScreen(Screen previous, MultiplayerSimulator simulator) {
        this.previousScreen = previous;
        this.simulator = simulator;
        resumeButton = new ButtonControl(Asset.ResumeDefault,
                                         Asset.ResumeOver,
                                         LEFT_MARGIN, TOP_MARGIN);
        exitButton = new ButtonControl(Asset.ExitDefault,
                                       Asset.ExitOver,
                                       LEFT_MARGIN, TOP_MARGIN + 240);
        addButton(resumeButton);
        addButton(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == resumeButton) {
            resume();
        } else if (source == exitButton) {
            ScreenManager.getInstance().setScreen(new MainMenuScreen());
        }
    }

    private void resume() {
        ScreenManager.getInstance().setScreen(previousScreen);
    }
}
