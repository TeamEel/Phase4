/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cichlid.seprphase3.GUIInterface;


import javax.swing.JPanel;
import java.awt.Button;
import java.awt.Label;
import java.util.ArrayList;
import java.io.File;
import java.awt.event.*;


public class LoadInterface extends JPanel
{
    
    public static String savePath() 
    {
        return System.getProperty("user.home") +
               System.getProperty("file.separator") +
               "sepr.teameel.gamesaves" +
               System.getProperty("file.separator");
    }
    
    
    
    public ArrayList<Button> saves;
    public ArrayList<Label> savelbls;
    
    public int y;
    public int i;
    
    public LoadInterface()
    {
        y = 0;
        i = 0;
        setUpComponents();
    }
    
    public void setUpComponents()
    {
        setLayout(null);
        
        File saveDir = new File(savePath());
        File [] allFiles = saveDir.listFiles();
        
        
        
        for(File file : allFiles)
        {
            y = y + 4;
            i = i + 1;
            
            String title = file.toString();
            int length = title.length();
            int end = 50 + length;
            
            Button load = new Button("Load Game");
            add(load);
            load.setBounds(225, (10*y), 70, 30);
            
            Label name = new Label (i + " " + title.substring(51) + ":");
            add(name);
            name.setBounds(50, (10*y), 170, 30);  
        } 
    }
}
