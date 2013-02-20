/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.Persistence.FileSystem;
import cichlid.seprphase3.Persistence.SaveGame;
import cichlid.seprphase3.Simulator.FailureModel;
import cichlid.seprphase3.Simulator.PhysicalModel;
import cichlid.seprphase3.Simulator.Simulator;
import com.fasterxml.jackson.core.JsonParseException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Button;
import java.awt.Label;
import java.util.ArrayList;
import java.io.File;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadInterface extends BaseInterface implements MouseListener {

    public static String savePath() {
        return System.getProperty("user.home") +
               System.getProperty("file.separator") +
               "sepr.teameel.gamesaves" +
               System.getProperty("file.separator");
    }
    private BufferedImage backgroundImage;
    PlantGUIElement menuButton;
    PlantGUIElement loadButton;
    PlantGUIElement[] saves;
    String[] labels;
    public String saveLbl;
    public SaveGame saveGame;
    public Simulator simulator;
    private PhysicalModel physicalModel;
    private FailureModel failureModel;
    public GUIWindow parent;
    public ArrayList<PlantGUIElement> saveBut;
    public ArrayList<String> savelbls;
    Map<Rectangle, Integer> m;
    public int y;
    public int i;
    private String userName;

    public LoadInterface(GUIWindow _parent, Simulator _simulator, String username) {
        y = 0;
        i = 0;

        simulator = _simulator;
        parent = _parent;
        userName = username;

        backgroundImage = ImageUtils.loadImage("images/menu.png");

        BufferedImage buttonImage = ImageUtils.loadImage("images/button.png");
        menuButton = new PlantGUIElement(buttonImage, 1100, 50, 1.0f, 0, 0);

        saveBut = new ArrayList<PlantGUIElement>();
        savelbls = new ArrayList<String>();

        m = new HashMap<Rectangle, Integer>();

        saveGame = new SaveGame();

        setUpComponents();

        this.addMouseListener(this);
    }

    public void setUpComponents() {

        BufferedImage buttonImage = ImageUtils.loadImage("images/button.png");


        for (String string : listGames()) {
            y = y + 8;
            i = i + 1;


            String[] bits = string.split("\\.");
            Timestamp t = new Timestamp(Long.parseLong(bits[3]));
            Date d = new Date(t.getTime());

            SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");


            loadButton = new PlantGUIElement(buttonImage, 600, 10 * y, 1.0f, 0, 0);
            saveBut.add(loadButton);
            

            saveLbl = new String(i + " " + userName + ": " + d);
            savelbls.add(saveLbl);

        }

        saves = saveBut.toArray(new PlantGUIElement[saveBut.size()]);
        labels = savelbls.toArray(new String[savelbls.size()]);

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

        for (int z = 0; z < saves.length; z++) {
            g.drawString(labels[z], 10, saves[z].y() + 50);
            g.drawImage(saves[z].image, saves[z].x(), saves[z].y(), null);
            g.drawString("Load Game", saves[z].x() + 25, saves[z].y() + 50);
            m.put(saves[z].location, z);

        }
    }

    @Override
    public void mouseClicked(MouseEvent click) {

        if (menuButton.location.contains(click.getPoint())) {
            parent.setWindow(new MenuInterface(parent, simulator));
        }
        
        
        if (loadButton.location.contains(click.getPoint())) {
        int n = m.get(loadButton.location);

        Simulator sim = new Simulator();
        sim.setUsername(userName);
        sim.loadGame(n);
        parent.runGame(sim);
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
