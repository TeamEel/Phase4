package display.saveload;

import java.awt.Color;

/**
 *
 * @author David
 */
public class LoadGameButtonDefault extends LoadGameButtonDrawable {

    public LoadGameButtonDefault(String username, String timestamp) {
        super(username, timestamp);
    }
    
    @Override
    protected Color color() {
        return Color.white;
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
