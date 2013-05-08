/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.gui.screens;

import eel.seprphase4.gui.Asset;
import eel.seprphase4.gui.controls.ImageControl;
import eel.seprphase4.gui.Screen;
import eel.seprphase4.gui.ScreenManager;
import eel.seprphase4.gui.controls.HotkeyControl;
import eel.seprphase4.gui.widgets.PlantWidget;
import eel.seprphase4.gui.widgets.ControlRodButtonsWidget;
import eel.seprphase4.gui.widgets.componentwidgets.PumpWidget;
import eel.seprphase4.gui.widgets.componentwidgets.QuencherWidget;
import eel.seprphase4.gui.widgets.componentwidgets.TurbineWidget;
import eel.seprphase4.gui.widgets.componentwidgets.ValveWidget;
import eel.seprphase4.gui.widgets.displaywidgets.CondenserPressureGaugeWidget;
import eel.seprphase4.gui.widgets.displaywidgets.CondenserThermometerWidget;
import eel.seprphase4.gui.widgets.displaywidgets.CondenserWaterLevelWidget;
import eel.seprphase4.gui.widgets.displaywidgets.EnergyGeneratedWidget;
import eel.seprphase4.gui.widgets.displaywidgets.ReactorPressureGaugeWidget;
import eel.seprphase4.gui.widgets.displaywidgets.ReactorThermometerWidget;
import eel.seprphase4.gui.widgets.displaywidgets.ReactorWaterLevelWidget;
import eel.seprphase4.Simulator.Simulator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author James
 */
public class PlantScreen extends Screen implements ActionListener {

    protected Simulator simulator;
    private HotkeyControl escapeHotkey;
    private PlantWidget plant;

    public PlantScreen(Simulator simulator) {
        this.simulator = simulator;
        escapeHotkey = new HotkeyControl(KeyEvent.VK_ESCAPE);
        escapeHotkey.addActionListener(this);
        plant = new PlantWidget(simulator);
        plant.addActionListener(this);
        add(plant, 0);

        add(new ImageControl(Asset.PlantDefaultWater, 0, 0), 0);

        add(new ReactorWaterLevelWidget(simulator, 278, 440), 1);
        add(new CondenserWaterLevelWidget(simulator, 956, 465), 1);
        add(new QuencherWidget(simulator, 136, 207), 1);

        add(new ImageControl(Asset.PlantBackground, 0, 0), 2);

        add(new EnergyGeneratedWidget(simulator, 95, 85), 2);

        add(new ReactorThermometerWidget(simulator, 550, 400), 10);
        add(new CondenserThermometerWidget(simulator, 850, 400), 10);

        add(new ReactorPressureGaugeWidget(simulator, 450, 300), 11);
        add(new CondenserPressureGaugeWidget(simulator, 900, 350), 11);

        add(new PumpWidget(simulator, 680, 550, 1), 3);
        add(new PumpWidget(simulator, 1168, 444, 2), 3);

        add(new ValveWidget(simulator, 1010, 300, 1, false), 3);
        add(new ValveWidget(simulator, 598, 115, 2, true), 3);

        add(new eel.seprphase4.gui.controls.FailedCondenserControl(simulator, simulator, 955, 511), 3);
        add(new TurbineWidget(simulator, 953, 85), 3);

        add(new eel.seprphase4.gui.controls.ControlRodsControl(simulator, simulator, 350, 376), 4);

        add(new ControlRodButtonsWidget(simulator, 150, 450), 5);
        add(new eel.seprphase4.gui.controls.ReactorWaterLevelAlarmControl(simulator, simulator, 620, 350), 5);
        add(escapeHotkey, 100);
    }

    public PlantScreen(String playerName) {
        this(new Simulator(playerName));
    }

    @Deprecated
    public PlantScreen() {
        this("bill");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == escapeHotkey) {
            pause();
        } else if (source == plant) {
            gameOver();
        }
    }

    protected void gameOver() {
        ScreenManager.getInstance().setScreen(new GameOverScreen(simulator));
    }

    protected void pause() {
        ScreenManager.getInstance().setScreen(new PauseScreen(this, simulator));
    }
}
