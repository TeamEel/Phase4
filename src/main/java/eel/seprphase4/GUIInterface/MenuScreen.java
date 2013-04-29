/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.GUIInterface;

import eel.seprphase4.GUIInterface.Sprites.Menu.*;
import eel.seprphase4.GUIInterface.Sprites.SimpleSprite;
import eel.seprphase4.drawing.Screen;
import eel.seprphase4.drawing.SpriteCanvas;
import java.awt.image.*;
import java.io.IOException;

/**
 * This is displayed at the start of the game and lets the player start or load a game.
 */
public class MenuScreen extends Screen {

    // The background image.
    private BufferedImage backgroundImage;
    // The two buttons.
    private PlantGUIElement newGameButton;
    private PlantGUIElement newMultiplayerGameButton;
    private PlantGUIElement loadGameButton;
    // Whether images are currently loading.
    private Boolean loading = false;
    SpriteCanvas canvas;

    public MenuScreen() throws IOException {
        super("/menu/background.png");
        addSprite(new MenuLogo(), 483, 92);
        addSprite(new MenuButton("singleplayer"), 834, 384);
        addSprite(new MenuButton("multiplayer"), 834, 476);
        addSprite(new MenuButton("load"), 834, 573);
    }

    private void addSprite(SimpleSprite sprite,
                           int x, int y) {
        sprite.addToScreen(this);
        sprite.moveTo(x, y);
    }

}
