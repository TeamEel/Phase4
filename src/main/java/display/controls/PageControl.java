/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.controls;

import display.Control;
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
public class PageControl implements Control, ActionListener {
    private ArrayList<Control> pages;
    private ButtonControl transportControl;
    private ButtonControl finishControl;
    private int currentPage;
    
    public PageControl(ButtonControl transportControl, ButtonControl finishControl) {
        pages = new ArrayList<Control>();
        
        currentPage = 0;
        
        this.transportControl = transportControl;
        this.finishControl = finishControl;
        
        this.finishControl.addActionListener(this);
        this.transportControl.addActionListener(this);
    }
    
    public void next() {
        if(currentPage<pages.size()-1) {
            currentPage++;
        }
    }
    
    public void add(Control control) { 
        pages.add(control);
    }
            
    @Override
    public void paint(Graphics g) {
        pages.get(currentPage).paint(g);
        if(currentPage<pages.size()-1) {
            transportControl.paint(g);
        }
        else {
            finishControl.paint(g);
        }
    }

    @Override
    public void advance(int ms) {
        pages.get(currentPage).advance(ms);
        
        if(currentPage<pages.size()-1) {
            transportControl.advance(ms);
        }
        else {
            finishControl.advance(ms);
        }
    }

    @Override
    public void onMouseExited() {
        transportControl.onMouseExited();
        finishControl.onMouseExited();
        pages.get(currentPage).onMouseExited();
    }

    @Override
    public void onMouseMoved(Point point) {
        if(currentPage<pages.size()-1) {
            transportControl.onMouseMoved(point);
        }
        else {
            finishControl.onMouseMoved(point);
        }
        pages.get(currentPage).onMouseMoved(point);
    }

    @Override
    public boolean onMousePressed(Point point) {
        if(currentPage<pages.size()-1) {
            if(transportControl.onMousePressed(point)) {
                return true;
            }
            
        }
        else {
            if(finishControl.onMousePressed(point)) {
                return true;
            }
           
        }
        return pages.get(currentPage).onMousePressed(point);
    }

    @Override
    public boolean onMouseReleased(Point point) {
        if(currentPage<pages.size()-1) {
            if(transportControl.onMouseReleased(point)) {
                return true;
            }
            
        }
        else {
            if(finishControl.onMouseReleased(point)) {
                return true;
            }
           
        }
        return pages.get(currentPage).onMouseReleased(point);
    }

    @Override
    public boolean onKeyTyped(KeyEvent e) {
        return false;
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
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.transportControl) {
            this.next();
        }
    }
    
    
}
