/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import display.drawable.Drawable;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public class AnimationControl implements Control {

    private Drawable image;
   
    private final ArrayList<ActionListener> actionListeners;
    private HitBox hitBox;
    
    int x, y;

    public AnimationControl(Drawable resource, int x, int y) {
        this.image = resource;
        this.x = x;
        this.y = y;
        
        
        this.actionListeners = new ArrayList<ActionListener>();
        this.hitBox = this.image.hitBox(x, y);
    }
    
    public AnimationControl(Asset asset, int x, int y) {
        this(DrawableFactory.create(asset), x, y);
    }

    @Override
    public void paint(Graphics g) {
        image.draw(g, x, y);
    }

    @Override
    public boolean onKeyPressed(KeyEvent e) {
        return false;
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        return false;
    }

    @Override
    public boolean onKeyTyped(KeyEvent e) {
        return false;
    }

    @Override
    public void advance(int ms) {
        
            image.advance(ms);
        
    }
    

    public void reverse(int ms) {
        
            image.advance(-ms);
        
    }
    
  
    

    public void addActionListener(ActionListener al) {
        actionListeners.add(al);
    }


    @Override
    public void onMouseExited() {
     
    }

    @Override
    public void onMouseMoved(Point point) {
        
    }

    @Override
    public boolean onMousePressed(Point point) {
        if (hitBox.contains(point)) {
            
            return true;
        }
        
        return false;
    }

    @Override
    public boolean onMouseReleased(Point point) {
        if (hitBox.contains(point)) {
            ActionEvent ae = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "");
            for (ActionListener al : actionListeners) {
                al.actionPerformed(ae);
            }
            
            return true;
        }
        return false;
    }

}
