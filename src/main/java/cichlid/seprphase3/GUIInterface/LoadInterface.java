/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cichlid.seprphase3.GUIInterface;


import cichlid.seprphase3.Persistence.SaveGame; 
import cichlid.seprphase3.Simulator.FailureModel;
import cichlid.seprphase3.Simulator.PhysicalModel;
import cichlid.seprphase3.Simulator.Simulator;
import com.fasterxml.jackson.core.JsonParseException;
import javax.swing.JPanel;
import java.awt.Button;
import java.awt.Label;
import java.util.ArrayList;
import java.io.File;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoadInterface extends JPanel implements MouseListener
{
    
    public static String savePath() 
    {
        return System.getProperty("user.home") +
               System.getProperty("file.separator") +
               "sepr.teameel.gamesaves" +
               System.getProperty("file.separator");
    }
    
    public SaveGame saveGame;
    
    public Simulator simulator;
    private PhysicalModel physicalModel;
    private FailureModel failureModel;
    public GUIWindow parent;
    
    public ArrayList<Button> saveBut;
    public ArrayList<Label> savelbls;;
    public ArrayList<String> saves;
    
    public int y;
    public int i;
    
    
    private String userName;
    
    
    public LoadInterface(GUIWindow _parent, Simulator _simulator, String username)
    {
        y = 0;
        i = 0;
        
        simulator = _simulator;
        parent = _parent;
        userName = username;
        
        saveBut = new ArrayList<Button>();
        savelbls = new ArrayList<Label>();
        saves = new ArrayList<String>();
        
        saveGame = new SaveGame();
        
        setUpComponents();
    }
    
    public void setUpComponents()
    {
        setLayout(null);
        
        File saveDir = new File(savePath());
        File [] allFiles = saveDir.listFiles();
        
        
        
        for(File file : allFiles)
        {
            if (file.isFile()) {
    
            if (file.getName().matches("sepr.teameel." + userName + ".([0-9]+).nuke")) {
                
                y = y + 4;
                i = i + 1;
                
                String title = file.getName();
            
            
                saves.add(title);
                
               
                    String[] bits = title.split("\\.");
                    Timestamp t = new Timestamp(Long.parseLong(bits[3]));
                    Date d = new Date(t.getTime());
                    SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
                
                                                                 
            
            
                Button load = new Button("Load Game " + i);
                add(load);
                load.setBounds(230, (10*y), 80, 30);
                saveBut.add(load);
                load.addMouseListener(this);
            
                Label name = new Label (i + " " + userName + ": " + d);
                add(name);
                name.setBounds(50, (10*y), 170, 30);  
                savelbls.add(name);
                } 
            }
        }
            
        
    }
    
    public String[] listGames() {
        return saves.toArray(new String[saves.size()]);
        
    }
    
    @Override
    public void mouseClicked(MouseEvent click)
    {
        
        int n = saveBut.indexOf(click.getSource());
        
            try {
            SaveGame saveGame = SaveGame.load(listGames()[n]);
            this.physicalModel = saveGame.getPhysicalModel();
            this.failureModel = saveGame.getFailureModel();
            this.userName = saveGame.getUserName();
        } catch (JsonParseException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
    
        parent.setWindow(new PlantInterface(physicalModel, physicalModel, simulator));
         
                      
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
