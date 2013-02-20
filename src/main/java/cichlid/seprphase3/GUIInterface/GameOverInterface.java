package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.Utilities.Energy;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class GameOverInterface extends JPanel implements MouseListener {

    Animation explosion;
    public PlantGUIElement playAgainButton;
    public Boolean block = true;
    
    Energy powerGenerated;
    String name;

    GameOverInterface(Animation preloadedExplosion, Energy powerGenerated, String name) {
        explosion = preloadedExplosion;
        explosion.reset();
        this.powerGenerated = powerGenerated;
        this.name = name;
        
        BufferedImage buttonImage = ImageUtils.loadImage("images/button.png");
        playAgainButton = new PlantGUIElement(buttonImage, 580, 550, 1.2f, 0, 0);
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        if(!explosion.isAtEnd()) {
            g.drawImage(explosion.stepImage(), 0, 0, null);
        } else {
            g.drawImage(explosion.endImage(), 0, 0, null);
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Impact", Font.BOLD, 40));
            g.drawString("Game over!", 550, 200);
            g.drawString("Oh no! " + name + ", you blew up the Reactor!", 360, 300);
            g.drawString("However, you generated: " + powerGenerated.toString() + " power before you did!", 210, 400);
            g.drawString("Click anywhere to start a new game!", 320, 450);
            g.setColor(Color.BLACK);
            g.drawImage(playAgainButton.image, playAgainButton.x(), playAgainButton.y(), null);
            g.drawString("Play Again!", playAgainButton.x() + 20, playAgainButton.y() + 60);
        }
        
        
    }

    @Override
    public void mouseClicked(MouseEvent click) {
        if (playAgainButton.location.contains(click.getPoint())) {
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
