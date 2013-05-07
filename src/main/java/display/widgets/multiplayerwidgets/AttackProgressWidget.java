/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.widgets.multiplayerwidgets;

import display.Asset;
import display.DrawableFactory;
import display.drawable.Drawable;
import display.drawable.DrawableImage;
import display.drawable.RangeDrawable;
import eel.seprphase4.MultiplayerSimulator;
import java.awt.Graphics;

/**
 *
 * @author drm511
 */
public abstract class AttackProgressWidget extends MultiplayerDisplayWidget {

    private final RangeDrawable progressMeter;
    
    public AttackProgressWidget(MultiplayerSimulator simulator, int x, int y) {
        super(simulator, x, y);      
        this.progressMeter = DrawableFactory.createRange(Asset.Exclamation, 0, 1);
    }
    
    @Override
    public void paint(Graphics g) {
        if (progress() > 0) {
            progressMeter.setLevel(progress());
            progressMeter.draw(g, x, y);
        }
    }

    @Override
    public void advance(int ms) {
        // ignore
    }

    protected abstract double progress();
}
