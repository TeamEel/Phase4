/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import display.drawable.Animation;
import display.drawable.Drawable;
import display.drawable.DrawableImage;

/**
 *
 * @author drm511
 */
public class DrawableFactory {

    public static Drawable create(Asset id) {
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
                return new Animation(Range.Formatted("/plant/pump/s%02d.png", 1,60), true,20);
            case PlantFailedPump:
                return new DrawableImage("/plant/pumpfault.png");
            case PlantValveOpen:
                return new DrawableImage("/plant/valve/s01.png");
            case PlantValveClosed:
                return new DrawableImage("/plant/valve/s10.png");
            case PlantValveAnimation:
                return new Animation(Range.Formatted("/plant/valve/s%02d.png", 1,10), false,20);
            case PlantUpArrow:
                return new DrawableImage("/plant/uparrow.png");
            case PlantUpArrowOver:
                return new DrawableImage("/plant/uparrow_sel.png");
            case PlantDownArrow:
                return new DrawableImage("/plant/downarrow.png");
            case PlantDownArrowOver:
                return new DrawableImage("/plant/downarrow_sel.png");
            case PlantDanger:
                return new DrawableImage("/plant/danger.png");
            case PlantQuencher:
                return new DrawableImage("/plant/quencher.png");
            case PlantReactorWaterLevelAnimation:
                return new Animation(Range.Formatted("/plant/reactor/s%02d.png", 0,10), false,1);
            case PlantCondenserWaterLevelAnimation:
                return new Animation(Range.Formatted("/plant/reactor/s%02d.png", 0,10), false,1);
                    
            default:
                throw new Error("Invalid asset requested");
        }
    }
}
