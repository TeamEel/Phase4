/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.controls;

import display.Asset;
import display.Control;
import eel.seprphase4.Simulator.PlantController;
import eel.seprphase4.Simulator.PlantStatus;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author James
 */
public class QuencherControl implements Control,ActionListener {
    private PlantStatus status;
    private PlantController control;
   
    private ImageControl quencher;

    public QuencherControl(PlantStatus status, PlantController control,
                         int x, int y) {
    
        this.status = status;
        this.control = control;
        
        this.quencher = new ImageControl(Asset.PlantQuencher,x,y);
        this.quencher.addActionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        if(!status.quencherUsed())
        {
            quencher.paint(g);
        }
    }

    @Override
    public void advance(int ms) {
        if(!status.quencherUsed())
        {
            quencher.advance(ms);
        }
    }

    @Override
    public void onMouseExited() {
        quencher.onMouseExited();
    }

    @Override
    public void onMouseMoved(Point point) {
        quencher.onMouseMoved(point);
    }

    @Override
    public boolean onMousePressed(Point point) {
        return quencher.onMousePressed(point);
    }

    @Override
    public boolean onMouseReleased(Point point) {
        return quencher.onMouseReleased(point);
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
        control.quenchReactor();
    }
}
