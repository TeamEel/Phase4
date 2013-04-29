/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.GUIInterface;

import eel.seprphase4.GUIInterface.Sprites.Menu.*;
import eel.seprphase4.GUIInterface.Sprites.SimpleSprite;
import eel.seprphase4.drawing.ImageLoader;
import eel.seprphase4.drawing.Screen;
import eel.seprphase4.drawing.SpriteCanvas;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;
import javax.swing.JOptionPane;

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
        super(ImageLoader.imageResource("/menu/background.png"));
        // Setup references.
        //this.context = context;
//       
//        canvas = new SpriteCanvas(ImageLoader.imageResource("/shared/white.png"));
//
//        canvas.setFrameInterval(10);
//        canvas.setScaleFactor(1);
//                add(canvas);
//        canvas.start();


        addSprite(new MenuBackgroundImage(), 0, 0);
        addSprite(new MenuLogo(), 483, 92);
        addSprite(new MenuButton("singleplayer"), 834, 384);
        addSprite(new MenuButton("multiplayer"), 834, 476);
        addSprite(new MenuButton("load"), 834, 573);
        /*
         // Load the background image.
         backgroundImage = ImageUtils.loadImage("menu.png");

         // Load the button image and setup the buttons.
         BufferedImage buttonImage = ImageUtils.loadImage("button.png");
         newGameButton = new PlantGUIElement(buttonImage, 200, 450, 1.0f);
         newMultiplayerGameButton = new PlantGUIElement(buttonImage, 200, 550, 1.0f);
         loadGameButton = new PlantGUIElement(buttonImage, 200, 650, 1.0f);

         // Add a mouse listener to allow this class to deal with mouse input.
         this.addMouseListener(this);
         */
    }

    private void addSprite(SimpleSprite sprite,
                           int x, int y) {
        sprite.addToScreen(this);
        sprite.moveTo(x, y);
    }
//    @Override
//    public void mouseClicked(MouseEvent click) {
//
//        // If the user clicked the new game button.
//        if (newGameButton.clicked(click)) {
//
//            // Get the name from an input dialog.
//            String name = JOptionPane.showInputDialog("Enter your name");
//
//            // If the name is unusable, do nothing with this mouse click.
//            if (name == null || name.equals("")) {
//                return;
//            }
//
//
//            // Set loading to true.
//            loading = true;
//
//            // The screen must be explicitly repainted here.
//            paint(this.getGraphics());
//
//    
//            context.transitionTo(new PlantScreen(context,name));
//            
//        }
//        
//        if (newMultiplayerGameButton.clicked(click)) {
//
//            // Get the name from an input dialog.
//            String name = JOptionPane.showInputDialog("Enter your name");
//
//            // If the name is unusable, do nothing with this mouse click.
//            if (name == null || name.equals("")) {
//                return;
//            }
//
//            // Set loading to true.
//            loading = true;
//
//            // The screen must be explicitly repainted here.
//            paint(this.getGraphics());
//
//         
//            context.transitionTo(new MultiplayerPlantScreen(context, name));
//            
//        }
//
//        if (loadGameButton.clicked(click)) {
//
//
//            // Set the window to the interface for loading games.
//            context.transitionTo(new LoadScreen(context));
//        }
//    }
}
