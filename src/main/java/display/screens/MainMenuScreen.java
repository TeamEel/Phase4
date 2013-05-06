/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.Asset;
import display.controls.ButtonControl;
import display.controls.ImageControl;
import display.Screen;
import display.ScreenManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author drm511
 */
public class MainMenuScreen extends MenuScreen implements ActionListener {

    public final ButtonControl singlePlayer;
    public final ButtonControl multiPlayer;
    public final ButtonControl loadGame;

    public MainMenuScreen() {
        singlePlayer = new ButtonControl(Asset.SingleplayerDefault,
                                         Asset.SingleplayerOver,
                                         Asset.SingleplayerPressed,
                                         LEFT_MARGIN + 34, TOP_MARGIN + 84);

        multiPlayer = new ButtonControl(Asset.MultiplayerDefault,
                                        Asset.MultiplayerOver,
                                        Asset.MultiplayerPressed,
                                        LEFT_MARGIN + 34, TOP_MARGIN + 174);

        loadGame = new ButtonControl(Asset.LoadgameDefault,
                                     Asset.LoadgameOver,
                                     Asset.LoadgamePressed,
                                     LEFT_MARGIN + 34, TOP_MARGIN + 264);
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
            ScreenManager.getInstance().setScreen(new SingleplayerStartScreen());
        } else if (source == multiPlayer) {
            ScreenManager.getInstance().setScreen(new MultiplayerStartScreen());            
        } else if (source == loadGame) {
            ScreenManager.getInstance().setScreen(new LoadScreen());
        }
    }
}
