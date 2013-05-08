/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.gui.screens;

import eel.seprphase4.gui.Asset;
import eel.seprphase4.gui.Screen;
import eel.seprphase4.gui.ScreenManager;
import eel.seprphase4.gui.controls.ButtonControl;
import eel.seprphase4.gui.controls.ImageControl;
import eel.seprphase4.gui.controls.PageControl;
import eel.seprphase4.gui.drawable.DrawableText;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author James
 */
public class MultiplayerCutScreen extends CutScreen implements ActionListener {

    private final String player1Name;
    private final String player2Name;

    public MultiplayerCutScreen(String player1Name, String player2Name) {

        pages.add(player1Name + " and " + player2Name + " are rival Physics graduate students!\n" +
                 "They are always building their mad scientist inventions, \n" +
                 "always with the intent of outdoing each other! \n" +
                 "Whenever they aren’t inventing some insane creation, \n" +
                 "they are sabotaging the other’s projects!");

        pages.add("Looks like " + player1Name + " and " + player2Name +
                 " are up to their wacky antics again!\n" +
                 player1Name + "has decided to build a nuclear reactor for their project,\n" +
                 "and " + player2Name + " is determined not to be outdone!");

        pages.add(player1Name + ", your job is to generate as much power as possible using your\n" +
                 "homebrew power plant!\n\n" +
                 "Watch out for " + player2Name + " though!\n" +
                 "He is determined to sabotage you at any cost!");

        pages.add(player1Name + ", generate power by spinning the turbine as fast as possible.\n" +
                 "Raise the control rods using the arrow buttons to heat the water faster.\n" +
                 "Increasing the steam production spins the turbine faster.\n" +
                 "Click the valves and pumps to turn them on and off.\n" +
                 "Press [Escape] to pause.\n" +
                 "Keep an eye on the reactor temperature and pressure!\n" +
                 "Go too high and the reactor will MELT DOWN!");

        pages.add(player2Name + ", press c to fail condenser, press 1 to fail pump 1 and 2 to fail pump 2.\n" +
                 "the more failures you induce, the harder it gets for you.\n" +
                 "You can enable random failures again by pressing [space].\n" +
                 "Press [Escape] pause.");

        add(pages, 5);

        add(new ImageControl(Asset.MenuScientists, -100, 200), 3);
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == startButton) {
            ScreenManager.getInstance().setScreen(new MultiplayerPlantScreen(player1Name, player2Name));
        } else if (source == exitButton) {
            ScreenManager.getInstance().setScreen(new MainMenuScreen());
        }
    }
}
