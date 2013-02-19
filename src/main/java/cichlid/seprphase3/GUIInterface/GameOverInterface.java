package cichlid.seprphase3.GUIInterface;

/**
 *
 * @author Tomasz
 * 
 */

import cichlid.seprphase3.Simulator.Simulator;
import java.awt.event.*;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class GameOverInterface extends JPanel implements MouseListener {

    AnimatedPlantGUIElement explosion;
    Button playAgainButton;
    Button leaveButton;
    GUIWindow parent;
    Simulator simulator;
    
    GameOverInterface(GUIWindow parent, Simulator simulator) {
        this.parent = parent;
        this.simulator = simulator;
        setUpComponent();
    }
   
    private void setUpComponent()
    {
    explosion = new AnimatedPlantGUIElement(false, "animations/explosion", null, null, 0, 0, 1.0f, 0, 0);
    playAgainButton = new Button("Play Again");
    leaveButton = new Button("Leave Game");
    playAgainButton.setLocation(200,500);
    leaveButton.setLocation(200,600);
    }
    
       @Override
   public void paintComponent(Graphics g) {
           
           g.drawImage(explosion.stepImage(),0,0,null);
           
   }
    
   @Override
   public void mouseClicked(MouseEvent e) 
   {
       if(e.getSource().equals(playAgainButton)) {
           parent.setWindow(new PlantInterface(simulator, simulator, simulator));    
       }
       
       if(e.getSource().equals(leaveButton)) {
           System.exit(0);
       }
   }
   
   @Override
   public void mouseExited(MouseEvent e)
   {
      
   }
   
   @Override
   public void mouseEntered(MouseEvent e)
   {
       
   }
   
   @Override
   public void mouseReleased(MouseEvent e)
   {
       
   }
   
   @Override
   public void mousePressed(MouseEvent e)
   {
       
   }
       
    
}
