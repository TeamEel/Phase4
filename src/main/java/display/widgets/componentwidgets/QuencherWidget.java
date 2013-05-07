/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.widgets.componentwidgets;

import display.Asset;
import display.DrawableFactory;
import display.controls.ImageControl;
import display.drawable.Drawable;
import display.widgets.ClickableWidget;
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
