package eel.seprphase4.gui.saveload;

import eel.seprphase4.gui.controls.ButtonControl;

/**
 *
 * @author David
 */
public class LoadGameButton extends ButtonControl {

    public LoadGameButton(String username, String timestamp, int x, int y) {
        super(new LoadGameButtonDefault(username, timestamp),
              new LoadGameButtonOver(username, timestamp),
              x, y);
    }
}
