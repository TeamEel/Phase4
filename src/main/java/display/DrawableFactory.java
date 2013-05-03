/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import display.drawable.Animation;
import display.drawable.Drawable;
import display.drawable.DrawableImage;

import static eel.seprphase4.drawing.builders.BuildAnimation.*;
import static eel.seprphase4.drawing.builders.BuildAnimationSet.buildAnimationSet;
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
            case PlantBackground:
                return new DrawableImage("/plant/background.png");
            case PlantDefaultWater:
                return new DrawableImage("/plant/waterbaseline.png");  
            case PlantPump:
                return new Animation(Range.Formatted("/plant/pump/s%02d.png", 1,60), true,22);
            case PlantFailedPump:
                return new DrawableImage("/plant/pumpfault.png");
            default:
                throw new Error("Invalid asset requested");
        }
    }
}
