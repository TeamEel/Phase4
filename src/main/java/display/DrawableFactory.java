/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import display.drawable.Drawable;
import display.drawable.DrawableImage;

/**
 *
 * @author drm511
 */
public class DrawableFactory {

    static Drawable create(Asset id) {
        switch (id) {
            case MenuBackground:
                return new DrawableImage("/menu/background.png");
            case MenuLogo:
                return new DrawableImage("/menu/logo.png");
            case SingleplayerDefault:
                return new DrawableImage("/menu/button_singleplayer.png");
            case SingleplayerOver:
                return new DrawableImage("/menu/button_singleplayer_sel.png");
            case SingleplayerPressed:
                return new DrawableImage("/menu/button_singleplayer.png");
            case MultiplayerDefault:
                return new DrawableImage("/menu/button_multiplayer.png");
            case MultiplayerOver:
                return new DrawableImage("/menu/button_multiplayer_sel.png");
            case MultiplayerPressed:
                //TODO: properly
                return new DrawableImage("/menu/button_multiplayer_press.png");
            case LoadgameDefault:
                return new DrawableImage("/menu/button_load.png");
            case LoadgameOver:
                return new DrawableImage("/menu/button_load_sel.png");
            case LoadgamePressed:
                //TODO: properly
                return new DrawableImage("/menu/button_load.png");
            default:
                throw new Error("Invalid asset requested");
        }
    }
}
