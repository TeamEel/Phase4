/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import com.fasterxml.jackson.core.JsonParseException;
import display.Asset;
import display.ScreenManager;
import display.controls.ButtonControl;
import display.saveload.LoadGameButton;
import static display.screens.MenuScreen.LEFT_MARGIN;
import eel.seprphase4.Persistence.FileSystem;
import eel.seprphase4.Persistence.SaveGame;
import eel.seprphase4.Persistence.SaveGameDescription;
import eel.seprphase4.Simulator.Simulator;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author drm511
 */
public class LoadScreen extends MenuScreen implements ActionListener {

    private static final int maxGamesToDisplay = 4;
    private final ButtonControl upButton;
    private final ButtonControl downButton;
    private final ButtonControl backButton;
    private ArrayList<LoadGameButton> gameButtons;
    private HashMap<LoadGameButton, String> buttonPaths;
    private int cursor;

    public LoadScreen() {
        upButton = new ButtonControl(Asset.MenuUpDefault,
                                     Asset.MenuUpOver,
                                     LEFT_MARGIN, TOP_MARGIN);
        downButton = new ButtonControl(Asset.MenuDownDefault,
                                       Asset.MenuDownOver,
                                       LEFT_MARGIN, TOP_MARGIN + 300);
        backButton = new ButtonControl(Asset.BackDefault,
                                       Asset.BackOver,
                                       LEFT_MARGIN, TOP_MARGIN + 350);
        gameButtons = new ArrayList<LoadGameButton>();
        buttonPaths = new HashMap<LoadGameButton, String>();

        cursor = 0;

        addButton(upButton);
        addButton(downButton);
        addButton(backButton);
        SaveGameDescription[] games = FileSystem.listSaveGames();
        for (SaveGameDescription g : games) {
            addGame(g);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        final int first = cursor;
        final int last = Math.min(cursor + maxGamesToDisplay, gameButtons.size());
        int yOffset = TOP_MARGIN + 40;
        for (int i = first; i < last; i++) {
            ButtonControl b = gameButtons.get(i);
            b.move(LEFT_MARGIN, yOffset);
            b.paint(g);
            yOffset += 65;
        }
    }

    @Override
    public void onMouseExited() {
        super.onMouseExited();
        for (ButtonControl b : gameButtons) {
            b.onMouseExited();
        }
    }

    @Override
    public void onMouseMoved(Point p) {
        super.onMouseMoved(p);
        for (ButtonControl b : gameButtons) {
            b.onMouseMoved(p);
        }
    }

    @Override
    public boolean onMousePressed(Point p) {
        if (super.onMousePressed(p)) {
            return true;
        }
        for (ButtonControl b : gameButtons) {
            if (b.onMousePressed(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onMouseReleased(Point p) {
        if (super.onMouseReleased(p)) {
            return true;
        }
        for (ButtonControl b : gameButtons) {
            if (b.onMouseReleased(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == upButton) {
            cursor--;
        } else if (source == downButton) {
            cursor++;
        } else if (source == backButton) {
            ScreenManager.getInstance().setScreen(new MainMenuScreen());
        }
        cursor = Math.max(cursor, 0);
        cursor = Math.min(cursor, gameButtons.size() - 1);
        if (source instanceof LoadGameButton) {
            LoadGameButton sourceButton = (LoadGameButton)source;
            if (buttonPaths.containsKey(sourceButton)) {
                try {
                    Simulator s = new Simulator(SaveGame.load(buttonPaths.get(sourceButton)));
                    ScreenManager.getInstance().setScreen(new PlantScreen(s));
                } catch (JsonParseException ex) {
                    // ignore
                    ex.printStackTrace();
                } catch (IOException ex) {
                    // ignore
                    ex.printStackTrace();
                }
            }
        }
    }

    private void addGame(SaveGameDescription desc) {
        LoadGameButton button = new LoadGameButton(desc.username, desc.timestamp, 0, 0);
        gameButtons.add(button);
        buttonPaths.put(button, desc.path);
        button.addActionListener(this);
    }
}
