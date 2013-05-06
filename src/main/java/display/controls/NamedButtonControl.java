/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.controls;

import display.Asset;
import display.DrawableFactory;

/**
 *
 * @author James
 */
public class NamedButtonControl extends ButtonControl {
    private String name;
    
    public NamedButtonControl(String name,
                         Asset defaultAsset,
                         Asset mouseOverAsset,
                         Asset pressedAsset,
                         int x, int y) {
        
        super(DrawableFactory.create(defaultAsset),
             DrawableFactory.create(mouseOverAsset),
             DrawableFactory.create(pressedAsset),
             x, y);
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
