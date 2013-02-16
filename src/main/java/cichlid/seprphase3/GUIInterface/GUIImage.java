/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cichlid.seprphase3.GUIInterface;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Tomasz
 */

public abstract class GUIImage extends JPanel {

public String name;
private BufferedImage Img;
private int x;
private int y;

    MouseListener OnClick = new MouseAdapter() {
        @Override
    public void mouseClicked(MouseEvent e) {
        ActionWhenPressed();
        
    }
        
        @Override
    public void mouseReleased(MouseEvent e){
        ActionWhenReleased();   
    }
    };

    public GUIImage(String nameIn, String ImgPath, int x, int y){
        try
        {
            name = nameIn;
            Img = ImageIO.read(new File(ImgPath));
            this.x = x;
            this.y = y;
        }
        catch (IOException e) {
            System.out.println("IOException in GUIButton " + nameIn);
        }
            
    }
    
    abstract void ActionWhenPressed();
    abstract void ActionWhenReleased();
   
    void ImageShowingPanel() {
        addMouseListener(OnClick);
    }
    
    public void SetCoord(int x, int y){
      this.x = x;
      this.y = y;
    }
  
    public void Update(Graphics g){
        g.drawImage(Img,x,y,null);          
    }
    
}