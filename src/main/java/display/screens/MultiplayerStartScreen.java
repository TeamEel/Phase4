/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.Asset;
import display.ScreenManager;
import display.controls.ButtonControl;
import display.controls.ImageControl;
import display.controls.TextFieldControl;
import static display.screens.MenuScreen.LEFT_MARGIN;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author drm511
 */
public class MultiplayerStartScreen extends MenuScreen implements ActionListener {

    private final TextFieldControl player1Name;
    private final TextFieldControl player2Name;
    private final ButtonControl backButton;
    private final ButtonControl startButton;

    public MultiplayerStartScreen() {
        final ImageControl enterPlayer1Name = new ImageControl(Asset.EnterPlayer1Name, LEFT_MARGIN, TOP_MARGIN);
        final ImageControl enterPlayer2Name = new ImageControl(Asset.EnterPlayer2Name, LEFT_MARGIN, TOP_MARGIN + 150);
        player1Name = new TextFieldControl("Player 1", LEFT_MARGIN, TOP_MARGIN + 50);
        player2Name = new TextFieldControl("Player 2", LEFT_MARGIN, TOP_MARGIN + 200);
        backButton = new ButtonControl(Asset.BackDefault,
                                       Asset.BackOver,
                                       Asset.BackPressed,
                                       LEFT_MARGIN, TOP_MARGIN + 350);
        startButton = new ButtonControl(Asset.StartDefault,
                                        Asset.StartOver,
                                        Asset.StartPressed,
                                        LEFT_MARGIN + 400, TOP_MARGIN + 350);
        add(enterPlayer1Name, 2);
        add(enterPlayer2Name, 2);
        add(player1Name, 2);
        add(player2Name, 2);
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
            ScreenManager.getInstance().setScreen(new MultiplayerCutScreen(player1Name.getText(),
                                                                             player2Name.getText()));
        }
    }
}
