package display.screens;

import display.Screen;
import display.ScreenManager;
import display.controls.ButtonControl;
import display.drawable.DrawableText;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class PauseScreen extends MenuScreen {

    private final Screen previousScreen;
    private final ButtonControl resumeButton;
    private final ButtonControl saveButton;
    private final ButtonControl exitButton;

    public PauseScreen(Screen previous) {
        Font font = new Font("Arial", Font.BOLD, 20);
        this.previousScreen = previous;
        resumeButton = new ButtonControl(new DrawableText("RESUME", font, Color.white),
                                         new DrawableText("RESUME", font, Color.orange),
                                         new DrawableText("RESUME", font, Color.orange),
                                         LEFT_MARGIN, TOP_MARGIN);
        saveButton = new ButtonControl(new DrawableText("SAVE", font, Color.white),
                                       new DrawableText("SAVE", font, Color.orange),
                                       new DrawableText("SAVE", font, Color.orange),
                                       LEFT_MARGIN, TOP_MARGIN + 50);
        exitButton = new ButtonControl(new DrawableText("EXIT", font, Color.white),
                                       new DrawableText("EXIT", font, Color.orange),
                                       new DrawableText("EXIT", font, Color.orange),
                                       LEFT_MARGIN, TOP_MARGIN + 100);
        addButton(resumeButton);
        addButton(saveButton);
        addButton(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == resumeButton) {
            ScreenManager.getInstance().setScreen(previousScreen);
        } else if (source == saveButton) {
            // save game
        } else if (source == exitButton) {
            ScreenManager.getInstance().setScreen(new MainMenuScreen());
        }
    }
}
