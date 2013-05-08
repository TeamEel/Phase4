package display.screens;

import display.Asset;
import display.Screen;
import display.ScreenManager;
import display.controls.ButtonControl;
import eel.seprphase4.Simulator.Simulator;
import java.awt.Font;
import java.awt.event.ActionEvent;

/**
 *
 * @author David
 */
public class PauseScreen extends MenuScreen {

    private final Screen previousScreen;
    private final Simulator simulator;
    private final ButtonControl resumeButton;
    private final ButtonControl saveButton;
    private final ButtonControl exitButton;

    public PauseScreen(Screen previous, Simulator simulator) {
        this.previousScreen = previous;
        this.simulator = simulator;
        resumeButton = new ButtonControl(Asset.ResumeDefault,
                                         Asset.ResumeOver,
                                         LEFT_MARGIN, TOP_MARGIN);
        saveButton = new ButtonControl(Asset.SaveDefault,
                                       Asset.SaveOver,
                                       LEFT_MARGIN, TOP_MARGIN + 120);
        exitButton = new ButtonControl(Asset.ExitDefault,
                                       Asset.ExitOver,
                                       LEFT_MARGIN, TOP_MARGIN + 240);
        addButton(resumeButton);
        addButton(saveButton);
        addButton(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == resumeButton) {
            resume();
        } else if (source == saveButton) {
            simulator.saveGame();
            resume();
        } else if (source == exitButton) {
            ScreenManager.getInstance().setScreen(new MainMenuScreen());
        }
    }

    private void resume() {
        ScreenManager.getInstance().setScreen(previousScreen);
    }
}
