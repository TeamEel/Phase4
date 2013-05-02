/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.TextFieldControl;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author drm511
 */
public class SingleplayerStartScreen extends MenuScreen {

    public SingleplayerStartScreen() {
        System.out.println("in single player start");
        add(new TextFieldControl("Player 1", new Font("Courier New", Font.BOLD, 50), Color.white, 800, 300), 2);
    }
}
