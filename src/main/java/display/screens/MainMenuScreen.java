/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.Asset;
import display.ButtonControl;
import display.ImageControl;
import display.Screen;
import display.ScreenManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author drm511
 */
public class MainMenuScreen extends MenuScreen implements ActionListener {

    public ButtonControl singlePlayer;
    public ButtonControl multiPlayer;
    public ButtonControl loadGame;

    public MainMenuScreen() {
        singlePlayer = new ButtonControl(Asset.SingleplayerDefault,
                                         Asset.SingleplayerOver,
                                         Asset.SingleplayerPressed,
                                         834, 384);

        multiPlayer = new ButtonControl(Asset.MultiplayerDefault,
                                        Asset.MultiplayerOver,
                                        Asset.MultiplayerPressed,
                                        834, 474);

        loadGame = new ButtonControl(Asset.LoadgameDefault,
                                     Asset.LoadgameOver,
                                     Asset.LoadgamePressed,
                                     834, 564);
        singlePlayer.addActionListener(this);
        multiPlayer.addActionListener(this);
        loadGame.addActionListener(this);
        add(singlePlayer, 2);
        add(multiPlayer, 2);
        add(loadGame, 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == singlePlayer) {
            System.out.println("changing to single player start screen");
            ScreenManager.getInstance().setScreen(new SingleplayerStartScreen());
        } else if (source == multiPlayer) {
            ScreenManager.getInstance().setScreen(new MultiplayerStartScreen());            
        } else if (source == loadGame) {
            ScreenManager.getInstance().setScreen(new LoadScreen());
        }
    }
}
