
package eel.seprphase4.gui.saveload;

import java.awt.Color;

/**
 *
 * @author David
 */
public class LoadGameButtonOver extends LoadGameButtonDrawable {

    public LoadGameButtonOver(String username, String timestamp) {
        super(username, timestamp);
    }
    
    @Override
    protected Color color() {
        return new Color(255, 155, 0);
    }

    @Override
    protected int xOffset() {
        return 0;
    }

    @Override
    protected int yOffset() {
        return 0;
    }
    
}
