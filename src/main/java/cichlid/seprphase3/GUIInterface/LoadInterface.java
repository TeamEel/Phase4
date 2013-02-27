/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.Persistence.FileSystem;
import cichlid.seprphase3.Persistence.SaveGame;
import cichlid.seprphase3.Simulator.Simulator;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * This interface allows games to be loaded from a list of games.
 */
public class LoadInterface extends BaseInterface implements MouseListener {

    // Automatically build a save path for saved games.
    public static String savePath() {
        return System.getProperty("user.home") +
               System.getProperty("file.separator") +
               "sepr.teameel.gamesaves" +
               System.getProperty("file.separator");
    }
    // Background Image.
    private BufferedImage backgroundImage;
    // Buttons and Labels
    PlantGUIElement menuButton;
    public ArrayList<PlantGUIElement> loadButtons;
    public ArrayList<String> savelbls;
    // Useful references
    public SaveGame saveGame;
    public Simulator simulator;
    public GUIWindow parent;
    // Maps a space on the screen to a particular save.
    Map<Rectangle, Integer> m;
    public int y;
    public int i;
    private String userName;

    public LoadInterface(GUIWindow _parent, Simulator _simulator, String _username) {
        y = 0;
        i = 0;

        simulator = _simulator;
        parent = _parent;
        userName = _username;

        backgroundImage = ImageUtils.loadImage("menu.png");

        BufferedImage buttonImage = ImageUtils.loadImage("button.png");
        menuButton = new PlantGUIElement(buttonImage, 1100, 80, 1.0f);

        loadButtons = new ArrayList<PlantGUIElement>();
        savelbls = new ArrayList<String>();

        m = new HashMap<Rectangle, Integer>();

        saveGame = new SaveGame();

        setUpComponents();

        this.addMouseListener(this);
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
            m.put(loadButtons.get(i).location, i);
        }
    }

    @Override
    public void mouseClicked(MouseEvent click) {

        if (clicked(menuButton, click)) {
            parent.setWindow(new MenuInterface(parent, simulator));
        }

        for (PlantGUIElement button : loadButtons) {
            if (clicked(button, click)) {
                int gameNumber = m.get(button.location);
                Simulator sim = new Simulator();
                sim.setUsername(userName);
                sim.loadGame(gameNumber);
                parent.runGame(sim);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }
}
