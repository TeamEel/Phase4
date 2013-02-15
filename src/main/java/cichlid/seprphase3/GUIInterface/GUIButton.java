package cichlid.seprphase3.GUIInterface;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class GUIButton extends JPanel {

    private String name;
    private BufferedImage UpImg;
    private BufferedImage DownImg;
    private int x;
    private int y;
    private boolean ClickState = false;

    MouseListener OnClick = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            ClickState = true;
            ActionWhenPressed();
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            ClickState = false;
            ActionWhenReleased();
        }
    };

    public GUIButton(String nameIn, String UpImgFilePath, String DownImgFilePath, int x, int y){
        try {
            name = nameIn;
            UpImg = ImageIO.read(new File(UpImgFilePath));
            DownImg = ImageIO.read(new File(DownImgFilePath));
            this.x = x;
            this.y = y;
        } catch (IOException e) {
            System.out.println("IOException in GUIButton " + nameIn + ": " + e.getMessage());
        }
    }
    
    abstract void ActionWhenPressed();
    abstract void ActionWhenReleased();
   
    void ImageShowingPanel() {
        addMouseListener(OnClick);
    }
    
    public void SetCoord(int x, int y) {
      this.x = x;
      this.y = y;
    }
  
    public void Update(Graphics g) {
        if (ClickState = true) {
            g.drawImage(DownImg,x,y,null);
        } else {
            g.drawImage(UpImg,x,y,null);
        }
    }
}
