package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.Utilities.Energy;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * This interface is shown when the game is over.
 */
public class GameOverInterface extends BaseInterface implements MouseListener {

    // This animation is used to show the nuclear explosion.
    private Animation explosion;
    
    // This button is to allow the player to start a new game.
    private  PlantGUIElement playAgainButton;
    
    // This is polled by the GUIWindow - when it is set to false, the GameOverInterface is no
    // longer displayed and the menu is displayed instead.
    public Boolean block = true;
    
    // The variables passed in from the simulator, to be displayed on the GameOverScreen.
    private Energy powerGenerated;
    private String name;

    /**
     * Creates a new GameOverInterface.
     * @param preloadedExplosion        The explosion animation.
     * @param powerGenerated            The power generated in the previous game.
     * @param name                      The name of the player.
     */
    public GameOverInterface(Animation preloadedExplosion, Energy powerGenerated, String name) {
        explosion = preloadedExplosion;
        
        // Make sure that the explosion plays from the start.
        explosion.reset();
        
        // Set the misc. variables.
        this.powerGenerated = powerGenerated;
        this.name = name;
        
        // Load the button image for the button and make the button.
        BufferedImage buttonImage = ImageUtils.loadImage("images/button.png");
        playAgainButton = new PlantGUIElement(buttonImage, 580, 550, 1.2f, 0, 0);
        
        // Add the mouse listener which allows this interface to accept mouse input.
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        
        // Draw the explosion.
        g.drawImage(explosion.stepImage(), 0, 0, null);
        
        // If the explosion is over, show the text and the buttons.
        if(explosion.isAtEnd()) {
            
            // Allow text anti-alialising for prettier text and set the font.
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Impact", Font.BOLD, 40));
            
            // Draw the text.
            g.drawString("Game over!", 550, 200);
            g.drawString("Oh no! " + name + ", you blew up the Reactor!", 360, 300);
            g.drawString("However, you generated: " + powerGenerated.toString() + " power before you did!", 210, 400);
            g.drawString("Click to start a new game!", 350, 450);
            
            // Draw the button.
            g.setColor(Color.BLACK);
            g.drawImage(playAgainButton.image, playAgainButton.x(), playAgainButton.y(), null);
            g.drawString("Play Again!", playAgainButton.x() + 20, playAgainButton.y() + 60);
        }
        
        
    }

    /**
     * Called when the mouse is clicked on the screen.
     * @param click         The click event, containing the location.
     */
    @Override
    public void mouseClicked(MouseEvent click) {
        if( clicked(playAgainButton, click) ) {
            block = false;
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
