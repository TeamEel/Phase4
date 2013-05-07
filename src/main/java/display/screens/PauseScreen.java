package display.screens;

import display.Screen;

/**
 *
 * @author David
 */
public class PauseScreen extends MenuScreen {

    private final Screen previousScreen;

    public PauseScreen(Screen previous) {
        this.previousScreen = previous;
    }
}
