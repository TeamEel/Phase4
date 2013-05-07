/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import static display.Asset.MenuUpDefault;
import static display.Asset.PlantCondenserWaterLevelAnimation;
import static display.Asset.PlantReactorWaterLevelAnimation;
import static display.Asset.Turbine;
import static display.Asset.ValveOpeningDefault;
import display.drawable.Animation;
import display.drawable.Drawable;
import display.drawable.DrawableImage;
import display.drawable.RangeDrawable;

/**
 *
 * @author drm511
 */
public class DrawableFactory {

    public static Drawable create(Asset a) {
        switch (a) {
            case MenuBackground:
                return new DrawableImage("/menu/background.png");
            case MenuLogo:
                return new DrawableImage("/menu/logo.png");
            case SingleplayerDefault:
                return new DrawableImage("/menu/button_singleplayer.png");
            case SingleplayerOver:
                return new DrawableImage("/menu/button_singleplayer_sel.png");
            case SingleplayerPressed:
                return new DrawableImage("/menu/button_singleplayer_press.png");
            case MultiplayerDefault:
                return new DrawableImage("/menu/button_multiplayer.png");
            case MultiplayerOver:
                return new DrawableImage("/menu/button_multiplayer_sel.png");
            case MultiplayerPressed:
                return new DrawableImage("/menu/button_multiplayer_press.png");
            case LoadgameDefault:
                return new DrawableImage("/menu/button_load.png");
            case LoadgameOver:
                return new DrawableImage("/menu/button_load_sel.png");
            case LoadgamePressed:
                return new DrawableImage("/menu/button_load_press.png");
            case PlantBackground:
                return new DrawableImage("/plant/background.png");
            case PlantDefaultWater:
                return new DrawableImage("/plant/waterbaseline.png");
            case PumpDefault:
                return new Animation(Range.formatted("/plant/pump/s%02d.png", 1, 60), true, 20);
            case PumpOver:
                return new Animation(Range.formatted("/plant/pump_sel/s%02d.png", 1, 60), true, 20);
            case PumpFailed:
                return new DrawableImage("/plant/pumpfault.png");
            case PlantValveOpen:
                return new DrawableImage("/plant/valve/s01.png");
            case PlantValveClosed:
                return new DrawableImage("/plant/valve/s10.png");
            case ValveOpeningDefault:
                return new Animation(Range.formatted("/plant/valve/s%02d.png", 1, 10), false, 20);
            case ValveOpeningOver:
                return new Animation(Range.formatted("/plant/valve_sel/s%02d.png", 1, 10), false, 20);
            case ValveClosingDefault:
                return new Animation(Range.formatted("/plant/valve/s%02d.png", 10, 1), false, 20);
            case ValveClosingOver:
                return new Animation(Range.formatted("/plant/valve_sel/s%02d.png", 10, 1), false, 20);
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
            case Quencher:
                return new DrawableImage("/plant/quencher.png");
            case CondenserFailed:
                return new DrawableImage("/plant/failedcondenser.png");
            case Turbine:
                return new Animation(Range.formatted("/plant/turbine/s%02d.png", 1, 2), true, 80);
            case TurbineFailed:
                return new DrawableImage("/plant/failedturbine.png");
            case MenuScientists:
                return new DrawableImage("/menu/scientists.png");
            case PlantControlRods:
                return new DrawableImage("/plant/controlrods.png");
            case PlantReactorWaterLevelAnimation:
                return new Animation(Range.formatted("/plant/reactor/s%02d.png", 0, 10), false, 1);
            case PlantCondenserWaterLevelAnimation:
                return new Animation(Range.formatted("/plant/reactor/s%02d.png", 0, 10), false, 1);
            case TextFieldBackground:
                return new DrawableImage("/menu/textfield.png");
            case TextFieldSelectedBackground:
                return new DrawableImage("/menu/textfield_selected.png");
            case EnterName:
                return new DrawableImage("/menu/enter_name.png");
            case EnterPlayer1Name:
                return new DrawableImage("/menu/enter_player_1_name.png");
            case EnterPlayer2Name:
                return new DrawableImage("/menu/enter_player_2_name.png");
            case BackDefault:
                return new DrawableImage("/menu/back.png");
            case BackOver:
                return new DrawableImage("/menu/back_sel.png");
            case BackPressed:
                return new DrawableImage("/menu/back_press.png");
            case StartDefault:
                return new DrawableImage("/menu/start.png");
            case StartOver:
                return new DrawableImage("/menu/start_sel.png");
            case StartPressed:
                return new DrawableImage("/menu/start_press.png");
            case MenuUpDefault:
                return new DrawableImage("/menu/arrows/up.png");
            case MenuUpOver:
                return new DrawableImage("/menu/arrows/up_over.png");
            case MenuUpPressed:
                return new DrawableImage("/menu/arrows/up_press.png");
            case MenuDownDefault:
                return new DrawableImage("/menu/arrows/down.png");
            case MenuDownOver:
                return new DrawableImage("/menu/arrows/down_over.png");
            case MenuDownPressed:
                return new DrawableImage("/menu/arrows/down_press.png");
            default:
                throw new Error("Invalid asset requested");
        }
    }

    public static RangeDrawable createRange(Asset a, double min, double max) {
        switch (a) {
            case Exclamation:
                return new RangeDrawable(Range.formatted("/plant/exclaim/exclaim_%01d.png", 0, 8), min, max);
            case Thermometer:
                return new RangeDrawable(Range.formatted("/plant/thermometer/thermo_%02d.png", 0, 10), min, max);
            case Gauge:
                return new RangeDrawable(Range.formatted("/plant/gauge/gauge_small_%02d.png", 0, 9), min, max);
            case PlantReactorWaterLevelAnimation:
                return new RangeDrawable(Range.formatted("/plant/reactor/s%02d.png", 0, 10), min, max);
            case PlantCondenserWaterLevelAnimation:
                return new RangeDrawable(Range.formatted("/plant/reactor/s%02d.png", 0, 10), min, max);
            default:
                throw new Error("Invalid RangeDrawable requested");
        }
    }
}
