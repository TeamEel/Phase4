/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.gui.screens;

import eel.seprphase4.gui.Asset;
import eel.seprphase4.gui.controls.ImageControl;
import eel.seprphase4.gui.Screen;
import eel.seprphase4.gui.controls.ButtonControl;
import java.awt.event.ActionListener;

/**
 *
 * @author drm511
 */
public abstract class MenuScreen extends Screen implements ActionListener {

    protected final static int LEFT_MARGIN = 700;
    protected final static int TOP_MARGIN = 290;
    
    public MenuScreen() {
        add(new ImageControl(Asset.MenuBackground, 0, 0), 0);
        add(new ImageControl(Asset.MenuLogo, 483, 92), 1);
    }
    
    protected void addButton(ButtonControl button) {
        add(button, 2);
        button.addActionListener(this);
    }
}
