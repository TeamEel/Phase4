package display.saveload;

import display.controls.ButtonControl;

/**
 *
 * @author David
 */
public class LoadGameButton extends ButtonControl {

    public LoadGameButton(String username, String timestamp, int x, int y) {
        super(new LoadGameButtonDefault(username, timestamp),
              new LoadGameButtonOver(username, timestamp),
              new LoadGameButtonPressed(username, timestamp),
              x, y);
    }
}
