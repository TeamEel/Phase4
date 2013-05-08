package eel.seprphase4.gui.widgets.componentwidgets;

import eel.seprphase4.gui.Asset;
import eel.seprphase4.gui.DrawableFactory;
import eel.seprphase4.gui.HitBox;
import eel.seprphase4.gui.drawable.Drawable;
import eel.seprphase4.gui.widgets.ClickableWidget;
import eel.seprphase4.gui.widgets.ClickableWidget;
import eel.seprphase4.Simulator.CannotRepairException;
import eel.seprphase4.Simulator.KeyNotFoundException;
import eel.seprphase4.Simulator.Simulator;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author James
 */
public class PumpWidget extends ClickableWidget {

    private final int pumpNumber;
    private final Drawable pumpDefault;
    private final Drawable pumpOver;
    private final Drawable pumpFailed;

    public PumpWidget(Simulator simulator, int x, int y, int pumpNumber) {
        super(simulator, x, y, DrawableFactory.create(Asset.PumpDefault).hitBox(x, y));
        this.pumpNumber = pumpNumber;
        this.pumpDefault = DrawableFactory.create(Asset.PumpDefault);
        this.pumpOver = DrawableFactory.create(Asset.PumpOver);
        this.pumpFailed = DrawableFactory.create(Asset.PumpFailed);
    }

    @Override
    public void paint(Graphics g) {
        if (simulator.pumpHasFailed(pumpNumber)) {
            pumpFailed.draw(g, x, y);
        } else if (mouseIsOver()) {
            pumpOver.draw(g, x, y);
        } else {
            pumpDefault.draw(g, x, y);
        }
    }

    @Override
    public void onClicked() {
        try {
            if (simulator.pumpHasFailed(pumpNumber)) {
                simulator.repairPump(pumpNumber);
            } else {
                simulator.changePumpState(pumpNumber, !simulator.pumpIsOn(pumpNumber));
            }
        } catch (CannotRepairException e) {
            // ignore
            e.printStackTrace();
        } catch (KeyNotFoundException e) {
            // ignore
            e.printStackTrace();
        }
    }

    @Override
    public void advance(int ms) {
        if (simulator.pumpHasFailed(pumpNumber)) {
            pumpFailed.advance(ms);
        } else if (simulator.pumpIsOn(pumpNumber)) {
            pumpDefault.advance(ms);
            pumpOver.advance(ms);
        }
    }
}