/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.*;
import cichlid.seprphase3.GUIInterface.GUIWindow;
import cichlid.seprphase3.Simulator.GameManager;
import cichlid.seprphase3.Simulator.PlantController;
import cichlid.seprphase3.Simulator.PlantStatus;
import cichlid.seprphase3.Simulator.Simulator;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.html.Option;



public class MenuInterface extends JPanel implements MouseListener
{
    private BufferedImage backgroundImage;
    private JLabel intro;
    private Button New;
    private Button load;
    
    private GUIWindow parent;
    private Simulator simulator;
    private Boolean loading = false;
    
    
   public MenuInterface(GUIWindow _parent, Simulator _simulator)
   {
       parent = _parent;
       simulator = _simulator;
       setUpComponent();
       
   }
   
   private void setUpComponent()
   {
       backgroundImage = ImageUtils.loadImage("images/menu.png");
       setLayout(null);
       
       intro = new JLabel("<html>Hello and welcome to Nuke Dukem the nucler power plant simulator!");
       add(intro);
       intro.setBounds(100, 100, 200, 30);
       
       
       New = new Button("New Game");
       add(New);
       New.setBounds(100, 140, 65, 30);
       New.addMouseListener(this);
       
       
       load = new Button("Load Game");
       add(load);
       load.setBounds(180, 140, 70, 30);
       load.addMouseListener(this);
       
   }
   
    private void LoadScreen(Graphics2D g){
        g.drawString("LOADING", 600, 400);
    }
    
    private void EndLoadScreen(Graphics2D g){
        g.clearRect(0, 0, 1366, 768);    
    }
    
   
   @Override
   public void paintComponent(Graphics g) {
       g.drawImage(backgroundImage, 0, 0, null);
       
       if (loading == true) {
            g.setColor(Color.red);
            g.setFont(g.getFont().deriveFont(30.0f));
            g.drawString("LOADING", 500, 400);
       }
   }
   
   @Override
   public void mouseClicked(MouseEvent e) 
   {
       if(e.getSource().equals(New)){
           String name = null;
           while (name == null) {
               name = JOptionPane.showInputDialog("Enter your name");
           }
           
           loading = true;
           paint(this.getGraphics());
           simulator.setUsername(name);
           parent.setWindow(new PlantInterface(simulator, simulator, simulator));
       }
       
       if(e.getSource().equals(load)){
           parent.setWindow(new LoadInterface(parent, simulator, "name"));
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
