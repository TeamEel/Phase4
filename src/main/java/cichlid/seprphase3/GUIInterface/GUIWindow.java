/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cichlid.seprphase3.GUIInterface;

import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author tr614
 */
public class GUIWindow {
    
private String title;
private int height;
private int width;
private java.awt.Frame frame;
    
    public GUIWindow() {
        title = "Title";
        height = 100;
        width = 100;
        frame = new Frame(title);
        frame.setSize(width,height);
        frame.setVisible(true);
        frame.addWindowListener(
        new WindowAdapter() {
                public void windowClosing(WindowEvent we){
                System.exit(0);    
            }
                
                public void windowMinimizing(WindowEvent we){
                System.exit(0);    
            }
        });
    }
    
    public GUIWindow(String titleIn, int heightIn, int widthIn) {
        title = titleIn;
        height = heightIn;
        width = widthIn;
        frame = new Frame(title);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.addWindowListener(
        new WindowAdapter() {
                public void windowClosing(WindowEvent we){
                System.exit(0);    
            }
        });
    }
    
    
    
}
