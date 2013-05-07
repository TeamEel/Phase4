package display.saveload;

import java.awt.Color;

/**
 *
 * @author David
 */
public class LoadGameButtonOver extends LoadGameButtonDrawable {

    private static final Color COLOR = new Color(255, 153, 0);

    public LoadGameButtonOver(String username, String timestamp) {
        super(username, timestamp);
    }

    @Override
    protected Color color() {
        return COLOR;
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
