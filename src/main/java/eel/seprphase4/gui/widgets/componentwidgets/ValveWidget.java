package eel.seprphase4.gui.widgets.componentwidgets;

import eel.seprphase4.gui.Asset;
import eel.seprphase4.gui.DrawableFactory;
import eel.seprphase4.gui.drawable.Drawable;
import eel.seprphase4.gui.widgets.ClickableWidget;
import eel.seprphase4.Simulator.Simulator;
import java.awt.Graphics;

/**
 *
 * @author James
 */
public class ValveWidget extends ClickableWidget {

    private final Drawable openingDefault;
    private final Drawable openingOver;
    private final Drawable closingDefault;
    private final Drawable closingOver;
    private final int valveNumber;

    public ValveWidget(Simulator simulator, int x, int y, int valveNumber, boolean rotated) {
        super(simulator, x, y, DrawableFactory.create(Asset.ValveOpeningDefault).hitBox(x, y));
        if (rotated) {
            this.closingDefault = DrawableFactory.create(Asset.ValveOpeningDefault);
            this.closingOver = DrawableFactory.create(Asset.ValveOpeningOver);
            this.openingDefault = DrawableFactory.create(Asset.ValveClosingDefault);
            this.openingOver = DrawableFactory.create(Asset.ValveClosingOver);
        } else {
            this.openingDefault = DrawableFactory.create(Asset.ValveOpeningDefault);
            this.openingOver = DrawableFactory.create(Asset.ValveOpeningOver);
            this.closingDefault = DrawableFactory.create(Asset.ValveClosingDefault);
            this.closingOver = DrawableFactory.create(Asset.ValveClosingOver);
        }
        this.valveNumber = valveNumber;
    }

    @Override
    public void paint(Graphics g) {
        if (simulator.valveIsOn(valveNumber)) {
            if (mouseIsOver()) {
                openingOver.draw(g, x, y);
            } else {
                openingDefault.draw(g, x, y);
            }
        } else {
            if (mouseIsOver()) {
                closingOver.draw(g, x, y);
            } else {
                closingDefault.draw(g, x, y);
            }
        }
    }

    @Override
    public void onClicked() {
        simulator.changeValveState(valveNumber, !simulator.valveIsOn(valveNumber));
        openingDefault.reset();
        closingDefault.reset();
        openingOver.reset();
        closingOver.reset();
    }

    @Override
    public void advance(int ms) {
        openingDefault.advance(ms);
        closingDefault.advance(ms);
        openingOver.advance(ms);
        closingOver.advance(ms);
    }
}