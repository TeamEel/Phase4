/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.GUIInterface;

import eel.seprphase4.Persistence.FileSystem;
import eel.seprphase4.Persistence.SaveGame;
import eel.seprphase4.Simulator.Simulator;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * This interface allows games to be loaded from a list of games.
 */
public class LoadScreen extends GameScreen {


    // Background Image.
    private BufferedImage backgroundImage;
    // Buttons and Labels
    PlantGUIElement menuButton;
    private ArrayList<PlantGUIElement> loadButtons;
    private ArrayList<String> savelbls;
    // Useful references
    private SaveGame saveGame;
    private Simulator simulator;
    // Maps a space on the screen to a particular save.
    Map<PlantGUIElement, Integer> m;
    private int y;
    private int i;
    private String userName;

    public LoadScreen() {
        y = 0;
        i = 0;

        //TODO: Set username.
        userName = "";
        backgroundImage = ImageUtils.loadImage("menu.png");

        BufferedImage buttonImage = ImageUtils.loadImage("button.png");
        menuButton = new PlantGUIElement(buttonImage, 1100, 80, 1.0f);

        loadButtons = new ArrayList<PlantGUIElement>();
        savelbls = new ArrayList<String>();

        m = new HashMap<PlantGUIElement, Integer>();

        saveGame = new SaveGame();

        setUpComponents();

//        this.addMouseListener(this);
    }

    public void setUpComponents() {

        BufferedImage buttonImage = ImageUtils.loadImage("button.png");

        for (String string : listGames()) {
            y = y + 8;
            i = i + 1;

            PlantGUIElement loadButton = new PlantGUIElement(buttonImage, 600, 10 * y, 1.0f);
            loadButtons.add(loadButton);


            String[] bits = string.split("\\.");
            Timestamp t = new Timestamp(Long.parseLong(bits[3]));
            Date d = new Date(t.getTime());

            String saveLbl = i + " " + userName + ": " + d;
            savelbls.add(saveLbl);
        }
    }

    public String[] listGames() {
        return FileSystem.listSaveGames(userName);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);

        g.setFont(new Font("Impact", Font.BOLD, 30));
        g.setColor(Color.BLACK);

        g.drawImage(menuButton.image, menuButton.x(), menuButton.y(), null);
        g.drawString("Main Menu", menuButton.x() + 30, menuButton.y() + 47);

        for (int i = 0; i < loadButtons.size(); i++) {
            g.drawString(savelbls.get(i), 70, loadButtons.get(i).y() + 50);
            g.drawImage(loadButtons.get(i).image, loadButtons.get(i).x(), loadButtons.get(i).y(), null);
            g.drawString("Load Game", loadButtons.get(i).x() + 25, loadButtons.get(i).y() + 50);
            m.put(loadButtons.get(i), i);
        }
    }
    
    // Automatically build a save path for saved games.
    public static String savePath() {
        return System.getProperty("user.home") +
               System.getProperty("file.separator") +
               "sepr.teameel.gamesaves" +
               System.getProperty("file.separator");
    }
//    @Override
//    public void mouseClicked(MouseEvent click) {
//
//        /*
//        if (menuButton.clicked(click)) {
//            context.setWindow(new MenuScreen(parent, simulator));
//        }
//
//        for (PlantGUIElement button : loadButtons) {
//            if (button.clicked(click)) {
//                int gameNumber = m.get(button);
//                Simulator sim = new Simulator();
//                sim.setUsername(userName);
//                sim.loadGame(gameNumber);
//                context.runGame(sim);
//            }
//        }
//        */
//    }
}
