/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.Simulator.Simulator;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.JOptionPane;

/**
 * This is displayed at the start of the game and lets the player start or load a game.
 */
public class MenuInterface extends BaseInterface implements MouseListener {

    // The background image.
    private BufferedImage backgroundImage;
    // The two buttons.
    private PlantGUIElement newGameButton;
    private PlantGUIElement loadGameButton;
    // The parent GUIWindow
    private GUIWindow parent;
    // The simulator to use.
    private Simulator simulator;
    // Whether images are currently loading.
    private Boolean loading = false;

    public MenuInterface(GUIWindow _parent, Simulator _simulator) {
        // Setup references.
        parent = _parent;
        simulator = _simulator;

        // Load the background image.
        backgroundImage = ImageUtils.loadImage("menu.png");

        // Load the button image and setup the buttons.
        BufferedImage buttonImage = ImageUtils.loadImage("button.png");
        newGameButton = new PlantGUIElement(buttonImage, 200, 500, 1.0f);
        loadGameButton = new PlantGUIElement(buttonImage, 200, 600, 1.0f);

        // Add a mouse listener to allow this class to deal with mouse input.
        this.addMouseListener(this);
    }

    /**
     * Draws the interface to the screen.
     */
    @Override
    public void paintComponent(Graphics g) {
        // Draw the background first since it is under everything.
        g.drawImage(backgroundImage, 0, 0, null);

        // Enable image anti-aliasing for prettier text.
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(new Font("Impact", Font.BOLD, 30));
        g.setColor(Color.BLACK);

        // Draw the buttons.
        g.drawImage(newGameButton.getImage(), newGameButton.x(), newGameButton.y(), null);
        g.drawString("New Game", newGameButton.x() + 30, newGameButton.y() + 47);
        g.drawImage(loadGameButton.getImage(), loadGameButton.x(), loadGameButton.y(), null);
        g.drawString("Load Game", loadGameButton.x() + 25, loadGameButton.y() + 50);

        // If the images are currently loading, show this.
        if (loading == true) {
            g.setColor(Color.WHITE);
            g.drawString("Loading...", 450, 550);
        }
    }

    @Override
    public void mouseClicked(MouseEvent click) {

        // If the user clicked the new game button.
        if (newGameButton.clicked(click)) {

            // Get the name from an input dialog.
            String name = JOptionPane.showInputDialog("Enter your name");

            // If the name is unusable, do nothing with this mouse click.
            if (name == null || name.equals("")) {
                return;
            }

            // Set the username of the simulator to the user's name.
            simulator.setUsername(name);

            // Set loading to true.
            loading = true;

            // The screen must be explicitly repainted here.
            paint(this.getGraphics());

            // Start the game.
            parent.state = GameState.Running;
            parent.setWindow(new PlantInterface(parent, simulator, simulator, simulator));
        }

        if (loadGameButton.clicked(click)) {

            // Get the name to index saved games by.
            String name = JOptionPane.showInputDialog("Enter your name");

            // If the name is unusable, do nothing with this mouse click.
            if (name == null || name.equals("")) {
                return;
            }

            // Set the window to the interface for loading games.
            parent.setWindow(new LoadInterface(parent, simulator, name));
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
