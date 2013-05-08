/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.gui.widgets.componentwidgets;

import eel.seprphase4.gui.Asset;
import eel.seprphase4.gui.DrawableFactory;
import eel.seprphase4.gui.controls.ImageControl;
import eel.seprphase4.gui.drawable.Drawable;
import eel.seprphase4.gui.widgets.ClickableWidget;
import eel.seprphase4.Simulator.PlantController;
import eel.seprphase4.Simulator.PlantStatus;
import eel.seprphase4.Simulator.Simulator;
import java.awt.Graphics;

/**
 *
 * @author James
 */
public class QuencherWidget extends ClickableWidget {

    private PlantStatus status;
    private PlantController control;
    private Drawable quencherWater;

    public QuencherWidget(Simulator simulator, int x, int y) {
        super(simulator, x, y, DrawableFactory.create(Asset.Quencher).hitBox(x, y));
        this.quencherWater = DrawableFactory.create(Asset.Quencher);
    }

    @Override
    public void paint(Graphics g) {
        if (!simulator.quencherUsed()) {
            quencherWater.draw(g, x, y);
        }
    }

    @Override
    public void advance(int ms) {
        // ignore
    }

    @Override
    protected void onClicked() {
        if (!simulator.quencherUsed()) {
            simulator.quenchReactor();
        }
    }
}
