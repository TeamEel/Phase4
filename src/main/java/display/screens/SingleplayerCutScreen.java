/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.Asset;
import display.Screen;
import display.ScreenManager;
import display.controls.ButtonControl;
import display.controls.ImageControl;
import display.controls.PageControl;
import display.drawable.DrawableText;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author James
 */
public class SingleplayerCutScreen extends CutScreen {
    private final String player1Name;

    public SingleplayerCutScreen(String player1Name) {
        pages.add(player1Name +
                  " and your rival Comrad are Physics graduate students!\n" +
                  "They are always building their mad scientist inventions, \n" +
                  "always with the intent of outdoing each other! \n" +
                  "Whenever they aren’t inventing some insane creation, \n" +
                  "they are sabotaging the other’s projects!");

        pages.add("Looks like Comrad is up to their wacky antics again!\n" +
                  "you have decided to build a nuclear reactor for your project,\n" +
                  "and Comrad is determined not to be outdone!");

        pages.add("Your job is to generate as much power as possible using " + player1Name + "’s\n" +
                  "homebrew power plant!\n\n" +
                  "Watch out for Comrad though!\n" +
                  "He is determined to sabotage you at any cost!");

        pages.add(player1Name + ", generate power by spinning the turbine as fast as possible.\n" +
                  "Raise the control rods using the arrow buttons to heat the water faster.\n" +
                  "Increasing the steam production spins the turbine faster.\n" +
                  "Click the valves and pumps to turn them on and off.\n" +
                  "Press [Escape] to pause\n" +
                  "Keep an eye on the reactor temperature and pressure!\n" +
                  "Go too high and the reactor will MELT DOWN!");
        this.player1Name = player1Name;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == startButton) {
            ScreenManager.getInstance().setScreen(new PlantScreen(player1Name));
        } else if (source == exitButton) {
            ScreenManager.getInstance().setScreen(new MainMenuScreen());
        }
    }
}
