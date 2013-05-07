/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.Asset;
import display.controls.ImageControl;
import display.Screen;
import display.controls.PlantControl;
import display.widgets.ControlRodButtonsWidget;
import display.widgets.componentwidgets.PumpWidget;
import display.widgets.componentwidgets.QuencherWidget;
import display.widgets.componentwidgets.TurbineWidget;
import display.widgets.componentwidgets.ValveWidget;
import display.widgets.displaywidgets.CondenserPressureGaugeWidget;
import display.widgets.displaywidgets.CondenserThermometerWidget;
import display.widgets.displaywidgets.CondenserWaterLevelWidget;
import display.widgets.displaywidgets.ReactorPressureGaugeWidget;
import display.widgets.displaywidgets.ReactorThermometerWidget;
import display.widgets.displaywidgets.ReactorWaterLevelWidget;
import eel.seprphase4.Simulator.Simulator;

/**
 *
 * @author James
 */
public class PlantScreen extends Screen {

    protected Simulator s;

    public PlantScreen(String playerName) {
        super();
        s = new Simulator(playerName);

        add(new PlantControl(s, s), 0);

        add(new ImageControl(Asset.PlantDefaultWater, 0, 0), 0);

        add(new ReactorWaterLevelWidget(s, 278, 440), 1);
        add(new CondenserWaterLevelWidget(s, 956, 465), 1);
        add(new QuencherWidget(s, 136, 207), 1);


        add(new ImageControl(Asset.PlantBackground, 0, 0), 2);

        add(new ReactorThermometerWidget(s, 550, 400), 10);
        add(new CondenserThermometerWidget(s, 850, 400), 10);
        
        add(new ReactorPressureGaugeWidget(s, 450, 300), 11);
        add(new CondenserPressureGaugeWidget(s, 900, 350), 11);
        
        add(new PumpWidget(s, 680, 550, 1), 3);
        add(new PumpWidget(s, 1168, 444, 2), 3);

        add(new ValveWidget(s, 1010, 300, 1, false), 3);
        add(new ValveWidget(s, 598, 115, 2, true), 3);

        add(new display.controls.FailedCondenserControl(s, s, 955, 511), 3);
        add(new TurbineWidget(s, 953, 85), 3);
        
        add(new display.controls.ControlRodsControl(s, s, 350, 376), 4);

        add(new ControlRodButtonsWidget(s, 150, 450), 5);
        add(new display.controls.ReactorWaterLevelAlarmControl(s, s, 50, 50), 5);
    }

    @Deprecated
    public PlantScreen() {
        this("bill");
    }
}
