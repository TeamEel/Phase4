package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.Utilities.Energy;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;
import javax.swing.JPanel;

public class GameOverInterface extends JPanel implements MouseListener {

    Animation explosion;
    Button playAgainButton;
    Button leaveButton;
    public Boolean block = true;
    
    Energy powerGenerated;

    GameOverInterface(Animation preloadedExplosion, Energy powerGenerated) {
        explosion = preloadedExplosion;
        playAgainButton = new Button("Play Again");
        leaveButton = new Button("Leave Game");
        playAgainButton.setLocation(200, 500);
        leaveButton.setLocation(200, 600);
        this.powerGenerated = powerGenerated;
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
            g.drawString("Oh no! You blew up the Reactor!", 340, 300);
            g.drawString("However, you generated: " + powerGenerated.toString() + " power before you did!", 230, 500);
            g.drawString("Click anywhere to start a new game!", 300, 600);
        }
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //if (e.getSource().equals(playAgainButton)) {
        block = false;
        //}

        //if (e.getSource().equals(leaveButton)) {
        //    System.exit(0);
        //}
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
