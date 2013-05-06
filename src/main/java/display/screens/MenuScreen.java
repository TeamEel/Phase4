/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.Asset;
import display.controls.ImageControl;
import display.Screen;

/**
 *
 * @author drm511
 */
public abstract class MenuScreen extends Screen {

    protected final static int LEFT_MARGIN = 700;
    protected final static int TOP_MARGIN = 290;
    
    public MenuScreen() {
        add(new ImageControl(Asset.MenuBackground, 0, 0), 0);
        add(new ImageControl(Asset.MenuLogo, 483, 92), 1);
    }
}
